package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.OfferSellBuy;
import pl.project.repositories.OfferSellBuyRepository;
import pl.project.repositories.UserRepository;
import pl.project.repositoriesCRUD.OfferSellBuyCRUDRepository;

import java.util.ArrayList;
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

    public List<OfferSellBuy> getAllOfferSellBuy() {
        List<OfferSellBuy> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyCRUDRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public OfferSellBuy getOfferSellBuy(Integer id) {
        OfferSellBuy offerSellBuy = offerSellBuyCRUDRepository.findById(id).get();
        return offerSellBuy;
    }

    public void addOfferSellBuy(OfferSellBuy offerSellBuy) {
        try {
            offerSellBuyRepository.addOfferSellBuy(offerSellBuy);
            settleUserMoney(offerSellBuy.getUser().getId(), offerSellBuy.getUser().getCash(), offerSellBuy.getPrice(), offerSellBuy.getType());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    private void settleUserMoney(Integer userId, Float moneyUser, Float stockPrice, String typeTransaction) {
        if (typeTransaction.equals("Buy")) {
            userRepository.settleMoneyUser(userId, moneyUser - stockPrice);
        } else {
            userRepository.settleMoneyUser(userId, moneyUser + stockPrice);
        }
    }

    public void updateOfferSellBuy(Integer id, OfferSellBuy offerSellBuy) {
        offerSellBuyCRUDRepository.save(offerSellBuy);
    }


    public void deleteOfferSellBuy(Integer id) {
        offerSellBuyCRUDRepository.deleteById(id);
    }

}
