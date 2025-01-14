package aff;
import javax.swing.*;
import java.awt.*;

public class FenetreDeConnexion extends JFrame {

    private JTextField joueur1Field;
    private JTextField joueur2Field;
    private JButton jouerButton;
    private JButton continuerButton;
    private JButton listeButton;

    public FenetreDeConnexion() {
        super("Remplir pour jouer");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Joueur 1 :"), gbc);

        gbc.gridx = 1;
        joueur1Field = new JTextField(20);
        panel.add(joueur1Field, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Joueur 2 :"), gbc);

        gbc.gridx = 1;
        joueur2Field = new JTextField(20);
        panel.add(joueur2Field, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        listeButton = new JButton("Liste");
        listeButton.addActionListener(new ListeEcoute());
        panel.add(listeButton, gbc);

        gbc.gridx = 1;
        jouerButton = new JButton("Jouer");
        jouerButton.addActionListener(new JeuxEcoute(joueur1Field, joueur2Field));
        jouerButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.anchor = GridBagConstraints.CENTER; 
        panel.add(jouerButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        continuerButton = new JButton("Continuer");
        continuerButton.addActionListener(new ContinuerEcoute());
        panel.add(continuerButton, gbc);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
