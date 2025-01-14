package aff;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ExtractionDonnees {

    public void extraireDonnees(String nomFichier) {
        String joueur1 = "";
        String joueur2 = "";
        char[][] grille = new char[10][10];
        char joueurActuel = ' ';
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Joueur 1")) {
                    joueur1 = line.split(":")[1].trim();
                } else if (line.startsWith("Joueur 2")) {
                    joueur2 = line.split(":")[1].trim();
                } else if (line.startsWith("Grille")) {
                    for (int i = 0; i < 10; i++) {
                        line = br.readLine();
                        grille[i] = line.toCharArray();
                    }
                } else if (line.startsWith("Joueur actuel")) {
                    joueurActuel = line.split(":")[1].trim().charAt(0);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Joueur 1: " + joueur1);
        System.out.println("Joueur 2: " + joueur2);
        System.out.println("Grille:");
        for (char[] row : grille) {
            System.out.println(row);
        }
        System.out.println("Joueur actuel: " + joueurActuel);
    }

    public static String extraireDonneesnomJoueur1(String nomFichier) {
        String joueur1 = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Joueur 1")) {
                    joueur1 = line.split(":")[1].trim();
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joueur1;
    }

    public static String extraireDonneesnomJoueur2(String nomFichier) {
        String joueur2 = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Joueur 2")) {
                    joueur2 = line.split(":")[1].trim();
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joueur2;
    }

    public static char[][] extraireDonneesGrill(String nomFichier, int ROWS ,int COLS) {
        char[][] grille = new char[ROWS][COLS];
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
              if (line.startsWith("Grille")) {
                    for (int i = 0; i < ROWS; i++) {
                        line = br.readLine();
                        grille[i] = line.toCharArray();
                    }
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grille;
    }

    public static char extraireDonneesJoueuractuel(String nomFichier) {
        char joueurActuel = ' ';
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Joueur actuel")) {
                    joueurActuel = line.split(":")[1].trim().charAt(0);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joueurActuel;
    }
}