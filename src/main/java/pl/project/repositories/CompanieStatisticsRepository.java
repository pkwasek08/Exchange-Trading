package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.CompanieStatistics;

@Repository
public interface CompanieStatisticsRepository extends CrudRepository<CompanieStatistics, Integer> {
}
