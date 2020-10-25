package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.repositories.OfferSellBuyLimitRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferSellBuyLimitService {
    @Autowired
    private OfferSellBuyLimitRepository offerSellBuyLimitRepository;

    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        List<OfferSellBuyLimit> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyLimitRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public OfferSellBuyLimit getOfferSellBuyLimit(Integer id) {
        OfferSellBuyLimit offerSellBuyLimit = offerSellBuyLimitRepository.findById(id).get();
        return offerSellBuyLimit;
    }

    public void addOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitRepository.save(offerSellBuyLimit);
    }


    public void updateOfferSellBuyLimit(Integer id, OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitRepository.save(offerSellBuyLimit);
    }


    public void deleteOfferSellBuyLimit(Integer id) {
        offerSellBuyLimitRepository.deleteById(id);
    }
}
