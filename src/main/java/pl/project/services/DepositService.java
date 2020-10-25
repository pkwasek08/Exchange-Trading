package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Deposit;
import pl.project.repositories.DepositRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    private DepositRepository depositRepository;

    public List<Deposit> getAllDeposit() {
        List<Deposit> deposits = new ArrayList<>();
        depositRepository.findAll().forEach(deposits::add);
        return deposits;
    }

    public Deposit getDeposit(Integer id) {
        Deposit deposit = depositRepository.findById(id).get();
        return deposit;
    }

    public void addDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }


    public void updateDeposit(Integer id, Deposit deposit) {
        depositRepository.save(deposit);
    }


    public void deleteDeposit(Integer id) {
        depositRepository.deleteById(id);
    }
}
