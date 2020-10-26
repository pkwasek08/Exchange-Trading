package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.CompanieStatistics;
import pl.project.repositories.CompanieStatisticsRepository;
import pl.project.repositoriesCRUD.CompanieStatisticsCRUDRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanieStatisticsService {
    @Autowired
    private CompanieStatisticsCRUDRepository companieStatisticsCRUDRepository;
    @Autowired
    private CompanieStatisticsRepository companieStatisticsRepository;

    public List<CompanieStatistics> getAllCompanieStatistics() {
        List<CompanieStatistics> companiesStatistics = new ArrayList<>();
        companieStatisticsCRUDRepository.findAll().forEach(companiesStatistics::add);
        return companiesStatistics;
    }

    public CompanieStatistics getCompanieStatistics(Integer id) {
        CompanieStatistics companieStatistics = companieStatisticsCRUDRepository.findById(id).get();
        return companieStatistics;
    }

    public void addCompanieStatistics(CompanieStatistics companieStatistics) {
        companieStatisticsCRUDRepository.save(companieStatistics);
    }

    public List<CompanieStatistics> getCompanieStatisticsByCompanieId(Integer companieId) {
        return companieStatisticsCRUDRepository.findAllByCompanieId(companieId);
    }

    public CompanieStatistics getCompanieStatisticsByCompanieIdLatest(Integer companieId) {
        return companieStatisticsRepository.findByCompanieIdLatest(companieId);
    }

    public void updateCompanieStatistics(Integer id, CompanieStatistics companieStatistics) {
        companieStatisticsCRUDRepository.save(companieStatistics);
    }


    public void deleteCompanieStatistics(Integer id) {
        companieStatisticsCRUDRepository.deleteById(id);
    }
}
