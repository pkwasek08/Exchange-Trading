package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entieties.CompanieStatistics;
import pl.project.repositories.CompanieStatisticsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanieStatisticsService {
    @Autowired
    private CompanieStatisticsRepository companieStatisticsRepository;

    public List<CompanieStatistics> getAllCompanieStatistics() {
        List<CompanieStatistics> companiesStatistics = new ArrayList<>();
        companieStatisticsRepository.findAll().forEach(companiesStatistics::add);
        return companiesStatistics;
    }

    public CompanieStatistics getCompanieStatistics(Integer id) {
        CompanieStatistics companieStatistics = companieStatisticsRepository.findById(id).get();
        return companieStatistics;
    }

    public void addCompanieStatistics(CompanieStatistics companieStatistics) {
        companieStatisticsRepository.save(companieStatistics);
    }


    public void updateCompanieStatistics(Integer id, CompanieStatistics companieStatistics) {
        companieStatisticsRepository.save(companieStatistics);
    }


    public void deleteCompanieStatistics(Integer id) {
        companieStatisticsRepository.deleteById(id);
    }
}
