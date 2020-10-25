package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.OfferSellBuyLimit;

@Repository
public interface OfferSellBuyLimitRepository extends CrudRepository<OfferSellBuyLimit, Integer> {
}
