package pl.project.offerSellBuyLimit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OfferSellBuyLimitRepository extends CrudRepository<OfferSellBuyLimit, Integer> {
    List<OfferSellBuyLimit> findAllByTypeAndActive(String type, boolean active);
    List<OfferSellBuyLimit> findAllByUser_IdAndActive(Integer id, boolean active);
    Optional<OfferSellBuyLimit> findFirstByCompany_IdAndType(Integer companyId, String type);
}
