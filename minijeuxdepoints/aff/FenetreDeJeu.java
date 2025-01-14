package aff;
import javax.swing.*;
import java.awt.*;

public class FenetreDeJeu extends JFrame {

    private JeuDePointsPanel jeu;

    public FenetreDeJeu(String nomJoueur1, String nomJoueur2) {
        super("Jeu de Points");

        jeu = new JeuDePointsPanel(nomJoueur1, nomJoueur2); // Création de l'instance de JeuDePointsPanel

        // Panel principal avec BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Ajout du jeu au centre de panelPrincipal
        panelPrincipal.add(jeu, BorderLayout.CENTER);

        // Panel pour les boutons en bas de la fenêtre
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); // Utilisation de FlowLayout pour les boutons avec espacement

        // Bouton Liste des scores
        JButton boutonListe = new JButton("Liste des scores");
        boutonListe.setFont(new Font("Arial", Font.PLAIN, 16)); // Personnalisation de la police
        boutonListe.setHorizontalTextPosition(SwingConstants.LEFT); // Positionnement du texte à gauche de l'icône
        boutonListe.setPreferredSize(new Dimension(160, 40)); // Dimension plus grande pour le bouton
        boutonListe.addActionListener(new ListeEcoute());

        // Bouton Enregistrer la partie
        JButton boutonEnregistrer = new JButton("Enregistrer la partie");
        boutonEnregistrer.setFont(new Font("Arial", Font.PLAIN, 16)); // Personnalisation de la police
        boutonEnregistrer.setIcon(new ImageIcon("enregistrer_icon.png")); // Ajout d'une icône pour le bouton Enregistrer
        boutonEnregistrer.setHorizontalTextPosition(SwingConstants.LEFT); // Positionnement du texte à gauche de l'icône
        boutonEnregistrer.setIconTextGap(10); // Espacement entre l'icône et le texte
        boutonEnregistrer.setPreferredSize(new Dimension(200, 40)); // Dimension plus grande pour le bouton
        boutonEnregistrer.addActionListener(new EnregistrerButtonListener(jeu));

        // Ajout des boutons au panel des boutons
        panelBoutons.add(boutonListe);
        panelBoutons.add(boutonEnregistrer);

        // Ajout du panel des boutons en bas de panelPrincipal
        panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);

        // Ajout du panelPrincipal à la JFrame
        add(panelPrincipal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrage de la fenêtre
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FenetreDeJeu("Joueur 1", "Joueur 2");
        });
    }
}
