package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.OfferSellBuyLimit;

@Repository
public interface OfferSellBuyLimitCRUDRepository extends CrudRepository<OfferSellBuyLimit, Integer> {
}
