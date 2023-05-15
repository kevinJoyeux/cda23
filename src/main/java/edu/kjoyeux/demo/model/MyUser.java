package edu.kjoyeux.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kjoyeux.demo.view.VueEntreprise;
import edu.kjoyeux.demo.view.VueUtilisateur;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "utilisateur")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    private Integer id;
    @Column(length = 80, name="nom", nullable = false)
    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    private String nom;
    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    private String prenom;
    private String motDePasse;
    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    private String email;
    @ManyToOne
    @JsonView({VueUtilisateur.class})
    private Pays pays;
    @JsonView({VueUtilisateur.class, VueEntreprise.class})
    @ManyToMany
    @JoinTable(name = "role_utilisateur",inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @ManyToOne
    @JsonView(VueUtilisateur.class)
    private Entreprise entreprise;
    @JsonView(VueUtilisateur.class)
    private String nomImageProfil;

    @ManyToMany
    @JoinTable(name = "recherche_emploi_utilisateur",inverseJoinColumns = @JoinColumn(name = "emploi_id"))
    @JsonView({VueUtilisateur.class})
    private Set<Emploi> emploisRecherche = new HashSet<>();
    @JsonView(VueUtilisateur.class)
    @CreationTimestamp
    private LocalDate createdAt;
    @JsonView(VueUtilisateur.class)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
