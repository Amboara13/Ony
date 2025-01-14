package aff;
import zavamananaina.*;
import maison.*;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

// Classe écouteur dynamique
class BoutonEcoute implements MouseListener {
    private Class<?> Classe;
    private Dessin d;
    int isa_olona=0;

    public BoutonEcoute(Class<?> Classe, Dessin a) {
        this.Classe = Classe;
        this.d=a;
    }

   
    public void mouseClicked(MouseEvent e)
	{
        try 
        {
            if (Olombelona.class.isAssignableFrom(Classe)) 
            {
                if(isa_olona%2==0)
                {
                Constructor<?> constructor = Classe.getConstructor(double.class, double.class, String.class , char.class); // Récupérer le constructeur par défaut
                Object instance = constructor.newInstance(23,100,"Olona",'L');
                d.ajouterZavamananaina((Zavamananaina) instance); // Assurez-vous d'avoir une méthode statique pour récupérer le vecteur
                }else{
                    Constructor<?> constructor = Classe.getConstructor(double.class, double.class, String.class , char.class); // Récupérer le constructeur par défaut
                Object instance = constructor.newInstance(23,500,"Olona",'V');
                d.ajouterZavamananaina((Zavamananaina) instance); // Assurez-vous d'avoir une méthode statique pour récupérer le vecteur
                }
                isa_olona++;
            }else
            {  
                Constructor<?> constructor = Classe.getConstructor(double.class,double.class,int.class,int.class,String.class); // Récupérer le constructeur par défaut
                Object instance = constructor.newInstance(0,0,3,10,"Biby"); // Créer une nouvelle instance de la Classe
                d.ajouterZavamananaina((Zavamananaina) instance); // Assurez-vous d'avoir une méthode statique pour récupérer le vecteur
            }
                
            
        } catch (Exception ex) {
            ex.printStackTrace(); // Gérer les exceptions correctement selon vos besoins
        }
    }
    	public void mouseEntered(MouseEvent e) {
		//System.out.println("mouseEntered");
	}
	public void mouseExited(MouseEvent e) {
		//System.out.println("mouseExcited");
	}
	public void mousePressed(MouseEvent e) {
		//System.out.println("mousePressed " + e.getX() + " y = " + e.getY() + "dragged = " + e.MOUSE_DRAGGED);
	}

	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseRelassed");
	}

	
	public void mouseMoved(MouseEvent e) {
		//System.out.println("MouseMoved");
	}
	public void mouseDragged(MouseEvent e) {
		//System.out.println("Draggg " + e.getX() + " et " + e.getY());
	}
}
