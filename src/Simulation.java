import java.util.ArrayList;
import java.util.Random;

/**
 * <h3>Classe gérant la simulation de conquête galactique</h3>
 * @author Gueguen Ronan et Godet Antoine
 */
public class Simulation {

	/**
	 * @return Vrai si la partie est terminée
	 * @param galaxy Galaxie dans laquelle l'on vérifie le nombre d'espece encore en vie
	 */
	public static boolean victoire(Galaxie galaxy) {		
		return (galaxy.getListe_Espece().size() == 1);
	}

	/**
	 * <p>Supprime de la liste des espèces celles qui n'ont plus aucune planète (l'on considère qu'elles sont dans ce cas condamnées)</p>
	 * @param galaxy Galaxie dans laquelle l'on verifie "l'intégrité" des espèces 
	 */
	public static void check_espece_morte(Galaxie galaxy) {
		int i;
		for(i=0 ; i<galaxy.getListe_Espece().size(); i++) {
			int nb_planete = 0;
			int j;
			for(j=0 ; j<galaxy.getListe_Planete().size(); j++) {
				if(galaxy.getListe_Planete().get(j).getProprietaire() == galaxy.getListe_Espece().get(i)) {
					nb_planete++;
				}
			}
			if(nb_planete == 0) {
				galaxy.getListe_Espece().remove(i);
			}
		}
	}
	
	/**
	 * <p>Méthode initialisant une galaxie et tous ces attributs en fonction des Constantes</p>
	 * @param galaxy Galaxie à initialiser
	 */
	public static void init(Galaxie galaxy) {
		int i;
		int j;
		Random rand = new Random();
		
		ArrayList<String> nom_pris;
		nom_pris = new ArrayList<String>();
		//Liste des noms afin d'éviter les doublons au niveau des noms des especes.
		//Cette liste ne servant à rien en dehors de l'initialisation, nous la déclarons donc ici
		
		for(i=0 ; i < Constantes.Nb_espece ; i++) {
			//On créer une nouvelle espèce
			Espece espece = new Espece(galaxy, nom_pris);//Créer une espece en piochant pour les noms parmi la liste dans Constante
			galaxy.getListe_Espece().add(espece);
			
			//Et l'on créer toute ses planètes
			for(j=0 ; j < Constantes.Nb_planete_par_espece ; j++) {
				int pos_x;
				int pos_y;
				pos_x = rand.nextInt(Constantes.Largeur);
				pos_y = rand.nextInt(Constantes.Hauteur);
				
				while(galaxy.getGalaxie()[pos_x][pos_y] != null) {
					pos_x = rand.nextInt(Constantes.Largeur);
					pos_y = rand.nextInt(Constantes.Hauteur);
				}
				//System.out.println(galaxy.getListe_Planete().size());
				int id_planete = galaxy.getListe_Planete().size();
				int taille_planete = rand.nextInt(Constantes.PlaneteTailleMax + 1 - Constantes.PlaneteTailleMin)+Constantes.PlaneteTailleMin;
				
				Planete planete = new Planete(id_planete, taille_planete, pos_x, pos_y, espece);
				galaxy.getListe_Planete().add(planete);
				galaxy.getGalaxie()[pos_x][pos_y] = planete;
			}
			//Et l'on créer tous ses vaisseaux
			for(j=0 ; j < Constantes.Nb_vaisseau_par_espece ; j++) {
				int pos_x;
				int pos_y;
				pos_x = rand.nextInt(Constantes.Largeur);
				pos_y = rand.nextInt(Constantes.Hauteur);
				
				while(galaxy.getGalaxie()[pos_x][pos_y] != null) {
					pos_x = rand.nextInt(Constantes.Largeur);
					pos_y = rand.nextInt(Constantes.Hauteur);
				}
				
				int id_vaisseau = galaxy.getListe_Planete().size()-1;
				int resistance_vaisseau = rand.nextInt(Constantes.VaisseauResistanceMax + 1 - Constantes.VaisseauResistanceMin)+Constantes.VaisseauResistanceMin;
				
				int prop = rand.nextInt(3);
				if(prop == 0) {
					Vaisseau vaisseau = new Vaisseau(id_vaisseau, resistance_vaisseau, new Propulsion_Lineaire(), new Equipement(), pos_x, pos_y, espece);
					galaxy.getListe_Vaisseau().add(vaisseau);
					galaxy.getGalaxie()[pos_x][pos_y] = vaisseau;
				} else if(prop == 1) {
					Vaisseau vaisseau = new Vaisseau(id_vaisseau, resistance_vaisseau, new Propulsion_Lineaire(), new Equipement(), pos_x, pos_y, espece);
					galaxy.getListe_Vaisseau().add(vaisseau);
					galaxy.getGalaxie()[pos_x][pos_y] = vaisseau;
				} else {
					Vaisseau vaisseau = new Vaisseau(id_vaisseau, resistance_vaisseau, new Propulsion_Lineaire(), new Equipement(), pos_x, pos_y, espece);
					galaxy.getListe_Vaisseau().add(vaisseau);
					galaxy.getGalaxie()[pos_x][pos_y] = vaisseau;
				}
			}
		}
		
		//Création des planètes neutres
		for(i=0 ; i < Constantes.Nb_planete_neutre_debut ; i++) {
			int pos_x;
			int pos_y;
			pos_x = rand.nextInt(Constantes.Largeur);
			pos_y = rand.nextInt(Constantes.Hauteur);
			while (galaxy.getGalaxie()[pos_x][pos_y] != null){
				pos_x = rand.nextInt(Constantes.Largeur);
				pos_y = rand.nextInt(Constantes.Hauteur);
			}
			
			int id_planete = galaxy.getListe_Planete().size();
			int taille_planete = rand.nextInt(Constantes.PlaneteTailleMax + 1 - Constantes.PlaneteTailleMin)+Constantes.PlaneteTailleMin;
			
			Planete planete = new Planete(id_planete, taille_planete, pos_x, pos_y, null);
			galaxy.getListe_Planete().add(planete);
			galaxy.getGalaxie()[pos_x][pos_y] = planete;
		}
	}
	
	
	
