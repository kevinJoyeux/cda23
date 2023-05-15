package edu.kjoyeux.demo.dao;

import edu.kjoyeux.demo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LigneDeContrat extends JpaRepository<edu.kjoyeux.demo.model.LigneDeContrat, edu.kjoyeux.demo.model.LigneDeContrat.IdLigneDeContrat>{

}
