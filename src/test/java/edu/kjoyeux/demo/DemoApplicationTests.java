package edu.kjoyeux.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import edu.kjoyeux.demo.model.MyUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	@Test
	void creationUtilisateur_idUtilisateurNull() throws Exception{
		MyUser myUser = new MyUser();
		assertNull(myUser.getId());
	}
	@Test
	@WithMockUser(roles = {"UTILISATEUR"})
	void utilisateurNonConnecteAppelUrlListeUtilisateur_Okattendu() throws Exception {
		mvc.perform(get("/utilisateurs")).andExpect(status().isOk());
	}
	@Test
	@WithMockUser(roles = {"ADMINISTRATEUR"})
	void administrateurNonConnecteAppelUrlListeUtilisateur_Okattendu() throws Exception {
		mvc.perform(get("/utilisateurs")).andExpect(status().isOk());
	}

}
