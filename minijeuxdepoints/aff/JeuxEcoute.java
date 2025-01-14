package aff;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuxEcoute implements ActionListener {

    private JTextField joueur1Field;
    private JTextField joueur2Field;

    public JeuxEcoute(JTextField joueur1Field, JTextField joueur2Field) {
        this.joueur1Field = joueur1Field;
        this.joueur2Field = joueur2Field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String joueur1 = joueur1Field.getText();
        String joueur2 = joueur2Field.getText();
             
        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> new FenetreDeJeu(joueur1, joueur2));
      });
    }
}
