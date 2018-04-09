import java.util.ArrayList;

/**
 * <h3>Classe symbolisant le plateau de jeu avec les listes des planètes, vaisseaux et espèces. Elle s'occupe également d'effectuer le déroulé d'un tour</h3> 
 * @author Gueguen Ronan et Godet Antoine
 */

public class Galaxie {
	private Entite[][] grille_galaxie;
	
	private ArrayList<Planete> liste_planete;
	private ArrayList<Vaisseau> liste_vaisseau;
	private ArrayList<Espece> liste_espece;
	
	/**
	 * <p>Constructeur créant une nouvelle galaxie avec un plateau de jeu vide et une liste de planètes, de vaisseaux et d'especes vides</p>
	 */
	public Galaxie() {
		int i;
		int j;

		this.grille_galaxie = new Entite[Constantes.Largeur][Constantes.Hauteur];
		
		for(i=0 ; i<Constantes.Largeur ; i++) {
			for(j=0 ; j<Constantes.Hauteur ; j++) {
				this.grille_galaxie[i][j] = null;
			}
		}
		
		
		this.liste_planete = new ArrayList<Planete>();
		this.liste_vaisseau = new ArrayList<Vaisseau>();
		this.liste_espece = new ArrayList<Espece>();
	}
	
	//Retourne la grille
	/**
	 * <p>Getters renvoyant la grille de jeu</p>
	 */
	public Entite[][] getGalaxie() {
		return this.grille_galaxie;
	}

	//Retourne la liste des planetes
	/**
	 * <p>Getters renvoyant la liste des planètes de la galaxie</p>
	 */
	public ArrayList<Planete> getListe_Planete(){
		return this.liste_planete;
	}
	
	//retourne la liste des vaisseaux
	/**
	 * <p>Getters renvoyant la liste des vaisseaux de la galaxie</p>
	 */
	public ArrayList<Vaisseau> getListe_Vaisseau(){
		return this.liste_vaisseau;
	}
	
	//Retourne la liste des especes
	/**
	 * <p>Getters renvoyant la liste des especes de la galaxie</p>
	 */
	public ArrayList<Espece> getListe_Espece(){
		return this.liste_espece;
	}
	
