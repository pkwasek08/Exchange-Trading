package pl.project.repositories;

import org.springframework.stereotype.Repository;
import pl.project.entities.OfferSellBuy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class OfferSellBuyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addOfferSellBuy(OfferSellBuy offerSellBuy) {
        entityManager.createNativeQuery("INSERT INTO offers_sell_buy(amount, price, type, date, company_id, user_id, active) VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, offerSellBuy.getAmount())
                .setParameter(2, offerSellBuy.getPrice())
                .setParameter(3, offerSellBuy.getType())
                .setParameter(4, offerSellBuy.getDate())
                .setParameter(5, offerSellBuy.getCompanie().getId())
                .setParameter(6, offerSellBuy.getUser().getId())
                .setParameter(7, true)
                .executeUpdate();
    }
}
