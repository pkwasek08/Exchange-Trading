package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entieties.Transaction;
import pl.project.services.TransactionService;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/transaction")
    @CrossOrigin(origins = "*")
    public List<Transaction> getTransaction() {
        return transactionService.getAllTransaction();
    }

    @RequestMapping("/transaction/{id}")
    @CrossOrigin(origins = "*")
    public Transaction getTransaction(@PathVariable Integer id) {
        return transactionService.getTransaction(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transaction")
    @CrossOrigin(origins = "*")
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transaction/{id}")
    @CrossOrigin(origins = "*")
    public void updateTransaction(@RequestBody Transaction transaction, @PathVariable Integer id) {
        transactionService.updateTransaction(id, transaction);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/transaction/{id}")
    @CrossOrigin(origins = "*")
    public void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
    }
}
