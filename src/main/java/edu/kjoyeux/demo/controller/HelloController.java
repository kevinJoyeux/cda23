package edu.kjoyeux.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // prendre les infos
public class HelloController {
    @GetMapping("/")// prendre le chemin, recupere le fichier et l'execute
    public String hello(){
        return "le serveur marche sur les patates, mais y'a rien ici";// peut lire les balises html
    }
}
