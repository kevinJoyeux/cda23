package edu.kjoyeux.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.kjoyeux.demo.view.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateDeRetour;
    private LocalDate dateDeCreation;
    @OneToMany(mappedBy = "contrat")
    List<LigneDeContrat> listeLigneDeContrat;
}
