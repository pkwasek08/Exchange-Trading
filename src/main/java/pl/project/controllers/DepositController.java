package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Deposit;
import pl.project.services.DepositService;
import java.util.List;

@RestController
@RequestMapping(value = "/deposit")
public class DepositController {
    @Autowired
    private DepositService depositService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Deposit> getAllDeposit() {
        return depositService.getAllDeposit();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Deposit getDeposit(@PathVariable Integer id) {
        return depositService.getDeposit(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addDeposit(@RequestBody Deposit deposit) {
        depositService.addDeposit(deposit);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public void updateDeposit(@RequestBody Deposit deposit, @PathVariable Integer id) {
        depositService.updateDeposit(id, deposit);
    }

    @RequestMapping("/user")
    @CrossOrigin(origins = "*")
    public Deposit getCompanieStatisticsByCompanieIdLatest(@RequestParam Integer userId) {
        return depositService.getCashByUserId(userId);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteDeposit(@PathVariable Integer id) {
        depositService.deleteDeposit(id);
    }
}
