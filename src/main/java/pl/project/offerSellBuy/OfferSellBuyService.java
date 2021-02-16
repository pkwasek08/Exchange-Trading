package pl.project.offerSellBuy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.project.company.CompanyService;
import pl.project.companyStatistics.CompanyStatisticsService;
import pl.project.execDetails.ExecDetails;
import pl.project.execDetails.ExecDetailsHelper;
import pl.project.offerSellBuyLimit.OfferSellBuyLimit;
import pl.project.offerSellBuyLimit.OfferSellBuyLimitService;
import pl.project.stock.StockService;
import pl.project.user.UserService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OfferSellBuyService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private OfferSellBuyRepository offerSellBuyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyStatisticsService companyStatisticsService;
    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;
    @Autowired
    private StockService stockService;
    @Autowired
    private CompanyService companyService;

    public List<OfferSellBuy> getAllOfferSellBuy() {
        List<OfferSellBuy> offerSellBuyLimit = new ArrayList<>();
        offerSellBuyRepository.findAll().forEach(offerSellBuyLimit::add);
        return offerSellBuyLimit;
    }

    public Page<OfferSellBuy> getAllOffersByUserId(Integer userId, int page, int size) {
        return offerSellBuyRepository.findAllByUserId(userId, PageRequest.of(page, size));
    }

    public OfferSellBuy getOfferSellBuy(Integer id) {
        OfferSellBuy offerSellBuy = offerSellBuyRepository.findById(id).get();
        return offerSellBuy;
    }

    public ExecDetails addOfferSellBuy(OfferSellBuy offerSellBuy) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        try {
            executeTranscation(offerSellBuy);
        } catch (Exception e) {
            log.error(e.getCause());
        } finally {
            execHelper.addNewDbTime();
            return new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime());
        }
    }

    public ExecDetails addOfferSellBuy(OfferSellBuyDTO offerSellBuyDTO) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        OfferSellBuy offerSellBuy = new OfferSellBuy(0l, offerSellBuyDTO.getAmount(), offerSellBuyDTO.getPrice(),
                offerSellBuyDTO.getType(), offerSellBuyDTO.getDate(), companyService.getCompany(offerSellBuyDTO.getCompanyId()),
                userService.getUser(offerSellBuyDTO.getUserId()), true);
        try {
            executeTranscation(offerSellBuy);
        } catch (Exception e) {
            log.error(new Date().getTime() + " " + e.getStackTrace());
        } finally {
            return new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime());
        }
    }


    public void addOffer(OfferSellBuy offerSellBuy) {
        try {
            offerSellBuyRepository.save(offerSellBuy);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    public void updateOfferSellBuy(Integer id, OfferSellBuy offerSellBuy) {
        offerSellBuyRepository.save(offerSellBuy);
    }


    public void deleteOfferSellBuy(Integer id) {
        offerSellBuyRepository.deleteById(id);
    }

    private void executeSellTransaction(OfferSellBuy offerSellBuy) {
        List<OfferSellBuyLimit> offerLimitList = offerSellBuyLimitService.
                getAllOffersLimitByCompanyAndTypeAndActive(offerSellBuy.getCompany().getId(),
                        "Buy");

        int valueStockUser = 0;
        int amountStock = offerSellBuy.getAmount();
        for (OfferSellBuyLimit offerLimit : offerLimitList) {
            int amount;
            if (amountStock != 0) {
                if (offerLimit.getAmount() >= amountStock) {
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
                executedSellOffer.setDate(offerSellBuy.getDate());
                executedSellOffer.setCompany(offerSellBuy.getCompany());
                executedSellOffer.setUser(offerSellBuy.getUser());
                executedSellOffer.setActive(false);

                OfferSellBuy executedBuyOffer = new OfferSellBuy();
                executedBuyOffer.setId(null);
                executedBuyOffer.setAmount(amount);
                executedBuyOffer.setPrice(amount * offerLimit.getPrice());
                executedBuyOffer.setType(offerLimit.getType());
                executedBuyOffer.setDate(offerSellBuy.getDate());
                executedBuyOffer.setCompany(offerLimit.getCompany());
                executedBuyOffer.setUser(offerLimit.getUser());
                executedBuyOffer.setActive(false);

                offerSellBuy.getUser().setCash(offerSellBuy.getUser().getCash() -
                        offerLimit.getPrice() * amount);
                userService.settleUserMoney(offerSellBuy.getUser().getId(),
                        offerSellBuy.getUser().getCash());
                addOffer(executedBuyOffer);
                addOffer(executedSellOffer);
                stockService.removeStockFromUser(executedSellOffer.getUser(),
                        executedSellOffer.getCompany(),
                        executedSellOffer.getAmount());
                stockService.addStockToUser(executedBuyOffer.getUser(),
                        executedBuyOffer.getCompany(),
                        executedBuyOffer.getAmount());

                offerLimit.setAmount(offerLimit.getAmount() - amount);
                if (offerLimit.getAmount() == 0) {
                    offerLimit.setActive(false);
                }
                offerSellBuyLimitService.updateOfferSellBuyLimit(offerLimit);
                companyStatisticsService.
                        updateDailyCompanyStatistic(executedBuyOffer.getCompany().getId(),
                        offerLimit.getPrice(), executedBuyOffer.getAmount(),
                                executedBuyOffer.getDate());
                if (amount == 0) {
                    break;
                }
            } else {
                return;
            }
        }
    }

    private void executeBuyTransaction(OfferSellBuy offerSellBuy) {
        List<OfferSellBuyLimit> offerLimitList = offerSellBuyLimitService.getAllOffersLimitByCompanyAndTypeAndActive(offerSellBuy.getCompany().getId(), "Sell");

        int valueStockUser = 0;
        int amountStock = offerSellBuy.getAmount();
        for (OfferSellBuyLimit offerLimit : offerLimitList) {
            int amount;
            if (amountStock != 0) {
                if (offerLimit.getAmount() >= amountStock) {
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
                executedSellOffer.setDate(offerSellBuy.getDate());
                executedSellOffer.setCompany(offerLimit.getCompany());
                executedSellOffer.setUser(offerLimit.getUser());
                executedSellOffer.setActive(false);

                OfferSellBuy executedBuyOffer = new OfferSellBuy();
                executedBuyOffer.setId(null);
                executedBuyOffer.setAmount(amount);
                executedBuyOffer.setPrice(amount * offerLimit.getPrice());
                executedBuyOffer.setType(offerSellBuy.getType());
                executedBuyOffer.setDate(offerSellBuy.getDate());
                executedBuyOffer.setCompany(offerSellBuy.getCompany());
                executedBuyOffer.setUser(offerSellBuy.getUser());
                executedBuyOffer.setActive(false);

                offerSellBuy.getUser().setCash(offerSellBuy.getUser().getCash() - offerLimit.getPrice() * amount);
                userService.settleUserMoney(offerSellBuy.getUser().getId(), offerSellBuy.getUser().getCash());
                addOffer(executedBuyOffer);
                stockService.addStockToUser(executedBuyOffer.getUser(), executedBuyOffer.getCompany(), executedBuyOffer.getAmount());

                if (offerLimit.getUser() != null) {
                    offerLimit.getUser().setCash(offerLimit.getUser().getCash() + offerLimit.getPrice() * amount);
                    userService.settleUserMoney(offerLimit.getUser().getId(), offerLimit.getUser().getCash());
                    addOffer(executedSellOffer);
                }

                offerLimit.setAmount(offerLimit.getAmount() - amount);
                if (offerLimit.getAmount() == 0) {
                    offerLimit.setActive(false);
                }
                offerSellBuyLimitService.updateOfferSellBuyLimit(offerLimit);
                companyStatisticsService.updateDailyCompanyStatistic(executedBuyOffer.getCompany().getId(), offerLimit.getPrice(), executedBuyOffer.getAmount(), executedBuyOffer.getDate());
                if (amount == 0) {
                    break;
                }
            } else {
                return;
            }
        }
    }

    public void executeTranscation(OfferSellBuy offerSellBuy) {
        if (offerSellBuy.getType().equals("Sell")) {
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
