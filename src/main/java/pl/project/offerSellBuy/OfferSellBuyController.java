package pl.project.offerSellBuy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/offerSellBuy")
public class OfferSellBuyController {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private OfferSellBuyService offerSellBuyService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<OfferSellBuy> getAllOfferSellBuy() {
        return offerSellBuyService.getAllOfferSellBuy();
    }

    @RequestMapping("/user/paginator")
    @CrossOrigin(origins = "*")
    public Page<OfferSellBuy> getAllOfferByUserId(@RequestParam Integer userId, @RequestParam int page, @RequestParam int size) {
        return offerSellBuyService.getAllOffersByUserId(userId, page, size);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public OfferSellBuy getOfferSellBuy(@PathVariable Integer id) {
        return offerSellBuyService.getOfferSellBuy(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy) {
        offerSellBuyService.addOfferSellBuy(offerSellBuy);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy, @PathVariable Integer id) {
        offerSellBuyService.updateOfferSellBuy(id, offerSellBuy);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteOfferSellBuy(@PathVariable Integer id) {
        offerSellBuyService.deleteOfferSellBuy(id);
    }
}
