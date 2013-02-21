-- phpMyAdmin SQL Dump
-- version 3.3.7deb7
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 15 Février 2013 à 17:03
-- Version du serveur: 5.1.66
-- Version de PHP: 5.3.3-7+squeeze14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `qvgdm`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartient_reponse`
--

CREATE TABLE IF NOT EXISTS `appartient_reponse` (
  `id_question` int(11) NOT NULL,
  `id_reponse` int(11) NOT NULL,
  `is_juste` tinyint(1) NOT NULL,
  KEY `id_question` (`id_question`),
  KEY `id_reponse` (`id_reponse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `appartient_reponse`
--


-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE IF NOT EXISTS `joueur` (
  `id_joueur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id_joueur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `joueur`
--


-- --------------------------------------------------------

--
-- Structure de la table `joueur_reponse`
--

CREATE TABLE IF NOT EXISTS `joueur_reponse` (
  `id_joueur_reponse` int(11) NOT NULL AUTO_INCREMENT,
  `id_joueur` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_reponse` int(11) NOT NULL,
  PRIMARY KEY (`id_joueur_reponse`),
  KEY `id_joueur` (`id_joueur`),
  KEY `id_question` (`id_question`),
  KEY `id_reponse` (`id_reponse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `joueur_reponse`
--


-- --------------------------------------------------------

--
-- Structure de la table `joueur_score`
--

CREATE TABLE IF NOT EXISTS `joueur_score` (
  `id_joueur` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `score_final` int(11) NOT NULL,
  PRIMARY KEY (`id_joueur`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `joueur_score`
--


-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` text NOT NULL,
  `niveau` int(11) NOT NULL,
  PRIMARY KEY (`id_question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `question`
--


-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE IF NOT EXISTS `reponse` (
  `id_reponse` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` text NOT NULL,
  PRIMARY KEY (`id_reponse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `reponse`
--


-- --------------------------------------------------------

--
-- Structure de la table `somme`
--

CREATE TABLE IF NOT EXISTS `somme` (
  `id_somme` int(11) NOT NULL AUTO_INCREMENT,
  `valeur` int(11) NOT NULL,
  PRIMARY KEY (`id_somme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `somme`
--


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appartient_reponse`
--
ALTER TABLE `appartient_reponse`
  ADD CONSTRAINT `appartient_reponse_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`),
  ADD CONSTRAINT `appartient_reponse_ibfk_2` FOREIGN KEY (`id_reponse`) REFERENCES `reponse` (`id_reponse`);

--
-- Contraintes pour la table `joueur_reponse`
--
ALTER TABLE `joueur_reponse`
  ADD CONSTRAINT `joueur_reponse_ibfk_6` FOREIGN KEY (`id_reponse`) REFERENCES `reponse` (`id_reponse`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joueur_reponse_ibfk_4` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id_joueur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joueur_reponse_ibfk_5` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `joueur_score`
--
ALTER TABLE `joueur_score`
  ADD CONSTRAINT `joueur_score_ibfk_1` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id_joueur`) ON DELETE CASCADE ON UPDATE CASCADE;
