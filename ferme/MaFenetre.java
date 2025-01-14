package aff;

import javax.swing.*;
import java.awt.*;
import maison.*;
import java.util.Vector;
import maison.*;
import zavamananaina.*;
public class MaFenetre extends JFrame  {

	
    public JButton creerBouton(String nomBouton, Class<?> classe, Dessin a) {
        JButton bouton = new JButton(nomBouton);
        bouton.addMouseListener(new BoutonEcoute(classe,a));
        return bouton;
    }
	
	public MaFenetre(Vector <Zavamananaina> a, Maison maison_provisoire) throws Exception {
		setTitle("Trano");
		setSize((int) maison_provisoire.getWidth()+100,(int) maison_provisoire.getHeight()+100);
		
		Dessin d = new Dessin(a,maison_provisoire);
		// Crée un JPanel pour contenir les boutons
        JPanel panelBoutons = new JPanel();
        
        // Crée les boutons
        JButton bouton1 = creerBouton("Olombelona",Olombelona.class,d) ;
        JButton bouton2 = creerBouton("Chien",Chien.class,d) ;
        JButton bouton3 = creerBouton("Chat",Chat.class,d) ;
        JButton reverse= new JButton();
        reverse.addMouseListener(new ReverseEcoute(d));
        reverse.setText("Renverse");
        
        // Ajoute les boutons au JPanel
        panelBoutons.add(bouton1);
        panelBoutons.add(bouton2);
        panelBoutons.add(bouton3);
        panelBoutons.add(reverse);
        
        // Définit le gestionnaire de mise en page du JFrame
        setLayout(new BorderLayout());
        
        // Ajoute le JPanel contenant les boutons en haut de la fenêtre
        add(panelBoutons, BorderLayout.NORTH);
        


		d.setLayout(new FlowLayout());
		add(d);
		d.addMouseListener(new Ecoute(d,a));
		
		
        setVisible(true);
	}

}