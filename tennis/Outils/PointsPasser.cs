using System;
using System.Collections.Generic;
using System.Drawing; // Utilisation de Point qui fait partie de la bibliothèque System.Drawing

namespace Outils
{
public class PointsPasser
{
    // Fonction pour calculer le pgcd (plus grand commun diviseur)
    static int GCD(int a, int b)
    {
        while (b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Fonction pour trouver tous les points entiers sur le segment AB
    public static List<Point> ToutPointsPasser(Point A, Point B)
    {
        List<Point> points = new List<Point>();

        // Calcul des différences
        int dx = B.X - A.X;
        int dy = B.Y - A.Y;

        // Calcul du pgcd des différences des coordonnées
        int n = GCD(Math.Abs(dx), Math.Abs(dy));

        // Trouver les incréments dans les directions x et y
        int stepX = dx / n;
        int stepY = dy / n;

        // Générer tous les points du segment
        for (int i = 0; i <= n; i++)
        {
            int x = A.X + i * stepX;
            int y = A.Y + i * stepY;
            points.Add(new Point(x, y));
        }


        return points;
    }

    public static List<Point> FiltrePositif(Point A, Point B)
    {
        List<Point> allPoints = ToutPointsPasser(A, B);
        return allPoints.Where(p => p.X >= 0 && p.Y >= 0).ToList();
    }

      public static List<Point> TriPointY(List<Point> points, int ordre)
    {
        // Vérifier si l'ordre est 0 (croissant) ou 1 (décroissant)
        if (ordre == 0)
        {
            // Trier par Y de manière croissante
            points.Sort((p1, p2) => p1.Y.CompareTo(p2.Y));
        }
        else if (ordre == 1)
        {
            // Trier par Y de manière décroissante
            points.Sort((p1, p2) => p2.Y.CompareTo(p1.Y));
        }
        else
        {
            throw new ArgumentException("L'argument 'ordre' doit être 0 pour ascendant ou 1 pour descendant.");
        }

        return points;
    }

}
}