package pl.project.offerSellBuy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.project.execDetails.ExecDetails;

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

    @GetMapping("/user/paginator")
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
    public ResponseEntity<ExecDetails> addOfferSellBuy(@RequestBody OfferSellBuy offerSellBuy) {
        return ResponseEntity.ok(offerSellBuyService.addOfferSellBuy(offerSellBuy));
    }

    @PostMapping("/newOffer")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ExecDetails> addNewOffer(@RequestBody OfferSellBuyDTO offerSellBuyDTO) {
        return ResponseEntity.ok(offerSellBuyService.addOfferSellBuy(offerSellBuyDTO));
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
