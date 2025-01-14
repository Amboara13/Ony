package aff;

import java.lang.reflect.Field;
import java.util.Vector;
import javax.swing.JOptionPane;
import zavamananaina.*;
import maison.*;

public class FenetreSecondaire {
    FenetreSecondaire(Vector <Zavamananaina> trano,Class clazz)
     {   Field[] champsZavamananaina = Zavamananaina.class.getDeclaredFields();
        StringBuilder message = new StringBuilder();

        for (Zavamananaina olombelona : trano) {
            if (clazz.isInstance(olombelona)) {
            for (Field champ : champsZavamananaina) {
                champ.setAccessible(true);
                try {
                    Object valeurChamp = champ.get(olombelona);
                    message.append(champ.getName()).append(" : ").append(valeurChamp).append("\n");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }}
            message.append("\n");
        }

        // Affichage dans une boîte de dialogue
        JOptionPane.showMessageDialog(null, message.toString(), "Attributs", JOptionPane.INFORMATION_MESSAGE);
    }
}

