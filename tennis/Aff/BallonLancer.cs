using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using System.Threading;
using Outils;

namespace Aff
{
    public class BallonLancer
    {
        public  int Rayon {set ; get;} = 20;
        private int compteurDeplacement = 0;
        private  Brush _ballonBrush = Brushes.Yellow;
        
        public bool Envoyer { get; set; }
        public Terrain Terrain { get; set; }
        public int X { get; set; }
        public bool Position { get; set; }
        public MainForm Forme { get; set; }
        public int Y { get; set; }
        public Trajectoire Trajectoire { get; private set; }
    
        public BallonLancer(int startX, int startY, Terrain terrain, MainForm form)
        {
            Terrain = terrain;
            X = startX + terrain.DecallageBord;
            Y = startY + terrain.DecallageBord;
            Envoyer = false;
            Trajectoire = new Trajectoire(this, terrain);
            Forme = form;
            
        }
        public void Draw(Graphics g)
        {
            if (X < Terrain.DecallageBord || X > Terrain.Longueur + Terrain.DecallageBord ||
                Y < Terrain.DecallageBord || Y > Terrain.Largeur + Terrain.DecallageBord)
            {
                _ballonBrush = Brushes.Red;
            }
        Trajectoire.Draw(g, Y);
        g.FillEllipse(_ballonBrush, X - Rayon, Y - Rayon, Rayon * 2, Rayon * 2);
    }
        private bool estHaut(Terrain terrain)
        {
            bool rep=true;
            if(terrain.Largeur/2>this.Y )
            {
                rep = false;
            }
            return rep;
        }
        public void Move()
    {
    // Vérifie si le ballon n'est pas déjà à l'arrivée
    if (new Point(X, Y) != Trajectoire.ArriverPoint)
    {
        // Récupérer tous les points à passer
        List<Point> points = PointsPasser.FiltrePositif(new Point(X, Y), Trajectoire.ArriverPoint);
        
        Console.WriteLine($"depart: {X}, Y: {Y}");
        Console.WriteLine($"arrivee: {Trajectoire.ArriverPoint.X}, Y: {Trajectoire.ArriverPoint.Y}");

        if (compteurDeplacement < points.Count)
        {
            if (estHaut(Terrain))
            {
                points = PointsPasser.TriPointY(points, 1);
            }
            else
            {
                points = PointsPasser.TriPointY(points, 0);
            }

            X = points[compteurDeplacement].X;
            Y = points[compteurDeplacement].Y;
            Trajectoire.Update(X, Y);
            Console.WriteLine($"X: {X}, Y: {Y}");
            compteurDeplacement++;
        }
        else
    {
        
        X = Trajectoire.ArriverPoint.X;
        Y = Trajectoire.ArriverPoint.Y;
      
        Trajectoire.Update(X, Y);
        Console.WriteLine($"X final: {X}, Y final: {Y}");
        Forme.IsPaused=true;
    }

    }

}
    }
}