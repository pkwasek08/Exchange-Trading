package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.CompanieStatistics;
import pl.project.repositoriesCRUD.CompanieStatisticsCRUDRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompanieStatisticsService {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private CompanieStatisticsCRUDRepository companieStatisticsCRUDRepository;
    @Autowired
    private CompanieService companieService;

    public List<CompanieStatistics> getAllCompanieStatistics() {
        List<CompanieStatistics> companiesStatistics = new ArrayList<>();
        companieStatisticsCRUDRepository.findAll().forEach(companiesStatistics::add);
        return companiesStatistics;
    }

    public CompanieStatistics getCompanieStatistics(Integer id) {
        CompanieStatistics companieStatistics = companieStatisticsCRUDRepository.findById(id).get();
        return companieStatistics;
    }

    public void addCompanieStatistics(CompanieStatistics companieStatistics) {
        companieStatisticsCRUDRepository.save(companieStatistics);
    }

    public List<CompanieStatistics> getCompanieStatisticsByCompanieId(Integer companyId) {
        return companieStatisticsCRUDRepository.findAllByCompanie_IdOrderByDateDesc(companyId);
    }

    public CompanieStatistics getCompanieStatisticsByCompanieIdLatest(Integer companyId) {
         CompanieStatistics companieStatistics = companieStatisticsCRUDRepository.findFirstByCompanie_IdOrderByDateDesc(companyId);
         if(companieStatistics != null){
             return companieStatistics;
         } else {
             return getNewDailyCompanieStatistic(companyId);
         }
    }

    public void updateCompanieStatistics(Integer id, CompanieStatistics companieStatistics) {
        companieStatisticsCRUDRepository.save(companieStatistics);
    }


    public void deleteCompanieStatistics(Integer id) {
        companieStatisticsCRUDRepository.deleteById(id);
    }


    public void updateDailyCompanieStatistic(Integer companyId, float price, int amount) {
        CompanieStatistics companieStatistics = getCompanieStatisticsByCompanieIdLatest(companyId);
        seetleStaticCompany(companieStatistics, price, amount);
    }

    private void seetleStaticCompany(CompanieStatistics companieStatistics, float price, int amount){
        Float offerPrice = price;
        Float companieStatisticActualPrice = companieStatistics.getPrice();
        if (!(offerPrice.compareTo(companieStatistics.getPrice()) == 0)) {
            companieStatistics.setPrice(offerPrice);
            if(companieStatisticActualPrice != 0) {
                float trend = 100 - (companieStatistics.getPrice() / companieStatisticActualPrice) * 100;
                companieStatistics.setTrendValue(trend);
            }
        }
        if (companieStatistics.getMaxPrice() < offerPrice) {
            companieStatistics.setMaxPrice(offerPrice);
        }
        if (companieStatistics.getMinPrice() > offerPrice) {
            companieStatistics.setMinPrice(offerPrice);
        }
        companieStatistics.setVolume(companieStatistics.getVolume() + amount);
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        try {
            if (formatter.parse(formatter.format(new Date())).equals(formatter.parse(formatter.format(companieStatistics.getDate())))) {
                updateCompanieStatistics(companieStatistics.getId(), companieStatistics);
            } else {
                addCompanieStatistics(getNewDailyCompanieStatisticByCompanieStatistic(companieStatistics, amount));
            }
        } catch (ParseException e) {
            log.error("Error " + this.getClass() + " message: " + e.getMessage());
        }
    }

    private CompanieStatistics getNewDailyCompanieStatisticByCompanieStatistic(CompanieStatistics companieStatistics, int amountStocks){
        CompanieStatistics csDaily = new CompanieStatistics();
        csDaily.setPrice(companieStatistics.getPrice());
        csDaily.setDate(new Date());
        csDaily.setVolume(amountStocks);
        csDaily.setMaxPrice(companieStatistics.getMaxPrice());
        csDaily.setMinPrice(companieStatistics.getMinPrice());
        csDaily.setTrendValue(companieStatistics.getTrendValue());
        csDaily.setCompanie(companieStatistics.getCompanie());
        return csDaily;
    }

    private CompanieStatistics getNewDailyCompanieStatistic(Integer companyId){
        CompanieStatistics csDaily = new CompanieStatistics();
        csDaily.setPrice(0f);
        csDaily.setDate(new Date());
        csDaily.setVolume(0);
        csDaily.setMaxPrice(0f);
        csDaily.setMinPrice(Float.MAX_VALUE);
        csDaily.setTrendValue(0f);
        csDaily.setCompanie(companieService.getCompanie(companyId));
        return csDaily;
    }

}

