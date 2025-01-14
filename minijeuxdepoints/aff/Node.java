package aff;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node extends Point {
    public Node suivant;
    public Node precedent;

    public Node(Point point) {
        super(point);
    }

    public Node getSuivant() {
        return suivant;
    }

    public void setSuivant(Node suivant) {
        this.suivant = suivant;
    }

    public Node getPrecedent() {
        return precedent;
    }

    public void setPrecedent(Node precedent) {
        this.precedent = precedent;
    }

    public static boolean estALaDistanceUn(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) <= 1 && Math.abs(p1.getY() - p2.getY()) <= 1;
    }

    
    
    public static List<Point> findClosedFigure(List<Point> points, Point start) {
        List<Point> passer = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();

        Node nodeStart = null;
        int isa=0;
        for (Point point : points) {
            Node node = new Node(point);
            nodes.add(node);
            if (point.equals(start)) {
                nodeStart = node;
            }
        }
        while (isa<6) {
              Collections.shuffle(points);
            for (Point point : points) {
                Node node = new Node(point);
                nodes.add(node);
                if (point.equals(start)) {
                    nodeStart = node;
                }
            }
            isa++;
        }

        if (nodeStart == null) {
            return new ArrayList<>();
        }

        passer.add(start); 

        Node currentNode = nodeStart;
        Node originalStart = nodeStart;

        while (true) {
            Node nextNode = null;
            boolean found = false;

            
            for (Node node : nodes) {
                if (node != currentNode && estALaDistanceUn(currentNode, node) && !passer.contains(node.getLocation())) {
                   
                    boolean isDiagonal = estDiagonal(currentNode, node);
                    if (isDiagonal) {
                        nextNode = node;
                        System.out.println(nextNode);
                        break; 
                    } else if (nextNode == null) {
                        nextNode = node; 
                    }
                }
            }

            if (nextNode != null) {
                passer.add(nextNode.getLocation());
                currentNode.setSuivant(nextNode);
                nextNode.setPrecedent(currentNode);
                currentNode = nextNode;
                found = true;
            }

            if (!found) {
                if (estALaDistanceUn(originalStart, currentNode) && currentNode.getPrecedent() != originalStart) {
                    List<Point> result = new ArrayList<>();
                    Node temp = originalStart;
                    while (temp != null) {
                        result.add(temp.getLocation());
                        temp = temp.getSuivant();
                        if (temp == originalStart) break;
                    }
                    return result;
                } else {
                    return new ArrayList<>();
                }
            }
        }
    }
    
    public static boolean pointsDeDans(List<Point> points1, List<Point> points2) {
        for (Point p : points2) {
            if (Figure.isPointInsideFigure(points1, p)) {
                return true;
            }
        }
        return false;
    }
    public static void retirerPoint(List<Point> points1, List<Point> points2) {
        for (int i=0 ; i<points2.size() ; i++) {
            if (Figure.isPointInsideFigure(points1, points2.get(i))) {
                points2.remove(i);
            }
        }
    }
    
    public static boolean estDiagonal(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) == 1 && Math.abs(p1.getY() - p2.getY()) == 1;
    }

    public static int nombredePoint(List<Point> points1, List<Point> points2)
    {
        int isa=0;
        for (Point p : points2) {
            if (Figure.isPointInsideFigure(points1, p)) {
                isa++;
            }
        }
        return isa;
    }
    public static int total_point(List<Integer> list)
    {
        int a=list.get(0);
        for(int i=1 ; i<list.size() ; i++)
        {
            a=a+list.get(i);
        }
        return a;
    }
   
    public static boolean rempli(List<Point> polygone , List<Point> joueur1 , List<Point> joueur2)
{
    if(polygone!=null)
    {
        List<Point> devraitETreDedans=devraitETreDedans(polygone);
    for(Point p : devraitETreDedans)
    {
        if(Figure.isPointInsideFigure(joueur1, p))
        {
            continue;
        }
        else if(Figure.isPointInsideFigure(joueur2, p))
        {
            continue;
        }
        else{
            return false;
        }
    }
    
        
    }
    else return false;
    return true;
}
    public static List<Point> devraitETreDedans(List<Point> list )
    {
        
        
        List<Point> toutPointsdedans = new ArrayList<>();
        if(list!=null)
        {

        int[] xPoints = list.stream().mapToInt(p -> (int) p.getX()).toArray();
        int[] yPoints = list.stream().mapToInt(p -> (int) p.getY()).toArray();

        List<Integer> xvalide=new ArrayList<>();
        List<Integer> yvalide=new ArrayList<>();
        
        int minX=findMinimum(xPoints);
        int minY=findMinimum(yPoints);
        int maxX=findMaximum(xPoints);
        int maxY=findMaximum(yPoints);

        while(minX+1<maxX)
        {
            xvalide.add(minX);
            minX++;
        }
        while(minY+1<maxY)
        {
            xvalide.add(minY);
            minY++;
            
        }
        for(int i=0 ; i<xvalide.size() ; i++)
        {
            for(int j=0 ; i<xvalide.size() ; j++)
            {
                toutPointsdedans.add(new Point(xvalide.get(i),yvalide.get(j)));
            }
        }
        return toutPointsdedans;
    }
    else {
        return toutPointsdedans;
    }

    }
    public static int[] toutPointlimite(int[] x) {
        List<Integer> devraitETreDedans = new ArrayList<>();
        int min = findMinimum(x);
        int max = findMaximum(x);
        
        for (int i = min + 1; i < max; i++) {
            devraitETreDedans.add(i);
        }
        
        // Convertir la liste en tableau d'entiers
        return devraitETreDedans.stream().mapToInt(i -> i).toArray();
    }
    
    public static int findMinimum(int[] values) {
        
        // Initialiser la variable pour stocker le minimum
        int min = (int)values[0];
        
        // Parcourir la liste pour trouver le minimum
        for (int i = 1; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        
        return min;
    }
    
    public static int findMaximum(int[] values) {
             
                int min = (int)values[0];
        
                for (int i = 1; i < values.length; i++) {
                    if (values[i] > min) {
                        min = values[i];
                    }
                }
                                
                return min;
            }
    
    
            public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 5));
        points.add(new Point(2, 5));
        points.add(new Point(3, 4));
        points.add(new Point(3, 6));
        points.add(new Point(4, 5));
        points.add(new Point(5, 5));

        for (Point p : points) {
            List<Point> closedFigure = findClosedFigure(points, p); 
            if (closedFigure.size() > 1) {
                System.out.println("Closed Figure: " + closedFigure);
            }
        }
    }
}

*******************************************************************/*888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888////////////////////////////////////////////////////////////////////////////////-----------------*9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999998888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888 */
