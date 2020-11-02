package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.OfferSellBuy;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.repositories.OfferSellBuyRepository;
import pl.project.repositories.UserRepository;
import pl.project.repositoriesCRUD.OfferSellBuyCRUDRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OfferSellBuyService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private OfferSellBuyCRUDRepository offerSellBuyCRUDRepository;
    @Autowired
    private OfferSellBuyRepository offerSellBuyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanieStatisticsService companieStatisticsService;
    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;

    public List<OfferSellBuy> getAllOfferSellBuy() {
        List<OfferSellBuy> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyCRUDRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public List<OfferSellBuy> getAllOffersByUserId(Integer userId) {
        return offerSellBuyCRUDRepository.findAllByUserId(userId);
    }

    public OfferSellBuy getOfferSellBuy(Integer id) {
        OfferSellBuy offerSellBuy = offerSellBuyCRUDRepository.findById(id).get();
        return offerSellBuy;
    }

    public void addOfferSellBuy(OfferSellBuy offerSellBuy) {
        try {
            offerSellBuyRepository.addOfferSellBuy(offerSellBuy);
            executeTranscation(offerSellBuy);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void addOffer(OfferSellBuy offerSellBuy) {
        try {
            offerSellBuyRepository.addOfferSellBuy(offerSellBuy);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    public void updateOfferSellBuy(Integer id, OfferSellBuy offerSellBuy) {
        offerSellBuyCRUDRepository.save(offerSellBuy);
    }


    public void deleteOfferSellBuy(Integer id) {
        offerSellBuyCRUDRepository.deleteById(id);
    }

    public void executeTranscation(OfferSellBuy offerSellBuy) {
        String typeList = offerSellBuy.getType().equals("Sell") ? "Buy" : "Sell";
        List<OfferSellBuyLimit> offerLimitList = offerSellBuyLimitService.getAllOffersLimitByTypeAndActive(offerSellBuy.getCompanie().getId(), typeList);

        offerLimitList.stream().forEach(offerLimit -> {
            if (offerLimit.getPrice().compareTo(offerSellBuy.getPrice()) == 0) {
                int amountStock = settleAmountStock(offerLimit.getAmount(), offerSellBuy.getAmount());
                OfferSellBuy executedSellOffer = new OfferSellBuy();
                executedSellOffer.setId(null);
                executedSellOffer.setAmount(amountStock);
                executedSellOffer.setPrice(offerLimit.getPrice());
                executedSellOffer.setType(typeList);
                executedSellOffer.setDate(new Date());
                executedSellOffer.setCompanie(offerLimit.getCompanie());
                executedSellOffer.setUser(offerLimit.getUser());
                executedSellOffer.setActive(false);

                OfferSellBuy executedBuyOffer = new OfferSellBuy();
                executedBuyOffer.setId(null);
                executedBuyOffer.setAmount(amountStock);
                executedBuyOffer.setPrice(offerSellBuy.getPrice());
                executedBuyOffer.setType(offerSellBuy.getType());
                executedBuyOffer.setDate(new Date());
                executedBuyOffer.setCompanie(offerSellBuy.getCompanie());
                executedBuyOffer.setUser(offerSellBuy.getUser());
                executedBuyOffer.setActive(false);

                if (typeList.equals("Sell")) {
                    offerSellBuy.getUser().setCash(offerSellBuy.getUser().getCash() + offerLimit.getPrice() * amountStock);
                    offerLimit.getUser().setCash(offerLimit.getUser().getCash() - offerLimit.getPrice() * amountStock);
                } else {
                    offerSellBuy.getUser().setCash(offerSellBuy.getUser().getCash() - offerLimit.getPrice() * amountStock);
                    offerLimit.getUser().setCash(offerLimit.getUser().getCash() + offerLimit.getPrice() * amountStock);
                }
                userService.settleUserMoney(offerSellBuy.getUser().getId(), offerSellBuy.getUser().getCash());
                userService.settleUserMoney(offerLimit.getUser().getId(), offerLimit.getUser().getCash());

                addOffer(executedBuyOffer);
                addOffer(executedSellOffer);

                offerLimit.setAmount(offerLimit.getAmount() - amountStock);
                if(offerLimit.getAmount() == 0){
                    offerLimit.setActive(false);
                }
                offerSellBuyLimitService.updateOfferSellBuyLimitCRUD(offerLimit);
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
