package pl.project.repositoriesCRUD;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.project.entities.CompanieStatistics;
import java.util.List;

@Repository
public interface CompanieStatisticsCRUDRepository extends CrudRepository<CompanieStatistics, Integer> {
    @Query(value = "SELECT cs FROM CompanieStatistics cs WHERE cs.companieByCompanieId.id = :companieId ORDER BY cs.id DESC ")
    List<CompanieStatistics> findAllByCompanieId(@Param("companieId") Integer companieId);
}
