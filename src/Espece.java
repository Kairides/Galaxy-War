import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h3>Classe gérant la création d'une espece dans la galaxie</h3>
 * @author Gueguen Ronan et Godet Antoine
 */

public class Espece {
	private int id_espece;
	private String nom;
	private double taux_natalite_espece;
	private double taux_productivite_espece;
	private Color couleur;
	
	/**
	 * <p>Constructeur créeant une espece de manière aléatoire à partir des noms déjà pris</p>
	 * @param galaxy Galaxie où est placé l'espece et permettant de généré l'identifiant de l'espece
	 * @param ArrayList<String> liste des noms déjà pris et permettant de ne pas avoir de doublons
	 */
	//Un constructeur construisant une espece aléatoirement
	public Espece(Galaxie galaxy, ArrayList<String> nom_pris){
		Random rand = new Random();
		this.id_espece = galaxy.getListe_Espece().size()+1; //La première espece aura pour id 1, la seconde 2... puisque la taille de la liste des espèces s'incrémente à chaque nouvelle espece
		this.taux_natalite_espece = (rand.nextInt(4) + 5)/1000.0;
		this.taux_productivite_espece = (rand.nextInt(3)+1)/1000.0;

		this.nom = Constantes.ListeNom[rand.nextInt(Constantes.ListeNom.length)];
		
		while(nom_pris.contains(this.nom)) {
			this.nom = Constantes.ListeNom[rand.nextInt(Constantes.ListeNom.length)];
		}
		nom_pris.add(this.nom);

		this.couleur = Constantes.couleurs[galaxy.getListe_Espece().size()];		
	}
	
	/**
	 * <p>Getters renvoyant l'identifiant de l'espece</p>
	 */
	public int getId_espece() {
		return this.id_espece;
	}
	/**
	 * <p>Getters renvoyant le taux de natalité de l'espece</p>
	 */
	public double getTaux_natalite_espece() {
		return this.taux_natalite_espece;
	}
	/**
	 * <p>Getters renvoyant le taux de productivité de l'espece</p>
	 */
	public double getTaux_productivite_espece() {
		return this.taux_productivite_espece;
	}
	/**
	 * <p>Getters renvoyant la couleur de l'espece</p>
	 */
	public Color getCouleur() {
		return this.couleur;
	}
	/**
	 * <p>Getters le nom de l'espece</p>
	 */
	public String getNom() {
		return this.nom;
	}
	
	
}
