package pl.project.repositories;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OfferSellBuyRepository {
    @PersistenceContext
    private EntityManager entityManager;

}
