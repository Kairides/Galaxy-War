import java.awt.Color;

/**
 * <h3>Classe repertoriant toutes les constantes de la partie</h3>
 * @author Gueguen Ronan et Godet Antoine
 */

public class Constantes {
	// Caractéristiques de la galaxie
	public static final int Largeur = 60; // largeur de la grille galactique (en nombre de cases)
	public static final int Hauteur = 25; // hauteur de la grille galactique (en nombre de cases)
	
	// Caractéristiques des planètes
	public static final int PlaneteTailleMin = 1000; // taille minimale d'une planète
	public static final int PlaneteTailleMax = 20000; // taille maximale d'une planète
	
	// Caractéristiques des vaisseaux
	public static final int VaisseauResistanceMin = 300; // résistance minimale d'un vaisseau
	public static final int VaisseauResistanceMax = 1000; // résistance maximale d'un vaisseau
	
	// Caractéristiques des propulsions
	public static final int PropulsionPorteeMin = 1; // portée minimale d'une propulsion
	public static final int PropulsionPorteeMax = 5; // portée maximale d'une propulsion
	
	//Constantes de degats
	public static final int DegatVaisseauMin = 10;
	public static final int DegatVaisseauMax = 400;
	public static final int DegatPlaneteMin = 100;
	public static final int DegatPlaneteMax = 900;
	
	// Caractéristiques du carburant
	public static final int CarburantMin = 5;
	public static final int CarburantMax = 10;

	// Paramètres de la simulation
	public static final int TourMax = 10000; // nombre de tours maximum
	public static final int TourMs = 8; // durée d'un tour en millisecondes
	
	// Paramètres d'affichage
	public static final int GfxCase = 40; // taille des cases en pixel
	public static final int GfxPlaneteBase = 10; // taille de base des planètes en pixels
	public static final int GfxPlaneteFacteur = 700; // proportion des planètes relative à leur taille
	public static final int GfxVaisseauBase = 4; // taille de base des vaisseaux en pixels
	public static final int GfxVaisseauFacteur = 100; // proportion des vaisseaux relative à leur résistance
	
	//Constantes pour l'initialisation
	//Pre-conditions :
	//	- Nb_espece <= min(Constantes.listeNom.lenghth , Constantes.couleur.length)
	//	- Nb_espece*(Nb_planete_par_espece + Nb_vaisseau_par_espece) + nb_planete_neutre_debut <= Constantes.Largeur * Constantes.Hauteur
	
	public static final int Nb_espece = 4;
	public static final int Nb_planete_par_espece = 2;
	public static final int Nb_planete_neutre_debut = 175;
	public static final int Nb_vaisseau_par_espece = 0;
	
	//Constantes pour déterminer si il faut fermer la fenêtre à la fin de la simulation
	public static boolean fermer_la_fenetre_a_la_fin = false;
	
	//Quelques especes
	public static final String ListeNom[] = {
			   "Vogon",
			   "Nautolan",
			   "Zabrak",
			   "Dalek",
			   "Hobbit",
			   "Extro",
			   "Diplodocus",
			   "Vulcain",
			   "Marionnetiste",
			   "Hutt",
			   "Metroid",
			   "Decapodien",
			   "Geonosien",
			   "Murloc",
			   "Toad",
			   "Zerg",
			   "Cyborg",
			   "Petruccinator",
			   "Xenomorphe",
			   "Namek",
			   "Ziltoid",
			   "Zom",
			   "Masvidaliens"
			 };

	//Quelques couleurs
	public static final Color couleurs[] = {
		new Color(120,230,90),
		new Color(255,30,30),
		new Color(30,230,255),
		new Color(243,214,23),
		new Color(162,112,183),
		new Color(91,17,63),
		new Color(0,255,255),
		new Color(255,255,0),
		new Color(255,100,255)
	};
}
