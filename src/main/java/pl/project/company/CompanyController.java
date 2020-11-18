package pl.project.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public List<Company> getAllCompany() {
        return companyService.getAllCompany();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    @PostMapping()
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @PutMapping(value = "/{id}")
    public void updateCompany(@RequestBody Company company, @PathVariable Integer id) {
        companyService.updateCompany(id, company);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

}
