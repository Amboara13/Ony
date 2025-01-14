package zavamananaina;
import maison.*;
public class Chien extends Zavamananaina
{
    String fichierSauvegarde;
    String mivovoha()
    {
        return "ouaf ouaf" ;
    }
    public String getMivovoha()
    {
        return this.mivovoha();
    }

    public Chien (double a, double b,int d ,int e , String c)
    {
        super(a , b ,d ,e ,c);
        this.fichierSauvegarde="Chien.txt";
    }
}