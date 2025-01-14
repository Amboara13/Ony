using System;
using System.Drawing;

namespace Aff
{
    public class But
    {
        public Rectangle Rectangle { get; set; }  // Représente les dimensions du but

        // Constructeur pour initialiser la position et la taille du but
        public But(int x, int y, int largeur, int hauteur , Terrain terrain)
        {
            Rectangle = new Rectangle(terrain.DecallageBord+x, terrain.DecallageBord+y, largeur, hauteur);
        }

        public void Draw(Graphics g)
        {
            using (Pen pen = new Pen(Color.LightGray, 1) { DashStyle = System.Drawing.Drawing2D.DashStyle.Dot })
            {
                g.DrawRectangle(pen, Rectangle);

                // Dessiner la ligne horizontale du milieu
                int middleY = Rectangle.Y + (Rectangle.Height / 2);
                g.DrawLine(pen, 
                    Rectangle.X,           // x1 
                    middleY,              // y1
                    Rectangle.X + Rectangle.Width, // x2
                    middleY);             // y2

                // Dessiner la ligne verticale du milieu
                int middleX = Rectangle.X + (Rectangle.Width / 2);
                g.DrawLine(pen,
                    middleX,              // x1
                    Rectangle.Y,          // y1
                    middleX,              // x2
                    Rectangle.Y + Rectangle.Height); // y2
            }
        }
    }
}
