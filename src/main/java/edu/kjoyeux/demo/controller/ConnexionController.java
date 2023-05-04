package edu.kjoyeux.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.kjoyeux.demo.dao.UtilisateurDao;
import edu.kjoyeux.demo.model.MyUser;
import edu.kjoyeux.demo.security.JwtUtils;
import edu.kjoyeux.demo.security.MonUserDetails;
import edu.kjoyeux.demo.security.MonUserDetailsService;
import edu.kjoyeux.demo.view.VueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class ConnexionController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UtilisateurDao utilisateurDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MonUserDetailsService userDetailsService;
    @PostMapping("/connexion")
    public ResponseEntity<String> connexion(@RequestBody MyUser myUser){
        MonUserDetails userDetails ;
        try {
            userDetails = (MonUserDetails) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            myUser.getEmail(),
                            myUser.getMotDePasse()
                    )
            ).getPrincipal();
            System.out.println(myUser.toString());
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        MonUserDetails monUserDetails =(MonUserDetails)userDetailsService.loadUserByUsername(myUser.getEmail());
        return new ResponseEntity<>(jwtUtils.generateJwt(monUserDetails),HttpStatus.OK);
    }
    @PostMapping("/inscription")
    @JsonView(VueUtilisateur.class)
    public ResponseEntity<MyUser> inscription (@RequestBody MyUser myUser){
        if(myUser.getId()!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(myUser.getMotDePasse().length()<=3){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(myUser.getEmail()).matches()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<MyUser> optionnal = utilisateurDao.findByEmail(myUser.getEmail());
        if(optionnal.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String passwordHache = passwordEncoder.encode(myUser.getMotDePasse());
        myUser.setMotDePasse(passwordHache);

        utilisateurDao.save(myUser);

        return new ResponseEntity<>(myUser,HttpStatus.CREATED);
    }
}
