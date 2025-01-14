package aff;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import maison.*;
import java.awt.geom.Ellipse2D;
import java.util.Vector;
import zavamananaina.*;

public class Dessin extends JPanel 
{
    double x, y,  xo, yo;
	int dx, dy;
    Vector<Entana> meuble = new Vector<>();
    int maison_largeur, maison_hauteur;
	Vector<Zavamananaina> debut = new Vector<>();
    Zavamananaina[] mpifankatia= new Zavamananaina[2];
    double[] point= new double[2];
    int isa_sex=0;
    char[] sex={'L','V'};
    
    public void chargerInstances(String nomFichier) {
    try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Créez une nouvelle instance à partir de la ligne de texte et ajoutez-la à votre vecteur debut
            Zavamananaina instance = createInstanceFromLine(line); // Vous devez implémenter cette méthode
            debut.add(instance);
        }
        // Affichez les instances chargées avec succès à des fins de débogage
        System.out.println("Instances chargées avec succès : " + debut);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private Zavamananaina createInstanceFromLine(String line) {
        // Divisez la ligne de texte pour récupérer les attributs individuels de l'instance
        String[] attributes = line.split(", ");

        // Extrayez les valeurs des attributs
        String nom = attributes[0].substring(attributes[0].indexOf(": ") + 2);
        double x = Double.parseDouble(attributes[1].substring(attributes[1].indexOf(": ") + 2));
        // Parsez les autres attributs de manière similaire...

        // Créez une nouvelle instance de Zavamananaina avec les valeurs des attributs extraits
        Zavamananaina instance = new Zavamananaina(x, y,dx ,dy , nom); // Vous devez remplacer ... avec les autres attributs
        // Assurez-vous de créer le constructeur approprié dans votre classe Zavamananaina

        return instance;
    }
    public void ajouterZavamananaina(Zavamananaina zavamananaina) {
    debut.add(zavamananaina);
    repaint();

    }
    public Dessin(Vector<Zavamananaina> zavamananainas, Maison maison_provisoire) 
    {
        this.maison_largeur = (int) maison_provisoire.getWidth();
        this.maison_hauteur = (int) maison_provisoire.getHeight();
        this.meuble = maison_provisoire.getEnsembleEntana();
		this.debut=zavamananainas;
        point[0]=0;
        point[1]=maison_provisoire.getHeight()*4/5;
        chargerInstances("Chat.txt"); // Charge les instances de Chat
        chargerInstances("Olombelona.txt"); // Charge les instances de Olombelona
        chargerInstances("Chien.txt"); // Charge les instances de Olombelona

        for (Zavamananaina zavamananaina : zavamananainas) 
        {
            zavamananaina.setXo(zavamananaina.getX());
            zavamananaina.setYo(zavamananaina.getY());
            zavamananaina.setDx(zavamananaina.getDx());
            zavamananaina.setDy(zavamananaina.getDy());
        }
    }

        
    public void paint(Graphics g)  
    {
        super.paint(g);
        Graphics2D mais = (Graphics2D) g;
        mais.setStroke(new BasicStroke(5));
        mais.setColor(Color.BLUE);
        mais.drawRect(0, 0, maison_largeur + 50, maison_hauteur + 50);

        for (Entana entana : meuble) 
        {
            if (entana.getGenre() == 0) 
            {
                g.setColor(Color.RED);
            }
            if (entana.getGenre() == 1) 
            {
                g.setColor(Color.GREEN);
            }
            if (entana.getGenre() == 2) 
            {
                g.setColor(new Color(128, 0, 128));
            }
            g.fillRect((int) entana.getX(), (int) entana.getY(), (int) entana.getWidth(), (int) entana.getHeight());
        }
        for (Zavamananaina zavamananaina : debut) 
        {
            double x = zavamananaina.getX();
            double y = zavamananaina.getY();
            int dx = zavamananaina.getDx();
            int dy = zavamananaina.getDy();
            int dx_debut=dx;
            int dy_debut=dy;
            int charge=zavamananaina.getEncharge();
            Entana meubleCible = null;
            //x et y
            //System.out.println(zavamananaina.getNom());
            //System.out.println("Entree, charge="+charge);
            if(!( zavamananaina.getEncharge()==1 && zavamananaina instanceof Olombelona) && zavamananaina.getVie() <=3)
            {
                // Initialiser les meubles

                if (zavamananaina instanceof Chien) 
                {
                    for (Entana entana : meuble) 
                    {
                        if (entana.getGenre() == 1) 
                        {
                            meubleCible = entana;
                            break;
                        }
                    }
                } 
                else if (zavamananaina instanceof Chat) 
                {
                    for (Entana entana : meuble) 
                    {
                        if (entana.getGenre() == 2) 
                        {
                            meubleCible = entana;
                            break;
                        }
                    }
                }

                //deplacemet vers cible               
                //x
                 
                dx=2;
                if(zavamananaina.getX()+dx>=meubleCible.getX())
                {
                    x=meubleCible.getX()+5;
                }
                else
                {
                    x=x+dx;
                }

                //y
                if(zavamananaina.getY()>meubleCible.getY())
                {
                    dy=(-2);
                }
                else if(zavamananaina.getY()<meubleCible.getY())
                {
                    dy=2;
                }
                else{
                    dy=0;
                }
                    y=y+dy;

                if(Math.abs(meubleCible.getX()-x) <10 && Math.abs(meubleCible.getY()-y) <10 )
                {
                    dx=0;
                    dy=0;
                    zavamananaina.setEncharge(0);
                    ////System.out.println("tafiditra");
                }
                ////System.out.println("Maintenant, dx="+dx+" et dy="+dy);
            }
            else
            {
                if(zavamananaina.getEncharge()==0)
                {
                    dx=0;
                    dy=0;
                }
                else{
                    dx=dx_debut;
                    dy=dy_debut;
                }
                

                if(x+dx<0)
                {
                    x=0;
                }
                else if(x+dx>maison_largeur)
                {
                    x=maison_largeur;
                }
                else if(y+dy<0)
                {
                    y=0;
                }
                else if(y+dy>maison_hauteur)
                {
                    y=maison_hauteur;
                }else{
                    x = x + dx;
                    y = y + dy;
                }
            }
            //System.out.println("Sortie, charge="+charge);
            zavamananaina.setX(x);
            zavamananaina.setY(y);
            
           // //System.out.println("(" + x + ";" + y + ")");
            
            
            //affichage joli
            Graphics2D boule = (Graphics2D) g;
            
            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, 50, 50);
			if(zavamananaina instanceof Chien)
			{
				boule.setColor(Color.GREEN);
                boule.draw(oval);
                boule.setColor(Color.WHITE);
                boule.fill(oval);
			}
			if(zavamananaina instanceof Chat)
			{
				boule.setColor(new Color(128, 0, 128));
                boule.draw(oval);
                boule.setColor(Color.WHITE);
                boule.fill(oval);
			}
            else
            {
                if(zavamananaina.getSexe()=='L')
                {
                    boule.setColor(Color.WHITE);
                    boule.draw(oval);
                    boule.setColor(Color.BLACK);
                    boule.fill(oval);
                }
                if(zavamananaina.getSexe()=='V')
                {
                    boule.setColor(Color.WHITE);
                    boule.draw(oval);
                    boule.setColor(Color.PINK);
                    boule.fill(oval);
                }
            }
            if(zavamananaina instanceof Olombelona && zavamananaina.getSexe()=='L')
            {
                boule.setColor(Color.WHITE);    
            }else
            { 
                boule.setColor(Color.BLACK); 
            }
			      
			Font font = new Font("Arial", Font.BOLD, 14);
			boule.setFont(font);
            if(zavamananaina.getBonheur()==0)
            {
                boule.drawString("0", (int) Math.round(x + 15.0), (int) Math.round(y + 15.0));
            }
            if(zavamananaina.getBonheur()>=20)
            {
                boule.drawString("20", (int) Math.round(x + 15.0), (int) Math.round(y + 15.0));
            }else{
                boule.drawString(String.format("%.1f", zavamananaina.getBonheur()), (int) Math.round(x + 15.0), (int) Math.round(y + 15.0));
            }
            if(!(zavamananaina instanceof Olombelona))
            {
                boule.setColor(Color.BLUE);
                if(zavamananaina.getVie()==10)
                {
                    boule.drawString("10", (int) Math.round(x + 15.0), (int) Math.round(y + 40.0));     
                }
                else{
                    if(zavamananaina.getVie()<0)
                    {
                        zavamananaina.setVie(0);
                    }
                boule.drawString(String.format("%.1f", zavamananaina.getVie()), (int) Math.round(x + 15.0), (int) Math.round(y + 40.0)); // Dessiner juste en dessous du Bonheur
                }
                if(zavamananaina.getEncharge()==0)
                {
                    zavamananaina.setVie(zavamananaina.getVie()+0.1);
                    if(zavamananaina.getVie()>=10)
                    {
                    zavamananaina.setEncharge(1);
                    }
                }
                else{zavamananaina.setVie(zavamananaina.getVie()-0.01);}
                
            }
            
            //dx et dy
            if (x == 0 && y == this.maison_hauteur) 
            {
                zavamananaina.setDx(Math.abs(zavamananaina.getDx()));
                zavamananaina.setDy((-1) * Math.abs(zavamananaina.getDy()));
            } else if (x == this.maison_largeur && y == 0) 
            {
                zavamananaina.setDx((-1) * Math.abs(zavamananaina.getDx()));
                zavamananaina.setDy(Math.abs(zavamananaina.getDy()));
            } else if (x == 0 && y == 0) 
            {
                zavamananaina.setDx(Math.abs(zavamananaina.getDx()));
                zavamananaina.setDy(Math.abs(zavamananaina.getDy()));
            } else if (x == this.maison_largeur && y == this.maison_hauteur) 
            {
                zavamananaina.setDx((-1) * Math.abs(zavamananaina.getDx()));
                zavamananaina.setDy((-1) * Math.abs(zavamananaina.getDy()));
            } else if (x == 0 || x == this.maison_largeur)
            {
                zavamananaina.setDx(-zavamananaina.getDx());
            } else if (y == 0 || y == this.maison_hauteur) 
            {
                zavamananaina.setDy(-zavamananaina.getDy());
            }
			
            //diminution bohneur chat
            if (zavamananaina instanceof Chien) 
            {			
                for (Zavamananaina other : debut) 
                {

					if (other instanceof Chat && (Math.abs(other.getX()-zavamananaina.getX()))<=55 && (Math.abs(other.getY()-zavamananaina.getY()))<=55) 
					{
                        other.setBonheur(other.getBonheur() - 0.5);
                    }
                }
            }
            //augmentation bohneur chat
            if (zavamananaina instanceof Olombelona) 
            {
                for (Zavamananaina other : debut) 
                {
					if (other instanceof Chat && (Math.abs(other.getX()-zavamananaina.getX()))<=55 && (Math.abs(other.getY()-zavamananaina.getY()))<=55) 
					{
                        if(other.getBonheur()<20)
                        {
                            other.setBonheur(other.getBonheur() + 0.5);
                        }
                    }
                }
            }
             //bohneur humain
            if (zavamananaina instanceof Olombelona) 
            {
                
                for (Zavamananaina other : debut) 
                {
					if(zavamananaina != other)
                    {
                    if (other instanceof Olombelona && other.getSexe()!= zavamananaina.getSexe() && (Math.abs(other.getX()-zavamananaina.getX()))<=55 && (Math.abs(other.getY()-zavamananaina.getY()))<=55) 
					{
                        if(other.getBonheur()<20)
                        {
                            other.setBonheur(other.getBonheur() + 0.5);
                            mpifankatia[0]=zavamananaina;
                            mpifankatia[1]=other;
                        }
                    }
                    }
                }
                  
                for (Zavamananaina other : debut) 
                {
					if(zavamananaina != other)
                    {
                    if (other instanceof Olombelona && other.getSexe()== zavamananaina.getSexe() && (Math.abs(other.getX()-zavamananaina.getX()))<=55 && (Math.abs(other.getY()-zavamananaina.getY()))<=55) 
					{
                        if(other.getBonheur()<20)
                        {
                            other.setBonheur(other.getBonheur() - 0.5);
                        }
                    }
                    }
                }
                if(zavamananaina.getBonheur()>=20)
                {
                    for (Entana entana : meuble) 
                    {
                        if (entana.getGenre() == 0) 
                        {
                            meubleCible = entana;
                            break;
                        }
                    }
                

                //deplacemet vers cible               
                //x
                 
                dx=-2;
                if(zavamananaina.getX()+dx<=meubleCible.getX())
                {
                    x=meubleCible.getX();
                }
                else
                {
                    x=x+dx;
                }

                //y
                if(zavamananaina.getY()>meubleCible.getY())
                {
                    dy=(-2);
                }
                else if(zavamananaina.getY()<meubleCible.getY())
                {
                    dy=2;
                }
                else{
                    dy=0;
                }
                if(zavamananaina.getY()+dy>=meubleCible.getY())
                {
                    y=meubleCible.getY();
                }  
                else{
                    y=y+dy;
                }
                   


                if(Math.abs(meubleCible.getX()-x) <10 && Math.abs(meubleCible.getY()-y) <10 )
                {
                    dx=0;
                    dy=0;
                    if(mpifankatia[0].getX()==meubleCible.getX() && mpifankatia[0].getY()==meubleCible.getY() && mpifankatia[1].getX()==meubleCible.getX() && mpifankatia[1].getY()==meubleCible.getY() )
                    {
                        mpifankatia[0].setBonheur(0);
                        mpifankatia[1].setBonheur(0);
                        char son_sexe;
                        if(isa_sex %2 ==0)
                        {
                            son_sexe=sex[0];
                        }
                        else{
                            son_sexe=sex[1];
                        }
                        isa_sex++;
                        this.debut.add(new Olombelona(point[0],point[1],"Zanaka",son_sexe));
                        if(point[0]>=maison_largeur)
                        {
                            point[1]=point[1]-70;
                            point[0]=0;
                        }
                        else{
                            point[0]=point[0]+70;
                        }
                      

                        repaint();
                    }
                   
                }
                    zavamananaina.setX(x);
                    zavamananaina.setY(y);
                }

            }
            
        }

        try {
            Thread.sleep(50);
        } catch (Exception e) {
        }
         SauvegardeInstances sauvegarde = new SauvegardeInstances();
        sauvegarde.sauvegarderInstances(debut, Chat.class, "Chat.txt");
        sauvegarde.sauvegarderInstances(debut, Chien.class, "Chien.txt");
        sauvegarde.sauvegarderInstances(debut, Olombelona.class, "Olombelona.txt");
        repaint();
    }
    Vector<Zavamananaina> getDebut()
    {
        return this.debut;
    }
}
