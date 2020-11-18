package pl.project.stock;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    List<Stock> findAllByUser_Id(Integer userId);

    Stock findByUser_IdAndCompany_Id(Integer userId, Integer companyId);

}
