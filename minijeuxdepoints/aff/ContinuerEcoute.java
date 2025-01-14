package aff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class ContinuerEcoute implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            new FenetreDeJeuSauvegarder();
        });
    }
}