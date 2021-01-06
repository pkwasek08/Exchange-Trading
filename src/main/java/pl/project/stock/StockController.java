package pl.project.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/view/user")
    @CrossOrigin(origins = "*")
    public List<UserStockDTO> getAllStockByUserIdTableView(@RequestParam Integer userId) {
        return stockService.getAllStockByUserIdTableView(userId);
    }

    @GetMapping("/company/{companyId}/user/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ExecDetailsUserStock> getStockByUserIdAndCompanyId(@PathVariable Integer userId, @PathVariable Integer companyId) {
        return ResponseEntity.ok(stockService.getStockExecDetailsByUserAndCompanyId(userId, companyId));
    }

    @GetMapping("/user/company")
    @CrossOrigin(origins = "*")
    public Stock getAllStockByUserIdAndCompanyId(@RequestParam Integer userId, @RequestParam Integer companyId) {
        return stockService.getStockByUserIdAndCompanyId(userId, companyId);
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

