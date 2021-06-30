package pl.project.offerSellBuyLimit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.project.company.CompanyService;
import pl.project.companyStatistics.CompanyStatisticsService;
import pl.project.execDetails.ExecDetails;
import pl.project.execDetails.ExecDetailsHelper;
import pl.project.offerSellBuy.OfferSellBuyService;
import pl.project.stock.StockService;
import pl.project.user.UserService;

import javax.persistence.NoResultException;
import java.time.OffsetDateTime;
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
    @Autowired
    private CompanyService companyService;

    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        List<OfferSellBuyLimit> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyLimitRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public List<OfferSellBuyLimit> getAllOffersLimitByCompanyAndTypeAndActive(Integer companyId, String type) {
        if (type.equals("Sell")) {
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

    public ExecDetails addOfferSellBuyLimitDetails(OfferLimitDTO offerLimitDTO) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        OfferSellBuyLimit offerSellBuyLimit = new OfferSellBuyLimit(offerLimitDTO.getId(), Math.toIntExact(offerLimitDTO.getAmount()),
                offerLimitDTO.getPrice(), offerLimitDTO.getType(), offerLimitDTO.getLimit(), offerLimitDTO.getDate(),
                companyService.getCompany(offerLimitDTO.getCompanyId()), userService.getUser(offerLimitDTO.getUserId()), true);
        execHelper.setStartDbTime(OffsetDateTime.now());
        offerSellBuyLimit = offerSellBuyLimitRepository.save(offerSellBuyLimit);
        execHelper.addNewDbTime();
        ExecDetails executeTranscationDetails = executeTranscationDetails(offerSellBuyLimit);
        execHelper.setDbTime(execHelper.getDbTime() + executeTranscationDetails.getDbTime());
        execHelper.setExecTime(execHelper.getExecTime() + executeTranscationDetails.getExeTime());
        return new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime());
    }

    public void addOfferSellBuyLimit(OfferLimitDTO offerLimitDTO) {
        OfferSellBuyLimit offerSellBuyLimit = new OfferSellBuyLimit(offerLimitDTO.getId(), Math.toIntExact(offerLimitDTO.getAmount()),
                offerLimitDTO.getPrice(), offerLimitDTO.getType(), offerLimitDTO.getLimit(), offerLimitDTO.getDate(),
                companyService.getCompany(offerLimitDTO.getCompanyId()), userService.getUser(offerLimitDTO.getUserId()), true);
        offerSellBuyLimit = offerSellBuyLimitRepository.save(offerSellBuyLimit);
        executeTranscation(offerSellBuyLimit);
    }


    public List<OfferLimitDTO> getOffersBuyLimitListByCompanyId(Integer companyId) {
        return offerSellBuyLimitDAO.findAllOfferBuyLimitByCompanyId(companyId);
    }

    public List<OfferLimitDTO> getOffersSellLimitListByCompanyId(Integer companyId) {
        return offerSellBuyLimitDAO.findAllOfferSellLimitByCompanyId(companyId);
    }


    public ExecDetailsOfferLimit getDetailsOfferBuyLimitByCompanyId(@NonNull Integer companyId, @NonNull Integer userId) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        List<OfferLimitDTO> offerBuyLimitDTOLimit = offerSellBuyLimitDAO.findAllOfferBuyLimitByCompanyIdAndUserId(companyId, userId);
        execHelper.addNewDbTime();
        return new ExecDetailsOfferLimit(new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime()), offerBuyLimitDTOLimit);
    }

    public ExecDetailsOfferLimit getDetailsOfferSellLimitByCompanyId(@NonNull Integer companyId, @NonNull Integer userId) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        List<OfferLimitDTO> offerSellLimitDTOList = offerSellBuyLimitDAO.findAllOfferSellLimitByCompanyIdAndUserId(companyId, userId);
        execHelper.addNewDbTime();
        return new ExecDetailsOfferLimit(new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime()), offerSellLimitDTOList);
    }

    public ExecDetailsOfferLimit getFirstOfferBuyLimitByCompanyId(@NonNull Integer companyId, @NonNull Integer userId) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        OfferLimitDTO offerLimitDTO = offerSellBuyLimitDAO.findOfferBuyLimitByCompanyId(companyId, userId);
        execHelper.addNewDbTime();
        return new ExecDetailsOfferLimit(new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime()), offerLimitDTO);
    }

    public ExecDetailsOfferLimit getFirstOfferSellLimitByCompanyId(@NonNull Integer companyId, @NonNull Integer userId) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        OfferLimitDTO offerLimitDTO = offerSellBuyLimitDAO.findOfferSellLimitByCompanyId(companyId, userId);
        execHelper.addNewDbTime();
        return new ExecDetailsOfferLimit(new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime()), offerLimitDTO);
    }

    public void updateOfferSellBuyLimit(OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitRepository.save(offerSellBuyLimit);
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

    public ExecDetails executeTranscationDetails(OfferSellBuyLimit offerSellBuyLimit) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        if (offerSellBuyLimit.getType().equals("Buy")) {
            float purchaseOffer = offerSellBuyLimit.getPrice() * offerSellBuyLimit.getAmount();
            execHelper.setStartDbTime(OffsetDateTime.now());
            userService.settleUserMoney(offerSellBuyLimit.getUser().getId(), offerSellBuyLimit.getUser().getCash() - purchaseOffer);
            execHelper.addNewDbTime();
        } else {
            ExecDetails removeStockFromUserDetails = stockService.removeStockFromUserDetails(offerSellBuyLimit.getUser(), offerSellBuyLimit.getCompany(), offerSellBuyLimit.getAmount());
            execHelper.setDbTime(execHelper.getDbTime() + removeStockFromUserDetails.getDbTime());
            execHelper.setExecTime(execHelper.getExecTime() + removeStockFromUserDetails.getExeTime());
        }
        return new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime());
    }

    private int settleAmountStock(Integer sellAmount, Integer buyAmount) {
        if (sellAmount >= buyAmount) {
            return buyAmount;
        } else {
            return sellAmount;
        }
    }
}
