package edu.kjoyeux.demo.security;

import edu.kjoyeux.demo.dao.UtilisateurDao;
import edu.kjoyeux.demo.model.MyUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MonUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        System.out.println(jwt);
        if(jwt !=null && jwt.startsWith("Bearer ")){
            String token = jwt.substring(7);

            System.out.println(token);
            if (jwtUtils.isTokenValide(token)){
                Claims donnes = jwtUtils.getData(token);
                // On recupere l'utilisateur dans la base de données en fonction de l'email du JWT
                UserDetails userDetails = userDetailsService.loadUserByUsername(donnes.getSubject());

                // On ajoute l'utilisateur au process d'identification de spring security
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                System.out.println(donnes.getSubject());
            }
        }
        filterChain.doFilter(request, response);
    }
}
