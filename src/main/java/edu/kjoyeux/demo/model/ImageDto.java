package edu.kjoyeux.demo.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class ImageDto implements Serializable {
    public ImageDto(){

    }
    public ImageDto(int idUtilisateur, String imageProfil) {
        this.idUtilisateur = idUtilisateur;
        this.imageProfil=imageProfil;
    }

    private int idUtilisateur;
    private String imageProfil;
}
