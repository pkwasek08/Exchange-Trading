package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entieties.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}
