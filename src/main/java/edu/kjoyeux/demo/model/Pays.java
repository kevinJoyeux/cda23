package edu.kjoyeux.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.kjoyeux.demo.view.VueEntreprise;
import edu.kjoyeux.demo.view.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class})
    private Integer id;
    @JsonView({VueUtilisateur.class})
    private String nom;
}
