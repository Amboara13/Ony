using System;
using System.Drawing;

namespace Aff
{
    public class Cible
    {
        
        public float X { get; set; }
        public float Y { get; set; }
        public float DX { get; set; }
        public int Rayon { get; set; } = 20; 
        public bool DirectionDroite { get; set; } = true;

        public Cible(float x, float y , float dx)
        {
            X = x;
            Y = y;
            DX = dx;
        }

        public void Draw(Graphics g)
        {
            using (Brush lightGrayBrush = new SolidBrush(Color.Lavender))
            {
                g.FillEllipse(lightGrayBrush, X - Rayon, Y - Rayon, Rayon * 2, Rayon * 2);
            }
        }        
    }
}