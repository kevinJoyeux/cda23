package edu.kjoyeux.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableWebSecurity
public class ConfigurationSecurite extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MonUserDetailsService monUserDetailsService;
    @Autowired
    JwtFilter filtre;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, mot_de_passe, 1 FROM UTILISATEUR WHERE EMAIL = ?")
                .authoritiesByUsernameQuery("SELECT EMAIL, IF(admin, 'ROLE_ADMINISTRATEUR', 'ROLE_UTILISATEUR')" + " FROM UTILISATEUR" + " WHERE EMAIL = ? ");*/
        auth.userDetailsService(monUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().configurationSource(httpServletRequest -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.applyPermitDefaultValues();
                    corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
                    corsConfiguration.setAllowedHeaders(
                            Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin"));
                    return corsConfiguration;
                })
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/connexion","/inscription","/utilisateur-par-pays/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMINISTRATEUR")
                .antMatchers("/**").hasAnyRole("ADMINISTRATEUR","UTILISATEUR")
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filtre, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder creationPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }

}
