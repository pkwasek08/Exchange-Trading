package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entieties.CompanieStatistics;

@Repository
public interface CompanieStatisticsRepository extends CrudRepository<CompanieStatistics, Integer> {
}
