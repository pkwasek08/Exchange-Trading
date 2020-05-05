package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entieties.CompanieStatistics;
import pl.project.services.CompanieStatisticsService;

import java.util.List;

@RestController
public class CompanieStatisticsController {
    @Autowired
    private CompanieStatisticsService companieStatisticsService;

    @GetMapping("/companieStatistic")
    @CrossOrigin(origins = "*")
    public List<CompanieStatistics> getAllCompanieStatistics() {
        return companieStatisticsService.getAllCompanieStatistics();
    }

    @GetMapping("/companieStatistic/{id}")
    @CrossOrigin(origins = "*")
    public CompanieStatistics getCompanieStatistics(@PathVariable Integer id) {
        return companieStatisticsService.getCompanieStatistics(id);
    }

    @PostMapping(value = "/companieStatistic")
    @CrossOrigin(origins = "*")
    public void addCompanieStatistics(@RequestBody CompanieStatistics companiesStatistic) {
        companieStatisticsService.addCompanieStatistics(companiesStatistic);
    }

    @PutMapping(value = "/companieStatistic/{id}")
    @CrossOrigin(origins = "*")
    public void updateCompanieStatistics(@RequestBody CompanieStatistics companiesStatistic, @PathVariable Integer id) {
        companieStatisticsService.updateCompanieStatistics(id, companiesStatistic);
    }

    @DeleteMapping(value = "/companieStatistic/{id}")
    @CrossOrigin(origins = "*")
    public void deleteCompanieStatistics(@PathVariable Integer id) {
        companieStatisticsService.deleteCompanieStatistics(id);
    }
}
