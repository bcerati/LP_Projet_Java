-- phpMyAdmin SQL Dump
-- version 3.3.7deb7
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Dim 10 Mars 2013 à 22:18
-- Version du serveur: 5.1.66
-- Version de PHP: 5.4.12-1~dotdeb.1

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
  PRIMARY KEY (`id_question`),
  KEY `id_question` (`id_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=182 ;

--
-- Contenu de la table `question`
--

INSERT INTO `question` (`id_question`, `intitule`, `niveau`) VALUES
(1, 'Dans quelle ville Napoléon 1er est-il né ?', 2),
(2, 'Chez un homme normalement constitué qu''est-ce qui pousse le moins vite ?', 2),
(3, 'Le chromosome est :', 2),
(4, 'Du haut à la base de la colonne, les vertèbres sont disposées :', 2),
(5, 'Le nerf sciatique innerve :', 2),
(6, 'Vers 3 ans, l''enfant possède normalement :', 2),
(7, 'Les glandes salivaires sont au nombre de :', 2),
(8, 'Quel est le nom de le Première dame de France ? Valérie...', 2),
(9, 'Quel autre nom donne-t-on aux globules blancs ?', 2),
(10, 'Le saturnisme est une maladie due à une intoxication au :', 2),
(11, 'Avec quel instrument peut-on ouvrir une porte ?', 2),
(12, 'Quel mot désigne l''élevage des oiseaux ?', 2),
(13, 'C''est en 1984 que Patrick Bruel se fait connaître du grand public avec...', 2),
(14, 'Dans l''armée Française, les noms Puma, Gazelle et Alouette font référence à...', 2),
(15, 'De quelle couleur est la peau des Simpsons ?', 1),
(16, 'En 1936, les Jeux Olympiques se sont déroulés dans quelle ville ?', 2),
(17, 'Dans quel domaine professionnel les "nez" exercent-ils ?', 2),
(18, 'Le raccommodage à l''aiguille d''une étoffe déchirée est...', 2),
(19, 'Quand on savoure "le jus de la treille", on boit...', 2),
(20, 'Qu''est-ce qu''un "va-nu-pieds" ?', 2),
(21, 'Dans une série télévisée des années 60, Max la Menace était un piètre...', 2),
(22, 'Les petits chocolats ronds fourrés que l''on déguste à Noël sont des...', 2),
(23, 'Quelle est la capitale de la Corée du Nord ?', 2),
(24, 'Le creux poplité se trouve...', 2),
(25, 'Quel est le palindrome de 9522 ?', 2),
(26, 'Combien d''épreuves se compose un décathlon ?', 2),
(27, 'Sur quel support "La Joconde" a t-elle été peinte ?', 2),
(28, 'Dans quelle grande ville française peut-on flâner sur la Promenade des Anglais ?', 2),
(29, 'En 2002, quel chanteur français interprète le tube : "Manhattan-Kaboul", en duo avec Axelle Red ?', 2),
(30, 'Un requiem est une musique composée pour...', 2),
(31, 'Dans l''échelle des degrés Fahrenheit, à quelle température le zéro Celsius correspond-il ?', 2),
(32, 'Combien de temps a duré la Guerre de 100 ans ?', 2),
(33, 'En février 2009, le film "Slumdog Millionnaire" reçoit à Hollywood :', 2),
(34, 'Alain Bernard est le 1er nageur français à avoir décroché une médaille d''or olympique :', 2),
(35, 'L''adjectif "éburnéen" qualifie quelque chose qui à l''aspect...', 2),
(36, 'Un dirigeant est un ploutocrate si il est...', 2),
(37, 'Si je dis : "Tout humain est mortel, je suis un humain. Donc je suis mortel." J''ai fait...', 2),
(38, 'Quelle base azotée n''est pas une base de l''ADN ?', 2),
(39, 'L''adjectif "callipyge" qualifie une femme qui a...', 2),
(40, 'Quel est le titre du premier succès de Claude François ?', 2),
(41, 'Laquelle de ces races n''est pas celle d''un chat ?', 2),
(42, 'Qui ne prête pas serment pour exercer sa fonction ?', 2),
(43, 'Au XIX siècle, on employait le terme "Joconde" pour désigner...', 2),
(44, 'Si je fais un "birdy", quel sport suis-je en train de pratiquer ?', 2),
(45, 'Quelle femme célèbre n''a pas donné son nom à une rose ?', 2),
(46, 'Lequel de ces continents est le moins vaste ?', 2),
(47, 'Continuez cette célèbre chanson de Dalida : "Gigi l''amoroso, toujours vainqueur...', 2),
(48, 'Qui est la première femme à avoir été élue présidente du Costa Rica en février 2010 ?', 2),
(49, 'Quelle voiture a été lancée par une campagne de pub la comparant à une poire ?', 2),
(51, 'L''oniromancie est un procédé de divination par...', 2),
(52, 'Continuez ce succès de Sylvie Vartan : "L''amour c''est comme une cigarette, ça brûle et...', 2),
(53, 'Quel est le nom de la chèvre de M. Seguin ?', 2),
(54, 'Quel artiste a sorti en 2007 l''album "Pomme C" ?', 2),
(55, 'Quelle série TV raconte les vies de Chandler, Ross et Rachel ?', 2),
(56, 'Les bêtises de Cambrai son parfumées à la...', 2),
(57, 'Boucaner de la viande c''est la...', 2),
(58, 'Lequel de ces mots masculins au singulier peut devenir féminin au pluriel ?', 2),
(59, 'Que manque-t-il à la célèbre statue la "Vénus de Milo" ?', 2),
(60, 'Qui a créé la Légion d''honneur ?', 2),
(61, 'Quelle est la différence de variation de la distance entre la Lune et la Terre ?', 3),
(62, 'En quelle année est mort Clyde Tombaugh, le découvreur de la planète Pluton ?', 3),
(63, 'Quelle est la valeur exacte de la célérité de la lumière dans le vide ?', 3),
(64, 'Combien vaut l''inclinaison de l''axe de rotation de Jupiter ?', 3),
(65, 'Autour de quelle géante gazeuse orbite le satellite Larissa ?', 3),
(66, 'Combien mesurait Youri Gagarine, le premier homme placé en orbite autour de la Terre ?', 3),
(67, 'Dans quelle constellation se situe la célèbre nébuleuse de la Tête de Cheval ?', 3),
(68, 'Combien vaut l''albédo de la planète Terre ?', 3),
(69, 'Lequel de ces personnages n''apparaît pas dans le livre Dialogue sur les deux grands systèmes du monde de Galilée ?', 3),
(70, 'Quelle est le rayon de Titania, satellite d''Uranus ?', 3),
(71, 'Laquelle de ces sondes spatiales n''a pas étudié la planète Vénus ?', 3),
(72, 'Qu''est-ce qu''un ''zulte''', 3),
(73, 'Comment qualifie-t-on un arbre qui n''a plus de feuilles, en héraldique ?', 3),
(74, '"Effarouché" est une position spécifique...', 3),
(75, 'Que désigne l''adjectif ''pâmé'' ?', 3),
(76, 'Une croix ne peut pas être...', 3),
(77, 'Un poisson dont la queue est d''un émail particulier est...', 3),
(78, 'Le terme ''éployé'' peut désigner une aigle...', 3),
(79, 'Le terme ''coiffé'' s''applique uniquement...', 3),
(80, 'Comment qualifie-t-on un oiseau de fauconnerie dont les grelots sont d''un émail particulier ?', 3),
(81, 'Comment qualifie-t-on une grappe de raisin dont la tige est d''un émail particulier ?', 3),
(82, 'Le ''senois'' est un émail rare. Quelle nuance de couleur représente-t-il ?', 3),
(83, 'Parmi ces émaux rares, lequel n''existe pas ?', 3),
(84, 'Qui est le père adoptif de Caligula ?', 3),
(85, 'Que fit venir Caligula de Grèce pour montrer sa grandeur ?', 3),
(86, 'Comment est tuée la fille de Caligula ?', 3),
(87, 'Combien d''années régna Caligula ?', 3),
(88, 'À quel âge Caligula meurt-il ?', 3),
(89, 'Où le corps de Caligula fut-il porté ?', 3),
(90, 'Que collectionne le numismate ?', 3),
(91, 'Quel sport américain, qui se joue sur un terrain, comprend le plus de règles ?', 3),
(92, 'Qu''a inventé le belge Joseph Merlin en 1760 ?', 3),
(93, 'De quel pays nous vient le jeu d''échecs ?', 3),
(94, 'Combien de retraits y a-t-il pendant la première manche d''une partie de baseball ?', 3),
(95, 'Avec combien de pions joue-t-on au parchesi ?', 3),
(96, 'Qu''est-ce que le Fan-Tan ?', 3),
(97, 'Quel québécois, né en 1863, fut célèbre pour sa grande force ?', 3),
(98, 'Combien de carrés de valeur double y -a-t-il au Scrabble ?', 3),
(99, 'La tête fémorale s''articule avec', 3),
(100, 'Quelle doit être la stabilité au feu des éléments de la structure de l''immeuble  ?', 3),
(101, 'Quelle doit être la distance maximale entre l''IGH et un centre principal de secours  ?', 3),
(102, 'La surface qui constitue la limite latérale du volume de protection de l''IGH est de :', 3),
(103, 'Laquelle de ces glandes sécrète l''adrénaline ?', 3),
(104, 'En septembre 2001, quel fabricant d''ordinateurs fusionna avec son concurrent Compaq ?', 3),
(105, 'Combien de cycles par seconde équivaut à un mégahertz ?', 3),
(106, 'Dans quel domaine associe t-on l''Allemand Johannes Gensfleisch ?', 3),
(107, 'Que craint l''androphobe ?', 3),
(108, 'De quel aliment provient le tofu ?', 3),
(109, 'Quelle est la fraction de gravité terrestre qu''a la lune ?', 3),
(110, 'Quel quotient intellectuel est le seuil du génie ?', 3),
(111, 'Quel sens est le plus en danger si vous êtes atteint par la foudre ?', 3),
(112, 'Combien de moteurs possède un Boeing 737 ?', 3),
(113, 'Quel pays entre dans l''Union Européenne en 2013 ?', 3),
(114, 'Quelle est la capitale du Monténégro ?', 3),
(115, 'Igoumenitsa est une ville qui se trouve :', 3),
(116, 'Dans quelle ville se trouve le siège de l''U. E. F. A ?', 3),
(117, 'Dans quel pays l''euro n''est-il pas la monnaie nationale ?', 3),
(118, 'Quel pays n''a qu''un seul voisin ?', 3),
(119, 'Quel pays fait partie de l''Union latine ?', 3),
(120, 'Riga est la capitale de :', 3),
(121, 'Qui a adhéré à l''Union européenne en premier lieu ?', 3),
(122, 'La Nouvelle Star est une émission où les candidats doivent  ... ?', 1),
(123, 'De quelle couleur est la vache Milka ?', 1),
(124, 'Compléter la chanson du film Les Bronzés, "Y a du soleil et .... ?', 1),
(125, 'Quel navire a sombré après avoir heurté un iceberg  ?', 1),
(126, 'Quelle est la capitale de la France', 1),
(127, 'Qui est Laure Manaudou ?', 1),
(128, 'Qui a inventé le vaccin contre la rage ?', 1),
(129, 'Dans la série Kaamelott, Arthur est roi de ... ?', 1),
(130, 'Qu’est-ce qu’un silure ?', 1),
(131, 'Qui a écrit le livre "Les Misérables" ?', 1),
(132, 'Où se déroule la saga Star Wars ?', 1),
(133, 'Qui vit sur l’astéroïde B612 ?', 1),
(134, 'Dans Le Livre de la jungle, quel est le nom de l’ours ?', 1),
(135, 'L''eau bout à ... ?', 1),
(136, 'Laquelle de ces marques automobiles est française ?', 1),
(137, 'Quel est l''âge supposé de l''Univers ?', 1),
(138, 'En bureautique, un traitement de texte sert à ... ?', 1),
(139, 'Qui a mis le premier le pied sur la Lune ?', 1),
(140, 'De quelle couleur est l’indigo ?', 1),
(141, 'Quel dessinateur a inventé le personnage d’Astérix ?', 1),
(142, 'Quel héros de la mythologie aimait se regarder  ?', 1),
(143, 'Qui était Picasso ?', 1),
(144, 'Quelle est la licence nécessaire pour pouvoir vendre de l''alcool en France ?', 1),
(145, 'Compléter le dicton : « Jamais deux sans ... ', 1),
(146, 'A quel sport jouait Zinedine Zidane ?', 1),
(147, 'Qui transmet la maladie du sommeil ?', 1),
(148, 'Dans la saga Pokemon, comment s''appelle le pokemon qui suit le héro ?', 1),
(149, 'Dans la série Friends, comment s''appelle le frère de Monica ?', 1),
(150, 'Sur la langue, se trouvent :', 1),
(151, 'Qu’a perdu un amnésique ?', 1),
(152, 'Quel est l''âge de la majorité en France ?', 1),
(153, 'En quelle année a eu lieu la Révolution Française ?', 1),
(154, 'Que signifie U.S.A ?', 1),
(155, 'Qui est à l''origine de la série Kaamelott ?', 1),
(156, 'Combien y a t-il de seconde dans une heure ?', 1),
(157, 'Qu''est ce qui a quatre pates et qui trompe énormément ?', 1),
(158, 'Que célèbre t-on le 14 juillet ?', 1),
(159, 'Quelle est la durée du mandat du président de la république en France ?', 1),
(160, 'Qui était Jean-Paul II ?', 1),
(161, 'Pour obtenir la couleur "vert", quelle couleur mélange t-on avec le jaune ?', 1),
(162, 'Quelle entreprise fabrique l''iPhone ?', 1),
(163, 'Quelle course n''est pas une course automobile ?', 1),
(164, 'La clarinette est un instrument ... ?', 1),
(165, 'Dans le domaine bancaire, le livret A permet ... ?', 1),
(166, 'Quel est le nom officiel du terrain de tennis ?', 1),
(167, 'Parmis ces acteurs, lequel n''est pas français ?', 1),
(168, 'Qui a gagné la coupe du monde de football en 1998 ?', 1),
(169, 'Dans les bd d''Asterix et Obelix, comment s''appelle le chien des deux héros ?', 1),
(170, 'Quel est le pays d''origine des bergers allemands ?', 1),
(171, 'Lequel de ces films ne traite pas de la Seconde Guerre Mondiale ?', 1),
(172, 'Compléter le titre de la chanson de Johnny Halliday : "Allumer  ... ?', 1),
(173, 'Quel est la race de Bill, le chien de Boule ?', 1),
(174, 'Que tient la statue de la Liberté dans sa main droite ?', 1),
(175, 'Que fait la mante religieuse après l''accouplement ?', 1),
(176, 'Quel groupe réunissait Filip Nikolic, Frank Delhaye et Adel Kachermi ?', 1),
(177, 'Qu''est-ce qui est "un long fleuve tranquille" pour Etienne Chatiliez ?', 1),
(178, 'Au rugby, les passes se font toujours ... ?', 1),
(179, 'Aux Etats-Unis, quelle est la ville du jeu ?', 1),
(180, 'Dans lequel de ces films l''acteur Bruce Willis n''a pas joué ?', 1),
(181, 'Qui vit dans "la Petite Maison dans la prairie" ?', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE IF NOT EXISTS `reponse` (
  `id_reponse` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` text NOT NULL,
  `id_question` int(11) NOT NULL,
  `is_juste` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_reponse`),
  KEY `id_question` (`id_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=725 ;

--
-- Contenu de la table `reponse`
--

INSERT INTO `reponse` (`id_reponse`, `intitule`, `id_question`, `is_juste`) VALUES
(1, 'Ajaccio', 1, 1),
(2, 'Paris', 1, 0),
(3, 'Nice', 1, 0),
(4, 'Marseille', 1, 0),
(5, 'Les cheveux', 2, 0),
(6, 'Les ongles des mains', 2, 0),
(7, 'Les ongles des pieds', 2, 1),
(8, 'La barbe', 2, 0),
(9, 'La molécule constitutive de l''ADN', 3, 0),
(10, 'Le support physique du programme génétique', 3, 1),
(11, 'Contenu dans le noyau des cellules sexuelles', 3, 0),
(12, 'Contenu dans la moelle osseuse', 3, 0),
(13, 'Vertèbres cervicales, lombaires, dorsales, sacrées et coccygiennes', 4, 0),
(14, 'Vertèbres cervicales, lombaires, sacrées, dorsales et coccygiennes', 4, 0),
(15, 'Vertèbres cervicales, dorsales, lombaires, sacrées et coccygiennes', 4, 1),
(16, 'Vertèbres cervicales, lombaires, sacrées, coccygiennes et dorsales', 4, 0),
(17, 'Le bassin', 5, 0),
(18, 'La jambe', 5, 1),
(19, 'Le dos', 5, 0),
(20, 'Le cou', 5, 0),
(21, '24 dents', 6, 0),
(22, '20 dents', 6, 1),
(23, '22 dents', 6, 0),
(24, '16 dents', 6, 0),
(25, '3 paires', 7, 1),
(26, '4 paires', 7, 0),
(27, '5 paires', 7, 0),
(28, '6 paires', 7, 0),
(29, 'Trierweiler', 8, 1),
(30, 'Trierweiller', 8, 0),
(31, 'Treierweiler', 8, 0),
(32, 'Treierweiller', 8, 0),
(33, 'Leucocytes', 9, 1),
(34, 'Plaquettes', 9, 0),
(35, 'Triglycérides', 9, 0),
(36, 'Hématies', 9, 0),
(37, 'Plomb', 10, 1),
(38, 'Fer', 10, 0),
(39, 'Cuivre', 10, 0),
(40, 'Aluminium', 10, 0),
(41, 'Un merle', 11, 0),
(42, 'Un rossignol', 11, 1),
(43, 'Un pigeon', 11, 0),
(44, 'Un pinson', 11, 0),
(45, 'L''ostréiculture', 12, 0),
(46, 'La cuniculture', 12, 0),
(47, 'L''apiculture', 12, 0),
(48, 'L''aviculture', 12, 1),
(49, 'Marre de cette nana-là', 13, 1),
(50, 'Qui à le droit ?', 13, 0),
(51, 'Casser la voix', 13, 0),
(52, 'Alors regarde', 13, 0),
(53, 'Des avions', 14, 0),
(54, 'Des tanks', 14, 0),
(55, 'Des bateaux', 14, 0),
(56, 'Des hélicoptères', 14, 1),
(57, 'Bleue', 15, 0),
(58, 'Jaune', 15, 1),
(59, 'Verte', 15, 0),
(60, 'Rouge', 15, 0),
(61, 'Mexico', 16, 0),
(62, 'Berlin', 16, 1),
(63, 'Helsinki', 16, 0),
(64, 'Londres', 16, 0),
(65, 'La maroquinerie', 17, 0),
(66, 'La parfumerie', 17, 1),
(67, 'L''oenologie', 17, 0),
(68, 'La cuisine', 17, 0),
(69, 'Une reprise', 18, 1),
(70, 'Un repiquage', 18, 0),
(71, 'Une réduction', 18, 0),
(72, 'Un revers', 18, 0),
(73, 'Du lait', 19, 0),
(74, 'Du cidre', 19, 0),
(75, 'De la bière', 19, 0),
(76, 'Du vin', 19, 1),
(77, 'Un nudiste', 20, 0),
(78, 'Un vagabond', 20, 1),
(79, 'Un coureur', 20, 0),
(80, 'Un chasseur', 20, 0),
(81, 'Professeur', 21, 0),
(82, 'Gendarme', 21, 0),
(83, 'Agent secret', 21, 1),
(84, 'Avocat', 21, 0),
(85, 'Calissons', 22, 0),
(86, 'Roudoudous', 22, 0),
(87, 'Bêtises', 22, 0),
(88, 'Crottes', 22, 1),
(89, 'Séoul', 23, 0),
(90, 'Hanoï', 23, 0),
(91, 'Pyongyang', 23, 1),
(92, 'Rangoon', 23, 0),
(93, 'Dans l''oreille', 24, 0),
(94, 'A l''arrière du genou', 24, 1),
(95, 'Dans les narines', 24, 0),
(96, 'Dans le pied', 24, 0),
(97, '5229', 25, 0),
(98, '9225', 25, 0),
(99, '5922', 25, 0),
(100, '2259', 25, 1),
(101, 'Neuf', 26, 0),
(102, 'Dix', 26, 1),
(103, 'Onze', 26, 0),
(104, 'Douze', 26, 0),
(105, 'Sur bois', 27, 1),
(106, 'Sur toile', 27, 0),
(107, 'Sur papier', 27, 0),
(108, 'Sur tissu', 27, 0),
(109, 'Cannes', 28, 0),
(110, 'Toulon', 28, 0),
(111, 'Nice', 28, 1),
(112, 'Marseille', 28, 0),
(113, 'Marc Lavoine', 29, 0),
(114, 'Garou', 29, 0),
(115, 'Jean-Jacques Goldman', 29, 0),
(116, 'Renaud', 29, 1),
(117, 'Célébrer des fiançailles', 30, 0),
(118, 'Un enterrement', 30, 1),
(119, 'Célébrer une naissance', 30, 0),
(120, 'Une messe de mariage', 30, 0),
(121, '72°F', 31, 0),
(122, '16°F', 31, 0),
(123, '32°F', 31, 1),
(124, '54°F', 31, 0),
(125, '99 ans', 32, 0),
(126, '100 ans', 32, 0),
(127, '105 ans', 32, 0),
(128, '116 ans', 32, 1),
(129, '1 oscar', 33, 0),
(130, '3 oscars', 33, 0),
(131, '8 oscars', 33, 1),
(132, '13 oscars', 33, 0),
(133, 'Au 50 mètres nage libre', 34, 0),
(134, 'Au 100 mètres nage libre', 34, 1),
(135, 'Au 200 mètres nage libre', 34, 0),
(136, 'Au 4x100 mètres nage libre', 34, 0),
(137, 'Du beurre', 35, 0),
(138, 'D''un burin', 35, 0),
(139, 'De l''ivoire', 35, 1),
(140, 'De la fumée', 35, 0),
(141, 'Insignifiant', 36, 0),
(142, 'Riche', 36, 1),
(143, 'Intelligent', 36, 0),
(144, 'Naïf', 36, 0),
(145, 'Un syllogisme', 37, 1),
(146, 'Une périphrase', 37, 0),
(147, 'Une litote', 37, 0),
(148, 'Un pléonasme', 37, 0),
(149, 'Guanine', 38, 0),
(150, 'Adénine', 38, 0),
(151, 'Rubéine', 38, 1),
(152, 'Thymine', 38, 0),
(153, 'Une opulente poitrine', 39, 0),
(154, 'De beaux cheveux', 39, 0),
(155, 'Une grande taille', 39, 0),
(156, 'De belles fesses', 39, 1),
(157, 'Belles, belles, belles', 40, 1),
(158, 'Ces soirées-là', 40, 0),
(159, 'Alexandrie, Alexandra', 40, 0),
(160, 'Daydreamer', 40, 0),
(161, 'Abyssin', 41, 0),
(162, 'Léonberg', 41, 1),
(163, 'Sibérien', 41, 0),
(164, 'Chartreux', 41, 0),
(165, 'L''avocat', 42, 0),
(166, 'Le notaire', 42, 0),
(167, 'Le député', 42, 1),
(168, 'Le pharmacien', 42, 0),
(169, 'Une veuve', 43, 0),
(170, 'Un grand séducteur', 43, 1),
(171, 'Un enfant gracieux', 43, 0),
(172, 'Une orpheline', 43, 0),
(173, 'Du golf', 44, 1),
(174, 'Du basket', 44, 0),
(175, 'Du cyclisme', 44, 0),
(176, 'Du volley-ball', 44, 0),
(177, 'Catherine Deneuve', 45, 0),
(178, 'Stéphanie de Monaco', 45, 0),
(179, 'Gina Lollobrigida', 45, 0),
(180, 'Sophie Marceau', 45, 1),
(181, 'L''Antarctique', 46, 0),
(182, 'L''Europe', 46, 1),
(183, 'L''Amérique', 46, 0),
(184, 'L''Asie', 46, 0),
(185, '...parfois sans coeur"', 47, 1),
(186, '...toujours à l''heure"', 47, 0),
(187, '...jamais boudeur"', 47, 0),
(188, '...souvent rieur"', 47, 0),
(189, 'Laura Alpaga', 48, 0),
(190, 'Laura Chinchilla', 48, 1),
(191, 'Laura Chihuahua', 48, 0),
(192, 'Laura Anaconda', 48, 0),
(193, 'La R5', 49, 0),
(194, 'La 404', 49, 0),
(195, 'La DS', 49, 0),
(196, 'La R14', 49, 1),
(201, 'Les lignes du pied', 51, 0),
(202, 'Les pierres', 51, 0),
(203, 'L''iris de l''oeil', 51, 0),
(204, 'Les rêves', 51, 1),
(205, '...ça monte à la tête"', 52, 1),
(206, '...ça vous rend distraite"', 52, 0),
(207, '...joue les trouble-fêtes"', 52, 0),
(208, '...puis ça vous embête"', 52, 0),
(209, 'Brochette', 53, 0),
(210, 'Andouillette', 53, 0),
(211, 'Paupiette', 53, 0),
(212, 'Blanquette', 53, 1),
(213, 'Zazie', 54, 0),
(214, 'Jenifer', 54, 0),
(215, 'Calogero', 54, 1),
(216, 'Jean-Michel Jarre', 54, 0),
(217, 'Melrose Place', 55, 0),
(218, 'Friends', 55, 1),
(219, 'Sex and the City', 55, 0),
(220, 'Cold Case', 55, 0),
(221, 'Menthe', 56, 1),
(222, 'Bergamote', 56, 0),
(223, 'Fleur d''oranger', 56, 0),
(224, 'Rose', 56, 0),
(225, 'Dépecer', 57, 0),
(226, 'Fumer', 57, 1),
(227, 'Dévorer à pleines dents', 57, 0),
(228, 'Laisser faisander', 57, 0),
(229, 'Emblème', 58, 0),
(230, 'Délice', 58, 1),
(231, 'Augure', 58, 0),
(232, 'Luxe', 58, 0),
(233, 'Le nez', 59, 0),
(234, 'Les seins', 59, 0),
(235, 'La tête', 59, 0),
(236, 'Les bras', 59, 1),
(237, 'Napoléon Bonaparte', 60, 1),
(238, 'Louis IX', 60, 0),
(239, 'Charlemagne', 60, 0),
(240, 'François 1er', 60, 0),
(241, '13 millions de km', 61, 0),
(242, '108 millions de km', 61, 0),
(243, '247 millions de km', 61, 0),
(244, '384 millions de km', 61, 1),
(245, 'En 1937', 62, 0),
(246, 'En 1947', 62, 0),
(247, 'En 1987', 62, 0),
(248, 'En 1997', 62, 1),
(249, '301.698.422 m/s', 63, 0),
(250, '300.000.000 m/s', 63, 0),
(251, '299.792.458 m/s', 63, 1),
(252, '299.104.728 m/s', 63, 0),
(253, '1,4°', 64, 0),
(254, '3,1°', 64, 1),
(255, '5,7°', 64, 0),
(256, '7,2', 64, 0),
(257, 'Jupiter', 65, 0),
(258, 'Saturne', 65, 0),
(259, 'Uranus', 65, 0),
(260, 'Neptune', 65, 1),
(261, '1m69', 66, 1),
(262, '1m74', 66, 0),
(263, '1m79', 66, 0),
(264, '1m84', 66, 0),
(265, 'Le Sagittaire', 67, 0),
(266, 'Pégase', 67, 0),
(267, 'Le Scorpion', 67, 0),
(268, 'Orion', 67, 1),
(269, 'Environ 0,28', 68, 0),
(270, 'Environ 0,37', 68, 1),
(271, 'Environ 0,43', 68, 0),
(272, 'Environ 0,55', 68, 0),
(273, 'Salviati', 69, 0),
(274, 'Silviani', 69, 1),
(275, 'Simplicio', 69, 0),
(276, 'Sagredo', 69, 0),
(277, 'Environ 650 km', 70, 0),
(278, 'Environ 720 km', 70, 0),
(279, 'Environ 790 km', 70, 1),
(280, 'Environ 850 km', 70, 0),
(281, 'Venera 4', 71, 0),
(282, 'Magellan', 71, 0),
(283, 'Mariner 8', 71, 1),
(284, 'Véga 1', 71, 0),
(285, 'Un poireau en héraldique flamande', 72, 0),
(286, 'Une pièce d''échec', 72, 1),
(287, 'Un turban', 72, 0),
(288, 'Une figure géométrique', 72, 0),
(289, 'Sec', 73, 1),
(290, 'Défeuillé', 73, 0),
(291, 'Mort', 73, 0),
(292, 'Nu', 73, 0),
(293, 'Au daim', 74, 0),
(294, 'Au cheval', 74, 0),
(295, 'A l''écureuil', 74, 0),
(296, 'Au chat', 74, 1),
(297, 'Un lion à l''oeil fermé', 75, 0),
(298, 'Un poisson à la gueule ouverte', 75, 1),
(299, 'Un oiseau la tête en bas', 75, 0),
(300, 'Tout animal couché sur le dos', 75, 0),
(301, 'Nouée', 76, 0),
(302, 'Danchée', 76, 0),
(303, 'Bretessée', 76, 1),
(304, 'Vivrée', 76, 0),
(305, 'Lorré', 77, 0),
(306, 'Caudé', 77, 0),
(307, 'Peautré', 77, 1),
(308, 'Aposté', 77, 0),
(309, 'Bicéphale', 78, 1),
(310, 'Auréolée', 78, 0),
(311, 'Pointant son bec vers le haut', 78, 0),
(312, 'Aux pattes relevées', 78, 0),
(313, 'Au sanglier', 79, 1),
(314, 'Au lion', 79, 0),
(315, 'Au dauphin', 79, 0),
(316, 'Au faucon', 79, 0),
(317, 'Garotté', 80, 0),
(318, 'Garni', 80, 0),
(319, 'Guiché', 80, 0),
(320, 'Grilleté', 80, 1),
(321, 'Cepé', 81, 0),
(322, 'Pampré', 81, 1),
(323, 'Thyrsé', 81, 0),
(324, 'Sarmenté', 81, 0),
(325, 'Entre rouge et brun', 82, 1),
(326, 'Entre bleu et vert', 82, 0),
(327, 'Entre vert et jaune', 82, 0),
(328, 'Entre bleu et gris', 82, 0),
(329, 'Terre', 83, 0),
(330, 'Acier', 83, 0),
(331, 'Feu', 83, 0),
(332, 'Cuivre', 83, 1),
(333, 'Auguste', 84, 0),
(334, 'Tibère', 84, 1),
(335, 'Artaban', 84, 0),
(336, 'Néron', 84, 0),
(337, 'Des femmes', 85, 0),
(338, 'Des peintures', 85, 0),
(339, 'Des esclaves', 85, 0),
(340, 'Des statues', 85, 1),
(341, 'Elle est poignardée', 86, 0),
(342, 'Elle est dévorée par des lions', 86, 0),
(343, 'Elle est décapitée', 86, 0),
(344, 'Elle est écrasée contre un mur', 86, 1),
(345, 'De 1 à 2 ans', 87, 0),
(346, 'De 2 à 3 ans', 87, 0),
(347, 'De 3 à 4 ans', 87, 1),
(348, 'De 5 à 6 ans', 87, 0),
(349, '24 ans', 88, 0),
(350, '29 ans', 88, 1),
(351, '34 ans', 88, 0),
(352, '39 ans', 88, 0),
(353, 'Dans le Tibre', 89, 0),
(354, 'Sur la place de Rome', 89, 0),
(355, 'Dans les jardins de Lamia', 89, 1),
(356, 'Au Circus Maximus', 89, 0),
(357, 'Les trimbres-poste', 90, 0),
(358, 'La monnaie et les médailles', 90, 1),
(359, 'Les cartes postales', 90, 0),
(360, 'Les macarons', 90, 0),
(361, 'Tennis', 91, 0),
(362, 'Baseball', 91, 0),
(363, 'Golf', 91, 0),
(364, 'Football', 91, 1),
(365, 'Le patin à roulettes', 92, 1),
(366, 'La raquette', 92, 0),
(367, 'Le patin à glace', 92, 0),
(368, 'Le ski de randonnée', 92, 0),
(369, 'Perse', 93, 1),
(370, 'Égypte', 93, 0),
(371, 'Chine', 93, 0),
(372, 'Inde', 93, 0),
(373, '9', 94, 0),
(374, '6', 94, 1),
(375, '3', 94, 0),
(376, '1', 94, 0),
(377, '4', 95, 1),
(378, '5', 95, 0),
(379, '6', 95, 0),
(380, '8', 95, 0),
(381, 'Un jeu de cerceaux', 96, 0),
(382, 'Un jeu de cartes', 96, 1),
(383, 'Un jeu de dames', 96, 0),
(384, 'Un jeu de dés', 96, 0),
(385, 'Louis Cyr', 97, 1),
(386, 'Édouard Carpentier', 97, 0),
(387, 'Jos Montferrand', 97, 0),
(388, 'Alexis Lapointe', 97, 0),
(389, '20', 98, 0),
(390, '17', 98, 1),
(391, '12', 98, 0),
(392, '8', 98, 0),
(393, 'L''acétabulum de l''os coxal', 99, 1),
(394, 'Le processus coronoide de l''ulna', 99, 0),
(395, 'La patela', 99, 0),
(396, 'Le sphénoïde', 99, 0),
(397, '2 heures', 100, 1),
(398, '1 heure 30', 100, 0),
(399, '1 heure', 100, 0),
(400, '30 minutes', 100, 0),
(401, '1km', 101, 0),
(402, '2km', 101, 0),
(403, '3km', 101, 1),
(404, '4km', 101, 0),
(405, '4 m de chaque côté', 102, 0),
(406, '6 m de chaque côté', 102, 0),
(407, '8 m de chaque côté', 102, 1),
(408, '10 m de chaque côté', 102, 0),
(409, 'Thyroïde', 103, 0),
(410, 'Surrénale', 103, 1),
(411, 'Hypophyse', 103, 0),
(412, 'Sudoripare', 103, 0),
(413, 'Dell', 104, 0),
(414, 'IBM', 104, 0),
(415, 'Toshiba', 104, 0),
(416, 'Hewlett Packard', 104, 1),
(417, '1 000 000 000', 105, 0),
(418, '1 000 000', 105, 1),
(419, '1 000', 105, 0),
(420, '100 000', 105, 0),
(421, 'Musique', 106, 0),
(422, 'Chimie', 106, 0),
(423, 'Imprimerie', 106, 1),
(424, 'Astronomie', 106, 0),
(425, 'Les hommes', 107, 1),
(426, 'L''eau', 107, 0),
(427, 'Les insectes', 107, 0),
(428, 'Les animaux', 107, 0),
(429, 'L''avocat', 108, 0),
(430, 'La laitue', 108, 0),
(431, 'Le soya', 108, 1),
(432, 'Le lait', 108, 0),
(433, '1/2', 109, 0),
(434, '1/3', 109, 0),
(435, '1/5', 109, 0),
(436, '1/6', 109, 1),
(437, '90', 110, 0),
(438, '100', 110, 0),
(439, '140', 110, 1),
(440, '180', 110, 0),
(441, 'L''ouïe', 111, 1),
(442, 'La vue', 111, 0),
(443, 'Le goût', 111, 0),
(444, 'L''odorat', 111, 0),
(445, '1', 112, 0),
(446, '2', 112, 1),
(447, '4', 112, 0),
(448, '6', 112, 0),
(449, 'La Bosnie', 113, 0),
(450, 'La Croatie', 113, 1),
(451, 'L''Islande', 113, 0),
(452, 'La Turquie', 113, 0),
(453, 'Belgrade', 114, 0),
(454, 'Tirana', 114, 0),
(455, 'Sarajevo', 114, 0),
(456, 'Podgorica', 114, 1),
(457, 'En Serbie', 115, 0),
(458, 'En Roumanie', 115, 0),
(459, 'En Grèce', 115, 1),
(460, 'En Bulgarie', 115, 0),
(461, 'Genève', 116, 0),
(462, 'Nyon', 116, 1),
(463, 'Lausanne', 116, 0),
(464, 'Zürich', 116, 0),
(465, 'Le Danemark', 117, 1),
(466, 'Le Monténégro', 117, 0),
(467, 'Le Kosovo', 117, 0),
(468, 'La Slovénie', 117, 0),
(469, 'L''Andorre', 118, 0),
(470, 'Le Liechtenstein', 118, 0),
(471, 'Saint-Marin', 118, 1),
(472, 'Le Luxembourg', 118, 0),
(473, 'L''Allemagne', 119, 0),
(474, 'La Suisse', 119, 0),
(475, 'La Belgique', 119, 0),
(476, 'La Roumanie', 119, 1),
(477, 'La Lettonie', 120, 1),
(478, 'La Lituanie', 120, 0),
(479, 'L''Estonie', 120, 0),
(480, 'La Finlande', 120, 0),
(481, 'Espagne', 121, 0),
(482, 'Portugal', 121, 0),
(483, 'Suède', 121, 0),
(484, 'Grèce', 121, 1),
(485, 'Mimer', 122, 0),
(486, 'Chanter', 122, 1),
(487, 'Se raconter des blagues', 122, 0),
(488, 'Danser', 122, 0),
(489, 'Jaune', 123, 0),
(490, 'Blanc', 123, 0),
(491, 'Violet', 123, 1),
(492, 'Noir', 123, 0),
(493, 'L''apéro', 124, 0),
(494, 'La mer', 124, 0),
(495, 'La fiesta', 124, 0),
(496, 'Des nanas', 124, 1),
(497, 'La santa Maria', 125, 0),
(498, 'Le Titanic', 125, 1),
(499, 'Le Costa Concordia', 125, 0),
(500, 'Le Nautilus', 125, 0),
(501, 'Paris', 126, 1),
(502, 'Metz', 126, 0),
(503, 'Marseille', 126, 0),
(504, 'Sarregumines', 126, 0),
(505, 'Une chanteuse', 127, 0),
(506, 'Une danseuse', 127, 0),
(507, 'Une nageuse', 127, 1),
(508, 'Un présentatrice de journal', 127, 0),
(509, 'Ghislaine Arabian', 128, 0),
(510, 'Louis Pasteur', 128, 1),
(511, 'Jules Renard', 128, 0),
(512, 'Robert Pattinson', 128, 0),
(513, 'France', 129, 0),
(514, 'L''Allemagne de l''Est', 129, 0),
(515, 'Bretagne', 129, 1),
(516, 'La Nouvelle Zélande', 129, 0),
(517, 'Un arbre', 130, 0),
(518, 'Un outils agricole', 130, 0),
(519, 'Un copeau de bois', 130, 0),
(520, 'Un poisson', 130, 1),
(521, 'Bigard', 131, 0),
(522, 'Shakespeare', 131, 0),
(523, 'Victor Hugo', 131, 1),
(524, 'Omar Sy', 131, 0),
(525, 'Au Japon', 132, 0),
(526, 'Sous l''eau', 132, 0),
(527, 'A Paris', 132, 0),
(528, 'Dans l''espace', 132, 1),
(529, 'Le Petit Prince', 133, 1),
(530, 'E.T.', 133, 0),
(531, 'Monsieur Spoke', 133, 0),
(532, 'Wall-E', 133, 0),
(533, 'Baloo', 134, 1),
(534, 'Grizli', 134, 0),
(535, 'Boobi', 134, 0),
(536, 'Mooglie', 134, 0),
(537, '50 degrés', 135, 0),
(538, '90 degrés', 135, 0),
(539, '100 degrés', 135, 1),
(540, '360 degrés', 135, 0),
(541, 'Kia', 136, 0),
(542, 'Ford', 136, 0),
(543, 'Peugeot', 136, 1),
(544, 'Dacia', 136, 0),
(545, 'Dix ans', 137, 0),
(546, 'Cent ans', 137, 0),
(547, 'Mille ans', 137, 0),
(548, '13 Millards d''années', 137, 1),
(549, 'Ecrire du texte', 138, 1),
(550, 'Programmer', 138, 0),
(551, 'Faire des dessins', 138, 0),
(552, 'Jouer à des jeux', 138, 0),
(553, 'Mister Bean', 139, 0),
(554, 'Alain Delon', 139, 0),
(555, 'Neil Armstrong', 139, 1),
(556, 'Chuck Norris', 139, 0),
(557, 'Bleu', 140, 1),
(558, 'Jaune', 140, 0),
(559, 'Vert', 140, 0),
(560, 'Violet', 140, 0),
(561, 'Hergé', 141, 0),
(562, 'Christian Clavier', 141, 0),
(563, 'Scribitix', 141, 0),
(564, 'Albert Uderzo', 141, 1),
(565, 'Vénus', 142, 0),
(566, 'Delon', 142, 0),
(567, 'Aphrodite', 142, 0),
(568, 'Narcisse', 142, 1),
(569, 'Peintre', 143, 1),
(570, 'Joueur de rugby', 143, 0),
(571, 'Chanteur de music hall', 143, 0),
(572, 'Ecrivain', 143, 0),
(573, 'La licence pro', 144, 0),
(574, 'La licence poétique', 144, 0),
(575, 'La licence IV', 144, 1),
(576, 'Un autorisation du maire', 144, 0),
(577, 'trois', 145, 1),
(578, 'quatre', 145, 0),
(579, 'cinq', 145, 0),
(580, 'six', 145, 0),
(581, 'La pêche à la mouche', 146, 0),
(582, 'La danse classique', 146, 0),
(583, 'Le handball', 146, 0),
(584, 'Le football', 146, 1),
(585, 'Nounours', 147, 0),
(586, 'La mouche tsé-tsé', 147, 1),
(587, 'Le moustique chikungunya', 147, 0),
(588, 'Morphée', 147, 0),
(589, 'Psykocouac', 148, 0),
(590, 'Pikachu', 148, 1),
(591, 'Mew', 148, 0),
(592, 'Salamèche', 148, 0),
(593, 'Ross', 149, 1),
(594, 'Chandler', 149, 0),
(595, 'Joey', 149, 0),
(596, 'Bob', 149, 0),
(597, 'Les papilles', 150, 1),
(598, 'Les pupilles', 150, 0),
(599, 'Les chakras', 150, 0),
(600, 'Les postillons', 150, 0),
(601, 'Ses clés', 151, 0),
(602, 'Ses prières', 151, 0),
(603, 'La mémoire', 151, 1),
(604, 'Sa foi', 151, 0),
(605, '21', 152, 0),
(606, '16', 152, 0),
(607, '25', 152, 0),
(608, '18', 152, 1),
(609, '1750', 153, 0),
(610, '1789', 153, 1),
(611, '1804', 153, 0),
(612, '1945', 153, 0),
(613, 'Une Super Annonce', 154, 0),
(614, 'Un Sacré Ami', 154, 0),
(615, 'United States of America', 154, 1),
(616, 'Une Société Anonyme', 154, 0),
(617, 'Arielle Dombasl', 155, 0),
(618, 'Luc Besson', 155, 0),
(619, 'Chuck Norris', 155, 0),
(620, 'Alexandre Astier', 155, 1),
(621, '60', 156, 0),
(622, '36000', 156, 0),
(623, '6000', 156, 0),
(624, '3600', 156, 1),
(625, 'Un chien', 157, 0),
(626, 'Un éléphant', 157, 1),
(627, 'Un renard', 157, 0),
(628, 'Une fourmis', 157, 0),
(629, 'Noël', 158, 0),
(630, 'La fête nationale', 158, 1),
(631, 'La fête du travail', 158, 0),
(632, 'La moitié du mois de juillet', 158, 0),
(633, '3 ans', 159, 0),
(634, '5 ans', 159, 1),
(635, '7 ans', 159, 0),
(636, '10 ans', 159, 0),
(637, 'Un joueur de country', 160, 0),
(638, 'Un acrobate', 160, 0),
(639, 'Un pape', 160, 1),
(640, 'Un député', 160, 0),
(641, 'Le rouge', 161, 0),
(642, 'Le blanc', 161, 0),
(643, 'Le bleu', 161, 1),
(644, 'Le orange', 161, 0),
(645, 'Microsoft', 162, 0),
(646, 'Apple', 162, 1),
(647, 'Carrefour', 162, 0),
(648, 'Aldi', 162, 0),
(649, 'Les 24 heures du Mans', 163, 0),
(650, 'le Bol d''Or', 163, 1),
(651, 'Le Rallye de Monte-Carlo', 163, 0),
(652, 'Indianapolis', 163, 0),
(653, 'A percussion', 164, 0),
(654, 'A corde', 164, 0),
(655, 'A vent', 164, 1),
(656, 'A rythme', 164, 0),
(657, 'De connaitre son horoscope', 165, 0),
(658, 'De perdre de l''argent', 165, 0),
(659, 'D''épargner', 165, 1),
(660, 'De téléphoner', 165, 0),
(661, 'Le court', 166, 1),
(662, 'Le couloir', 166, 0),
(663, 'Le carré', 166, 0),
(664, 'La surface', 166, 0),
(665, 'Gérard Depardieu', 167, 0),
(666, 'Daniel Auteuil', 167, 0),
(667, 'Jean-Claude Van Damme', 167, 1),
(668, 'Thierry Lhermitte', 167, 0),
(669, 'Le Brésil', 168, 0),
(670, 'L''Espagne', 168, 0),
(671, 'L''Allemagne', 168, 0),
(672, 'La France', 168, 1),
(673, 'Ironix', 169, 0),
(674, 'Idefix', 169, 1),
(675, 'Agencetousrix', 169, 0),
(676, 'Panoramix', 169, 0),
(677, 'L''Italie', 170, 0),
(678, 'La Belgique', 170, 0),
(679, 'L''Allemagne', 170, 1),
(680, 'L''Angleterre', 170, 0),
(681, 'La Chute', 171, 0),
(682, 'Star Wars', 171, 1),
(683, 'La 7e Compagnie', 171, 0),
(684, 'La Grande Vadrouille', 171, 0),
(685, 'Le feu', 172, 1),
(686, 'La voiture', 172, 0),
(687, 'Le barbecue', 172, 0),
(688, 'Une cigarette', 172, 0),
(689, 'Pékinois', 173, 0),
(690, 'Braque Danois', 173, 0),
(691, 'Setter Irlandais Rouge', 173, 0),
(692, 'Cocker', 173, 1),
(693, 'Un flambeau', 174, 1),
(694, 'La Bible', 174, 0),
(695, 'Une boussole', 174, 0),
(696, 'Son sein', 174, 0),
(697, 'Chasse le mâle', 175, 0),
(698, 'Dévore le mâle', 175, 1),
(699, 'S''endort', 175, 0),
(700, 'Emet des cris stridents', 175, 0),
(701, 'Les  Trois Mercenaires', 176, 0),
(702, 'Fifrad', 176, 0),
(703, 'Die Goldenen Zitronen', 176, 0),
(704, '2be3', 176, 1),
(705, 'La vie', 177, 1),
(706, 'La mort', 177, 0),
(707, 'Le sommeil', 177, 0),
(708, 'L''amour', 177, 0),
(709, 'Vers l''avant', 178, 0),
(710, 'Si l''autre est d''accord', 178, 0),
(711, 'Vers l''arrière', 178, 1),
(712, 'Avec style', 178, 0),
(713, 'Seattle', 179, 0),
(714, 'La Vegas', 179, 1),
(715, 'Los Angeles', 179, 0),
(716, 'New York', 179, 0),
(717, 'Le Cinquième Elément', 180, 0),
(718, 'Le diner de cons', 180, 1),
(719, 'Piège de Cristal', 180, 0),
(720, 'Le Sixième Sens', 180, 0),
(721, 'Delphine Batho', 181, 0),
(722, 'Maxime Le Forestier', 181, 0),
(723, 'Les Ingalls', 181, 1),
(724, 'Les Sœurs Brontë', 181, 0);

-- --------------------------------------------------------

--
-- Structure de la table `somme`
--

CREATE TABLE IF NOT EXISTS `somme` (
  `id_somme` int(11) NOT NULL AUTO_INCREMENT,
  `valeur` int(11) NOT NULL,
  PRIMARY KEY (`id_somme`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `somme`
--

INSERT INTO `somme` (`id_somme`, `valeur`) VALUES
(1, 200),
(2, 300),
(3, 500),
(4, 800),
(5, 1500),
(6, 3000),
(7, 6000),
(8, 12000),
(9, 24000),
(10, 48000),
(11, 72000),
(12, 100000),
(13, 150000),
(14, 300000),
(15, 1000000),
(16, 0);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `joueur_reponse`
--
ALTER TABLE `joueur_reponse`
  ADD CONSTRAINT `joueur_reponse_ibfk_4` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id_joueur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joueur_reponse_ibfk_5` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joueur_reponse_ibfk_6` FOREIGN KEY (`id_reponse`) REFERENCES `reponse` (`id_reponse`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `joueur_score`
--
ALTER TABLE `joueur_score`
  ADD CONSTRAINT `joueur_score_ibfk_1` FOREIGN KEY (`id_joueur`) REFERENCES `joueur` (`id_joueur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE;
