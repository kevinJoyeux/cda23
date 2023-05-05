package edu.kjoyeux.demo.dao;

import edu.kjoyeux.demo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<MyUser,Integer>{
    MyUser findByPrenom(String prenom);
    Optional<MyUser> findByEmail(String email);
    @Query("FROM MyUser U JOIN U.pays P WHERE P.nom = ?1")
    List<MyUser> trouveUtilisateurSelonPays(String pays);
}
