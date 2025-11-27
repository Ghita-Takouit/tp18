package ma.projet.grpc.tp18.controllers;

import ma.projet.grpc.tp18.entities.Compte;
import ma.projet.grpc.tp18.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteRestController {

    @Autowired
    private CompteRepository compteRepository;

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Compte getCompteById(@PathVariable Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Compte saveCompte(@RequestBody Compte compte) {
        return compteRepository.save(compte);
    }

    @GetMapping("/stats")
    public CompteStats getStats() {
        List<Compte> comptes = compteRepository.findAll();
        int count = comptes.size();
        float sum = (float) comptes.stream().mapToDouble(Compte::getSolde).sum();
        float average = count > 0 ? sum / count : 0;

        return new CompteStats(count, sum, average);
    }

    public static class CompteStats {
        public int count;
        public float sum;
        public float average;

        public CompteStats(int count, float sum, float average) {
            this.count = count;
            this.sum = sum;
            this.average = average;
        }
    }
}

