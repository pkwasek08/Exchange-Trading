package pl.project.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    @Query("select NEW pl.project.company.CompanyInfoDTO(c.id, c.name) from Company c")
    List<CompanyInfoDTO> getCompanyInfoList();
}
