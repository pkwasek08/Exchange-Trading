package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entieties.Companie;
import pl.project.repositories.CompanieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanieService {
    @Autowired
    private CompanieRepository companieRepository;

    public List<Companie> getAllCompanie() {
        List<Companie> companies = new ArrayList<>();
        companieRepository.findAll().forEach(companies::add);
        return companies;
    }

    public Companie getCompanie(Integer id) {
        Companie companie = companieRepository.findById(id).get();
        return companie;
    }

    public void addCompanie(Companie companie) {
        companieRepository.save(companie);
    }


    public void updateCompanie(Integer id, Companie companie) {
        companieRepository.save(companie);
    }


    public void deleteCompanie(Integer id) {
        companieRepository.deleteById(id);
    }
}
