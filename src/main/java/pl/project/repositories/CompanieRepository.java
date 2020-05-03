package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entieties.Companie;

@Repository
public interface CompanieRepository extends CrudRepository<Companie, Integer> {
}
