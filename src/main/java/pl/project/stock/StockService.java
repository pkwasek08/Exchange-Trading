package pl.project.stock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.company.Company;
import pl.project.companyStatistics.CompanyStatistics;
import pl.project.companyStatistics.CompanyStatisticsService;
import pl.project.offerSellBuyLimit.OfferSellBuyLimitService;
import pl.project.offerSellBuyLimit.OfferSellBuyLimit;
import pl.project.user.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CompanyStatisticsService companyStatisticsService;

    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;

    public List<Stock> getAllStock() {
        List<Stock> users = new ArrayList<>();
        stockRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Stock> getAllStockByUserId(Integer userId) {
        return stockRepository.findAllByUser_Id(userId);
    }

    public List<UserStockDTO> getAllStockByUserIdTableView(Integer userId){
        List<Stock> stockUserList = getAllStockByUserId(userId);
        List<UserStockDTO> userStockViewList = new ArrayList<>();
        stockUserList.stream().forEach(stock -> {
            UserStockDTO userStockDTO = new UserStockDTO(stock.getCompany().getName(), stock.getCompany().getIndustry(), stock.getAmount());
            CompanyStatistics companyStatistics = companyStatisticsService.getCompanyStatisticsByCompanyIdLatest(stock.getCompany().getId());
            userStockDTO.setActualPrice(companyStatistics.getPrice());
            userStockDTO.setTrend(companyStatistics.getTrendValue());
            userStockDTO.setActualPrice(getCurrentPriceStockUser(stock.getCompany().getId(), stock.getAmount()));
            userStockViewList.add(userStockDTO);
        });
        return userStockViewList;
    }

    public Stock getStockByUserIdAndCompanyId(Integer userId, Integer companyId) {
        return stockRepository.findByUser_IdAndCompany_Id(userId, companyId);
    }


    public Stock getStock(Integer id) {
        return stockRepository.findById(id).get();
    }

    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }


    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }


    public void deleteStock(Integer id) {
        stockRepository.deleteById(id);
    }

    public void addStockToUser(User user, Company company, int amount) {
        Stock stock = getStockByUserIdAndCompanyId(user.getId(), company.getId());
        if (stock == null) {
            Stock newStock = new Stock();
            newStock.setId(0);
            newStock.setAmount(amount);
            newStock.setUser(user);
            newStock.setCompany(company);
            addStock(newStock);
        } else {
            stock.setAmount(stock.getAmount() + amount);
            updateStock(stock);
        }
    }

    public void removeStockFromUser(User user, Company company, int amount) {
        Stock stock = getStockByUserIdAndCompanyId(user.getId(), company.getId());
        stock.setAmount(stock.getAmount() - amount);
        if (stock.getAmount() > 0) {
            updateStock(stock);
        } else {
            deleteStock(stock.getId());
        }
    }

    private Float getCurrentPriceStockUser(Integer companyId, int amountStockUser){
        List<OfferSellBuyLimit> offerLimitList = offerSellBuyLimitService.getAllOffersLimitByCompanyAndTypeAndActive(companyId, "Buy");
        Float valueStockUser = 0f;
        int amountStock = amountStockUser;
        for (OfferSellBuyLimit offerLimit : offerLimitList) {
            if (amountStock != 0) {
                if (offerLimit.getAmount() >= amountStockUser) {
                    valueStockUser += amountStock * offerLimit.getPrice();
                    amountStock = 0;
                } else {
                    valueStockUser += offerLimit.getAmount() * offerLimit.getPrice();
                    amountStock -= offerLimit.getAmount();
                }
            }
        }
        return valueStockUser;
    }
}
