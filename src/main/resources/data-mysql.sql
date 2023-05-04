INSERT INTO `pays` (`id`, `nom`) VALUES
                                     (1, 'France'),
                                     (2, 'Allemagne'),
                                     (3, 'Espagne');
INSERT INTO `emploi` (`id`, `nom`) VALUES
                                       (1, 'Developpeur'),
                                       (2, 'Testeur'),
                                       (3, 'Chef de projet');
INSERT INTO `entreprise` (`id`, `nom`) VALUES
                                       (1, 'Amazon'),
                                       (2, 'Google'),
                                       (3, 'MacDo');
INSERT INTO `role` (`id`, `nom`) VALUES
                                     (1, 'ROLE_UTILISATEUR'),
                                     (2, 'ROLE_ADMINISTRATEUR');
INSERT into utilisateur (nom,prenom,pays_id, entreprise_id,email, mot_de_passe, role_id, created_at, updated_at) values ("Joyeux","Kévin",1,1,"la@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",1, UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Charlène",1,2,"li@a.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",1,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Yohan",1,3,"lu@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Daniel",3,3,"mlu@az.ld","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("De Souza","Christine",3,3,"lo@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Abby",2,1,"lt@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Popeye",1,1,"lj@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Pepette",2,2,"lm@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Charly",3,1,"lq@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ("Joyeux","Nenette",2,1,"lz@la.com","$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q",2,UTC_TIMESTAMP(),UTC_TIMESTAMP());
INSERT INTO recherche_emploi_utilisateur (`my_user_id`, `emploi_id`) VALUES (1,1), (1,2),(2,2);