	//Renvoie la liste de toute les positions des cases vides autour d'une position donnée
	//Utile pour tirer ensuite aléatoirement une de ces positions (pour la sortir des vaisseaux su hangar notamment)
	/**
	 * <p>Methode renvoyant les coordonnées (tableau de 2 entiers) renvoyant la liste des cases alentours vides</p>
	 * @param x position de x de la case autour de laquelle nous allons faire la vérification
	 * @param y position de y de la case autour de laquelle nous allons faire la vérification 
	 */
	public ArrayList<int[]> case_alentour_vides(int x, int y) {
		ArrayList<int[]> liste_cellule = new ArrayList<int[]>();

		//Il vaut mieux ici faire 8 if que deux while imbriqué car l'union des <= et des modulos donnent des résultats très peu satisfaisant
		if(this.grille_galaxie[adapte_largeur(x-1)][adapte_hauteur(y-1)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x-1);
			cellule[1] = adapte_hauteur(y-1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x-1)][adapte_hauteur(y)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x-1);
			cellule[1] = adapte_hauteur(y);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x-1)][adapte_hauteur(y+1)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x-1);
			cellule[1] = adapte_hauteur(y+1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x)][adapte_hauteur(y-1)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x);
			cellule[1] = adapte_hauteur(y-1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x)][adapte_hauteur(y+1)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x);
			cellule[1] = adapte_hauteur(y+1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x+1)][adapte_hauteur(y-1)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x+1);
			cellule[1] = adapte_hauteur(y-1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x+1)][adapte_hauteur(y)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x+1);
			cellule[1] = adapte_hauteur(y);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x+1)][adapte_hauteur(y+1)] == null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x+1);
			cellule[1] = adapte_hauteur(y+1);
			liste_cellule.add(cellule);
		}
		
		return liste_cellule;
	}
	
	/**
	 * <p>Methode renvoyant les coordonnées (tableau de 2 entiers) renvoyant la liste des cases alentours non vide</p>
	 * @param x position de x de la case autour de laquelle nous allons faire la vérification
	 * @param y position de y de la case autour de laquelle nous allons faire la vérification 
	 */
	public ArrayList<int[]> case_alentour_non_vides(int x, int y) {
		ArrayList<int[]> liste_cellule = new ArrayList<int[]>();

		//Il vaut mieux ici faire 8 if que deux while imbriqué car l'union des <= et des modulos donnent des résultats très peu satisfaisant
		if(this.grille_galaxie[adapte_largeur(x-1)][adapte_hauteur(y-1)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x-1);
			cellule[1] = adapte_hauteur(y-1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x-1)][adapte_hauteur(y)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x-1);
			cellule[1] = adapte_hauteur(y);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x-1)][adapte_hauteur(y+1)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x-1);
			cellule[1] = adapte_hauteur(y+1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x)][adapte_hauteur(y-1)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x);
			cellule[1] = adapte_hauteur(y-1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x)][adapte_hauteur(y+1)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x);
			cellule[1] = adapte_hauteur(y+1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x+1)][adapte_hauteur(y-1)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x+1);
			cellule[1] = adapte_hauteur(y-1);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x+1)][adapte_hauteur(y)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x+1);
			cellule[1] = adapte_hauteur(y);
			liste_cellule.add(cellule);
		}
		if(this.grille_galaxie[adapte_largeur(x+1)][adapte_hauteur(y+1)] != null) {
			int[] cellule = new int[2];
			cellule[0] = adapte_largeur(x+1);
			cellule[1] = adapte_hauteur(y+1);
			liste_cellule.add(cellule);
		}
		
		return liste_cellule;
	}
	
	/**
	 * <p>Méthode adaptant un entier en fonction de la largeur pour gérer la circularité du plateau</p>
	 * @param x entier à adapter
	 */
	public int adapte_largeur(int x) {
		if(x < 0) {
			return x + Constantes.Largeur;
		} else if(x >= Constantes.Largeur) {
			return x - Constantes.Largeur;
		} else {
			return x;
		}
	}
	/**
	 * <p>Méthode adaptant un entier en fonction de la hauteur pour gérer la circularité du plateau</p>
	 * @param y entier à adapter
	 */
	public int adapte_hauteur(int y) {
		if(y < 0) {
			return y + Constantes.Hauteur;
		} else if(y >= Constantes.Hauteur) {
			return y - Constantes.Hauteur;
		} else {
			return y;
		}
	}
	//Ajoute une espece à la liste des especes
	/**
	 * <p>Méthode ajoutant une espece à la liste des especes</p>
	 * @param nouvelle_espece Espece à ajouter  
	 */
	public void ajouter_espece(Espece nouvelle_espece){
		this.getListe_Espece().add(nouvelle_espece);
	}
	
	//Ajoute une planete à la liste des planetes
	/**
	 * <p>Méthode ajoutant une planète à la liste des planètes et à la grille à la position adéquate</p>
	 * @param nouvelle_planète Planète à ajouter  
	 */
	public void ajouter_planete(Planete nouvelle_planete){
		this.liste_planete.add(nouvelle_planete);
		this.grille_galaxie[nouvelle_planete.getPos_x()][nouvelle_planete.getPos_y()] = nouvelle_planete;
	}
	
	//Ajoute un vaisseau à la liste des vaisseaux
	/**
	 * <p>Méthode ajoutant un vaisseau à la liste des vaisseaux et à la grille à la position adéquate</p>
	 * @param nouveau_vaisseau Vaisseau à ajouter  
	 */
	public void ajouter_vaisseau(Vaisseau nouveau_vaisseau){
		this.liste_vaisseau.add(nouveau_vaisseau);
		this.grille_galaxie[nouveau_vaisseau.getPos_x()][nouveau_vaisseau.getPos_y()] = nouveau_vaisseau;
	}
	
	//Supprime un vaisseau de la liste des vaisseaux
	/**
	 * <p>Méthode supprimant un vaisseau de la liste des vaisseaux et de la grille à la position adéquate</p>
	 * @param vaisseau Vaisseau à supprimer  
	 */
	public void suppression_vaisseau(Vaisseau vaisseau) {
		this.liste_vaisseau.remove(vaisseau);
		this.grille_galaxie[vaisseau.getPos_x()][vaisseau.getPos_y()] = null;
	}
	
	//Pas besoin de faire des méthodes pour supprimer les planètes et les espèces 
	
	/**
	 * <p>Méthode appellant les méthodes d'interaction de toutes les entités pour simuler le passage d'un tour</p>  
	 */
	public void effectue_tour() {
		int i;
		
		for(i=0 ; i < this.liste_planete.size() ; i++) {
			this.liste_planete.get(i).reproduction();
			this.liste_planete.get(i).action_construction(this);
		}
		
		for(i=0 ; i < this.liste_vaisseau.size() ; i++) {
				this.liste_vaisseau.get(i).move(this);
				this.liste_vaisseau.get(i).interaction(this);
		}
	}
	
}
