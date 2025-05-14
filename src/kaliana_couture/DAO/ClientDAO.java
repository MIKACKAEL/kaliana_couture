/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.DAO;

/**
 *
 * @author TUF
 */
import kaliana_couture.modul.Client;
import kaliana_couture.utils.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // Méthode d'insertion d'un client
    public void insert(Client client) {
        String sql = "INSERT INTO client (nom, surnom, tel) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getSurnom());
            stmt.setString(3, client.getTel());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setIdClient(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode de mise à jour d'un client
    public void update(Client client) {
        String sql = "UPDATE client SET nom = ?, surnom = ?, tel = ? WHERE id_client = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getSurnom());
            stmt.setString(3, client.getTel());
            stmt.setInt(4, client.getIdClient());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode de suppression d'un client
    public void delete(int idClient) {
        String sql = "DELETE FROM client WHERE id_client = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idClient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients() {
    List<Client> clients = new ArrayList<>();
    String query = "SELECT id_client, nom, surnom, tel FROM client";

    try (Connection con = DatabaseConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Client client = new Client(
                rs.getInt("id_client"),
                rs.getString("nom"),
                rs.getString("surnom"),
                rs.getString("tel")
            );
            clients.add(client);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return clients;
}



    public Client getById(int idClient) {
        String sql = "SELECT * FROM client WHERE id_client = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt("id_client"),
                            rs.getString("nom"),
                            rs.getString("surnom"),
                            rs.getString("tel")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Méthode pour récupérer un client par téléphone
    public Client rechercherClientParNomOuSurnom(String keyword) {
        String sql = "SELECT * FROM client WHERE nom LIKE ? OR surnom LIKE ?";
            try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                String param = "%" + keyword + "%";
                stmt.setString(1, param);
                stmt.setString(2, param);
                ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Client(
                    rs.getInt("id_client"),
                    rs.getString("nom"),
                    rs.getString("surnom"),
                    rs.getString("tel")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
        public Client getByNameOrSurname(String searchTerm) {
        String sql = "SELECT * FROM client WHERE nom LIKE ? OR surnom LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            String searchPattern = "%" + searchTerm + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractClient(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Client extractClient(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_client");
        String nom = rs.getString("nom");
        String surnom = rs.getString("surnom");
        String tel = rs.getString("tel");
        return new Client(id, nom, surnom, tel);
    }
}
