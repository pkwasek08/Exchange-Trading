package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.dto.OfferLimitDTO;
import pl.project.entities.OfferSellBuy;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.repositories.OfferSellBuyLimitRepository;
import pl.project.repositoriesCRUD.OfferSellBuyLimitCRUDRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OfferSellBuyLimitService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private OfferSellBuyLimitCRUDRepository offerSellBuyLimitCRUDRepository;
    @Autowired
    private OfferSellBuyLimitRepository offerSellBuyLimitRepository;
    @Autowired
    private OfferSellBuyService offerSellBuyService;
    @Autowired
    private CompanieStatisticsService companieStatisticsService;
    @Autowired
    private UserService userService;

    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        List<OfferSellBuyLimit> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyLimitCRUDRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByTypeAndActive(Integer companieId, String type) {
        return offerSellBuyLimitRepository.findAllOfferLimitByCompanieIdAndActive(companieId, type);
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByUserIdAndActive(Integer userId) {
        return offerSellBuyLimitCRUDRepository.findAllByUser_IdAndActive(userId, true);
    }

    public OfferSellBuyLimit getOfferSellBuyLimit(Integer id) {
        OfferSellBuyLimit offerSellBuyLimit = offerSellBuyLimitCRUDRepository.findById(id).get();
        return offerSellBuyLimit;
    }


    public void addOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimit.setId(0);
        offerSellBuyLimit = offerSellBuyLimitCRUDRepository.save(offerSellBuyLimit);
        executeTranscation(offerSellBuyLimit);
    }

    public List<OfferLimitDTO> getOffersBuyLimitByCompanieId(Integer companieId) {
        return offerSellBuyLimitRepository.findAllOfferBuyLimitByCompanieId(companieId);
    }

    public List<OfferLimitDTO> getOffersSellLimitByCompanieId(Integer companieId) {
        return offerSellBuyLimitRepository.findAllOfferSellLimitByCompanieId(companieId);
    }


    public void updateOfferSellBuyLimitCRUD(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitCRUDRepository.save(offerSellBuyLimit);
    }

    public void updateOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitRepository.updateOfferSellBuy(offerSellBuyLimit);
    }


    public void deleteOfferSellBuyLimit(Integer id) {
        offerSellBuyLimitCRUDRepository.deleteById(id);
    }

    public void executeTranscation(OfferSellBuyLimit offerSellBuyLimit) {
        String typeList = offerSellBuyLimit.getType().equals("Sell") ? "Buy" : "Sell";
        List<OfferSellBuyLimit> offerLimitList = getAllOffersLimitByTypeAndActive(offerSellBuyLimit.getCompanie().getId(), typeList);

        offerLimitList.stream().forEach(offerLimit -> {
            if (offerLimit.getPrice().compareTo(offerSellBuyLimit.getPrice()) == 0 && offerSellBuyLimit.getAmount() != 0) {
                int amountStock = settleAmountStock(offerLimit.getAmount(), offerSellBuyLimit.getAmount());
                OfferSellBuy executedSellOffer = new OfferSellBuy();
                executedSellOffer.setId(null);
                executedSellOffer.setAmount(amountStock);
                executedSellOffer.setPrice(offerLimit.getPrice());
                executedSellOffer.setType(offerLimit.getType());
                executedSellOffer.setDate(new Date());
                executedSellOffer.setCompanie(offerLimit.getCompanie());
                executedSellOffer.setUser(offerLimit.getUser());
                executedSellOffer.setActive(false);

                OfferSellBuy executedBuyOffer = new OfferSellBuy();
                executedBuyOffer.setId(null);
                executedBuyOffer.setAmount(amountStock);
                executedBuyOffer.setPrice(offerSellBuyLimit.getPrice());
                executedBuyOffer.setType(offerSellBuyLimit.getType());
                executedBuyOffer.setDate(new Date());
                executedBuyOffer.setCompanie(offerSellBuyLimit.getCompanie());
                executedBuyOffer.setUser(offerSellBuyLimit.getUser());
                executedBuyOffer.setActive(false);
                offerSellBuyService.addOffer(executedBuyOffer);
                offerSellBuyService.addOffer(executedSellOffer);

                if (typeList.equals("Sell")) {
                    offerSellBuyLimit.getUser().setCash(offerSellBuyLimit.getUser().getCash() + offerLimit.getPrice() * amountStock);
                    offerLimit.getUser().setCash(offerLimit.getUser().getCash() - offerLimit.getPrice() * amountStock);
                } else {
                    offerSellBuyLimit.getUser().setCash(offerSellBuyLimit.getUser().getCash() - offerLimit.getPrice() * amountStock);
                    offerLimit.getUser().setCash(offerLimit.getUser().getCash() + offerLimit.getPrice() * amountStock);
                }
                userService.settleUserMoney(offerSellBuyLimit.getUser().getId(), offerSellBuyLimit.getUser().getCash());
                userService.settleUserMoney(offerLimit.getUser().getId(), offerLimit.getUser().getCash());

                offerLimit.setAmount(offerLimit.getAmount() - amountStock);
                offerSellBuyLimit.setAmount(offerSellBuyLimit.getAmount() - amountStock);
                if (offerLimit.getAmount() == 0) {
                    offerLimit.setActive(false);
                }
                if (offerSellBuyLimit.getAmount() == 0) {
                    offerSellBuyLimit.setActive(false);
                }
                updateOfferSellBuyLimitCRUD(offerLimit);
                updateOfferSellBuyLimitCRUD(offerSellBuyLimit);
                companieStatisticsService.updateDailyCompanieStatistic(executedBuyOffer);
            }
        });

    }

    private int settleAmountStock(Integer sellAmount, Integer buyAmount) {
        if (sellAmount >= buyAmount) {
            return buyAmount;
        } else {
            return sellAmount;
        }
    }
}
