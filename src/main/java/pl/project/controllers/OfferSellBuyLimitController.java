package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entieties.OfferSellBuyLimit;
import pl.project.services.OfferSellBuyLimitService;

import java.util.List;

@RestController
public class OfferSellBuyLimitController {
    @Autowired
    private OfferSellBuyLimitService offerSellBuyLimitService;

    @RequestMapping("/offerSellBuyLimit")
    @CrossOrigin(origins = "*")
    public List<OfferSellBuyLimit> getAllOfferSellBuyLimit() {
        return offerSellBuyLimitService.getAllOfferSellBuyLimit();
    }

    @RequestMapping("/offerSellBuyLimit/{id}")
    @CrossOrigin(origins = "*")
    public OfferSellBuyLimit getOfferSellBuyLimit(@PathVariable Integer id) {
        return offerSellBuyLimitService.getOfferSellBuyLimit(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/offerSellBuyLimit")
    @CrossOrigin(origins = "*")
    public void addOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit) {
        offerSellBuyLimitService.addOfferSellBuyLimit(offerSellBuyLimit);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/offerSellBuyLimit/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuyLimit(@RequestBody OfferSellBuyLimit offerSellBuyLimit, @PathVariable Integer id) {
        offerSellBuyLimitService.updateOfferSellBuyLimit(id, offerSellBuyLimit);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/offerSellBuyLimit/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuyLimit(@PathVariable Integer id) {
        offerSellBuyLimitService.deleteOfferSellBuyLimit(id);
    }
}
