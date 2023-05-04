package edu.kjoyeux.demo.dao;

import edu.kjoyeux.demo.model.Emploi;
import edu.kjoyeux.demo.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploiDao extends JpaRepository<Emploi,Integer>{
}
