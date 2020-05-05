package pl.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entieties.Companie;
import pl.project.services.CompanieService;

import java.util.List;

@RestController
public class CompanieController {
    @Autowired
    private CompanieService companieService;

    @GetMapping("/companie")
    @CrossOrigin(origins = "*")
    public List<Companie> getAllCompanie() {
        return companieService.getAllCompanie();
    }

    @GetMapping("/companie/{id}")
    @CrossOrigin(origins = "*")
    public Companie getCity(@PathVariable Integer id) {
        return companieService.getCompanie(id);
    }

    @PostMapping(value = "/companie")
    @CrossOrigin(origins = "*")
    public void addCity(@RequestBody Companie companie) {
        companieService.addCompanie(companie);
    }

    @PutMapping(value = "/companie/{id}")
    @CrossOrigin(origins = "*")
    public void updateCity(@RequestBody Companie companie, @PathVariable Integer id) {
        companieService.updateCompanie(id, companie);
    }

    @DeleteMapping(value = "/companie/{id}")
    @CrossOrigin(origins = "*")
    public void deleteCity(@PathVariable Integer id) {
        companieService.deleteCompanie(id);
    }

}
