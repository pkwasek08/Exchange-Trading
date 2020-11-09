package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.CompanieStatistics;
import java.util.List;

@Repository
public interface CompanieStatisticsCRUDRepository extends CrudRepository<CompanieStatistics, Integer> {
    List<CompanieStatistics> findAllByCompanie_IdOrderByDateDesc(int id);

    CompanieStatistics findFirstByCompanie_IdOrderByDateDesc(int id);
}
