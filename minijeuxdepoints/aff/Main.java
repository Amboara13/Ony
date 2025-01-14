// Source code is decompiled from a .class file using FernFlower decompiler.
package aff;

import javax.swing.SwingUtilities;

public class Main {
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              new FenetreDeConnexion();
          }
      });
  }

}
