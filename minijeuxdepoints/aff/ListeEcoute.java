package aff;

import java.awt.event.*;

import javax.swing.SwingUtilities;

public class ListeEcoute implements ActionListener {
  

    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
         new ListeGagnant();
      });
    }
}