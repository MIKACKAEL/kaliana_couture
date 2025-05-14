/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package kaliana_couture.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/kaliana_couture"; // URL de ta base de données
    private static final String USER = "root"; // Utilisateur de ta base de données
    private static final String PASSWORD = ""; // Mot de passe de ta base de données

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        try {
            // On tente de créer une connexion à la base de données
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // En cas d'erreur, on imprime la pile d'exception et on relance l'exception
            e.printStackTrace();
            throw e;
        }
    }
}
