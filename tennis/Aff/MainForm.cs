using System;
using System.Drawing;
using System.Windows.Forms;
using System.ComponentModel;
using Data;

namespace Aff
{
    public class MainForm : Form
    {
        
        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public bool IsPaused { get; set; } = false;

         [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden)]
        public But[] Buts { get; set; } 
        private Terrain _terrain;
        private BallonLancer _ballonJoueur1;
        private BallonLancer _ballonJoueur2;
        private int _jour = 1;
        private System.Windows.Forms.Timer _timer;
        private int _longueurTerrain = 800;
        private int _largeurTerrain = 600;
        private int _margeTerrain = 50;
        private BallonLancerListener _ballonListener;
        private TrajectoireListener _trajectoireListener;
        private bool _waitingForEnter = false;


        //graph
        private string functionExpression1 = "Sin(x)";
        private string functionExpression2 = "Pow(x,2)";
        private string functionExpression3 = "3 * Sin(x/2)";
        private string functionExpression4 = "2 * Cos(x/3)";
        private But but1, but2, but3, but4;
        private Cible cible1, cible2, cible3, cible4;

        private double representationX = 0.01;
        private int representationY = 20;

        private List<PointF> points1, points2, points3, points4;
        private int compteur1 = 0, compteur2 = 0, compteur3 = 0, compteur4 = 0;
        private int vitesseDevant = 10;

        private Point? impactPoint = null;
        private bool isPlayer1Turn => _jour == 1;

        public MainForm()
        {

            SetupGame();
            InitializeTimer();
            InitializeListeners();
            GraphForm();
        }

