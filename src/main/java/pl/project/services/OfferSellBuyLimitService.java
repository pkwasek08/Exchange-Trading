package pl.project.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.repositories.OfferSellBuyLimitRepository;
import pl.project.repositoriesCRUD.OfferSellBuyLimitCRUDRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferSellBuyLimitService {
    @Autowired
    private OfferSellBuyLimitCRUDRepository offerSellBuyLimitCRUDRepository;
    @Autowired
    private OfferSellBuyLimitRepository offerSellBuyLimitRepository;

    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        List<OfferSellBuyLimit> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyLimitCRUDRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public OfferSellBuyLimit getOfferSellBuyLimit(Integer id) {
        OfferSellBuyLimit offerSellBuyLimit = offerSellBuyLimitCRUDRepository.findById(id).get();
        return offerSellBuyLimit;
    }

    public void addOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitRepository.addOfferSellBuy(offerSellBuyLimit);
    }


    public void updateOfferSellBuyLimit(Integer id, OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitCRUDRepository.save(offerSellBuyLimit);
    }


    public void deleteOfferSellBuyLimit(Integer id) {
        offerSellBuyLimitCRUDRepository.deleteById(id);
    }
}
