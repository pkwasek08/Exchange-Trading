package pl.project.companyStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companyStatistic")
public class CompanyStatisticsController {
    @Autowired
    private CompanyStatisticsService companyStatisticsService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<CompanyStatistics> getAllCompanyStatistics() {
        return companyStatisticsService.getAllCompanyStatistics();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public CompanyStatistics getCompanyStatistics(@PathVariable Integer id) {
        return companyStatisticsService.getCompanyStatistics(id);
    }

    @RequestMapping("/company")
    @CrossOrigin(origins = "*")
    public List<CompanyStatistics> getCompanyStatisticsByCompanyId(@RequestParam Integer companyId) {
        return companyStatisticsService.getCompanyStatisticsByCompanyId(companyId);
    }

    @RequestMapping("/company/paginator")
    @CrossOrigin(origins = "*")
    public Page<CompanyStatistics> getCompanyStatisticsByCompanyIdPage(@RequestParam Integer companyId, @RequestParam int page, @RequestParam int size) {
        return companyStatisticsService.getCompanyStatisticsPageByCompanyId(companyId, page, size);
    }


    @RequestMapping("/company/latest")
    @CrossOrigin(origins = "*")
    public CompanyStatistics getCompanyStatisticsByCompanyIdLatest(@RequestParam Integer companyId) {
        return companyStatisticsService.getCompanyStatisticsByCompanyIdLatest(companyId);
    }


    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addCompanyStatistics(@RequestBody CompanyStatistics companyStatistic) {
        companyStatisticsService.addCompanyStatistics(companyStatistic);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateCompanyStatistics(@RequestBody CompanyStatistics companyStatistic, @PathVariable Integer id) {
        companyStatisticsService.updateCompanyStatistics(id, companyStatistic);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteCompanyStatistics(@PathVariable Integer id) {
        companyStatisticsService.deleteCompanyStatistics(id);
    }
}
