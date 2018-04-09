import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @brief Classe permettant de réaliser un affichage graphique de la galaxie
 * 
 * La grille galactique est représentée graphiquement dans un panneau, les 
 * cases étant numérotées à partir du coin supérieur gauche (0,0) jusqu'au
 * coin inférieur droit (Largeur,Hauteur) 
 */
public final class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	// Largeur et hauteur du panel graphique, calculés une seule fois à la construction
	private int larg, haut;

	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	// CODE FACTICE POUR ILLUSTRER LE MECANISME D'AFFICHAGE
	private ArrayList<Planete> lesPlanetes;
	private ArrayList<Vaisseau> lesVaisseaux;
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	
	/**
	 * @brief Constructeur initialisant le panneau d'affichage
	 */
	// TODO : à compléter pour faire le lien entre la galaxie et le panneau d'affichage ...
	public Affichage() {
		// calcul des dimensions du panneau en fonction des paramètres de la galaxie
		larg = Constantes.Largeur*Constantes.GfxCase;
		haut = Constantes.Hauteur*Constantes.GfxCase;
		setPreferredSize(new Dimension(larg,haut));
		
		//this.lesPlanetes = new ArrayList<Planete>();
		//this.lesVaisseaux = new ArrayList<Vaisseau>();
	}

	/**
	 * @brief Efface le contenu du panneau en redéssiantn le fond et la grille
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 */
	public void efface(Graphics g) {
		// espace galactique : fond noir
		g.setColor(Color.BLACK);
		g.fillRect(0,0,larg,haut);
		// grille galactique : lignes grises
		g.setColor(Color.GRAY);
		for (int x=Constantes.GfxCase ; x<larg; x+=Constantes.GfxCase) {
			// affichage des lignes verticales
			g.drawLine(x, 0, x, haut);
		}
		for (int y=Constantes.GfxCase ; y<haut; y+=Constantes.GfxCase) {
			// affichage des lignes horizontales
			g.drawLine(0, y, larg, y);
		}
	}

	/**
	 * @brief Affiche une planète selon ses caractéristiques
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 * @param x Abcisse de la case contenant la planète 
	 * @param y Ordonnée de la case contenant la planète
	 * @param t Taille de la planète
	 * @param c Couleur de la planète
	 */
	public void affichePlanete(Graphics g, int x, int y, int t, Color c) {
		// calcul des coordonnées graphiques du centre de la planète
		int xg = x*Constantes.GfxCase + Constantes.GfxCase/2;
		int yg = y*Constantes.GfxCase + Constantes.GfxCase/2;
		// calcul du rayon de la planète selon sa taille
		int tg = Constantes.GfxPlaneteBase + t/Constantes.GfxPlaneteFacteur;
		// affichage
		g.setColor(c);
		g.fillOval(xg-tg/2,yg-tg/2,tg,tg);
	}
	
	/**
	 * @brief Affiche un vaisseau selon ses caractéristiques
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 * @param x Abcisse de la case contenant le vaisseau 
	 * @param y Ordonnée de la case contenant le vaisseau
	 * @param r Résistance du vaisseau
	 * @param s Chaîne symbolisant la propulsion et l'équipement du vaisseau
	 * @param c Couleur du vaisseau
	 */
	public void afficheVaisseau(Graphics g, int x, int y, int r, String s, Color c) {
		// calcul des coordonnées graphiques du centre de la planète
		int xg = x*Constantes.GfxCase + Constantes.GfxCase/2;
		int yg = y*Constantes.GfxCase + Constantes.GfxCase/2;
		// calcul du côté du vaisseau selon sa taille
		int tg = Constantes.GfxVaisseauBase + r/Constantes.GfxVaisseauFacteur;
		// affichage du carré
		g.setColor(c);
		g.fillRect(xg-tg/2,yg-tg/2,tg,tg);
		// calcul des dimensions graphiques de la chaîne à afficher
		int lg = (int)Math.ceil(g.getFont().getStringBounds(s,((Graphics2D)g).getFontRenderContext()).getWidth());
		int hg = (int)Math.ceil(g.getFont().getStringBounds(s,((Graphics2D)g).getFontRenderContext()).getHeight());
		// affichage de la chaîne sur le carré
		g.setColor(Color.WHITE);
		g.drawString(s, xg-lg/2, yg+hg/2);
	}

	/**
	 * @brief Provoque le rafraichissement du panneau
	 */
	@SuppressWarnings("unchecked")
	public void rafraichir(ArrayList<Planete> planetes, ArrayList<Vaisseau> vaisseaux) {
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		// CODE FACTICE POUR ILLUSTRER LE MECANISME D'AFFICHAGE
		lesPlanetes = (ArrayList<Planete>) planetes.clone(); // recopie la liste pour éviter des problèmes de synchronisation
		lesVaisseaux =(ArrayList<Vaisseau>) vaisseaux.clone(); // recopie la liste pour éviter des problèmes de synchronisation
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		repaint();
	}

	/**
	 * @brief Réaffiche le panneau à la demande
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 */
	// @override
	public void paintComponent(Graphics g) {
		// affichage par défaut
		super.paintComponent(g);

		// l'espace et la grille galactique
		efface(g);
		
		g.setXORMode(Color.BLACK);

		// affichage des planètes et vaisseaux
		// TODO : à compléter
		if (lesPlanetes==null) {
			System.out.println("Planètes non définies");
		} else {
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		// CODE FACTICE POUR ILLUSTRER LE MECANISME D'AFFICHAGE
			for (Planete p : lesPlanetes) {
				if(p.getProprietaire() == null) {
					affichePlanete(g,p.getPos_x(),p.getPos_y(),p.getTaille_planete(),new Color(255,255,255));
				} else {
					affichePlanete(g,p.getPos_x(),p.getPos_y(),p.getTaille_planete(),p.getProprietaire().getCouleur());
				}
			}
			for (Vaisseau p : lesVaisseaux) {
				afficheVaisseau(g,p.getPos_x(),p.getPos_y(),p.getResistance(),p.getPropulsion().getTypePropulsion()+"p",p.getProprietaire().getCouleur());
			}
		}
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
	}
	
}