        public void GraphForm()
        {
            this.DoubleBuffered = true;

            int butWidth = _terrain.Longueur-2*_margeTerrain;
            int butHeight = 100;
            int x = _margeTerrain;
            int y1 = 20;
            but1 = new But(x, y1, butWidth, butHeight, _terrain);
            
            int y2 = y1 + butHeight + y1;
            but2 = new But(x, y2, butWidth, butHeight, _terrain);


            int y4 = _terrain.Largeur-y1-butHeight;
            but4 = new But(x, y4, butWidth, butHeight, _terrain);

            int y3 = y4-butHeight-y1;
            but3 = new But(x, y3, butWidth, butHeight, _terrain);

            points1 = new List<PointF>();
            points2 = new List<PointF>();
            points3 = new List<PointF>();
            points4 = new List<PointF>();
            InitializePoints();

            cible1 = new Cible(points3[0].X, points3[0].Y, vitesseDevant/2);
            cible2 = new Cible(points2[0].X, points2[0].Y, vitesseDevant);
            cible3 = new Cible(points1[0].X, points1[0].Y, vitesseDevant);
            cible4 = new Cible(points4[0].X, points4[0].Y, vitesseDevant/2);

            _timer = new System.Windows.Forms.Timer();
            _timer.Interval = 100;
            _timer.Tick += Timer_Tick;
            _timer.Start();
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
            Buts = buts;
            var pointLists = new List<PointF>[] { points1, points2, points3, points4 };

            for (int xPixel = but1.Rectangle.Left; xPixel < but1.Rectangle.Right; xPixel++)
            {
                for (int i = 0; i < pointLists.Length; i++)
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
        private void SetupGame()
        {

            this.DoubleBuffered = true;
            this.Size = new Size(_longueurTerrain + 2 * _margeTerrain, 
                               _largeurTerrain + 3 * _margeTerrain);

            _terrain = new Terrain(_longueurTerrain, _largeurTerrain);
            ResetPositions();
            UpdateActivePlayer();
        }

        private void UpdateActivePlayer()
        {
            var activeBallon = _jour == 1 ? _ballonJoueur1 : _ballonJoueur2;
            _ballonListener = new BallonLancerListener(activeBallon);
            _trajectoireListener = new TrajectoireListener(activeBallon.Trajectoire);
            activeBallon.Trajectoire.Update(activeBallon.X, activeBallon.Y);
            this.KeyDown += (sender, e) => _trajectoireListener.HandleKeyPress(sender, e);
        }

        private void InitializeTimer()
        {
            _timer = new System.Windows.Forms.Timer();
            _timer.Interval = 100;
            _timer.Tick += Timer_Tick;
            _timer.Start();
        }

        private void InitializeListeners()
        {
            this.KeyDown += (s, e) =>
            {
                if (_waitingForEnter && e.KeyCode == Keys.Enter)
                {
                    ResetPositions();
                    _jour = _jour == 1 ? 2 : 1;
                    UpdateActivePlayer();
                    _waitingForEnter = false;
                }
                else if (!_waitingForEnter)
                {
                    _ballonListener?.OnKeyDown(s, e);
                }
            };
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            var activeBallon = _jour == 1 ? _ballonJoueur1 : _ballonJoueur2;   
            if (activeBallon.Envoyer)
            {
                activeBallon.Move();
                // Vérifier si le ballon a atteint sa destination via sa trajectoire
                var destinationPoint = activeBallon.Trajectoire.ArriverPoint;
                if (activeBallon.X == destinationPoint.X && activeBallon.Y == destinationPoint.Y)
                {
                    _waitingForEnter = true;
                    activeBallon.Envoyer = false;
                }
            }
            if (_ballonJoueur1.Envoyer && isPlayer1Turn)
            {
                CheckCollision(_ballonJoueur1, new[] { cible1, cible2 });
            }
            if (_ballonJoueur2.Envoyer && !isPlayer1Turn)
            {
                CheckCollision(_ballonJoueur2, new[] { cible3, cible4 });
            }
            UpdateCible(ref compteur1, points1, ref cible1);
            UpdateCible(ref compteur2, points2, ref cible2);
            UpdateCible(ref compteur3, points3, ref cible3);
            UpdateCible(ref compteur4, points4, ref cible4);
            this.Invalidate();
        }

        private void UpdateCible(ref int compteur, List<PointF> points, ref Cible cible)
        {
            if (points != null && points.Count > 0 && !IsPaused)
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

        private void ResetPositions()
        {
            _ballonJoueur1 = new BallonLancer(
                _longueurTerrain / 2, 
                _terrain.Largeur, 
                _terrain, 
                this
            );
            _ballonJoueur2 = new BallonLancer(
                _longueurTerrain / 2, 
                0, 
                _terrain, 
                this
            );
        }

  private Point ConvertToGraphCoordinates(Cible lacible, But but)
    {
        
                    int x = (int)lacible.X;
                    int y = (int) lacible.Y;
        
        return new Point(x, y);
    }

    private void CheckCollision(BallonLancer ballon, Cible[] cibles)
{
    int i=0;
    foreach (var cible in cibles)
    {
        // Calcul de la distance entre les centres des cercles (ballon et cible)
        Point ciblegraph = ConvertToGraphCoordinates(cible, Buts[i]); 
        double distance = Math.Sqrt(Math.Pow(ciblegraph.X - ballon.X, 2) + Math.Pow(ciblegraph.Y - ballon.Y, 2));
        double distanceIntersection = cible.Rayon + ballon.Rayon;
        // Si la distance est inférieure ou égale à la somme des rayons, il y a collision
        Console.WriteLine($"---------------------------------------------"); 
        Console.WriteLine($"ballon: X = {ballon.X}, Y = {ballon.Y}"); 
        Console.WriteLine($"cible: X = {cible.X}, Y = {cible.Y}");
        Console.WriteLine($"distance:   {distance}");
        Console.WriteLine($"distanceIntersection:   {distanceIntersection}"); 
        Console.WriteLine($"---------------------------------------------"); 
        if (distance <= distanceIntersection )
        {
            // Mettre en pause le jeu si une collision est détectée
            IsPaused = true;

            // Calculer le point d'impact en fonction de la proportion des rayons
            double t = ballon.Rayon / (cible.Rayon + ballon.Rayon); // Proportion de la distance
            double impactX = (ballon.X + cible.X)/2;  // Calcul de l'impact sur l'axe X
            double impactY = (ballon.Y + cible.Y)/2;  // Calcul de l'impact sur l'axe Y

            // Convertir les coordonnées en entier pour l'affichage du point
            Point intersectionPoint = new Point((int)impactX, (int)impactY);

            // Afficher le point d'impact
            Console.WriteLine($"Impact: X = {intersectionPoint.X}, Y = {intersectionPoint.Y}");

            // Enregistrer le point d'impact
            impactPoint = new Point(intersectionPoint.X, intersectionPoint.Y);


            if( cible.DX == vitesseDevant)
            {
                Donnees.EnregistrerScore((int)_jour, 2 ,1);
            }
            else
            {
                Donnees.EnregistrerScore((int)_jour, 1 ,1);
            }
            break; 
        }
        i++;
    }
}

        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);
            
            using (Font font = new Font("Arial", 20))
            {
                e.Graphics.DrawString($"JOUR: {_jour}", font, Brushes.Green, _terrain.Longueur/3, 5);
            }

            _terrain.Draw(e.Graphics);
            _ballonJoueur1.Draw(e.Graphics);
            _ballonJoueur2.Draw(e.Graphics);

                        Graphics g = e.Graphics;
            Pen pen = new Pen(Color.LightGray, 2);

            but1.Draw(g);
            but2.Draw(g);
            but3.Draw(g);
            but4.Draw(g);

            try
            {
                var allPoints = new List<PointF>[] { points1, points2, points3, points4 };
                var allCibles = new Cible[] { cible1, cible2, cible3, cible4 };

                for (int i = 0; i < allCibles.Length; i++)
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

            // Draw impact cross if exists
            if (impactPoint.HasValue)
            {
                using (Pen redPen = new Pen(Color.Red, 2))
                {
                    int size = 10;
                    e.Graphics.DrawLine(redPen, 
                        impactPoint.Value.X - size, impactPoint.Value.Y - size,
                        impactPoint.Value.X + size, impactPoint.Value.Y + size);
                    e.Graphics.DrawLine(redPen,
                        impactPoint.Value.X - size, impactPoint.Value.Y + size,
                        impactPoint.Value.X + size, impactPoint.Value.Y - size);
                }
            }

        //score actuel
        var scores = Donnees.scoreActuel(1);
        if (scores != null && scores.Count > 0)
        {
            using (Font scoreFont = new Font("Arial", 24, FontStyle.Bold))
            {
                string score1 = scores[0]["points_joueur1"].ToString();
                string score2 = scores[0]["points_joueur2"].ToString();
                
                
                // Draw scores on right side of terrain
                int scoreX = _terrain.Longueur + _terrain.DecallageBord + 20;
                e.Graphics.DrawString($"Score J1: {score1}", 
                                   scoreFont, 
                                   Brushes.Blue, 
                                   scoreX, 
                                   _terrain.Largeur/5);
                
                e.Graphics.DrawString($"Score J2: {score2}", 
                                   scoreFont, 
                                   Brushes.Red, 
                                   scoreX, 
                                   2*_terrain.Largeur/5);
            }
        }

//score suivi
  var traceScores = Donnees.ScoreTracer(1);
    if (traceScores != null && traceScores.Count > 0)
    {
        using (Font traceFont = new Font("Arial", 14))
        {
            int traceX = _terrain.Longueur + _terrain.DecallageBord + 20;
            int traceY = (2 * _terrain.Largeur/3) + 50;

            e.Graphics.DrawString("Historique:", traceFont, Brushes.Black, traceX, traceY);
            traceY += 25;

            foreach (var trace in traceScores)
            {
                string traceText = $"{trace["points_joueur1"]} - {trace["points_joueur2"]} - {trace["date_et_heure"]} ";
                e.Graphics.DrawString(traceText, 
                                   traceFont, 
                                   Brushes.DarkGray, 
                                   traceX, 
                                   traceY);
                traceY += 20;
            }
        }
    }

        
        }
              
        protected override void OnResize(EventArgs e)
        {
            base.OnResize(e);
            this.Invalidate();
        }

         protected override void OnKeyDown(KeyEventArgs e)
    {
        base.OnKeyDown(e);
       
        if (e.KeyCode == Keys.X)
        {
            IsPaused = false;
            impactPoint = null;  // Reset impact point
        }
        
        if (e.Control)  // Check if Ctrl is pressed
        {
            if (e.KeyCode >= Keys.D1 && e.KeyCode <= Keys.D4)
            {
                int butNumber = e.KeyCode - Keys.D1;  // Convert to 0-3
                TeleportBallToTarget(butNumber);
            }
        }
    }

    private void TeleportBallToTarget(int targetBut)
    {
        Cible targetCible = null;
        BallonLancer activeBall = null;

        switch(targetBut)
        {
            case 0:
                targetCible = cible1;
                activeBall = _ballonJoueur1;
                break;
            case 1:
                targetCible = cible2;
                activeBall = _ballonJoueur1;
                break;
            case 2:
                targetCible = cible3;
                activeBall = _ballonJoueur2;
                break;
            case 3:
                targetCible = cible4;
                activeBall = _ballonJoueur2;
                break;
        }

        if (targetCible != null && activeBall != null)
        {
            activeBall.Trajectoire.ArriverPoint= new Point((int)targetCible.X ,(int)targetCible.Y);
            activeBall.Envoyer = true;
            activeBall.Move();
            activeBall.X = activeBall.Trajectoire.ArriverPoint.X;
            activeBall.Y = activeBall.Trajectoire.ArriverPoint.Y;

            IsPaused=true;
            if (_ballonJoueur1.Envoyer && isPlayer1Turn)
            {
                CheckCollision(_ballonJoueur1, new[] { cible1, cible2 });
            }
            if (_ballonJoueur2.Envoyer && !isPlayer1Turn)
            {
                CheckCollision(activeBall, new[] { cible3, cible4 });
            }
        }
    }

   
    }


    
}