package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entieties.Deposit;
import pl.project.services.DepositService;

import java.util.List;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @RequestMapping("/deposit")
    @CrossOrigin(origins = "*")
    public List<Deposit> getAllDeposit() {
        return depositService.getAllDeposit();
    }

    @RequestMapping("/deposit/{id}")
    @CrossOrigin(origins = "*")
    public Deposit getDeposit(@PathVariable Integer id) {
        return depositService.getDeposit(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deposit")
    @CrossOrigin(origins = "*")
    public void addDeposit(@RequestBody Deposit deposit) {
        depositService.addDeposit(deposit);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deposit/{id}")
    @CrossOrigin(origins = "*")
    public void updateDeposit(@RequestBody Deposit deposit, @PathVariable Integer id) {
        depositService.updateDeposit(id, deposit);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deposit/{id}")
    @CrossOrigin(origins = "*")
    public void deleteDeposit(@PathVariable Integer id) {
        depositService.deleteDeposit(id);
    }
}
