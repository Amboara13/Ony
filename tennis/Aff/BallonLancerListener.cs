using System.Windows.Forms;

namespace Aff
{
    public class BallonLancerListener
    {
        private readonly BallonLancer _ballon;

        public BallonLancerListener(BallonLancer ballon)
        {
            _ballon = ballon;

        
        }

        // Méthode pour écouter la touche "Enter" et démarrer l'envoi du ballon
        public void OnKeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                _ballon.Envoyer = true;  // Mettre Envoyer à true pour activer le mouvement du ballon
            }
        }

        
         
        public void CheckBallState()
        {
        // Check if ball has reached destination
        if (_ballon.X == _ballon.Trajectoire.ArriverPoint.X && 
            _ballon.Y == _ballon.Trajectoire.ArriverPoint.Y)
        {
            _ballon.Forme.IsPaused = true;
        }
    }
    }
}
