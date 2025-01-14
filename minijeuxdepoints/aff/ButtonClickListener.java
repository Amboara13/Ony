package aff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {

    public JeuDePointsPanel jeu;
    private int x;
    private int y;

    public ButtonClickListener(JeuDePointsPanel jeu, int x, int y) {
        this.jeu = jeu;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jeu.jouerCoup(x, y);
    }
}
