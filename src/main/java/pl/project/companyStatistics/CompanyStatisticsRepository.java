package pl.project.companyStatistics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyStatisticsRepository extends CrudRepository<CompanyStatistics, Integer> {
    List<CompanyStatistics> findAllByCompany_IdOrderByDateDesc(int id);

    CompanyStatistics findFirstByCompany_IdOrderByDateDesc(int id);

    Page<CompanyStatistics> findAllByCompany_IdOrderByDateDesc(int id, Pageable pageable);
}
