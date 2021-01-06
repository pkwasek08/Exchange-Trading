package pl.project.offerSellBuyLimit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offerSellBuyLimit")
public class OfferSellBuyLimitController {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        return offerSellBuyLimitService.getAllOfferSellBuyLimit();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public OfferSellBuyLimit getOfferSellBuyLimit(@PathVariable Integer id) {
        return offerSellBuyLimitService.getOfferSellBuyLimit(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitService.addOfferSellBuyLimit(offerSellBuyLimit);
    }

    @PostMapping("/newOfferLimit")
    @CrossOrigin(origins = "*")
    public void addNewOfferLimit(@RequestBody OfferLimitDTO offerLimitDTO) {
        offerSellBuyLimitService.addOfferSellBuyLimit(offerLimitDTO);
    }

    @RequestMapping("/sell/company")
    @CrossOrigin(origins = "*")
    public List<OfferLimitDTO> getOffersSellLimitByCompanyId(@RequestParam Integer companyId) {
        return offerSellBuyLimitService.getOffersSellLimitListByCompanyId(companyId);
    }

    @RequestMapping("/buy/company")
    @CrossOrigin(origins = "*")
    public List<OfferLimitDTO> getOffersBuyLimitByCompanyId(@RequestParam Integer companyId) {
        return offerSellBuyLimitService.getOffersBuyLimitListByCompanyId(companyId);
    }

    @RequestMapping("/sell/company/first/{companyId}/user/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ExecDetailsOfferLimit> getFirstOffersSellByCompanyId(@PathVariable Integer companyId, @PathVariable Integer userId) {
        return ResponseEntity.ok(offerSellBuyLimitService.getFirstOfferSellLimitByCompanyId(companyId, userId));
    }

    @RequestMapping("/buy/company/first/{companyId}/user/{userId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ExecDetailsOfferLimit> getFirstOffersBuyByCompanyId(@PathVariable Integer companyId, @PathVariable Integer userId) {
        return ResponseEntity.ok(offerSellBuyLimitService.getFirstOfferBuyLimitByCompanyId(companyId, userId));
    }

    @RequestMapping("/user")
    @CrossOrigin(origins = "*")
    public List<OfferSellBuyLimit> getActiveOffersLimitByUserId(@RequestParam Integer userId) {
        return offerSellBuyLimitService.getAllOffersLimitByUserIdAndActive(userId);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitService.updateOfferSellBuyLimit(offerSellBuyLimit);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuyLimit(@PathVariable Integer id) {
        offerSellBuyLimitService.deleteOfferSellBuyLimit(id);
    }
}
