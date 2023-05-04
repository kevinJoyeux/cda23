package edu.kjoyeux.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtUtils {
    public String generateJwt(MonUserDetails userDetails){
        Map<String, Object> donnees= new HashMap<>();
        donnees.put("prenom",userDetails.getMyUser().getPrenom());
        donnees.put("nom",userDetails.getMyUser().getNom());
        donnees.put("role",userDetails.getMyUser().getRole().getNom());
        return Jwts.builder()
                .setClaims(donnees)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256,"azerty")
                .compact();
    }
    public Claims getData(String jwt){
        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody();
    }
    public boolean isTokenValide(String jwt){
        try{
            Claims donnes = getData(jwt);
        }catch (SignatureException e){
            return false;
        }
        return true;
    }

}
