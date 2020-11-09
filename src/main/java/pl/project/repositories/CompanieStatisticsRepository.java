package pl.project.repositories;

import org.springframework.stereotype.Repository;
import pl.project.entities.CompanieStatistics;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CompanieStatisticsRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public CompanieStatistics findByCompanieIdLatest(int companieId) {
        return entityManager.createQuery("SELECT cs FROM CompanieStatistics cs WHERE cs.companieByCompanieId.id = :companieId ORDER BY cs.date DESC",
                CompanieStatistics.class).setParameter("companieId",companieId).setMaxResults(1).getSingleResult();
    }
}
