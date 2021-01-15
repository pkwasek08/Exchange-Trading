package pl.project.companyStatistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.project.company.CompanyService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class CompanyStatisticsService {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;
    @Autowired
    private CompanyStatisticDAO companyStatisticDAO;
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
        if (companyStatistics != null) {
            return companyStatistics;
        } else {
            return getNewDailyCompanyStatistic(companyId, new Date());
        }
    }

    public CompanyStatistics getCompanyStatisticsByCompanyIdAndDate(Integer companyId, Date dateTrade) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTrade);
        CompanyStatistics companyStatistics = companyStatisticDAO.findFirstByCompany_IdAndDayAndMonthAndYear(companyId,
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        if (nonNull(companyStatistics)) {
            return companyStatistics;
        } else {
            return getNewDailyCompanyStatistic(companyId, dateTrade);
        }
    }

    public void updateCompanyStatistics(Integer id, CompanyStatistics companyStatistics) {
        companyStatisticsRepository.save(companyStatistics);
    }


    public void deleteCompanyStatistics(Integer id) {
        companyStatisticsRepository.deleteById(id);
    }


    public void updateDailyCompanyStatistic(Integer companyId, float price, int amount, Date dateTrade) {
        CompanyStatistics companyStatistics = getCompanyStatisticsByCompanyIdAndDate(companyId, dateTrade);
        settleStaticCompany(companyStatistics, price, amount);
    }

    private void settleStaticCompany(CompanyStatistics companyStatistics, float price, int amount) {
        Float offerPrice = price;
        Float companyStatisticActualPrice = companyStatistics.getPrice();
        if (!(offerPrice.compareTo(companyStatistics.getPrice()) == 0)) {
            companyStatistics.setPrice(offerPrice);
            if (companyStatisticActualPrice != 0) {
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

        addCompanyStatistics(companyStatistics);

    }

    private CompanyStatistics getNewDailyCompanyStatisticByCompanyStatistic(CompanyStatistics companyStatistics) {
        CompanyStatistics csDaily = new CompanyStatistics();
        csDaily.setPrice(companyStatistics.getPrice());
        csDaily.setDate(new Date());
        csDaily.setVolume(companyStatistics.getVolume());
        csDaily.setMaxPrice(companyStatistics.getMaxPrice());
        csDaily.setMinPrice(companyStatistics.getMinPrice());
        csDaily.setTrendValue(companyStatistics.getTrendValue());
        csDaily.setCompany(companyStatistics.getCompany());
        return csDaily;
    }

    private CompanyStatistics getNewDailyCompanyStatistic(Integer companyId, Date dateTrade) {
        CompanyStatistics csDaily = new CompanyStatistics();
        csDaily.setPrice(0f);
        csDaily.setDate(dateTrade);
        csDaily.setVolume(0);
        csDaily.setMaxPrice(0f);
        csDaily.setMinPrice(Float.MAX_VALUE);
        csDaily.setTrendValue(0f);
        csDaily.setCompany(companyService.getCompany(companyId));
        return csDaily;
    }

}

