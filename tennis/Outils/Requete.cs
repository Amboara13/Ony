// Outils/Requete.cs
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

namespace Outils
{
    public class Requete
    {
    // Exécuter une requête SELECT et retourner les données sous forme de liste de dictionnaires
        public static List<Dictionary<string, object>> GetData(string requete)
        {
            List<Dictionary<string, object>> dataList = new List<Dictionary<string, object>>();

            using (var dataConnexion = new DataConnexion())
            using (var connection = dataConnexion.GetConnection())
            {
                try
                {
                    connection?.Open();
                    using (MySqlCommand command = new MySqlCommand(requete, connection))
                    using (MySqlDataReader reader = command.ExecuteReader())
                    {
                        int columnCount = reader?.FieldCount ?? 0;

                        while (reader?.Read() == true)
                        {
                            var row = new Dictionary<string, object>();

                            for (int i = 0; i < columnCount; i++)
                            {
                                row[reader.GetName(i)] = reader.GetValue(i);
                            }

                            dataList.Add(row);
                        }
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Erreur lors de l'exécution de la requête : " + e.Message);
                }
            }

            return dataList;
        }
            // Afficher les résultats dans un format lisible
        public static void AfficherResultat(List<Dictionary<string, object>> dataList)
        {
            if (dataList.Count == 0)
            {
                Console.WriteLine("Aucun résultat trouvé.");
                return;
            }

            // Affichage des noms de colonnes
            var columnWidths = new Dictionary<string, int>();

            foreach (var row in dataList)
            {
                foreach (var entry in row)
                {
                    int width = entry.Key.Length;
                    if (entry.Value != null)
                    {
                        width = Math.Max(width, entry.Value.ToString().Length);
                    }
                    columnWidths[entry.Key] = Math.Max(columnWidths.GetValueOrDefault(entry.Key, 0), width);
                }
            }

            // Affichage des colonnes
            foreach (var entry in columnWidths)
            {
                Console.Write(string.Format("{0,-" + entry.Value + "}", entry.Key) + "\t");
            }
            Console.WriteLine();

            // Affichage des données
            foreach (var row in dataList)
            {
                foreach (var key in row.Keys)
                {
                    Console.Write(string.Format("{0,-" + columnWidths[key] + "}", row[key]) + "\t");
                }
                Console.WriteLine();
            }
        }

        // Exécuter une requête d'insertion ou de mise à jour
        public static void ExecuterRequete(string requete)
        {
            using (var dataConnexion = new DataConnexion())
            using (var connection = dataConnexion.GetConnection())
            {
                try
                {
                    connection.Open();
                    using (var command = new MySqlCommand(requete, connection))
                    {
                        int rowsAffected = command.ExecuteNonQuery();
                        Console.WriteLine($"{rowsAffected} ligne(s) affectée(s).");
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Erreur lors de l'exécution de la requête : " + e.Message);
                }
            }
        }

        // Méthode pour insérer des données en lot
        public static void InsererDonnees(List<Dictionary<string, object>> dataList, string tableName)
        {
            if (dataList.Count == 0)
            {
                Console.WriteLine("La liste de données est vide.");
                return;
            }

            var keys = new List<string>(dataList[0].Keys);
            var columnNames = string.Join(", ", keys);
            var valuePlaceholders = string.Join(", ", new string[keys.Count].Select(_ => "?"));

            string sql = $"INSERT INTO {tableName} ({columnNames}) VALUES ({valuePlaceholders})";

            using (var dataConnexion = new DataConnexion())
            using (var connection = dataConnexion.GetConnection())
            {
                try
                {
                    connection.Open();

                    using (var command = new MySqlCommand(sql, connection))
                    {
                        foreach (var dataMap in dataList)
                        {
                            for (int i = 0; i < keys.Count; i++)
                            {
                                command.Parameters.AddWithValue($"@{keys[i]}", dataMap[keys[i]]);
                            }
                            command.ExecuteNonQuery();
                            command.Parameters.Clear();  // Clear parameters for the next iteration
                        }

                        Console.WriteLine("Insertion réussie.");
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Erreur lors de l'insertion des données : " + e.Message);
                }
            }
        }
    }
}
