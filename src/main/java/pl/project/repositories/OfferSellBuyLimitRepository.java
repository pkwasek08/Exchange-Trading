package pl.project.repositories;

import com.mysql.cj.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.project.dto.OfferLimitDTO;
import pl.project.entities.OfferSellBuyLimit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OfferSellBuyLimitRepository {
    Logger log = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public int addOfferSellBuy(OfferSellBuyLimit offerSellBuyLimit) {
        return entityManager.createNativeQuery("INSERT INTO offers_sell_buy_limit(amount, price, type, date, limit_price, company_id, user_id, active) VALUES (?,?,?,?,?,?,?,?)")
                .setParameter(1, offerSellBuyLimit.getAmount())
                .setParameter(2, offerSellBuyLimit.getPrice())
                .setParameter(3, offerSellBuyLimit.getType())
                .setParameter(4, offerSellBuyLimit.getDate())
                .setParameter(5, offerSellBuyLimit.getLimit())
                .setParameter(6, offerSellBuyLimit.getCompanie().getId())
                .setParameter(7, offerSellBuyLimit.getUser().getId())
                .setParameter(8, offerSellBuyLimit.getActive())
                .executeUpdate();
    }

    @Transactional
    public void updateOfferSellBuy(OfferSellBuyLimit offerSellBuyLimit) {
        entityManager.createNativeQuery("UPDATE offers_sell_buy_limit SET amount = :amount, price = :price, type= :type, date = :date , limit_price = :limit, company_id = :companyId, " +
                "user_id = :userId, active = :active where id = :id")
                .setParameter("id", offerSellBuyLimit.getId())
                .setParameter("amount", offerSellBuyLimit.getAmount())
                .setParameter("price", offerSellBuyLimit.getPrice())
                .setParameter("type", offerSellBuyLimit.getType())
                .setParameter("date", offerSellBuyLimit.getDate())
                .setParameter("limit", offerSellBuyLimit.getLimit())
                .setParameter("companyId", offerSellBuyLimit.getCompanie().getId())
                .setParameter("userId", offerSellBuyLimit.getUser().getId())
                .setParameter("active", offerSellBuyLimit.getActive())
                .executeUpdate();
    }

    public List<OfferSellBuyLimit> findAllOfferLimitByCompanieIdAndActive(Integer companieId, String type) {
        String sql = "SELECT o FROM OfferSellBuyLimit o WHERE o.companie.id = :companieId AND o.type = :type AND o.active = TRUE ORDER BY price DESC ";
        return entityManager.createQuery(sql).setParameter("companieId", companieId).setParameter("type", type).getResultList();
    }

    public List<OfferLimitDTO> findAllOfferBuyLimitByCompanieId(Integer companieId) {
        String sql = "SELECT NEW pl.project.dto.OfferLimitDTO(o.price, sum(o.amount))" +
                " FROM OfferSellBuyLimit o WHERE o.companie.id = :companieId AND o.type = 'Buy' AND o.active = TRUE GROUP BY o.price ORDER BY o.price ASC";
        return entityManager.createQuery(sql).setParameter("companieId", companieId).getResultList();
    }

    public List<OfferLimitDTO> findAllOfferSellLimitByCompanieId(Integer companieId) {
        String sql = "SELECT NEW pl.project.dto.OfferLimitDTO(o.price, sum(o.amount))" +
                " FROM OfferSellBuyLimit o WHERE o.companie.id = :companieId AND o.type = 'Sell' AND o.active = TRUE GROUP BY o.price ORDER BY o.price ASC";
        return entityManager.createQuery(sql).setParameter("companieId", companieId).getResultList();
    }
}
