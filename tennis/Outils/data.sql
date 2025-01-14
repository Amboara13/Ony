CREATE DATABASE Tennis;
use Tennis;

CREATE TABLE Tennis_Partie (
    partie_id INT AUTO_INCREMENT PRIMARY KEY,
    date_et_heure DATETIME NOT NULL,
    points_joueur1 INT DEFAULT 0,
    points_joueur2 INT DEFAULT 0,
    set_id INT 
);

//ENREGISTREMENT
INSERT INTO Tennis_Partie (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),0,1,1);
INSERT INTO Tennis_Partie (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),1,0,1);
INSERT INTO Tennis_Partie (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),0,1,1);

//score
SELECT sum(points_joueur1) as points_joueur1 , sum(points_joueur2) as points_joueur2 FROM Tennis_Partie WHERE set_id=1 ;
//,mettre la condition  if points_joueur1  = 1=> affiche 15 , if 2=>30 , if 3=>40 ; 


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
WHERE set_id = 1;

CREATE TABLE Tennis_Partie_Tracer (
    partie_id INT AUTO_INCREMENT PRIMARY KEY,
    date_et_heure DATETIME NOT NULL,
    points_joueur1 INT DEFAULT 0,
    points_joueur2 INT DEFAULT 0,
    set_id INT 
);

INSERT INTO Tennis_Partie (date_et_heure,points_joueur1,points_joueur2,set_id)VALUES (now(),0,1,1);


truncate tennis_partie ;
truncate tennis_partie_tracer;