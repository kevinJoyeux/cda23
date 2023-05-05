-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 05 mai 2023 à 06:32
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spring_mns_23`
--

-- --------------------------------------------------------

--
-- Structure de la table `emploi`
--

DROP TABLE IF EXISTS `emploi`;
CREATE TABLE IF NOT EXISTS `emploi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `emploi`
--

INSERT INTO `emploi` (`id`, `nom`) VALUES
(1, 'Developpeur'),
(2, 'Testeur'),
(3, 'Chef de projet');

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`id`, `nom`) VALUES
(1, 'Amazon'),
(2, 'Google'),
(3, 'MacDo');

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

DROP TABLE IF EXISTS `pays`;
CREATE TABLE IF NOT EXISTS `pays` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`id`, `nom`) VALUES
(1, 'France'),
(2, 'Allemagne'),
(3, 'Espagne');

-- --------------------------------------------------------

--
-- Structure de la table `recherche_emploi_utilisateur`
--

DROP TABLE IF EXISTS `recherche_emploi_utilisateur`;
CREATE TABLE IF NOT EXISTS `recherche_emploi_utilisateur` (
  `my_user_id` int NOT NULL,
  `emploi_id` int NOT NULL,
  PRIMARY KEY (`my_user_id`,`emploi_id`),
  KEY `FKm97nfoc6ask1llg09q3mppqre` (`emploi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `recherche_emploi_utilisateur`
--

INSERT INTO `recherche_emploi_utilisateur` (`my_user_id`, `emploi_id`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `nom`) VALUES
(1, 'ROLE_UTILISATEUR'),
(2, 'ROLE_ADMINISTRATEUR');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mot_de_passe` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nom` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `nom_image_profil` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `entreprise_id` int DEFAULT NULL,
  `pays_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8fjtucbyo2t6agaejym2j764f` (`entreprise_id`),
  KEY `FKc1g8do1rrrp4bwytrl73elnou` (`pays_id`),
  KEY `FKaqe8xtajee4k0wlqrvh2pso4l` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `created_at`, `email`, `mot_de_passe`, `nom`, `nom_image_profil`, `prenom`, `updated_at`, `entreprise_id`, `pays_id`, `role_id`) VALUES
(1, '2023-05-03', 'la@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Kévin', '2023-05-03 14:34:48.000000', 1, 1, 1),
(2, '2023-05-03', 'li@a.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Charlène', '2023-05-03 14:34:48.000000', 2, 1, 1),
(3, '2023-05-03', 'lu@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Yohan', '2023-05-03 14:34:48.000000', 3, 1, 2),
(4, '2023-05-03', 'mlu@az.ld', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Daniel', '2023-05-03 14:34:48.000000', 3, 3, 2),
(5, '2023-05-03', 'lo@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'De Souza', NULL, 'Christine', '2023-05-03 14:34:48.000000', 3, 3, 2),
(6, '2023-05-03', 'lt@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Abby', '2023-05-03 14:34:48.000000', 1, 2, 2),
(7, '2023-05-03', 'lj@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Popeye', '2023-05-03 14:34:48.000000', 1, 1, 2),
(8, '2023-05-03', 'lm@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Pepette', '2023-05-03 14:34:48.000000', 2, 2, 2),
(9, '2023-05-03', 'lq@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Charly', '2023-05-03 14:34:48.000000', 1, 3, 2),
(10, '2023-05-03', 'lz@la.com', '$2a$10$wXW2wHA2bu1TdQ26p.2UoehWv8m92w88kabSeL.348VqkpWvSt51q', 'Joyeux', NULL, 'Nenette', '2023-05-03 14:34:48.000000', 1, 2, 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `recherche_emploi_utilisateur`
--
ALTER TABLE `recherche_emploi_utilisateur`
  ADD CONSTRAINT `FKh331q27gy1bjp9b7jc3cg0of1` FOREIGN KEY (`my_user_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKm97nfoc6ask1llg09q3mppqre` FOREIGN KEY (`emploi_id`) REFERENCES `emploi` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK8fjtucbyo2t6agaejym2j764f` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`),
  ADD CONSTRAINT `FKaqe8xtajee4k0wlqrvh2pso4l` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKc1g8do1rrrp4bwytrl73elnou` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
