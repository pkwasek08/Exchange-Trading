package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Stock;
import pl.project.services.StockService;

import java.util.List;


@RestController
@RequestMapping(value = "/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Stock> getAllStock() {
        return stockService.getAllStock();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Stock getStock(@PathVariable Integer id) {
        return stockService.getStock(id);
    }

    @GetMapping("/user")
    @CrossOrigin(origins = "*")
    public List<Stock> getAllStockByUserId(@RequestParam Integer userId) {
        return stockService.getAllStockByUserId(userId);
    }

    @GetMapping("/user/company")
    @CrossOrigin(origins = "*")
    public Stock getAllStockByUserIdAndCompanyId(@RequestParam Integer userId, @RequestParam Integer companyId) {
        return stockService.getStockByUserIdAndCompanieId(userId, companyId);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addStock(@RequestBody Stock stock) {
        stockService.addStock(stock);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateStock(@RequestBody Stock stock, @PathVariable Integer id) {
        stockService.updateStock(stock);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
    }
}

