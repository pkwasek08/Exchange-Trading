package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Deposit;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Integer> {
}
