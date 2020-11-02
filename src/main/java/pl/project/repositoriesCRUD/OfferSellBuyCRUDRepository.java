package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.OfferSellBuy;

import java.util.List;

@Repository
public interface OfferSellBuyCRUDRepository extends CrudRepository<OfferSellBuy, Integer> {
    List<OfferSellBuy> findAllByUserId(Integer id);
}
