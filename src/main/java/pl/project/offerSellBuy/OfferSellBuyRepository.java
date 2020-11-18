package pl.project.offerSellBuy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferSellBuyRepository extends CrudRepository<OfferSellBuy, Integer> {
    List<OfferSellBuy> findAllByUserId(Integer id);

    Page<OfferSellBuy> findAllByUserId(Integer id, Pageable pageable);

}
