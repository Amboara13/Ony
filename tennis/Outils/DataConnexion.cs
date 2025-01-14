// Outils/DataConnexion.cs
using MySql.Data.MySqlClient;
using System;

namespace Outils
{
    public class DataConnexion : IDisposable
    {
        // Chaîne de connexion
        private readonly string _connectionString = "server=localhost;user id=root;password=;database=tennis"!;
        private MySqlConnection _connection;

        // Constructeur
        public DataConnexion()
        {
            // Vérification si la chaîne de connexion n'est pas nulle
            _connectionString = _connectionString ?? throw new ArgumentNullException(nameof(_connectionString));

            // Initialiser la connexion
            _connection = new MySqlConnection(_connectionString);
        }

        // Méthode pour obtenir la connexion
        public MySqlConnection GetConnection()
        {
            return _connection;
        }

        // Implémentation de IDisposable pour libérer les ressources
        public void Dispose()
        {
            _connection?.Dispose();
            _connection = null;
        }
    }
}
