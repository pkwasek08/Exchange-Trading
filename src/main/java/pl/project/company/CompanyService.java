package pl.project.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompany() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);
        return companies;
    }

    public Company getCompany(Integer id) {
        Company company = companyRepository.findById(id).get();
        return company;
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }


    public void updateCompany(Integer id, Company company) {
        companyRepository.save(company);
    }


    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
