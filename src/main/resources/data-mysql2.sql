INSERT INTO `pays` (`id`, `nom`) VALUES
                                     (1, 'Canada'),
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
                                     (2, 'ROLE_ADMINISTRATEUR'),
                                     (3, 'ROLE_SUPER_ADMINISTRATEUR');
INSERT into utilisateur (nom,prenom,pays_id, entreprise_id,email, mot_de_passe, created_at, updated_at) values ('Joyeux','Kévin',1,1,'la@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Charlène',1,2,'li@a.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Yohan',1,3,'lu@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Daniel',3,3,'mlu@az.ld','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('De Souza','Christine',3,3,'lo@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Abby',2,1,'lt@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Popeye',1,1,'lj@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Pepette',2,2,'lm@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Charly',3,1,'lq@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP()),
                                                                                              ('Joyeux','Nenette',2,1,'lz@la.com','$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q',UTC_TIMESTAMP(),UTC_TIMESTAMP());
INSERT INTO role_utilisateur (my_user_id, role_id) values (1,2),
                                                             (2,2),
                                                             (3,1),
                                                             (4,1),
                                                             (5,2),
                                                             (6,3),
                                                             (7,2),
                                                             (8,1),
                                                             (9,2),
                                                             (8,1),
                                                             (10,3);
INSERT INTO recherche_emploi_utilisateur (`my_user_id`, `emploi_id`) VALUES (1,1), (1,2),(2,2);
INSERT INTO `contrat` (`id`, `date_de_creation`, `date_de_retour`) VALUES
                                                                       (1, '2023-05-09', '2023-05-11'),
                                                                       (2, '2023-05-10', NULL);

INSERT INTO `materiel` (`id`, `nom`, `numero`) VALUES
                                                   (1, 'Ecran 30\'', 123),
                                                   (2, 'Ecran 30\'', 456),
                                                   (3, 'Clavier', 789);

INSERT INTO `ligne_de_contrat` (`contrat_id`, `materiel_id`, `date_de_retour_anticipe`) VALUES
                                                                                            (1, 1, NULL),
                                                                                            (1, 3, '2023-05-10'),
                                                                                            (2, 2, NULL);



