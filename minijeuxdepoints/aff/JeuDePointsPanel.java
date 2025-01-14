package aff;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JeuDePointsPanel extends JPanel {

    private  int ROWS = 30;
    private  int COLS = 30;

    private  int CELL_SIZE = 50;
    private RoundButton[][] boutons;
    private char[][] grille;
    private List<Point> pointsJoueur1;
    private List<Point> pointsJoueur2;
    private char joueurActuel = 'X';
    private String nomJoueur1;
    private String nomJoueur2;
    private Polygon polygoneGagnant;
    private char[][] grillesauvegarde;
    List<Polygon> polygoneDe1 = new ArrayList<>();
    List<Polygon> polygoneDe2 = new ArrayList<>();
    List<Integer> totalPointDe1 = new ArrayList<>();
    List<Integer> totalPointDe2 = new ArrayList<>();

    private int fontSize = 16; // Taille de la police par défaut
    public int getRows()
    {
        return this.ROWS;
    }
    public int getCols()
    {
        return this.COLS;
    }
    public JeuDePointsPanel() {
        String nom = "sauvegarde.txt";
        String jouer1=ExtractionDonnees.extraireDonneesnomJoueur1(nom) ;
        String jouer2=ExtractionDonnees.extraireDonneesnomJoueur2(nom) ;
        setNomJoueur1(jouer1.substring(0, jouer1.length() - 1));
        setNomJoueur2(jouer2.substring(0, jouer2.length() - 1));
        this.grillesauvegarde=(char[][]) ExtractionDonnees.extraireDonneesGrill(nom,this.ROWS, this.COLS);
        setJoueurActuel((char) ExtractionDonnees.extraireDonneesJoueuractuel(nom)); 
        this.pointsJoueur1 = new ArrayList<>();
        this.pointsJoueur2 = new ArrayList<>();
        this.grille = new char[ROWS - 1][COLS - 1];
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setLayout(null);
        initializeGridSauvegarde();
    }
    public JeuDePointsPanel(String nomJoueur1, String nomJoueur2) {
        this.nomJoueur1 = nomJoueur1;
        this.nomJoueur2 = nomJoueur2;
        this.pointsJoueur1 = new ArrayList<>();
        this.pointsJoueur2 = new ArrayList<>();
        this.grille = new char[ROWS - 1][COLS - 1];

        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setLayout(null);
        initializeGrid();
    }

    private void initializeGridSauvegarde() {

        boutons = new RoundButton[ROWS - 1][COLS - 1];
        for (int row = 0; row < ROWS - 1; row++) {
            for (int col = 0; col < COLS - 1; col++) {
                RoundButton button = new RoundButton(this, row, col);
                this.grille[row][col] = this.grillesauvegarde[row][col];
                button.setPreferredSize(new Dimension(CELL_SIZE / 3, CELL_SIZE / 3));
                int x = col * CELL_SIZE + CELL_SIZE / 3;
                int y = row * CELL_SIZE + CELL_SIZE / 3;
                button.setBounds(x, y, CELL_SIZE / 3, CELL_SIZE / 3);
                add(button);
                boutons[row][col] = button;
                if (grille[row][col] == 'X' || grille[row][col] == 'O') {
                    this.jouerCoup(row, col);
                }
            }
        }
    }
    private void initializeGrid() {
        boutons = new RoundButton[ROWS - 1][COLS - 1];
        for (int row = 0; row < ROWS - 1; row++) {
            for (int col = 0; col < COLS - 1; col++) {
                RoundButton button = new RoundButton(this, row, col);
                this.grille[row][col] = '.';
                button.setPreferredSize(new Dimension(CELL_SIZE / 3, CELL_SIZE / 3));
                int x = col * CELL_SIZE + CELL_SIZE / 3;
                int y = row * CELL_SIZE + CELL_SIZE / 3;
                button.setBounds(x, y, CELL_SIZE / 3, CELL_SIZE / 3);
                add(button);
                boutons[row][col] = button;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int x = 10; 
        int y = 455;

        if(totalPointDe1.size()>0)
        {
            g.drawString(nomJoueur1+" : "+ Node.total_point(totalPointDe1), x, y + fm.getAscent());
            drawGrid(g);
        }
        else{
            g.drawString(nomJoueur1, x, y + fm.getAscent());
            drawGrid(g);
        }
        g.setColor(Color.red);
         x = 370; 
         y = 455;

        if(totalPointDe2.size()>0)
        {
            g.drawString(nomJoueur2+" : "+ Node.total_point(totalPointDe2), x, y + fm.getAscent());
            drawGrid(g);
        }
        else{
            g.drawString(nomJoueur2, x, y + fm.getAscent());
            drawGrid(g);
        }
        
        if(polygoneDe1!=null)
        {
            for (Polygon pol1 : polygoneDe1) {
                if(pol1!=null)
                {
                g.setColor(Color.darkGray);
                if(pol1.xpoints!=null)
                {
                    dessinerPolygone(g, pol1.xpoints, pol1.ypoints, pol1.npoints);
                }
            }
                
            }     
        }

        if(polygoneDe2!=null)
        {
            for (Polygon pol2 : polygoneDe2) {
                if(pol2!=null)
                {
                    g.setColor(Color.red);
                    if(pol2.xpoints!=null)
                    {
                        dessinerPolygone(g, pol2.xpoints, pol2.ypoints, pol2.npoints);
                    }
                }

            }     
        }
       
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int col = 0; col < COLS; col++) {
            int x = col * CELL_SIZE + CELL_SIZE / 2;
            g.drawLine(x, 0, x, (ROWS - 1) * CELL_SIZE);
        }
        for (int row = 0; row < ROWS; row++) {
            int y = row * CELL_SIZE + CELL_SIZE / 2;
            g.drawLine(0, y, (COLS - 1) * CELL_SIZE, y);
        }
    }

    public void jouerCoup(int row, int col) {
        try {
            this.grille[row][col] = this.joueurActuel;
            this.boutons[row][col].setText(Character.toString(this.joueurActuel));
            this.boutons[row][col].setEnabled(false);
            if (this.joueurActuel == 'X') {
                pointsJoueur1.add(new Point(row, col));
            } else {
                pointsJoueur2.add(new Point(row, col));
            }

            if (Node.pointsDeDans(pointsJoueur1, pointsJoueur2)) {
                polygoneGagnant = Figure.polygoneCorrespondant(pointsJoueur1, pointsJoueur2);
                polygoneDe1.add(polygoneGagnant);
                repaint();
                Sauvegarde.sauvegarder(this.nomJoueur1, "gagnants.txt");
                Sauvegarde.sauvegarder(this.nomJoueur2, "perdants.txt");
                int score=Node.nombredePoint(pointsJoueur1, pointsJoueur2);
                totalPointDe1.add(score);
                //afficherMessage(this.nomJoueur1 + " gagne avec "+score);
                Node.retirerPoint(pointsJoueur1, pointsJoueur2);
                this.joueurActuel='O';
                
            }
            if (Node.pointsDeDans(pointsJoueur2, pointsJoueur1)) {
                polygoneGagnant = Figure.polygoneCorrespondant(pointsJoueur2, pointsJoueur1);
                polygoneDe2.add(polygoneGagnant);
                repaint();
                Sauvegarde.sauvegarder(this.nomJoueur2, "gagnants.txt");
                Sauvegarde.sauvegarder(this.nomJoueur1, "perdants.txt");

                int score=Node.nombredePoint(pointsJoueur2, pointsJoueur1);
                totalPointDe2.add(score);
                //afficherMessage(this.nomJoueur1 + " gagne avec "+score);
                Node.retirerPoint(pointsJoueur2, pointsJoueur1);
                this.joueurActuel='X';
            }

            this.joueurActuel = (this.joueurActuel == 'X' ? 'O' : 'X');

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void desactiverBoutons() {
        for (int i = 0; i < boutons.length; i++) {
            for (int j = 0; j < boutons[i].length; j++) {
                boutons[i][j].setEnabled(false);
            }
        }
    }

    private void dessinerPolygone(Graphics g, int[] rows, int[] cols, int size) {
        int[] xPoints = new int[size];
        int[] yPoints = new int[size];


        for (int i = 0; i < size; i++) {
            int row = rows[i];
            int col = cols[i];
            xPoints[i] = boutons[row][col].getX() + boutons[row][col].getWidth() / 2;
            yPoints[i] = boutons[row][col].getY() + boutons[row][col].getHeight() / 2;
        }

        g.drawPolygon(new Polygon(xPoints, yPoints, size));
    }

    private class RoundButton extends JButton {
        private int row;
        private int col;

        public RoundButton(JeuDePointsPanel jeu, int row, int col) {
            this.row = row;
            this.col = col;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            addActionListener(e -> jeu.jouerCoup(row, col));
        }

        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintComponent(g);

            

            if (grille[row][col] == 'X') {
                g.setColor(Color.darkGray);
            } else if (grille[row][col] == 'O') {
                g.setColor(Color.red);
            } else {
                g.setColor(new Color(0, 0, 0, 0));
            }
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(CELL_SIZE / 3, CELL_SIZE / 3);
        }

        Shape shape;

        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
            }
            return shape.contains(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Jeu de Points");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JeuDePointsPanel panel = new JeuDePointsPanel("George", "Bon");
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
    
    public String getNomJoueur1()
    {
        return this.nomJoueur1;
    }
    public String getNomJoueur2()
    {
        return this.nomJoueur2;
    }
    public char[][] getGrille()
    {
        return this.grille;
    }
    public  List<Point> getPointsJoueur1()
    {
        return this.pointsJoueur1;
    }
    public  List<Point> getPointsJoueur2()
    {
        return this.pointsJoueur2;
    }
    public  char getJoueurActuel()
    {
        return this.joueurActuel;
    }
    public void setNomJoueur1(String a)
    {
        this.nomJoueur1=a;
    }
    public void setNomJoueur2(String a)
    {
        this.nomJoueur2=a;
    }
    public void setGrille(char[][] a)
    {
        this.grille=a;
    }
    public  void setPointsJoueur1(List<Point> a)
    {
        this.pointsJoueur1=a;
    }
    public  void setPointsJoueur2(List<Point> a)
    {
        this.pointsJoueur2=a;
    }
    public  void setJoueurActuel(char a)
    {
        this.joueurActuel=a;
    }
}
