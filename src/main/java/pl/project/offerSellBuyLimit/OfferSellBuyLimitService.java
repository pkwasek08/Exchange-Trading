package pl.project.offerSellBuyLimit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.companyStatistics.CompanyStatisticsService;
import pl.project.offerSellBuy.OfferSellBuyService;
import pl.project.stock.StockService;
import pl.project.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferSellBuyLimitService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private OfferSellBuyLimitRepository offerSellBuyLimitRepository;
    @Autowired
    private OfferSellBuyLimitDAO offerSellBuyLimitDAO;
    @Autowired
    private OfferSellBuyService offerSellBuyService;
    @Autowired
    private CompanyStatisticsService companyStatisticsService;
    @Autowired
    private UserService userService;
    @Autowired
    private StockService stockService;

    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        List<OfferSellBuyLimit> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyLimitRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByCompanyAndTypeAndActive(Integer companyId, String type) {
        if(type.equals("Sell")){
            return offerSellBuyLimitDAO.findAllSellOfferLimitByCompanyIdAndActive(companyId);
        } else {
            return offerSellBuyLimitDAO.findAllBuyOfferLimitByCompanyIdAndActive(companyId);
        }
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByUserIdAndActive(Integer userId) {
        return offerSellBuyLimitRepository.findAllByUser_IdAndActive(userId, true);
    }

    public OfferSellBuyLimit getOfferSellBuyLimit(Integer id) {
        OfferSellBuyLimit offerSellBuyLimit = offerSellBuyLimitRepository.findById(id).get();
        return offerSellBuyLimit;
    }


    public void addOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimit = offerSellBuyLimitRepository.save(offerSellBuyLimit);
        executeTranscation(offerSellBuyLimit);
    }

    public List<OfferLimitDTO> getOffersBuyLimitByCompanyId(Integer companyId) {
        return offerSellBuyLimitDAO.findAllOfferBuyLimitByCompanyId(companyId);
    }

    public List<OfferLimitDTO> getOffersSellLimitByCompanyId(Integer companyId) {
        return offerSellBuyLimitDAO.findAllOfferSellLimitByCompanyId(companyId);
    }


    public void updateOfferSellBuyLimitCRUD(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitRepository.save(offerSellBuyLimit);
    }

    public void updateOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitDAO.updateOfferSellBuy(offerSellBuyLimit);
    }


    public void deleteOfferSellBuyLimit(Integer id) {
        OfferSellBuyLimit offerSellBuyLimit = getOfferSellBuyLimit(id);
        offerSellBuyLimitRepository.deleteById(id);
        if (offerSellBuyLimit.getType().equals("Buy")) {
            float purchaseOffer = offerSellBuyLimit.getPrice() * offerSellBuyLimit.getAmount();
            userService.settleUserMoney(offerSellBuyLimit.getUser().getId(), offerSellBuyLimit.getUser().getCash() + purchaseOffer);
        } else {
            stockService.addStockToUser(offerSellBuyLimit.getUser(), offerSellBuyLimit.getCompany(), offerSellBuyLimit.getAmount());
        }
    }

    public void executeTranscation(OfferSellBuyLimit offerSellBuyLimit) {
        if (offerSellBuyLimit.getType().equals("Buy")) {
            float purchaseOffer = offerSellBuyLimit.getPrice() * offerSellBuyLimit.getAmount();
            userService.settleUserMoney(offerSellBuyLimit.getUser().getId(), offerSellBuyLimit.getUser().getCash() - purchaseOffer);
        } else {
            stockService.removeStockFromUser(offerSellBuyLimit.getUser(), offerSellBuyLimit.getCompany(), offerSellBuyLimit.getAmount());
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
