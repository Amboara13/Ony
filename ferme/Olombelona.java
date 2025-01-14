package zavamananaina;

import java.util.Vector;

import maison.*;
public class Olombelona extends Zavamananaina
{
    String fichierSauvegarde;
    public Olombelona (double a, double b, String c , char d)
    {
        super(a , b ,0,0,c);
        super.setSexe(d);
        super.setBonheur(0);
        this.fichierSauvegarde = "Olombelona.txt";
    }

}