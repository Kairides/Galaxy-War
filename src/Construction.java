import java.util.Random;

/**
 * <h3>Classe gérant la construction d'un vaisseau sur une planète</h3>
 * @author Gueguen Ronan et Godet Antoine
 */

public class Construction {
	private int avancement_actuel;
	protected int avancement_max;
		
	//Un constructeur de base qui créer une construction vide
	/**
	 * <p>Créer une construction avec une résistance comprise entre les deux bornes (on initialise la résistance_actuel à 0)</p>
	 */
	public Construction() {
		Random rand = new Random();
		this.avancement_max = rand.nextInt(Constantes.VaisseauResistanceMax + 1 - Constantes.VaisseauResistanceMin)+Constantes.VaisseauResistanceMin;
		this.avancement_actuel = 0;
	}
	
	
	/**
	 * <p>Avance la construction, sauf si l'avancement incrémenté est supérieur à l'avancement maximal</p>
	 * @param avancee_en_plus Entier symbolisant l'avancement à ajouter
	 */
	public void avancer_construction(int avancee_en_plus) {
		this.avancement_actuel = (int)Math.min(this.avancement_max, Math.ceil((double)this.avancement_actuel+avancee_en_plus));
	}
	
	
	/**
	 * <p>Renvoie vrai si la résistance est superieur ou égale à l'avancement maximal</p>
	 */
	public boolean test_construction_terminee() {
		return this.avancement_actuel >= this.avancement_max;
	}
	
	
	
	/**
	 * <p>Getter renvoyant l'avancement actuel</p>
	 */
	public int getAvancement_actuel() {
		return avancement_actuel;
	}
	
	
	/**
	 * <p>Getter renvoyant l'avancement maximal</p>
	 */
	public int getAvancement_max() {
		return avancement_max;
	}
}