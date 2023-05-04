package edu.kjoyeux.demo.dao;
import edu.kjoyeux.demo.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EntrepriseDao extends JpaRepository<Entreprise, Integer> {
}
