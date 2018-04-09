import java.util.ArrayList;
import java.util.Random;

/**
 * <h3>Classe gérant la notion de planète ainsi que l'augmentation de sa population et la sortie des vaisseaux ayant fini d'être construit.</h3> 
 * @author Gueguen Ronan et Godet Antoine
 */
public class Planete extends Entite{
	private int taille_planete;
	private int population_planete;
	private Construction fabrique;
	
	
	//L'on a pas besoin de créer une planète avec une construction à moitié entamée, donc l'on créer à chaque fois une construction vide
	/**
	 * <p>Constructeur créant une planète</p>
	 * @param identifiant Entier représentant l'identifiant de la planète
	 * @param taille Entier représentant la taille de la planète
	 * @param x Entier symbolisant l'abscisse où placer la planète
	 * @param y Entier symbolisant l'ordonnée où placer la planète
	 * @param proprio Espece de la planète (permettant de l'afficher de la bonne couleur) 
	 */
	public Planete(int identifiant, int taille, int x, int y, Espece proprio) {
		super(identifiant, x, y, proprio);
		this.type_entite = "planete";
		
		this.identifiant = identifiant;
		this.taille_planete = taille;
		this.population_planete = taille/2;
		if(proprio == null) { //Si la planète est vide, elle ne construit pas de vaisseaux
			this.population_planete = 0;
			this.fabrique = null;
		} else {
			this.fabrique = new Construction();
		}
	}
	
	/**
	 * <p>Getter renvoyant l'identifiant de la planète</p>
	 */
	public int getIdentifiant() {
		return this.identifiant;
	}
	/**
	 * <p>Getter renvoyant la taille de la planète</p>
	 */
	public int getTaille_planete() {
		return this.taille_planete;
	}
	/**
	 * <p>Getter renvoyant la population de la planète</p>
	 */
	public int getPopulation_planete() {
		return this.population_planete;
	}
	/**
	 * <p>Getter renvoyant la Construction de la planète</p>
	 */
	public Construction getFabrique() {
		return this.fabrique;
	}
	
	//Utile lorsque de la colonisation des planètes et lorsque un vaisseau est sorti du hangar
	/**
	 * <p>Méthode commençant une nouvelle construction sur la planète (utilisé après la sortie du hangar d'un vaisseau)</p>
	 */
	public void nouvelle_construction() {
		this.fabrique = new Construction();
	}
	
	/**
	 * <p>Méthode implementant la méthode abstraite d'entité et gérant la prise des degats sur une planète</p>
	 * @param vaisseau originaire du tir
	 * @param galaxy où l'entité est placée 
	 */
	public void prend_degats(Vaisseau vaisseau, Galaxie galaxy) {
		this.population_planete = this.population_planete - vaisseau.getEquipement().getDegats_sur_planete();
		if(this.est_detruit()) {
			this.population_planete = 0;
			this.setProprietaire(null);
		}
	}
	
	/**
	 * <p>Méthode implémentant la méthode abstraite d'entité et revoyant true si la population de la planète est infèrieure ou égal à 0</p> 
	 */
	public boolean est_detruit() {
		return (this.population_planete <= 0);
	}
	
	/**
	 * <p>Méthode permettant de gérer la colonisation de la planète par un vaisseau</p>
	 * @param vaisseau_de_colonisation Vaisseau colonisant la planète
	 */
	public void colonisation(Vaisseau vaisseau_de_colonisation) {
		this.setProprietaire(vaisseau_de_colonisation.getProprietaire());
		//le vaisseau en train d'être construit n'est pas repris par les colons
		this.nouvelle_construction();
		this.population_planete = vaisseau_de_colonisation.getResistance();
	}
	
	
	//Augmente la population de la planète en fonction de son taux de natalité
	/**
	 * <p>Méthode gérant l'augmentation de la population d'une planète en fonction de sa population et du taux de reproduction de son espece</p>
	 */
	public void reproduction(){
		if(this.proprietaire != null) {
			double taille_max = this.taille_planete;
			double popu_eventuelle = this.population_planete*(1+this.proprietaire.getTaux_natalite_espece());
			this.population_planete = (int)minimum(taille_max,Math.ceil(popu_eventuelle));
		}
	}
	
	/**
	 * <p>Méthode renvoyant le minimum entre deux double</p>
	 * @param x double à comparer
	 * @param y double à comparer
	 */
	public double minimum(double x, double y) {
		if(x < y) {
			return x;
		} else {
			return y;
		}
	}
	
	//Réalise l'action de construction
	//On avance la construction d'un tour si la planète n'est pas neutre
	//Si la construction est terminée, on la fait sortir du hangar sur une case libre à coté de la planète et on l'ajoute à la grille et à la liste des vaisseaux
	/**
	 * <p>Méthode faisant sortir le vaisseau en construction sur une case libre à proximité si la construction est terminée et qui continue la construction sinon</p>
	 * @param galaxy Galaxie où les vaisseaux peuvent être créés
	 */
	public void action_construction(Galaxie galaxy) {
		
		if(this.proprietaire != null) {
			
			if(this.fabrique.test_construction_terminee()) {
				
				ArrayList<int[]> liste_cases_vides_alentours = galaxy.case_alentour_vides(this.pos_x, this.pos_y);
		
				if(liste_cases_vides_alentours.size() != 0) {
					Random rand = new Random();
					int prop = rand.nextInt(3);
					
					int[] cellule = liste_cases_vides_alentours.get(rand.nextInt(liste_cases_vides_alentours.size()));
					
					//La propulsion du vaisseau n'est pas definie dans la construction ppuisque elle ne l'influe pas, nous la déterminons à ce stade
					if(prop == 0) {
						Vaisseau nouveau_vaisseau = new Vaisseau(galaxy.getListe_Vaisseau().size()+1, this.fabrique.avancement_max, new Propulsion_Lineaire(), new Equipement(), cellule[0], cellule[1], this.proprietaire);
						galaxy.ajouter_vaisseau(nouveau_vaisseau);
					} else if(prop == 1) {
						Vaisseau nouveau_vaisseau = new Vaisseau(galaxy.getListe_Vaisseau().size()+1, this.fabrique.avancement_max, new Propulsion_Diagonale(), new Equipement(), cellule[0], cellule[1], this.proprietaire);
						galaxy.ajouter_vaisseau(nouveau_vaisseau);
					} else {
						Vaisseau nouveau_vaisseau = new Vaisseau(galaxy.getListe_Vaisseau().size()+1, this.fabrique.avancement_max, new Propulsion_Omnidirectionnel(), new Equipement(), cellule[0], cellule[1], this.proprietaire);
						galaxy.ajouter_vaisseau(nouveau_vaisseau);
					}
						
					this.fabrique = new Construction();
				}
			} else {
				int productivite_planete = (int)(this.population_planete*this.getProprietaire().getTaux_productivite_espece()); 
				this.fabrique.avancer_construction(productivite_planete);
			}
		}
	}
	
	/**
	 * <p>Méthode renvoyant la valeur absolue d'un entier</p>
	 * @param x Entier à comparer
	 */
	public int abs(int x) {
		if(x < 0) {
			return -x;
		} else {
			return x;
		}
	}
}