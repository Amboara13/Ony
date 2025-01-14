package aff;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import maison.*;


public class Ecoute implements MouseListener {
    Dessin dessin;
    Vector<Zavamananaina> zavamananainas;
	Zavamananaina selectedZavamananaina;
    
    public Ecoute(Dessin dessin, Vector<Zavamananaina> zavamananainas) {
        this.dessin = dessin;
        this.zavamananainas = zavamananainas;
    }
	
    public void mouseClicked(MouseEvent e)
	{
		if(selectedZavamananaina!=null)
		{
			selectedZavamananaina.setX(e.getX());
			selectedZavamananaina.setY(e.getY());
			selectedZavamananaina =null;
		}
		else{
        // Vérifier si un Zavamananaina est sélectionné
        	selectedZavamananaina = getSelectedZavamananaina(e.getX(), e.getY());
        	if(selectedZavamananaina != null)
			{
			System.out.println("Zavamananaina sélectionné: " + selectedZavamananaina.getNom());
			}
			else {
                // Aucun Zavamananaina sélectionné, donc considérer le deuxième clic comme un point
                // Faire quelque chose avec les coordonnées du point sélectionné
                System.out.println("Point sélectionné: (" + e.getX() + ", " + e.getY() + ")");
            }
        }
    }

    // Méthode pour vérifier si un Zavamananaina est sélectionné aux coordonnées spécifiées
    private Zavamananaina getSelectedZavamananaina(double x, double y) {
        for (Zavamananaina zavamananaina : zavamananainas) {
            // Comparer les coordonnées avec la position du Zavamananaina
            if (Math.abs(zavamananaina.getX()-x)<50 && Math.abs(zavamananaina.getY()-y)< 50 ) {
                return zavamananaina;
            }
        }
        return null; // Aucun Zavamananaina sélectionné aux coordonnées spécifiées
    }

    // Implémenter les autres méthodes de MouseListener si nécessaire



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