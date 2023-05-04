package edu.kjoyeux.demo.security;

import edu.kjoyeux.demo.dao.UtilisateurDao;
import edu.kjoyeux.demo.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> optional = utilisateurDao.findByEmail(email);
        if (optional.isEmpty()){
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }
        return new MonUserDetails(optional.get());
    }
    @Autowired
    private UtilisateurDao utilisateurDao;
}
