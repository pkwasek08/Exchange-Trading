package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Deposit;
import pl.project.repositories.DepositRepository;
import pl.project.repositoriesCRUD.DepositCRUDRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    private DepositCRUDRepository depositCRUDRepository;
    @Autowired
    private DepositRepository depositRepository;

    public List<Deposit> getAllDeposit() {
        List<Deposit> deposits = new ArrayList<>();
        depositCRUDRepository.findAll().forEach(deposits::add);
        return deposits;
    }

    public Deposit getDeposit(Integer id) {
        Deposit deposit = depositCRUDRepository.findById(id).get();
        return deposit;
    }

    public void addDeposit(Deposit deposit) {
        depositCRUDRepository.save(deposit);
    }

    public Deposit getCashByUserId(Integer userId){
        return depositRepository.getCashByUserId(userId);
    }

    public void updateDeposit(Integer id, Deposit deposit) {
        depositCRUDRepository.save(deposit);
    }


    public void deleteDeposit(Integer id) {
        depositCRUDRepository.deleteById(id);
    }
}