	/**
	 * 
	 * <p>Gère la simulation de la partie en rafraichissant l'affichage graphique et en affichant un rapport en console pour chaque tour</p>
	 * @param args paramètre de base pour le main
	 */
	public static void main(String[] args) {
		
		//Création de la galaxie vide
		Galaxie galaxie = new Galaxie();
		init(galaxie);

		// création du panneau d'affichage
		Affichage panneau = new Affichage();

		// création de la fenêtre principale contenant le panneau
		Fenetre fenetre = new Fenetre(panneau);
		
		// boucle de simulation
		int tour = 0;
		while (!victoire(galaxie) && tour<Constantes.TourMax) {
			
			//vaisseau.action_move(galaxie);
			// décompte des tours
			tour += 1;
			
			
			// Affichage d'un bref rapport textuel
			System.out.println("Tour " + tour + " :");
			int i;
			for(i = 0 ; i < galaxie.getListe_Espece().size() ; i++) {
				System.out.print(galaxie.getListe_Espece().get(i).getNom());
				System.out.print( "s : ");
				int nb_planete = 0;
				int j;
				for(j=0 ; j<galaxie.getListe_Planete().size(); j++) {
					if(galaxie.getListe_Planete().get(j).getProprietaire() == galaxie.getListe_Espece().get(i)) {
						nb_planete++;
					}
				}
				System.out.print(nb_planete);
				if(nb_planete < 2) {
					System.out.print(" planete et ");
				} else {
					System.out.print(" planetes et ");
				}
				int nb_vaisseau = 0;
				for(j=0 ; j<galaxie.getListe_Vaisseau().size(); j++) {
					if(galaxie.getListe_Vaisseau().get(j).getProprietaire() == galaxie.getListe_Espece().get(i)) {
						nb_vaisseau++;
					}
				}
				System.out.print(nb_vaisseau);
				if(nb_vaisseau < 2) {
					System.out.println(" vaisseau.");
				} else {
					System.out.println(" vaisseaux.");
				}
			}
			System.out.println();

			
			
			// Exécution des étapes du tour courant
			panneau.rafraichir(galaxie.getListe_Planete(),galaxie.getListe_Vaisseau());
			galaxie.effectue_tour();
			check_espece_morte(galaxie);
			/////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////
			
			// temporisation avant prochain tour
			try {
				Thread.sleep(Constantes.TourMs);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		panneau.rafraichir(galaxie.getListe_Planete(),galaxie.getListe_Vaisseau());
		
		System.out.println("-----------");
		if(galaxie.getListe_Espece().size() > 1) {
			System.out.println("Aucun gagnant");
		} else {
			System.out.print("Les ");
			System.out.print(galaxie.getListe_Espece().get(0).getNom()); //Il ne reste plus qu'une espece à ce stade
			System.out.println("s ont gagnes.");
		}
		if (tour <= 1500){
			System.out.println("Blitzkrieg");
		}
		
		// fermeture de la fenêtre
		if(Constantes.fermer_la_fenetre_a_la_fin) {
			fenetre.dispose();
		}
	}

}
