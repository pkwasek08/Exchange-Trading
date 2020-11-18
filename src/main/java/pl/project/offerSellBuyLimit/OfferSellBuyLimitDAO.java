package pl.project.offerSellBuyLimit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OfferSellBuyLimitDAO {
    Logger log = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

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
                .setParameter("companyId", offerSellBuyLimit.getCompany().getId())
                .setParameter("userId", offerSellBuyLimit.getUser().getId())
                .setParameter("active", offerSellBuyLimit.getActive())
                .executeUpdate();
    }

    public List<OfferSellBuyLimit> findAllSellOfferLimitByCompanyIdAndActive(Integer companyId) {
        String sql = "SELECT o FROM OfferSellBuyLimit o WHERE o.company.id = :companyId AND o.type = 'Sell' AND o.active = TRUE ORDER BY o.price ASC ";
        return entityManager.createQuery(sql).setParameter("companyId", companyId).getResultList();
    }

    public List<OfferSellBuyLimit> findAllBuyOfferLimitByCompanyIdAndActive(Integer companyId) {
        String sql = "SELECT o FROM OfferSellBuyLimit o WHERE o.company.id = :companyId AND o.type = 'Buy' AND o.active = TRUE ORDER BY o.price DESC ";
        return entityManager.createQuery(sql).setParameter("companyId", companyId).getResultList();
    }

    public List<OfferLimitDTO> findAllOfferBuyLimitByCompanyId(Integer companyId) {
        String sql = "SELECT NEW pl.project.offerSellBuyLimit.OfferLimitDTO(o.price, sum(o.amount))" +
                " FROM OfferSellBuyLimit o WHERE o.company.id = :companyId AND o.type = 'Buy' AND o.active = TRUE GROUP BY o.price ORDER BY o.price DESC";
        return entityManager.createQuery(sql).setParameter("companyId", companyId).setMaxResults(10).getResultList();
    }

    public List<OfferLimitDTO> findAllOfferSellLimitByCompanyId(Integer companyId) {
        String sql = "SELECT NEW pl.project.offerSellBuyLimit.OfferLimitDTO(o.price, sum(o.amount))" +
                " FROM OfferSellBuyLimit o WHERE o.company.id = :companyId AND o.type = 'Sell' AND o.active = TRUE GROUP BY o.price ORDER BY o.price ASC";
        return entityManager.createQuery(sql).setParameter("companyId", companyId).setMaxResults(10).getResultList();
    }
}
