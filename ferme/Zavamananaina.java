package maison;

import java.awt.*;
import java.util.Vector;

public class Zavamananaina 
{
    String nom;
    double x,y,xo,yo,bonheur,vie;
    int dx,dy,encharge;
    char sexe; 
    Vector<Zavamananaina> zanaka;
    public Vector<Zavamananaina> getZanaka()
    {
        return this.zanaka;
    }
    public void setEncharge(int a)
    {
        this.encharge=a;
    }
    public int getEncharge()
    {
        return this.encharge;
    }
    public void setSexe(char a)
    {
        this.sexe=a;
    }
    public char getSexe()
    {
        return this.sexe;
    }
    public void setNom(String a)
    {
        this.nom=a;
    }
    public void setX(double a)
    {
        this.x=a;
    }
    public void setY(double a)
    {
        this.y=a;
    }
    public void  setDx(int a)
    {
        this.dx =a;
    }
    public void  setDy(int a)
    {
        this.dy =a;
    }
    public void  setXo(double a)
    {
        this.xo =a;
    }
    public void  setYo(double a)
    {
        this.yo =a;
    }
    public double  getXo()
    {
        return this.xo;
    }
    public double  getYo()
    {
        return this.yo;
    }
    public  void setVie(double a)
    {
        this.vie=a;
    }
    public  void setBonheur(double a)
    {
        this.bonheur=a;
    }
    public  double getBonheur()
    {
        return this.bonheur;
    }
    public String getNom()
    {
        return this.nom;
    }
    public double getX()
    {
        return this.x;
    }
    public double getY()
    {
        return this.y;
    }
    public int  getDx()
    {
        return this.dx ;
    }
    public int  getDy()
    {
        return this.dy ;
    }
    public double getVie()
    {
        return this.vie;
    }
    public Zavamananaina (double a, double b,int d ,int e , String c)
    {
        this.setX(a);
        this.setY(b);
        this.setNom(c);
        this.setVie(10);
        this.setBonheur(20);
        this.setDx(d);
        this.setDy(e);
        this.setEncharge(1);
    }
    public void mouvementRectiligne( Maison maison_provisoire )
    {
        double xo=this.getX();
        double yo=this.getY();

        double x,y;

        while(this.getX()<maison_provisoire.getWidth() && this.getY()<maison_provisoire.getHeight())
        {
            this.setX(this.getX()+this.getDx());
            this.setY(this.getY()+this.getDy());

            if(this.getX()+this.getDx()<0 || this.getX()+this.getDx()>maison_provisoire.getWidth())
            {
                if(this.getX()+this.getDx()<0){x=0;}else{ x=maison_provisoire.getWidth();}
                
                this.setY(yo+this.getDy()*((x-xo)/this.getDx()));
                this.setX(x);
                System.out.println("("+this.getX()+";"+this.getY()+")");

                break;
            }

            if(this.getY()+this.getDy()<0 || this.getY()+this.getDy()>maison_provisoire.getHeight())
            {
                if(this.getY()+this.getDy()<0){y=0;}else{ y=maison_provisoire.getHeight();}
                this.setX(xo+this.getDx()*((y-yo)/this.getDy()));
                this.setY(y);                  
                System.out.println("("+this.getX()+";"+this.getY()+")");
                break;
            }
            System.out.println("("+this.getX()+";"+this.getY()+")");
        }
    }
    public void mouvementInfini(Maison maison_provisoire)
    {
        while (1<2)
        {   
            this.mouvementRectiligne(maison_provisoire);

            if(this.getX()==maison_provisoire.getWidth() || this.getX()==0 )
            {
                this.setDx(this.getDx()*(-1));
            }
            if(this.getY()==maison_provisoire.getHeight() || this.getY()==0 )
            {
                this.setDy(this.getDy()*(-1));
            }
            this.setX(this.getX()+this.getDx());
            this.setY(this.getY()+this.getDy());  
            System.out.println("("+this.getX()+";"+this.getY()+")");    
        }            
    }
    public String toString() {
        // Créez une chaîne de caractères représentant les attributs de Zavamananaina
        StringBuilder sb = new StringBuilder();
        sb.append("Nom: ").append(nom).append(", ");
        sb.append("X: ").append(x).append(", ");
        sb.append("Y: ").append(y).append(", ");
        sb.append("DX: ").append(dx).append(", ");
        sb.append("DY: ").append(dy).append(", ");
        sb.append("Xo: ").append(xo).append(", ");
        sb.append("Yo: ").append(yo).append(", ");
        sb.append("Bonheur: ").append(bonheur).append(", ");
        sb.append("Vie: ").append(vie).append(", ");
        sb.append("Sexe: ").append(sexe).append(", ");
        sb.append("Encharge: ").append(encharge).append(", ");
        sb.append("Zanaka: ").append(zanaka).append(", ");
        // Ajoutez d'autres attributs au besoin

        return sb.toString();
    }
}