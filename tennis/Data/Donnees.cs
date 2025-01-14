using Outils;


namespace Data
{
public class Donnees
{
   
    public static List<Dictionary<string, object>> scoreActuel (int set) 
    { 
        
         string SQL = @"
    SELECT 
        CASE 
            WHEN SUM(points_joueur1) = 1 THEN '15'
            WHEN SUM(points_joueur1) = 2 THEN '30'
            WHEN SUM(points_joueur1) = 3 THEN '40'
            ELSE '0'
        END as points_joueur1,
        CASE 
            WHEN SUM(points_joueur2) = 1 THEN '15'
            WHEN SUM(points_joueur2) = 2 THEN '30'
            WHEN SUM(points_joueur2) = 3 THEN '40'
            ELSE '0'
        END as points_joueur2
    FROM Tennis_Partie 
    WHERE set_id = "+set;   
        return Requete.GetData(SQL);
    }
    
    public static void ResetScores(){
        string SQL = @"truncate tennis_partie";
        Requete.ExecuterRequete(SQL);    
         SQL = @"truncate  tennis_partie_tracer";
        Requete.ExecuterRequete(SQL);    

    }
    public static void EnregistrerScore(int Joueur , int score ,  int set)
    {
        if(Joueur==1)
        {
            Requete.ExecuterRequete("INSERT INTO Tennis_Partie (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),"+score+",0,"+set+")");
            
        }
        else{
            Requete.ExecuterRequete("INSERT INTO Tennis_Partie (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),0,"+score+","+set+")");
        }

        List<Dictionary<string, object>> scores = Donnees.scoreActuel(set);
        string SQL = @"INSERT INTO Tennis_Partie_Tracer (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),"+scores[0]["points_joueur1"]+","+scores[0]["points_joueur2"]+","+set+")";
        Console.WriteLine(SQL);
        Requete.ExecuterRequete(SQL);       
    }

    public static List<Dictionary<string, object>> ScoreTracer(int set)
    {
        return Requete.GetData("SELECT * FROM tennis_partie_tracer where set_id  ="+set);
    }   
}
}