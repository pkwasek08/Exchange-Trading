package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entieties.OfferSellBuy;

@Repository
public interface OfferSellBuyRepository extends CrudRepository<OfferSellBuy, Integer> {
}
