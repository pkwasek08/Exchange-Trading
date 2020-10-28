package pl.project.repositories;

import org.springframework.stereotype.Repository;
import pl.project.entities.Deposit;
import pl.project.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /*public void addUser(User user) {
        entityManager.createQuery("SELECT d FROM Deposit d WHERE d.userByUserId.id = :userId").setParameter("userId",userId).setMaxResults(1).getSingleResult();
    }*/
}
