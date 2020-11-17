package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.CompanieStatistics;
import pl.project.services.CompanieStatisticsService;

import java.util.List;

@RestController
@RequestMapping(value = "/companieStatistic")
public class CompanieStatisticsController {
    @Autowired
    private CompanieStatisticsService companieStatisticsService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<CompanieStatistics> getAllCompanieStatistics() {
        return companieStatisticsService.getAllCompanieStatistics();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public CompanieStatistics getCompanieStatistics(@PathVariable Integer id) {
        return companieStatisticsService.getCompanieStatistics(id);
    }

    @RequestMapping("/companie")
    @CrossOrigin(origins = "*")
    public List<CompanieStatistics> getCompanieStatisticsByCompanieId(@RequestParam Integer companieId) {
        return companieStatisticsService.getCompanieStatisticsByCompanieId(companieId);
    }

    @RequestMapping("/companie/paginator")
    @CrossOrigin(origins = "*")
    public Page<CompanieStatistics> getCompanieStatisticsByCompanieIdPage(@RequestParam Integer companieId, @RequestParam int page, @RequestParam int size) {
        return companieStatisticsService.getCompanieStatisticsPageByCompanieId(companieId, page, size);
    }


    @RequestMapping("/companie/latest")
    @CrossOrigin(origins = "*")
    public CompanieStatistics getCompanieStatisticsByCompanieIdLatest(@RequestParam Integer companieId) {
        return companieStatisticsService.getCompanieStatisticsByCompanieIdLatest(companieId);
    }


    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addCompanieStatistics(@RequestBody CompanieStatistics companiesStatistic) {
        companieStatisticsService.addCompanieStatistics(companiesStatistic);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateCompanieStatistics(@RequestBody CompanieStatistics companiesStatistic, @PathVariable Integer id) {
        companieStatisticsService.updateCompanieStatistics(id, companiesStatistic);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteCompanieStatistics(@PathVariable Integer id) {
        companieStatisticsService.deleteCompanieStatistics(id);
    }
}
