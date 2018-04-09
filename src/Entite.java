/**
 * <h3>Classe abstraite symbolisant la notion d'Entité dans la galaxie</h3>
 * @author Gueguen Ronan et Godet Antoine
 */
public abstract class Entite {
	protected int pos_x;
	protected int pos_y;
	protected Espece proprietaire;
	protected int identifiant;
	
	protected String type_entite;
	//espace pour espace ; planete pour planète ; vaisseau pour vaisseau

	/**
	 * <p>Constructeur créant une nouvelle entité</p>
	 * @param id Entier symbolisant l'identifiant de l'entite
	 * @param pos_x Entier abscisse de l'entité dans la grille
	 * @param pos_y Entier ordonnée de l'entité dans la grille
	 * @param proprio Espece proprietaire de l'entité
	 */
	public Entite(int id, int pos_x, int pos_y, Espece proprio) {
		this.identifiant = id;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.proprietaire = proprio;
	}
	
	/**
	 * <p>Getter renvoyant le type de l'entité</p>
	 */
	public String getType_entite() {
		return this.type_entite;
	}
	/**
	 * <p>Getter renvoyant l'identifiant de l'entité</p>
	 */
	public int getId() {
		return this.identifiant;
	}
	/**
	 * <p>Getter renvoyant l'abscisse de l'entité</p>
	 */
	public int getPos_x() {
		return this.pos_x;
	}
	/**
	 * <p>Getter renvoyant l'ordonnée de l'entité</p>
	 */
	public int getPos_y() {
		return this.pos_y;
	}
	/**
	 * <p>Méthode abstraite permettant à une entité de prendre des dégâts</p>
	 * @param vaisseau originaire du tir
	 * @param galaxy où l'entité est placée 
	 */
	public abstract void prend_degats(Vaisseau vaisseau, Galaxie galaxy);

	/**
	 * <p>Setter modifiant le proprietaire de l'entité</p>
	 */
	public void setProprietaire(Espece proprio){
		this.proprietaire = proprio;
	}
	/**
	 * <p>Setter modifiant le proprietaire de l'entité</p>
	 */
	public Espece getProprietaire(){
		return this.proprietaire;
	}
	
	/**
	 * <p>Méthode abstraire renvoyant true si l'entité est détruite (resistance = 0 pour Vaisseau ; population = 0 pour Planete</p>
	 */
	public abstract boolean est_detruit();
}
