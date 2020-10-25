package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.services.OfferSellBuyLimitService;

import java.util.List;

@RestController
public class OfferSellBuyLimitController {
    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;

    @GetMapping("/offerSellBuyLimit")
    @CrossOrigin(origins = "*")
    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        return offerSellBuyLimitService.getAllOfferSellBuyLimit();
    }

    @GetMapping("/offerSellBuyLimit/{id}")
    @CrossOrigin(origins = "*")
    public OfferSellBuyLimit getOfferSellBuyLimit(@PathVariable Integer id) {
        return offerSellBuyLimitService.getOfferSellBuyLimit(id);
    }

    @PostMapping(value = "/offerSellBuyLimit")
    @CrossOrigin(origins = "*")
    public void addOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitService.addOfferSellBuyLimit(offerSellBuyLimit);
    }

    @PutMapping(value = "/offerSellBuyLimit/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit, @PathVariable Integer id) {
        offerSellBuyLimitService.updateOfferSellBuyLimit(id, offerSellBuyLimit);
    }

    @DeleteMapping(value = "/offerSellBuyLimit/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuyLimit(@PathVariable Integer id) {
        offerSellBuyLimitService.deleteOfferSellBuyLimit(id);
    }
}
