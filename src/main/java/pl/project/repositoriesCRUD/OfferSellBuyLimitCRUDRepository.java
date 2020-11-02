package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.OfferSellBuyLimit;

import java.util.List;


@Repository
public interface OfferSellBuyLimitCRUDRepository extends CrudRepository<OfferSellBuyLimit, Integer> {
    List<OfferSellBuyLimit> findAllByTypeAndActive(String type, boolean active);

    List<OfferSellBuyLimit> findAllByUser_IdAndActive(Integer id, boolean active);
}
