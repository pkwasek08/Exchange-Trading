package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.OfferSellBuy;
import pl.project.entities.OfferSellBuyLimit;
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
    private UserService userService;
    @Autowired
    private CompanieStatisticsService companieStatisticsService;
    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;
    @Autowired
    private StockService stockService;

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
            executeTranscation(offerSellBuy);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void addOffer(OfferSellBuy offerSellBuy) {
        try {
            offerSellBuyCRUDRepository.save(offerSellBuy);
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

    private void executeSellTransaction(OfferSellBuy offerSellBuy){
        List<OfferSellBuyLimit> offerLimitList = offerSellBuyLimitService.getAllOffersLimitByCompanyAndTypeAndActive(offerSellBuy.getCompanie().getId(), "Buy");

        int valueStockUser = 0;
        int amountStock = offerSellBuy.getAmount();
        for (OfferSellBuyLimit offerLimit : offerLimitList) {
            int amount;
            if (amountStock != 0) {
                if (offerLimit.getAmount() >= offerSellBuy.getAmount()) {
                    valueStockUser += amountStock * offerLimit.getPrice();
                    amount = amountStock;
                    amountStock = 0;
                } else {
                    valueStockUser += offerLimit.getAmount() * offerLimit.getPrice();
                    amountStock -= offerLimit.getAmount();
                    amount = offerLimit.getAmount();
                }
                OfferSellBuy executedSellOffer = new OfferSellBuy();
                executedSellOffer.setId(null);
                executedSellOffer.setAmount(amount);
                executedSellOffer.setPrice(amount * offerLimit.getPrice());
                executedSellOffer.setType(offerSellBuy.getType());
                executedSellOffer.setDate(new Date());
                executedSellOffer.setCompanie(offerSellBuy.getCompanie());
                executedSellOffer.setUser(offerSellBuy.getUser());
                executedSellOffer.setActive(false);

                OfferSellBuy executedBuyOffer = new OfferSellBuy();
                executedBuyOffer.setId(null);
                executedBuyOffer.setAmount(amount);
                executedBuyOffer.setPrice(amount * offerLimit.getPrice());
                executedBuyOffer.setType(offerLimit.getType());
                executedBuyOffer.setDate(new Date());
                executedBuyOffer.setCompanie(offerLimit.getCompanie());
                executedBuyOffer.setUser(offerLimit.getUser());
                executedBuyOffer.setActive(false);

                offerSellBuy.getUser().setCash(offerSellBuy.getUser().getCash() - offerLimit.getPrice() * amount);
                userService.settleUserMoney(offerSellBuy.getUser().getId(), offerSellBuy.getUser().getCash());
                addOffer(executedBuyOffer);
                addOffer(executedSellOffer);
                stockService.removeStockFromUser(executedSellOffer.getUser(), executedSellOffer.getCompanie(), executedSellOffer.getAmount());
                stockService.addStockToUser(executedBuyOffer.getUser(), executedBuyOffer.getCompanie(), executedBuyOffer.getAmount());

                offerLimit.setAmount(offerLimit.getAmount() - amount);
                if (offerLimit.getAmount() == 0) {
                    offerLimit.setActive(false);
                }
                offerSellBuyLimitService.updateOfferSellBuyLimitCRUD(offerLimit);
                companieStatisticsService.updateDailyCompanieStatistic(executedBuyOffer.getCompanie().getId(), offerLimit.getPrice(), executedBuyOffer.getAmount());
                if(amount == 0){
                    break;
                }
            }
        }
    }

    private void executeBuyTransaction(OfferSellBuy offerSellBuy){
        List<OfferSellBuyLimit> offerLimitList = offerSellBuyLimitService.getAllOffersLimitByCompanyAndTypeAndActive(offerSellBuy.getCompanie().getId(), "Sell");

        int valueStockUser = 0;
        int amountStock = offerSellBuy.getAmount();
        for (OfferSellBuyLimit offerLimit : offerLimitList) {
            int amount;
            if (amountStock != 0) {
                if (offerLimit.getAmount() >= offerSellBuy.getAmount()) {
                    valueStockUser += amountStock * offerLimit.getPrice();
                    amount = amountStock;
                    amountStock = 0;
                } else {
                    valueStockUser += offerLimit.getAmount() * offerLimit.getPrice();
                    amountStock -= offerLimit.getAmount();
                    amount = offerLimit.getAmount();
                }

                OfferSellBuy executedSellOffer = new OfferSellBuy();
                executedSellOffer.setId(null);
                executedSellOffer.setAmount(amount);
                executedSellOffer.setPrice(amount * offerLimit.getPrice());
                executedSellOffer.setType(offerLimit.getType());
                executedSellOffer.setDate(new Date());
                executedSellOffer.setCompanie(offerLimit.getCompanie());
                executedSellOffer.setUser(offerLimit.getUser());
                executedSellOffer.setActive(false);

                OfferSellBuy executedBuyOffer = new OfferSellBuy();
                executedBuyOffer.setId(null);
                executedBuyOffer.setAmount(amount);
                executedBuyOffer.setPrice(amount * offerLimit.getPrice());
                executedBuyOffer.setType(offerSellBuy.getType());
                executedBuyOffer.setDate(new Date());
                executedBuyOffer.setCompanie(offerSellBuy.getCompanie());
                executedBuyOffer.setUser(offerSellBuy.getUser());
                executedBuyOffer.setActive(false);

                offerSellBuy.getUser().setCash(offerSellBuy.getUser().getCash() - offerLimit.getPrice() * amount);
                userService.settleUserMoney(offerSellBuy.getUser().getId(), offerSellBuy.getUser().getCash());
                addOffer(executedBuyOffer);
                stockService.addStockToUser(executedBuyOffer.getUser(), executedBuyOffer.getCompanie(), executedBuyOffer.getAmount());

                if(offerLimit.getUser() != null) {
                    offerLimit.getUser().setCash(offerLimit.getUser().getCash() + offerLimit.getPrice() * amount);
                    userService.settleUserMoney(offerLimit.getUser().getId(), offerLimit.getUser().getCash());
                    addOffer(executedSellOffer);
                }

                offerLimit.setAmount(offerLimit.getAmount() - amount);
                if (offerLimit.getAmount() == 0) {
                    offerLimit.setActive(false);
                }
                offerSellBuyLimitService.updateOfferSellBuyLimitCRUD(offerLimit);
                companieStatisticsService.updateDailyCompanieStatistic(executedBuyOffer.getCompanie().getId(), offerLimit.getPrice(), executedBuyOffer.getAmount());
                if(amount == 0){
                    break;
                }
            }
        }
    }

    public void executeTranscation(OfferSellBuy offerSellBuy) {
        if(offerSellBuy.getType().equals("Sell")){
            executeSellTransaction(offerSellBuy);
        } else {
            executeBuyTransaction(offerSellBuy);
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
