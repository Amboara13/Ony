package aff;
import javax.swing.*;
import java.awt.*;
import maison.*;
import zavamananaina.*;
import java.util.Vector;
//import java.util.Scanner;
public class Test {
  public static void huhu() {
  }
  public static void main(String[] args) throws Exception {
    // huhu();
    Vector <Entana> obstacle= new Vector<>();
    obstacle.add(new Entana(20,560,140,70,0));
    obstacle.add(new Entana(570,20,60,60,1));
    obstacle.add(new Entana(570,500,60,60,2));
    Vector <Zavamananaina> velona =new Vector<>();
    velona.add(new Chien(0,0,7,1,"Bob"));
    velona.add(new Chat(0,0,1,11,"Mimi"));
    velona.add(new Olombelona(23,43,"Paul",'L'));
    velona.add(new Olombelona(500,583,"Lea",'V'));
    Maison maison_provisoire=new Maison(0,0,600,600,obstacle,velona);
    MaFenetre mf = new MaFenetre(velona, maison_provisoire);
    mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  }
}