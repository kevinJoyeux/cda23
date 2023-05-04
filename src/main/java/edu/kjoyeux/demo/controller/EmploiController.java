package edu.kjoyeux.demo.controller;

import edu.kjoyeux.demo.dao.EmploiDao;
import edu.kjoyeux.demo.model.Emploi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EmploiController {
    @Autowired
    private EmploiDao emploiDao;
    @GetMapping("/liste-emploi")
    public List<Emploi> getEmploi(){
        return emploiDao.findAll();
    }
    @GetMapping("/emploi/{id}")
    public ResponseEntity<Emploi> getEmploiKevin(@PathVariable int id){
      Optional<Emploi> optional= emploiDao.findById(id);
      if (optional.isPresent()){
          return new ResponseEntity<Emploi>(optional.get(),HttpStatus.OK);
      }else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/emploi")
    public ResponseEntity<Emploi> ajoutEmploi(@RequestBody Emploi nouvelEmploi){
        //si l'emploi possede un id
        if(nouvelEmploi.getId()!=null){
            Optional<Emploi> optional = emploiDao.findById(nouvelEmploi.getId());
            //si c'est un update
            if (optional.isPresent()){
                emploiDao.save(nouvelEmploi);
                return new ResponseEntity<>(nouvelEmploi,HttpStatus.OK);
            }
            //si il y a eu une tentative d'insertion d'un emploi avec un id qui n'existait pas
            return new ResponseEntity<>(nouvelEmploi,HttpStatus.BAD_REQUEST);
        }
        emploiDao.save(nouvelEmploi);
        return new ResponseEntity<>(nouvelEmploi,HttpStatus.CREATED);
    }
    @DeleteMapping("/emploi/{id}")
    public ResponseEntity<Emploi> supprimeEmploi(@PathVariable int id){
        Optional<Emploi> emploiAsupprimer = emploiDao.findById(id);
        if(emploiAsupprimer.isPresent()){
            emploiDao.deleteById(id);
            return new ResponseEntity<>(emploiAsupprimer.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
