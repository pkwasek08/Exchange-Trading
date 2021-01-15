package pl.project.companyStatistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CompanyStatisticDAO {
    Logger log = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    public CompanyStatistics findFirstByCompany_IdAndDayAndMonthAndYear(Integer companyId, Integer day, Integer month, Integer year) {
        try {
            String sql = "SELECT cs FROM CompanyStatistics cs WHERE cs.company.id = :companyId AND EXTRACT(day from cs.date) = :day AND EXTRACT(month from cs.date) = :month AND EXTRACT(year from cs.date) =:year ";
            return (CompanyStatistics) entityManager.createQuery(sql).setParameter("companyId", companyId).setParameter("day", day).setParameter("month", month)
                    .setParameter("year", year).setMaxResults(1).getSingleResult();
        } catch (NoResultException e){
            log.error(e.getMessage());
            return null;
        }
    }


}
