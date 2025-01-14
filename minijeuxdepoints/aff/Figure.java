package aff;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Figure {

    public Figure() {
    }

    public static boolean isPointInsideFigure(List<Point> liste_debut, Point pt) {
        if (liste_debut.size() < 4) {
            return false;
        }
        List<Polygon> polygons = new ArrayList<>();
        
        for (Point p : liste_debut) {
            List<Point> closedFigure = Node.findClosedFigure(liste_debut, p);
            if (closedFigure.size() >= 4) {
                Polygon polygon = BecomeFigure(closedFigure);
                polygons.add(polygon);
            }
        }
                
        for (int i = 0; i < polygons.size() - 1; i++) {
            if (tousPointsPresent(polygons.get(i + 1), polygons.get(i))) {
                polygons.remove(i);
                i--;
        }
        }
        for (Polygon polygon : polygons) {
            if (polygon.contains(pt)) {
                return true;
            }
        }

        return false;
    }

    public static Polygon theFigure(List<Point> liste_debut, Point pt) {
        if (liste_debut.size() < 4) {
            return null;
        }
        List<Polygon> polygons = new ArrayList<>();
        for (Point p : liste_debut) {
            List<Point> closedFigure = Node.findClosedFigure(liste_debut, p);
            if (closedFigure.size() >= 4) {
                Polygon polygon = BecomeFigure(closedFigure);
                polygons.add(polygon);
            }
        }
        for (int i = 0; i < polygons.size() - 1; i++) {
            if (tousPointsPresent(polygons.get(i + 1), polygons.get(i))) {
                polygons.remove(i);
                i--;
            }
        }
        for (Polygon polygon : polygons) {
            if (polygon.contains(pt)) {
                return polygon;
            }
        }

        return null;
    }

    public static List<Point> listtheFigure(List<Point> liste_debut, Point pt) {
        List<List<Point>> list = new ArrayList<>();
        if (liste_debut.size() < 4) {
            return null;
        }
        List<Polygon> polygons = new ArrayList<>();
        for (Point p : liste_debut) {
            List<Point> closedFigure = Node.findClosedFigure(liste_debut, p);
            if (closedFigure.size() >= 4) {
                Polygon polygon = BecomeFigure(closedFigure);
                list.add(closedFigure);
                polygons.add(polygon);
            }
        }
        for (int i = 0; i < polygons.size() - 1; i++) {
            if (tousPointsPresent(polygons.get(i + 1), polygons.get(i))) {
                polygons.remove(i);
                i--;
            }
        }
        for (int i=0 ; i<polygons.size() ; i++) {
            if (polygons.get(i).contains(pt)) {
                return list.get(i);
            }
        }

        return null;
    }


    private static Polygon BecomeFigure(List<Point> list) {
        int[] xPoints = list.stream().mapToInt(p -> (int) p.getX()).toArray();
        int[] yPoints = list.stream().mapToInt(p -> (int) p.getY()).toArray();
        return new Polygon(xPoints, yPoints, list.size());
    }

    private static boolean tousPointsPresent(Polygon polygon1, Polygon polygon2) {
        for (int i = 0; i < polygon2.npoints; i++) {
            if (!polygon1.contains(polygon2.xpoints[i], polygon2.ypoints[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static Polygon polygoneCorrespondant(List<Point> points1, List<Point> points2) {
        for (Point p : points2) {
            Polygon poly = Figure.theFigure(points1, p);
            if (poly != null) {
                return poly;
            }
        }
        return null;
    }
    public static List<Point> listCorrespondant(List<Point> points1, List<Point> points2) {
        for (Point p : points2) {
            Polygon poly = Figure.theFigure(points1, p);
            List<Point> lis= listtheFigure(points2, p);
            if (poly != null) {
                return lis;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Point> pointsJoueur1 = new ArrayList<>();
        pointsJoueur1.add(new Point(4, 5));
        pointsJoueur1.add(new Point(3, 6));
        pointsJoueur1.add(new Point(3, 4));
        pointsJoueur1.add(new Point(2, 5));
        pointsJoueur1.add(new Point(2, 3));

        List<Point> points = new ArrayList<>();
        points.add(new Point(3, 2));
        points.add(new Point(3, 4));
        points.add(new Point(3, 5));

        for (Point p : pointsJoueur1) {
            List<Point> closedFigure = Node.findClosedFigure(pointsJoueur1, p);
            if (!closedFigure.isEmpty()) {
                System.out.println("Figure");
                System.out.println(closedFigure);
            }
        }
    }

}
