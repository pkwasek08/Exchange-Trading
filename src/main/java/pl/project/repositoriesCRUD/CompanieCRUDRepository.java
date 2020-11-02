package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Companie;

@Repository
public interface CompanieCRUDRepository extends CrudRepository<Companie, Integer> {
}
