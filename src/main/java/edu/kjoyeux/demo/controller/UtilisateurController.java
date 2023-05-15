package edu.kjoyeux.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.kjoyeux.demo.dao.UtilisateurDao;
import edu.kjoyeux.demo.model.MyUser;
import edu.kjoyeux.demo.model.Role;
import edu.kjoyeux.demo.security.JwtUtils;
import edu.kjoyeux.demo.services.FichierService;
import edu.kjoyeux.demo.view.VueUtilisateur;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public class UtilisateurController {
    @Autowired
    private UtilisateurDao utilisateurDao;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    FichierService fichierService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/utilisateurs")
    @JsonView(VueUtilisateur.class)
    public List<MyUser> getUtilisateurs() {
        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{id}")
    @JsonView({VueUtilisateur.class})
    public ResponseEntity<MyUser> getUtilisateurKevin(@PathVariable int id) {
        //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        Optional<MyUser> optional = utilisateurDao.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<MyUser>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@GetMapping("/image-profil/{idUtilisateur}")
public ResponseEntity<byte[]> getImageProfil(@PathVariable int idUtilisateur) {
    Optional<MyUser> optional = utilisateurDao.findById(idUtilisateur);
    if (optional.isPresent()) {
        String nomImage = optional.get().getNomImageProfil();
        try {
            byte[] image = fichierService.getImageByName(nomImage);
            HttpHeaders enTete = new HttpHeaders();
            String mimeType = Files.probeContentType(new File(nomImage).toPath());
            enTete.setContentType(MediaType.valueOf(mimeType));
            return new ResponseEntity<>(image,enTete,HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IOException e){
            System.out.println("Le test du mimetype a echoué");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
    @PostMapping("/admin/utilisateur")
    @JsonView({VueUtilisateur.class})
    public ResponseEntity<MyUser> ajoutUtilisateur(@RequestPart("utilisateur")MyUser nouvelMyUser, @Nullable @RequestParam("fichier")MultipartFile fichier){

        //si l'utilisateur possede un id
        if (nouvelMyUser.getId() != null) {
            Optional<MyUser> optional = utilisateurDao.findById(nouvelMyUser.getId());
            //si c'est un update
            if (optional.isPresent()) {
                MyUser myUserToUpdate = optional.get();
                myUserToUpdate.setNom(nouvelMyUser.getNom());
                myUserToUpdate.setPrenom(nouvelMyUser.getPrenom());
                myUserToUpdate.setEmail(nouvelMyUser.getEmail());
                myUserToUpdate.setPays(nouvelMyUser.getPays());
                if(fichier!=null){
                    try {
                        fichierService.transfertVersDossierUpload(fichier,"image_profil");
                    } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                utilisateurDao.save(myUserToUpdate);
                return new ResponseEntity<>(nouvelMyUser, HttpStatus.OK);
            }
            //si il y a eu une tentative d'insertion d'un utilisateur avec un id qui n'existait pas
            return new ResponseEntity<>(nouvelMyUser, HttpStatus.BAD_REQUEST);
        }
        Role role = new Role();
        role.setId(1);
        nouvelMyUser.getRoles().add(role);
        String passwordHache = passwordEncoder.encode("root");
        nouvelMyUser.setMotDePasse(passwordHache);

        if(fichier!=null){
            try {
                String nomImage = UUID.randomUUID()+ "_" + fichier.getOriginalFilename();
                nouvelMyUser.setNomImageProfil(nomImage);
                fichierService.transfertVersDossierUpload(fichier,nomImage);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        utilisateurDao.save(nouvelMyUser);
        return new ResponseEntity<>(nouvelMyUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    public ResponseEntity<MyUser> supprimeUtilisateur(@PathVariable int id) {
        Optional<MyUser> utilisateurAsupprimer = utilisateurDao.findById(id);
        if (utilisateurAsupprimer.isPresent()) {
            utilisateurDao.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profil")
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<MyUser> getProfil(@RequestHeader("Authorization") String bearer) {
        String jwt = bearer.substring(7);
        Claims donnees = jwtUtils.getData(jwt);
        Optional<MyUser> myUser = utilisateurDao.findByEmail(donnees.getSubject());
        if (myUser.isPresent()) {
            return new ResponseEntity<>(myUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/utilisateur-par-pays/{nomPays}")
    @JsonView(VueUtilisateur.class)
    public List<MyUser> getUtilisateur(@PathVariable String nomPays) {
        //return utilisateurDao.testbidon();
        return utilisateurDao.trouveUtilisateurSelonPays(nomPays);
    }

}
