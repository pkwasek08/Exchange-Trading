package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.dto.OfferLimitDTO;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.services.OfferSellBuyLimitService;
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

    @RequestMapping("/sell/companie")
    @CrossOrigin(origins = "*")
    public List<OfferLimitDTO> getOffersSellLimitByCompanieId(@RequestParam Integer companieId) {
        return offerSellBuyLimitService.getOffersSellLimitByCompanieId(companieId);
    }

    @RequestMapping("/buy/companie")
    @CrossOrigin(origins = "*")
    public List<OfferLimitDTO> getOffersBuyLimitByCompanieId(@RequestParam Integer companieId) {
        return offerSellBuyLimitService.getOffersBuyLimitByCompanieId(companieId);
    }

    @RequestMapping("/user")
    @CrossOrigin(origins = "*")
    public List<OfferSellBuyLimit> getActiveOffersLimitByUserId(@RequestParam Integer userId) {
        return offerSellBuyLimitService.getAllOffersLimitByUserIdAndActive(userId);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitService.updateOfferSellBuyLimitCRUD(offerSellBuyLimit);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuyLimit(@PathVariable Integer id) {
        offerSellBuyLimitService.deleteOfferSellBuyLimit(id);
    }
}
