import java.util.Random;

/**
 * <h3>Classe héritant de Propulsion et gérant l'implémentation du déplacement pour une propulsion en linéaire</h3>
 * @author Gueguen Ronan et Godet Antoine
 */

public class Propulsion_Lineaire extends Propulsion{

	/**
	 * <p>Constructeur créant une propulsion linéaire</p>
	 */
	public Propulsion_Lineaire()
	{
		super("+");
	}
	
	//En gros, comme l'on ne peut pas accéder ici aux coordonnées (qui sont dans entité) pour régler ça, on renvoit juste le déplacement à gauche et à droite et l'on utilisera cette méthode dans vaisseau
	//t[0] = déplacement sur l'axe x
	//t[1] = déplacement sur l'axe y
	
	/**
	 * <p>Méthode implémentant la méthode abstraite de Propulsion et renvoyant le decalage en x et en y à appliquer par la suite</p>
	 */
	public int[] deplacement()
	{
		Random rand = new Random();
		int sens_deplacement = rand.nextInt(4);
		
		int deplacement;
		//deplacement = rand.nextInt(this.portee+1);
		deplacement = Math.min(this.carburant, rand.nextInt(this.portee+1));
		
		int[] t = new int[2];
		
		if(sens_deplacement == 0) {	//Déplacement vers le haut
			t[0] = 0;
			t[1] = deplacement;
		} else if(sens_deplacement == 1) { //Déplacement vers le bas
			t[0] = 0;
			t[1] = -deplacement;
		} else if(sens_deplacement == 2) { //Déplacement vers la gauche
			t[0] = -deplacement;
			t[1] = 0;
		} else if(sens_deplacement == 3) { //Déplacement vers la droite
			t[0] = deplacement;
			t[1] = 0;
		}
		return t;
	}
}
