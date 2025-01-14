package aff;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ListeGagnant extends JFrame {

    public ListeGagnant() {
        super("Liste de victoires");

        FileDataReader gagnant = new FileDataReader("gagnants.txt");
        Object[] gagnants = gagnant.getData();
        FileDataReader perdant = new FileDataReader("perdants.txt");
        Object[] perdants = perdant.getData();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Set<String> labelsDejaMentionnes = new HashSet<>();

        for (Object obj : gagnants) {
            String labelString = obj.toString().trim(); 
            if (labelString.endsWith(";")) {
                labelString = labelString.substring(0, labelString.length() - 1); 
            }
            if (!labelsDejaMentionnes.contains(labelString)) {
                int occurrencesGagnants = countOccurrences(gagnants, obj);
                panel.add(new JLabel(labelString + " : " + occurrencesGagnants + " victoire(s)"));
                labelsDejaMentionnes.add(labelString);
            }
        }

        for (Object obj : perdants) {
            String labelString = obj.toString().trim(); 
            if (labelString.endsWith(";")) {
                labelString = labelString.substring(0, labelString.length() - 1); 
            }
                int occurrencesPerdants = countOccurrences(perdants, obj);
                int occurrencesGagnants = countOccurrences(gagnants, obj);
                if (occurrencesGagnants == 0 && occurrencesPerdants > 0) {
                    panel.add(new JLabel(labelString + " : 0 victoire"));
                    labelsDejaMentionnes.add(labelString);
                }
        }

        add(panel);

        setSize(500, 500);
        setVisible(true);
    }

    private int countOccurrences(Object[] array, Object obj) {
        int count = 0;
        for (Object o : array) {
            if (o.equals(obj)) {
                count++;
            }
        }
        return count;
    }
}
