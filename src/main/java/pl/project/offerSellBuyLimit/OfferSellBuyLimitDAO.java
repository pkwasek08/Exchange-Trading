package pl.project.offerSellBuyLimit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OfferSellBuyLimitDAO {
    Logger log = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

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

    public OfferLimitDTO findOfferBuyLimitByCompanyId(Integer companyId, Integer userId) {
        String sql = "SELECT NEW pl.project.offerSellBuyLimit.OfferLimitDTO(o.price, sum(o.amount))" +
                " FROM OfferSellBuyLimit o WHERE o.company.id = :companyId AND o.type = 'Buy' AND o.active = TRUE " +
                " AND (o.user.id != :userId OR o.user.id = null) GROUP BY o.price ORDER BY o.price DESC";
        try {
            return (OfferLimitDTO) entityManager.createQuery(sql).setParameter("companyId", companyId).setParameter("userId", userId)
                    .setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            log.error(e.getMessage() + " " + e.getLocalizedMessage());
            return new OfferLimitDTO(0f, 0l);
        }
    }

    public OfferLimitDTO findOfferSellLimitByCompanyId(Integer companyId, Integer userId) {
        String sql = "SELECT NEW pl.project.offerSellBuyLimit.OfferLimitDTO(o.price, sum(o.amount))" +
                " FROM OfferSellBuyLimit o WHERE o.company.id = :companyId AND o.type = 'Sell' AND o.active = TRUE" +
                " AND (o.user.id != :userId OR o.user.id = null) GROUP BY o.price ORDER BY o.price ASC";
        try {
            return (OfferLimitDTO) entityManager.createQuery(sql).setParameter("companyId", companyId).setParameter("userId", userId)
                    .setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            log.error(e.getMessage() + " " + e.getLocalizedMessage());
            return new OfferLimitDTO(0f, 0l);
        }
    }
}
