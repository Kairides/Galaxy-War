import java.util.ArrayList;
import java.util.Random;

/**
 * <h3>Classe gérant un vaisseau et ses interactions avec les autres entités</h3>
 * @author Gueguen Ronan et Godet Antoine
 */
public class Vaisseau extends Entite{
	
	private int resistance;
	private Propulsion propulsion;
	private Equipement equipement;
	
	/**
	 * <p>Constructeur créant un vaisseau</p>
	 * @param identifiant Entier servant d'identifiant au vaisseau
	 * @param resistance Entier représentant la résistnce du vaisseau
	 * @param propulsion Propulsion choisie pour le vaisseau
	 * @param equipement Equipement choisi pour le vaisseau
	 * @param pos_x Entier représentant l'abscisse choisie pour le vaisseau
	 * @param pos_y Entier représentant l'ordonné choisie pour le vaisseau
	 * @param propriétaire Espèce possédant le vaisseau
	 */
	
	public Vaisseau(int identifiant, int resistance, Propulsion propulsion, Equipement equipement, int pos_x, int pos_y, Espece proprio) {
		super(identifiant, pos_x,pos_y, proprio);
		this.type_entite = "vaisseau";
		
		this.resistance = resistance;
		this.propulsion = propulsion;
		this.equipement = equipement;
	}
	
	/**
	 * <p>Getter renvoyant la résistance du vaisseau</p>
	 */
	public int getResistance() {
		return this.resistance;
	}
	
	/**
	 * <p>Getter renvoyant la propulsion du vaisseau</p>
	 */
	public Propulsion getPropulsion() {
		return this.propulsion;
	}
	
	/**
	 * <p>Getter renvoyant l'équipement du vaisseau</p>
	 */
	public Equipement getEquipement() {
		return this.equipement;
	}
	
	/**
	 * <p>Méthode gérant les intéractions du vaisseau avec les entités adjacentes</p>
	 * @param galaxy Galaxie dans la quelle evolue le vaisseau 
	 */
	public void interaction(Galaxie galaxy) {
		//ArrayList<int[]> liste_cible = this.acquerir_cible(galaxy);
		ArrayList<int[]> liste_cible = galaxy.case_alentour_non_vides(this.pos_x, this.pos_y);
		if(liste_cible.size() != 0) {
			Random rand = new Random();
			
			int[] cible = liste_cible.get(rand.nextInt(liste_cible.size()));
			
			if(galaxy.getGalaxie()[cible[0]][cible[1]].getType_entite() == "vaisseau" && galaxy.getGalaxie()[cible[0]][cible[1]].getProprietaire() != this.proprietaire) {
				//Tir sur vaisseau ennemie
				galaxy.getGalaxie()[cible[0]][cible[1]].prend_degats(this, galaxy);
			} else if(galaxy.getGalaxie()[cible[0]][cible[1]].getType_entite() == "planete"){
				
				if(galaxy.getGalaxie()[cible[0]][cible[1]].est_detruit()) {
					//Colonisation
					//Comme l'on ne peut pas accéder aux méthodes des planètes depuis le tableau d'entité
					//l'on prend la bonne planète du tableau de planète (celle aux coordonnées de son identifiant donc)
					galaxy.getListe_Planete().get(galaxy.getGalaxie()[cible[0]][cible[1]].getId()).colonisation(this);
					galaxy.suppression_vaisseau(this);
					
				} else if(galaxy.getGalaxie()[cible[0]][cible[1]].getProprietaire() == this.proprietaire) {
					//Rechagre du carburant sur un planète allié
					this.propulsion.carburant = this.propulsion.carburant + this.equipement.getRecharge_carburant();
				} else {
					//Tir sur planète sur une planète ennemie
					galaxy.getGalaxie()[cible[0]][cible[1]].prend_degats(this, galaxy);
				}
			}
		}
	}
	
	/**
	 * <p>Méthode implementant la méthode abstraite d'entité et permettant au vaisseau de prendre des dégâts</p>
	 * @param vaisseau Vaisseau originaire du tir
	 * @param galaxy Galaxie dans laquelle le vaisseau est placé
	 */
	public void prend_degats(Vaisseau vaisseau, Galaxie galaxy) {
		this.resistance = this.resistance - vaisseau.getEquipement().getDegats_sur_vaisseau();
		if(this.est_detruit()) {
			this.resistance = 0;
			galaxy.suppression_vaisseau(this);
		}
	}
	
	/**
	 * <p>Méthode implémentant la méthode abstraite d'entité et revoyant true si la résistace du vaisseau est infèrieure ou égal à 0</p> 
	 */
	public boolean est_detruit() {
		return (this.resistance <= 0);
	}
	
	/**
	 * <p>Méthode permettant au vaisseau de se déplacer à partir du décalage fourni par la méthode de déplacement de sa propulsion</p>
	 * @param galaxy Galaxie dans laquelle le vaisseau évolue
	 */
	public void move(Galaxie galaxy) {
		int[] deplacement = this.propulsion.deplacement();
		
		int carburant_retire = Math.abs(deplacement[0]) + Math.abs(deplacement[1]);
		int pos_provisoire_x = this.pos_x + deplacement[0];
		int pos_provisoire_y = this.pos_y + deplacement[1];
		
		if(pos_provisoire_x < 0) {
			pos_provisoire_x = pos_provisoire_x + Constantes.Largeur;
		} else if(pos_provisoire_x >= Constantes.Largeur){
			pos_provisoire_x = pos_provisoire_x - Constantes.Largeur;
		}
		if(pos_provisoire_y < 0) {
			pos_provisoire_y = pos_provisoire_y + Constantes.Hauteur;
		} else if(pos_provisoire_y >= Constantes.Hauteur){
			pos_provisoire_y = pos_provisoire_y - Constantes.Hauteur;
		}
		
		if(galaxy.getGalaxie()[pos_provisoire_x][pos_provisoire_y] == null) {
			galaxy.getGalaxie()[pos_provisoire_x][pos_provisoire_y] = this;
			galaxy.getGalaxie()[this.pos_x][this.pos_y] = null;
			this.pos_x = pos_provisoire_x;
			this.pos_y = pos_provisoire_y;
			
			this.propulsion.carburant = this.propulsion.carburant - carburant_retire;
			if(this.propulsion.carburant < 0) {
				this.propulsion.carburant = 0;
			}
		}
	}
}