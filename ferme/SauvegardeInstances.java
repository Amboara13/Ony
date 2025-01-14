package aff;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import zavamananaina.*;
import maison.*;

public class SauvegardeInstances {

    public void sauvegarderInstances(Vector<Zavamananaina> instances, Class<?> classe, String nomFichier) {
        try (FileWriter writer = new FileWriter(nomFichier)) {
            for (Zavamananaina instance : instances) {
                if (classe.isInstance(instance)) {
                    writer.write(instance.toString()); // Écrire les données de l'instance dans le fichier
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
