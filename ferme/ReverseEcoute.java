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
class ReverseEcoute implements MouseListener {
    
    private Dessin d;

    public ReverseEcoute(Dessin a) {
        this.d=a;
    }

    public void mouseClicked(MouseEvent e)
	{
        try 
        {   
            Vector<Zavamananaina> vivant=d.getDebut();
            for(Zavamananaina tout: vivant)
            {
                tout.setDx((-1)*tout.getDx());
                tout.setDy((-1)*tout.getDy());
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
