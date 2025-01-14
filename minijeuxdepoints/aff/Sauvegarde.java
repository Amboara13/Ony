// Source code is decompiled from a .class file using FernFlower decompiler.
package aff;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sauvegarde {
   public Sauvegarde() {
   }
   public static void sauvegarder(String var0 ,String var1 ) throws IOException {
      PrintWriter var2 = new PrintWriter(new FileWriter(var1, true));

      try {
         File var3 = new File(var1);
         boolean var4 = var3.exists();
         if (var4) {
            var2.println();
         }

         var2.print(var0 + ";");
      } catch (Throwable var6) {
         try {
            var2.close();
         } catch (Throwable var5) {
            var6.addSuppressed(var5);
         }

         throw var6;
      }

      var2.close();
   }


   public static void sauvegarderPartie(JeuDePointsPanel jeu) throws IOException {
      String nomFichier = "sauvegarde.txt";
      try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
          writer.println("Joueur 1 : " + jeu.getNomJoueur1()+";");
          writer.println("Joueur 2 : " + jeu.getNomJoueur2()+";");
          writer.println("Grille :");
          for (int i = 0; i < jeu.getRows()-1; i++) {
              for (int j = 0; j < jeu.getCols()-1; j++) {
                  writer.print(jeu.getGrille()[i][j]);
              }
              writer.println();
          }
          writer.println("Joueur actuel : " + jeu.getJoueurActuel()+";");
          writer.println("Points Joueur 1 : " + jeu.getPointsJoueur1()+";");
          writer.println("Points Joueur 2 : " + jeu.getPointsJoueur2()+";");
      }
   }

}
   
