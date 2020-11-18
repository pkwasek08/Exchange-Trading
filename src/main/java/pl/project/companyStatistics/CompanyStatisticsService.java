package pl.project.companyStatistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.project.company.CompanyService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompanyStatisticsService {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;
    @Autowired
    private CompanyService companyService;

    public List<CompanyStatistics> getAllCompanyStatistics() {
        List<CompanyStatistics> companiesStatistics = new ArrayList<>();
        companyStatisticsRepository.findAll().forEach(companiesStatistics::add);
        return companiesStatistics;
    }

    public CompanyStatistics getCompanyStatistics(Integer id) {
        CompanyStatistics companyStatistics = companyStatisticsRepository.findById(id).get();
        return companyStatistics;
    }

    public void addCompanyStatistics(CompanyStatistics companyStatistics) {
        companyStatisticsRepository.save(companyStatistics);
    }

    public List<CompanyStatistics> getCompanyStatisticsByCompanyId(Integer companyId) {
        return companyStatisticsRepository.findAllByCompany_IdOrderByDateDesc(companyId);
    }

    public Page<CompanyStatistics> getCompanyStatisticsPageByCompanyId(Integer companyId, int page, int size) {
        return companyStatisticsRepository.findAllByCompany_IdOrderByDateDesc(companyId, PageRequest.of(page, size));
    }

    public CompanyStatistics getCompanyStatisticsByCompanyIdLatest(Integer companyId) {
         CompanyStatistics companyStatistics = companyStatisticsRepository.findFirstByCompany_IdOrderByDateDesc(companyId);
         if(companyStatistics != null){
             return companyStatistics;
         } else {
             return getNewDailyCompanyStatistic(companyId);
         }
    }

    public void updateCompanyStatistics(Integer id, CompanyStatistics companyStatistics) {
        companyStatisticsRepository.save(companyStatistics);
    }


    public void deleteCompanyStatistics(Integer id) {
        companyStatisticsRepository.deleteById(id);
    }


    public void updateDailyCompanyStatistic(Integer companyId, float price, int amount) {
        CompanyStatistics companyStatistics = getCompanyStatisticsByCompanyIdLatest(companyId);
        settleStaticCompany(companyStatistics, price, amount);
    }

    private void settleStaticCompany(CompanyStatistics companyStatistics, float price, int amount){
        Float offerPrice = price;
        Float companyStatisticActualPrice = companyStatistics.getPrice();
        if (!(offerPrice.compareTo(companyStatistics.getPrice()) == 0)) {
            companyStatistics.setPrice(offerPrice);
            if(companyStatisticActualPrice != 0) {
                float trend = 100 - (companyStatistics.getPrice() / companyStatisticActualPrice) * 100;
                companyStatistics.setTrendValue(trend);
            }
        }
        if (companyStatistics.getMaxPrice() < offerPrice) {
            companyStatistics.setMaxPrice(offerPrice);
        }
        if (companyStatistics.getMinPrice() > offerPrice) {
            companyStatistics.setMinPrice(offerPrice);
        }
        companyStatistics.setVolume(companyStatistics.getVolume() + amount);
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        try {
            if (formatter.parse(formatter.format(new Date())).equals(formatter.parse(formatter.format(companyStatistics.getDate())))) {
                updateCompanyStatistics(companyStatistics.getId(), companyStatistics);
            } else {
                addCompanyStatistics(getNewDailyCompanyStatisticByCompanyStatistic(companyStatistics, amount));
            }
        } catch (ParseException e) {
            log.error("Error " + this.getClass() + " message: " + e.getMessage());
        }
    }

    private CompanyStatistics getNewDailyCompanyStatisticByCompanyStatistic(CompanyStatistics companyStatistics, int amountStocks){
        CompanyStatistics csDaily = new CompanyStatistics();
        csDaily.setPrice(companyStatistics.getPrice());
        csDaily.setDate(new Date());
        csDaily.setVolume(amountStocks);
        csDaily.setMaxPrice(companyStatistics.getMaxPrice());
        csDaily.setMinPrice(companyStatistics.getMinPrice());
        csDaily.setTrendValue(companyStatistics.getTrendValue());
        csDaily.setCompany(companyStatistics.getCompany());
        return csDaily;
    }

    private CompanyStatistics getNewDailyCompanyStatistic(Integer companyId){
        CompanyStatistics csDaily = new CompanyStatistics();
        csDaily.setPrice(0f);
        csDaily.setDate(new Date());
        csDaily.setVolume(0);
        csDaily.setMaxPrice(0f);
        csDaily.setMinPrice(Float.MAX_VALUE);
        csDaily.setTrendValue(0f);
        csDaily.setCompany(companyService.getCompany(companyId));
        return csDaily;
    }

}

