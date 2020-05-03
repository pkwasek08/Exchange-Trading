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

    @RequestMapping("/companieStatistic")
    @CrossOrigin(origins = "*")
    public List<CompanieStatistics> getAllCompanieStatistics() {
        return companieStatisticsService.getAllCompanieStatistics();
    }

    @RequestMapping("/companieStatistic/{id}")
    @CrossOrigin(origins = "*")
    public CompanieStatistics getCompanieStatistics(@PathVariable Integer id) {
        return companieStatisticsService.getCompanieStatistics(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/companieStatistic")
    @CrossOrigin(origins = "*")
    public void addCompanieStatistics(@RequestBody CompanieStatistics companiesStatistic) {
        companieStatisticsService.addCompanieStatistics(companiesStatistic);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/companieStatistic/{id}")
    @CrossOrigin(origins = "*")
    public void updateCompanieStatistics(@RequestBody CompanieStatistics companiesStatistic, @PathVariable Integer id) {
        companieStatisticsService.updateCompanieStatistics(id, companiesStatistic);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/companieStatistic/{id}")
    @CrossOrigin(origins = "*")
    public void deleteCompanieStatistics(@PathVariable Integer id) {
        companieStatisticsService.deleteCompanieStatistics(id);
    }
}
