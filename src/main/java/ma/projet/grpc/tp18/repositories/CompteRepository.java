package ma.projet.grpc.tp18.repositories;

import ma.projet.grpc.tp18.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}