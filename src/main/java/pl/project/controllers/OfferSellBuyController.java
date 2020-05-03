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

    @RequestMapping("/offerSellBuy")
    @CrossOrigin(origins = "*")
    public List<OfferSellBuy> getAllOfferSellBuy() {
        return offerSellBuyService.getAllOfferSellBuy();
    }

    @RequestMapping("/offerSellBuy/{id}")
    @CrossOrigin(origins = "*")
    public OfferSellBuy getOfferSellBuy(@PathVariable Integer id) {
        return offerSellBuyService.getOfferSellBuy(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/offerSellBuy")
    @CrossOrigin(origins = "*")
    public void addOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy) {
        offerSellBuyService.addOfferSellBuy(offerSellBuy);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/offerSellBuy/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy, @PathVariable Integer id) {
        offerSellBuyService.updateOfferSellBuy(id, offerSellBuy);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/offerSellBuy/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuy(@PathVariable Integer id) {
        offerSellBuyService.deleteOfferSellBuy(id);
    }
}
