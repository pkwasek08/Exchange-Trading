package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entieties.OfferSellBuy;
import pl.project.services.OfferSellBuyService;

import java.util.List;

@RestController
public class OfferSellBuyController {
    @Autowired
    private OfferSellBuyService offerSellBuyService;

    @GetMapping("/offerSellBuy")
    @CrossOrigin(origins = "*")
    public List<OfferSellBuy> getAllOfferSellBuy() {
        return offerSellBuyService.getAllOfferSellBuy();
    }

    @GetMapping("/offerSellBuy/{id}")
    @CrossOrigin(origins = "*")
    public OfferSellBuy getOfferSellBuy(@PathVariable Integer id) {
        return offerSellBuyService.getOfferSellBuy(id);
    }

    @PostMapping(value = "/offerSellBuy")
    @CrossOrigin(origins = "*")
    public void addOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy) {
        offerSellBuyService.addOfferSellBuy(offerSellBuy);
    }

    @PutMapping(value = "/offerSellBuy/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy, @PathVariable Integer id) {
        offerSellBuyService.updateOfferSellBuy(id, offerSellBuy);
    }

    @DeleteMapping(value = "/offerSellBuy/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuy(@PathVariable Integer id) {
        offerSellBuyService.deleteOfferSellBuy(id);
    }
}
