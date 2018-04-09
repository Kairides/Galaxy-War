import java.util.Random;

/**
 * <h3>Classe gérant l'équipement d'un vaisseau</h3>
 * @author Gueguen Ronan et Godet Antoine
 */
public class Equipement {
	private int degats_sur_vaisseau;
	private int degats_sur_planete;
	private int recharge_carburant;
	
	
	//Un equipement aleatoire
	/**
	 * <p>Constructeur créant un équipement aléatoire</p> 
	 */
	public Equipement() {
		Random rand = new Random();
		this.degats_sur_vaisseau = rand.nextInt(Constantes.DegatVaisseauMax-Constantes.DegatVaisseauMin)+Constantes.DegatVaisseauMin; //Degâts de 1 à 3
		this.degats_sur_planete = rand.nextInt(Constantes.DegatPlaneteMax-Constantes.DegatPlaneteMin)+Constantes.DegatPlaneteMin; //Degâts de 5 à 10
		this.recharge_carburant = 5;
	}
	
	/**
	 * <p>Getter renvoyant les degats sur un vaisseau de l'équipement</p> 
	 */	
	public int getDegats_sur_vaisseau() {
		return this.degats_sur_vaisseau;
	}
	/**
	 * <p>Getter renvoyant les degats sur une planète de l'équipement</p> 
	 */	
	public int getDegats_sur_planete() {
		return this.degats_sur_planete;
	}
	/**
	 * <p>Getter renvoyant la recharge en carburant de l'équipement</p> 
	 */	
	public int getRecharge_carburant() {
		return this.recharge_carburant;
	}
}