package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entieties.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
