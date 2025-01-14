package aff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EnregistrerButtonListener implements ActionListener {

    private JeuDePointsPanel jeu;

    public EnregistrerButtonListener(JeuDePointsPanel jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Sauvegarde.sauvegarderPartie(jeu); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
