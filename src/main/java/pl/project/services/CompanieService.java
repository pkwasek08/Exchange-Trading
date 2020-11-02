package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Companie;
import pl.project.repositoriesCRUD.CompanieCRUDRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanieService {
    @Autowired
    private CompanieCRUDRepository companieRepository;

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
