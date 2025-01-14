using System;
using System.Windows.Forms;

namespace Aff
{
    public class TrajectoireListener
    {
        private Trajectoire _trajectoire;

        public TrajectoireListener(Trajectoire trajectoire)
        {
            _trajectoire = trajectoire;
        }

        // Méthode pour gérer la pression des touches
       public void HandleKeyPress(object sender, KeyEventArgs e)
{
    if (e.KeyCode == Keys.Left)
    {
        // Si la touche directionnelle gauche est pressée, diminuer X de 3
        _trajectoire.ArriverPoint = new Point(_trajectoire.ArriverPoint.X - 3, _trajectoire.ArriverPoint.Y);
    }
    else if (e.KeyCode == Keys.Right)
    {
        // Si la touche directionnelle droite est pressée, augmenter X de 3
        _trajectoire.ArriverPoint = new Point(_trajectoire.ArriverPoint.X + 3, _trajectoire.ArriverPoint.Y);
    }
    else if (e.KeyCode == Keys.Up)
    {
        // Si la touche directionnelle droite est pressée, augmenter X de 3
        _trajectoire.ArriverPoint = new Point(_trajectoire.ArriverPoint.X, _trajectoire.ArriverPoint.Y-3);
    }
    else if (e.KeyCode == Keys.Down)
    {
        // Si la touche directionnelle droite est pressée, augmenter X de 3
        _trajectoire.ArriverPoint = new Point(_trajectoire.ArriverPoint.X, _trajectoire.ArriverPoint.Y+3);
    }

    // Après la modification, redessiner la trajectoire
    (sender as Form)?.Invalidate(); // Demande un redessin du formulaire
    }

    }
}
