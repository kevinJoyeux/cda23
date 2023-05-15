package edu.kjoyeux.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@IdClass(LigneDeContrat.IdLigneDeContrat.class)
public class LigneDeContrat {
    @Id
    private int materielId;

    @Id
    private int contratId;

    private LocalDate dateDeRetourAnticipe;
    @ManyToOne
    @MapsId("materiel_id")
    @JoinColumn(name = "materiel_id")
    Materiel materiel;
    @ManyToOne
    @MapsId("contrat_id")
    @JoinColumn(name = "contrat_id")
    Contrat contrat;
    @Embeddable
    @Getter
    @Setter
    public static class IdLigneDeContrat implements Serializable {
        @Column(name = "materiel_id")
        private int materielId;

        @Column(name = "contrat_id")
        private int contratId;
    }
}
