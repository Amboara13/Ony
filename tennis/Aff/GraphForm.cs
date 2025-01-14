using System;
using System.Drawing;
using System.Windows.Forms;
using System.ComponentModel;
using System.Collections.Generic;

namespace Aff
{
    public class GraphForm : Form
    {
        private string functionExpression1 = "Sin(x)";
        private string functionExpression2 = "Pow(x,2)";
        private string functionExpression3 = "3 * Sin(x/2)";
        private string functionExpression4 = "2 * Cos(x/3)";
        private But but1, but2, but3, but4;
        private Cible cible1, cible2, cible3, cible4;

        private double representationX = 0.01;
        private int representationY = 30;
        private System.Windows.Forms.Timer timer;
        private List<PointF> points1, points2, points3, points4;
        private int compteur1 = 0, compteur2 = 0, compteur3 = 0, compteur4 = 0;
        private int vitesseDevant = 10;

        private int longueurFenetre = 800;
        private int largeurFenetre = 800; // Increased for 4 buts

        public GraphForm()
        {
            this.DoubleBuffered = true;
            this.Text = "Plotter de Fonction";
            this.Size = new Size(longueurFenetre, largeurFenetre);

            int butWidth = 700;
            int butHeight = 150; // Reduced height for 4 buts
            int x = (this.ClientSize.Width - butWidth) / 2;
            
            
            int y1 = 50;
            but1 = new But(x, y1, butWidth, butHeight, new Terrain(100, 200));

            int y2 = y1 + butHeight + 50;
            but2 = new But(x, y2, butWidth, butHeight, new Terrain(100, 200));

            int y3 = y2 + butHeight + 50;
            but3 = new But(x, y3, butWidth, butHeight, new Terrain(100, 200));

            int y4 = y3 + butHeight + 50;
            but4 = new But(x, y4, butWidth, butHeight, new Terrain(100, 200));

            points1 = new List<PointF>();
            points2 = new List<PointF>();
            points3 = new List<PointF>();
            points4 = new List<PointF>();
            InitializePoints();

            cible1 = new Cible(points1[0].X, points1[0].Y, vitesseDevant);
            cible2 = new Cible(points2[0].X, points2[0].Y, vitesseDevant);
            cible3 = new Cible(points3[0].X, points3[0].Y, vitesseDevant/2);
            cible4 = new Cible(points4[0].X, points4[0].Y, vitesseDevant/2);

            timer = new System.Windows.Forms.Timer();
            timer.Interval = 100;
            timer.Tick += Timer_Tick;
            timer.Start();
        }

        private void InitializePoints()
        {
            var functions = new Func<double, double>[]
            {
                x => Curve.EvaluateFunction(functionExpression1, x),
                x => Curve.EvaluateFunction(functionExpression2, x),
                x => Curve.EvaluateFunction(functionExpression3, x),
                x => Curve.EvaluateFunction(functionExpression4, x)
            };

            var buts = new But[] { but1, but2, but3, but4 };
            var pointLists = new List<PointF>[] { points1, points2, points3, points4 };

            for (int xPixel = but1.Rectangle.Left; xPixel < but1.Rectangle.Right; xPixel++)
            {
                for (int i = 0; i < 4; i++)
                {
                    double x = (xPixel - buts[i].Rectangle.X - buts[i].Rectangle.Width / 2) * representationX;
                    double y = functions[i](x);
                    int yPixel = (int)(-y * representationY) + buts[i].Rectangle.Y + buts[i].Rectangle.Height / 2;

                    if (yPixel >= buts[i].Rectangle.Top && yPixel <= buts[i].Rectangle.Bottom)
                    {
                        pointLists[i].Add(new PointF(xPixel, yPixel));
                    }
                }
            }
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            UpdateCible(ref compteur1, points1, ref cible1);
            UpdateCible(ref compteur2, points2, ref cible2);
            UpdateCible(ref compteur3, points3, ref cible3);
            UpdateCible(ref compteur4, points4, ref cible4);
            this.Invalidate();
        }

        private void UpdateCible(ref int compteur, List<PointF> points, ref Cible cible)
        {
            if (points != null && points.Count > 0)
            {
                int nextCompteur = (int)(compteur + cible.DX);

                if (nextCompteur >= points.Count)
                {
                    compteur = points.Count - 1;
                    cible.DX = -cible.DX;
                }
                else if (nextCompteur < 0)
                {
                    compteur = 0;
                    cible.DX = -cible.DX;
                }
                else
                {
                    compteur = nextCompteur;
                }

                cible = new Cible(points[compteur].X, points[compteur].Y, cible.DX);
            }
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);
            Graphics g = e.Graphics;
            Pen pen = new Pen(Color.Blue, 2);

            but1.Draw(g);
            but2.Draw(g);
            but3.Draw(g);
            but4.Draw(g);

            try
            {
                var allPoints = new List<PointF>[] { points1, points2, points3, points4 };
                var allCibles = new Cible[] { cible1, cible2, cible3, cible4 };

                for (int i = 0; i < 4; i++)
                {
                    foreach (var point in allPoints[i])
                    {
                        g.FillEllipse(pen.Brush, point.X, point.Y, 2, 2);
                    }
                    allCibles[i]?.Draw(g);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Erreur dans l'expression de la fonction : " + ex.Message);
            }
        }
    }
}