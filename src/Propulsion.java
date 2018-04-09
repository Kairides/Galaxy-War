import java.util.Random;

/**
 * <h3>Classe abstraite gérant la propulsion d'un vaisseau</h3>
 * @author Gueguen Ronan et Godet Antoine
 */
public abstract class Propulsion {
	protected int portee;
	protected int carburant;
	private String type_propulsion;
	
	
	/**
	 * <p>Constructeur créant une propulsion</p>
	 * @param propulsion Chaîne de caractère precisant le type de propulsion (affiché par la suite)
	 */
	public Propulsion(String propulsion)
	{
		Random rand = new Random();
		this.portee = rand.nextInt(Constantes.PropulsionPorteeMax + 1 - Constantes.PropulsionPorteeMin)+Constantes.PropulsionPorteeMin;	//Portée de min à max
		this.carburant = rand.nextInt(Constantes.CarburantMax + 1 - Constantes.CarburantMin)+Constantes.CarburantMin;	//Carburant de min à max
		this.type_propulsion = propulsion;
	}
	
	/**
	 * <p>Getter renvoyant la portée de la propulsion</p>
	 */
	public int getPortee()
	{
		return this.portee;
	}
	
	/**
	 * <p>Getter renvoyant le carburant utilisé par la propulsion</p>
	 */
	public int getCarburant()
	{
		return this.carburant;
	}
	
	/**
	 * <p>Getter renvoyant le type de la planète</p>
	 */
	public String getTypePropulsion()
	{
		return this.type_propulsion;
	}
	/**
	 * <p>Méthode abstraite renvoyant les coordonnées de déplacement</p>
	 */
	public abstract int[] deplacement();
}