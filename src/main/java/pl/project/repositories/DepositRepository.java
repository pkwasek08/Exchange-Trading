package pl.project.repositories;

import org.springframework.stereotype.Repository;
import pl.project.entities.Deposit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.nonNull;

@Repository
public class DepositRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Deposit getCashByUserId(Integer userId) {
        nonNull(userId);
        return entityManager.createQuery("SELECT d FROM Deposit d WHERE d.userByUserId.id = :userId",
                Deposit.class).setParameter("userId",userId).setMaxResults(1).getSingleResult();
    }
}
