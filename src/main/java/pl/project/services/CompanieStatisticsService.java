package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.CompanieStatistics;
import pl.project.entities.OfferSellBuy;
import pl.project.entities.OfferSellBuyLimit;
import pl.project.repositories.CompanieStatisticsRepository;
import pl.project.repositoriesCRUD.CompanieStatisticsCRUDRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanieStatisticsService {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private CompanieStatisticsCRUDRepository companieStatisticsCRUDRepository;
    @Autowired
    private CompanieStatisticsRepository companieStatisticsRepository;

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

    public List<CompanieStatistics> getCompanieStatisticsByCompanieId(Integer companieId) {
        return companieStatisticsCRUDRepository.findAllByCompanieId(companieId);
    }

    public CompanieStatistics getCompanieStatisticsByCompanieIdLatest(Integer companieId) {
        return companieStatisticsRepository.findByCompanieIdLatest(companieId);
    }

    public void updateCompanieStatistics(Integer id, CompanieStatistics companieStatistics) {
        companieStatisticsCRUDRepository.save(companieStatistics);
    }


    public void deleteCompanieStatistics(Integer id) {
        companieStatisticsCRUDRepository.deleteById(id);
    }


    public void updateDailyCompanieStatistic(OfferSellBuy offer) {
        CompanieStatistics companieStatistics = getCompanieStatisticsByCompanieIdLatest(offer.getCompanie().getId());
        Float offerPrice = offer.getPrice();
        if (offerPrice != companieStatistics.getPrice()) {
            companieStatistics.setPrice(offerPrice);
        }
        if (companieStatistics.getMaxPrice() < offerPrice) {
            companieStatistics.setMaxPrice(offerPrice);
        }
        if (companieStatistics.getMinPrice() > offerPrice) {
            companieStatistics.setMinPrice(offerPrice);
        }
        companieStatistics.setVolume(companieStatistics.getVolume() + offer.getAmount());
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        try {
            if (formatter.parse(formatter.format(offer.getDate())).equals(formatter.parse(formatter.format(companieStatistics.getDate())))) {
                updateCompanieStatistics(companieStatistics.getId(), companieStatistics);
            } else {
                addCompanieStatistics(companieStatistics);
            }
        } catch (ParseException e) {
            log.error("Blad " + this.getClass() + " message: " + e.getMessage());
        }

    }

}

