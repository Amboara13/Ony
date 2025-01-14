using System;
using System.Drawing;

namespace Aff
{
    public class Terrain
    {
        public int Longueur { get; set; }
        public int Largeur { get; set; }
        public int DecallageBord { get; set; }  // Décalage pour les bords

        public Terrain(int longueur, int largeur)
        {
            Longueur = longueur;
            Largeur = largeur;
            DecallageBord = 50;  // Décalage du bord (si nécessaire)
        }

        // Méthode Draw pour dessiner le terrain avec les lignes et le filet
        public void Draw(Graphics g)
        {
            // Dessiner le terrain avec un fond vert
            g.FillRectangle(Brushes.Green, DecallageBord, DecallageBord, Longueur, Largeur);

            
            // Dessiner le filet au centre du terrain (très discontinu)
            using (Pen netPen = new Pen(Color.White, 5) { DashStyle = System.Drawing.Drawing2D.DashStyle.Dot })  // Ligne en pointillé pour le filet
            {
                // Filet au centre du terrain, horizontal
                int yFilet = DecallageBord + Largeur / 2;
                g.DrawLine(netPen, DecallageBord, yFilet, DecallageBord + Longueur, yFilet);
            }
        }
    }
}
