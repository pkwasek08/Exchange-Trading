package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Transaction;
import pl.project.repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransaction() {
        List<Transaction> offersSellBuyLimit = new ArrayList<>();
        transactionRepository.findAll().forEach(offersSellBuyLimit::add);
        return offersSellBuyLimit;
    }

    public Transaction getTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id).get();
        return transaction;
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


    public void updateTransaction(Integer id, Transaction transaction) {
        transactionRepository.save(transaction);
    }


    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

}
