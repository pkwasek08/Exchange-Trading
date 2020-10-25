package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Deposit;
import pl.project.services.DepositService;

import java.util.List;

@RestController
public class DepositController {
    @Autowired
    private DepositService depositService;

    @GetMapping("/deposit")
    @CrossOrigin(origins = "*")
    public List<Deposit> getAllDeposit() {
        return depositService.getAllDeposit();
    }

    @GetMapping("/deposit/{id}")
    @CrossOrigin(origins = "*")
    public Deposit getDeposit(@PathVariable Integer id) {
        return depositService.getDeposit(id);
    }

    @PostMapping(value = "/deposit")
    @CrossOrigin(origins = "*")
    public void addDeposit(@RequestBody Deposit deposit) {
        depositService.addDeposit(deposit);
    }

    @PutMapping(value = "/deposit/{id}")
    @CrossOrigin(origins = "*")
    public void updateDeposit(@RequestBody Deposit deposit, @PathVariable Integer id) {
        depositService.updateDeposit(id, deposit);
    }

    @DeleteMapping(value = "/deposit/{id}")
    @CrossOrigin(origins = "*")
    public void deleteDeposit(@PathVariable Integer id) {
        depositService.deleteDeposit(id);
    }
}
