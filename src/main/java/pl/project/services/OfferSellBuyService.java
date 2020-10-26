package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.OfferSellBuy;
import pl.project.repositoriesCRUD.OfferSellBuyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferSellBuyService {
    @Autowired
    private OfferSellBuyRepository offerSellBuyRepository;

    public List<OfferSellBuy> getAllOfferSellBuy() {
        List<OfferSellBuy> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public OfferSellBuy getOfferSellBuy(Integer id) {
        OfferSellBuy offerSellBuy = offerSellBuyRepository.findById(id).get();
        return offerSellBuy;
    }

    public void addOfferSellBuy(OfferSellBuy offerSellBuy) {
        offerSellBuyRepository.save(offerSellBuy);
    }


    public void updateOfferSellBuy(Integer id, OfferSellBuy offerSellBuy) {
        offerSellBuyRepository.save(offerSellBuy);
    }


    public void deleteOfferSellBuy(Integer id) {
        offerSellBuyRepository.deleteById(id);
    }

}
