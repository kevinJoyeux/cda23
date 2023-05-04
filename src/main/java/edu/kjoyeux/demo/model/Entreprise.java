package edu.kjoyeux.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kjoyeux.demo.view.VueEntreprise;
import edu.kjoyeux.demo.view.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    private Integer id;

    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    private String nom;
    public Entreprise(){
        super();
    }
    @OneToMany(mappedBy = "entreprise")
    @JsonView({ VueEntreprise.class})
    private Set<MyUser> ListeEmploye = new HashSet<>();
}
