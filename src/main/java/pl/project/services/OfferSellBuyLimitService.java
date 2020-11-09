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
import java.util.Optional;

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
    @Autowired
    private StockService stockService;

    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        List<OfferSellBuyLimit> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyLimitCRUDRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByCompanyAndTypeAndActive(Integer companieId, String type) {
        if(type.equals("Sell")){
            return offerSellBuyLimitRepository.findAllSellOfferLimitByCompanieIdAndActive(companieId);
        } else {
            return offerSellBuyLimitRepository.findAllBuyOfferLimitByCompanieIdAndActive(companieId);
        }
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByUserIdAndActive(Integer userId) {
        return offerSellBuyLimitCRUDRepository.findAllByUser_IdAndActive(userId, true);
    }

    public OfferSellBuyLimit getOfferSellBuyLimit(Integer id) {
        OfferSellBuyLimit offerSellBuyLimit = offerSellBuyLimitCRUDRepository.findById(id).get();
        return offerSellBuyLimit;
    }


    public void addOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
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
        OfferSellBuyLimit offerSellBuyLimit = getOfferSellBuyLimit(id);
        offerSellBuyLimitCRUDRepository.deleteById(id);
        if (offerSellBuyLimit.getType().equals("Buy")) {
            float purchaseOffer = offerSellBuyLimit.getPrice() * offerSellBuyLimit.getAmount();
            userService.settleUserMoney(offerSellBuyLimit.getUser().getId(), offerSellBuyLimit.getUser().getCash() + purchaseOffer);
        } else {
            stockService.addStockToUser(offerSellBuyLimit.getUser(), offerSellBuyLimit.getCompanie(), offerSellBuyLimit.getAmount());
        }
    }

    public void executeTranscation(OfferSellBuyLimit offerSellBuyLimit) {
        if (offerSellBuyLimit.getType().equals("Buy")) {
            float purchaseOffer = offerSellBuyLimit.getPrice() * offerSellBuyLimit.getAmount();
            userService.settleUserMoney(offerSellBuyLimit.getUser().getId(), offerSellBuyLimit.getUser().getCash() - purchaseOffer);
        } else {
            stockService.removeStockFromUser(offerSellBuyLimit.getUser(), offerSellBuyLimit.getCompanie(), offerSellBuyLimit.getAmount());
        }
    }

    private int settleAmountStock(Integer sellAmount, Integer buyAmount) {
        if (sellAmount >= buyAmount) {
            return buyAmount;
        } else {
            return sellAmount;
        }
    }
}
