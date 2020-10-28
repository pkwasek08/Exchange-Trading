package pl.project.repositories;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void settleMoneyUser(Integer userId, Float cash) {
        entityManager.createQuery("UPDATE User SET cash = :cash WHERE id = :id")
                .setParameter("cash", cash)
                .setParameter("id", userId)
                .executeUpdate();
    }
}
