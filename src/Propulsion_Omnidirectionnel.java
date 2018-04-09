import java.util.Random;

/**
 * <h3>Classe héritant de Propulsion et gérant l'implémentation du déplacement pour une propulsion Ominidirectionnel</h3>
 * @author Gueguen Ronan et Godet Antoine
 */

public class Propulsion_Omnidirectionnel extends Propulsion{

	
	/**
	 * <p>Constructeur créant une propulsion omnidirectionnelle</p>
	 */
	public Propulsion_Omnidirectionnel()
	{
		super("*");
	}
	
	/**
	 * <p>Méthode implémentant la méthode abstraite de Propulsion et renvoyant le decalage en x et en y à appliquer par la suite</p>
	 */
	public int[] deplacement()
	{
		Random rand = new Random();
		int sens_x = rand.nextInt(2);
		int sens_y = rand.nextInt(2);
		
		int deplacement_x;
		int deplacement_y;
		//deplacement_x = rand.nextInt(this.portee+1);
		//deplacement_y = rand.nextInt(this.portee - deplacement_x +1);
		deplacement_x = Math.min(this.carburant, rand.nextInt(this.portee+1));
		deplacement_y = Math.min(this.carburant, rand.nextInt(this.portee - deplacement_x +1));
		
		int[] t = new int[2];
		
		if(sens_x == 0) {	//Déplacement vers le haut
			if(sens_y == 0) {	//Déplacement vers la droite
				t[0] = deplacement_x;
				t[1] = deplacement_y;
			} else {	//Déplacement vers la gauche
				t[0] = -deplacement_x;
				t[1] = deplacement_y;
			}
		} else {	//Déplacement vers le bas
			if(sens_y == 0) {	//Déplacement vers la droite
				t[0] = deplacement_x;
				t[1] = -deplacement_y;
			} else {	//Déplacement vers la gauche
				t[0] = -deplacement_x;
				t[1] = -deplacement_y;
			}
		}
		return t;
	}
}
