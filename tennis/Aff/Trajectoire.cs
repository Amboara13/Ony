using System;
using System.Drawing;

namespace Aff
{
    public class Trajectoire
    {
        // Dernier point de la trajectoire
        private Point _departPoint;
        private BallonLancer Sabal;

        // Point d'arrivée (initialisé à (800, 0))
        private Point _arriverPoint;

        private bool estHaut(Terrain terrain)
        {
            bool rep=true;
            if(terrain.Largeur/2<Sabal.Y )
            {
                rep = false;
            }
            return rep;
        }
        

        // Constructeur pour initialiser la trajectoire
        public Trajectoire(BallonLancer bal,Terrain terrain)
        {
            Sabal = bal;
            _departPoint = Point.Empty; // Initialisation du dernier point à un point vide
            if(estHaut(terrain))
            {
                _arriverPoint = new Point(bal.X, terrain.Largeur+2*terrain.DecallageBord); // Valeur par défaut de arriverPoint
            }
            else{
                _arriverPoint = new Point(bal.X, 0);
            }
        }

        // Getter et Setter pour le dernier point (LastChara)
        public Point DepartPoint
        {
            get { return _departPoint; }
            set { _departPoint = value; }
        }

        // Getter et Setter pour arriverPoint
        public Point ArriverPoint
        {
            get { return _arriverPoint; }
            set { _arriverPoint = value; }
        }

        // Méthode pour mettre à jour le dernier point de la trajectoire
        public void Update(int x, int y)
        {
            _departPoint = new Point(x, y);
        }

        // Méthode pour dessiner la trajectoire
        public void Draw(Graphics g, int currentY)
        {
            using (var dashedPen = new Pen(Color.Yellow,2))
            {
                // Si le dernier point est valide (non vide)
                if (_departPoint != Point.Empty)
                {
                    // Utilisation du point arrivé (arriverPoint) dans le dessin
                    g.DrawLine(dashedPen, _arriverPoint, new Point(_departPoint.X, _departPoint.Y));
                }
            }
        }
    }
}
