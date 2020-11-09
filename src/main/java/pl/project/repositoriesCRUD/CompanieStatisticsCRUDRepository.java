package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.CompanieStatistics;
import java.util.List;

@Repository
public interface CompanieStatisticsCRUDRepository extends CrudRepository<CompanieStatistics, Integer> {
    List<CompanieStatistics> findAllByCompanie_Id(int id);

    CompanieStatistics findFirstByCompanie_IdOrderByIdDesc(int id);
}
