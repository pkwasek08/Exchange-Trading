package pl.project.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.execDetails.ExecDetails;
import pl.project.execDetails.ExecDetailsHelper;

import java.time.OffsetDateTime;
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

    public ExecDetailsCompany getCompanyInfoListDetails() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        List<CompanyInfoDTO> companyInfoList = companyRepository.getCompanyInfoList();
        execHelper.addNewDbTime();
        return new ExecDetailsCompany(new ExecDetails(execHelper.getExecTime(), execHelper.getDbTime()), companyInfoList);
    }

    public List<CompanyInfoDTO> getCompanyInfoList() {
        return companyRepository.getCompanyInfoList();
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
