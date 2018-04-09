import javax.swing.JFrame;

/**
 * <h3>Classe engendrant une fenêtre principale d'application associée à un panneau</h3>
 */
public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;

	public Fenetre(Affichage pan) {
		// titrage de la fenêtre
		super("Galaxy Wars");
		
		// réglage des paramètres
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);

		// ajout de la grille
		getContentPane().add(pan);
		pack();
		
		// affichage centré
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
