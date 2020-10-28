package pl.project.repositories;

import org.springframework.stereotype.Repository;
import pl.project.entities.OfferSellBuyLimit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class OfferSellBuyLimitRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addOfferSellBuy(OfferSellBuyLimit offerSellBuyLimit) {
        entityManager.createNativeQuery("INSERT INTO offers_sell_buy_limit(amount, price, type, date, limit_price, company_id, user_id, active) VALUES (?,?,?,?,?,?,?,?)")
                .setParameter(1, offerSellBuyLimit.getAmount())
                .setParameter(2, offerSellBuyLimit.getPrice())
                .setParameter(3, offerSellBuyLimit.getType())
                .setParameter(4, offerSellBuyLimit.getDate())
                .setParameter(5, offerSellBuyLimit.getLimit())
                .setParameter(6, offerSellBuyLimit.getCompanie().getId())
                .setParameter(7, offerSellBuyLimit.getUser().getId())
                .setParameter(8, true)
                .executeUpdate();
    }
}
