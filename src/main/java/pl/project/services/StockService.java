package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Companie;
import pl.project.entities.Stock;
import pl.project.entities.User;
import pl.project.repositoriesCRUD.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStock() {
        List<Stock> users = new ArrayList<>();
        stockRepository.findAll().forEach(users::add);
        return users;
    }

    public List<Stock> getAllStockByUserId(Integer userId) {
        return stockRepository.findAllByUser_Id(userId);
    }

    public Stock getStockByUserIdAndCompanieId(Integer userId, Integer companieId) {
        return stockRepository.findByUser_IdAndCompany_Id(userId, companieId);
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

    public void addStockToUser(User user, Companie company, int amount) {
        Stock stock = getStockByUserIdAndCompanieId(user.getId(), company.getId());
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

    public void removeStockFromUser(User user, Companie company, int amount) {
        Stock stock = getStockByUserIdAndCompanieId(user.getId(), company.getId());
        stock.setAmount(stock.getAmount() - amount);
        if (stock.getAmount() > 0) {
            updateStock(stock);
        } else {
            deleteStock(stock.getId());
        }
    }
}
