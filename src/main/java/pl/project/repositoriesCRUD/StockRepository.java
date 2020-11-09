package pl.project.repositoriesCRUD;

import org.springframework.data.repository.CrudRepository;
import pl.project.entities.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    List<Stock> findAllByUser_Id(Integer userId);

    Stock findByUser_IdAndCompany_Id(Integer userId, Integer companyId);

}
