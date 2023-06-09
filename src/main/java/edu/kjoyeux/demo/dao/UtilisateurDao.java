package edu.kjoyeux.demo.dao;

import edu.kjoyeux.demo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<MyUser,Integer>{
    MyUser findByPrenom(String prenom);
    @Query("FROM MyUser U JOIN FETCH U.roles WHERE U.email = :email")
    Optional<MyUser> findByEmail(@Param("email") String email);
    @Query("FROM MyUser U JOIN U.pays P WHERE P.nom = ?1")
    List<MyUser> trouveUtilisateurSelonPays(String pays);
}
