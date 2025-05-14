/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.DAO;

/**
 *
 * @author TUF
 */

import kaliana_couture.modul.MesureRobe;
import kaliana_couture.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import kaliana_couture.modul.Client;

public class MesureRobeDAO {

    // Insertion d'une nouvelle mesure de robe
    public boolean insertMesureRobe(MesureRobe mesureRobe) {
        String sql = "INSERT INTO mesure_robe (id_client, t_enc, ep, cdvt, tp, tt, tb, ltdvt, lj, hh, es, hs, gb) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mesureRobe.getIdClient());
            stmt.setDouble(2, mesureRobe.gettEnc());
            stmt.setDouble(3, mesureRobe.getEp());
            stmt.setDouble(4, mesureRobe.getCdvt());
            stmt.setDouble(5, mesureRobe.getTp());
            stmt.setDouble(6, mesureRobe.getTt());
            stmt.setDouble(7, mesureRobe.getTb());
            stmt.setDouble(8, mesureRobe.getLtdvt());
            stmt.setDouble(9, mesureRobe.getLj());
            stmt.setDouble(10, mesureRobe.getHh());
            stmt.setDouble(11, mesureRobe.getEs());
            stmt.setDouble(12, mesureRobe.getHs());
            stmt.setDouble(13, mesureRobe.getGb());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mise à jour des mesures de robe
    public boolean updateMesureRobe(MesureRobe mesureRobe) {
        String sql = "UPDATE mesure_robe SET t_enc = ?, ep = ?, cdvt = ?, tp = ?, tt = ?, tb = ?, ltdvt = ?, lj = ?, hh = ?, es = ?, hs = ?, gb = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, mesureRobe.gettEnc());
            stmt.setDouble(2, mesureRobe.getEp());
            stmt.setDouble(3, mesureRobe.getCdvt());
            stmt.setDouble(4, mesureRobe.getTp());
            stmt.setDouble(5, mesureRobe.getTt());
            stmt.setDouble(6, mesureRobe.getTb());
            stmt.setDouble(7, mesureRobe.getLtdvt());
            stmt.setDouble(8, mesureRobe.getLj());
            stmt.setDouble(9, mesureRobe.getHh());
            stmt.setDouble(10, mesureRobe.getEs());
            stmt.setDouble(11, mesureRobe.getHs());
            stmt.setDouble(12, mesureRobe.getGb());
            stmt.setInt(13, mesureRobe.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Recherche par ID
    public MesureRobe getMesureRobeById(int id) {
        String sql = "SELECT * FROM mesure_robe WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new MesureRobe(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getTimestamp("date_time_creation"),
                        rs.getDouble("t_enc"),
                        rs.getDouble("ep"),
                        rs.getDouble("cdvt"),
                        rs.getDouble("tp"),
                        rs.getDouble("tt"),
                        rs.getDouble("tb"),
                        rs.getDouble("ltdvt"),
                        rs.getDouble("lj"),
                        rs.getDouble("hh"),
                        rs.getDouble("es"),
                        rs.getDouble("hs"),
                        rs.getDouble("gb")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Recherche multi-critères
    public List<MesureRobe> getMesuresByClient(int clientId) {
        List<MesureRobe> mesures = new ArrayList<>();
        String sql = "SELECT * FROM mesure_robe WHERE id_client = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MesureRobe mesure = new MesureRobe(
                        rs.getInt("id_client"),
                        rs.getDouble("t_enc"),
                        rs.getDouble("ep"),
                        rs.getDouble("cdvt"),
                        rs.getDouble("tp"),
                        rs.getDouble("tt"),
                        rs.getDouble("tb"),
                        rs.getDouble("ltdvt"),
                        rs.getDouble("lj"),
                        rs.getDouble("hh"),
                        rs.getDouble("es"),
                        rs.getDouble("hs"),
                        rs.getDouble("gb")
                    );
                    mesures.add(mesure);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesures;
    }

     // Méthode pour récupérer les informations du client (nom et téléphone) par son ID
    public static String[] getClientInfoById(int idClient) {
        String[] clientInfo = new String[2]; // Tableau pour stocker le nom et le téléphone

        // Connexion à la base de données
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT nom, tel FROM client WHERE id_client = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idClient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                clientInfo[0] = rs.getString("nom"); // Nom du client
                clientInfo[1] = rs.getString("tel"); // Téléphone du client
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientInfo;
    }
    
    //fonction calcules des mesure
    public Map<String, Double> getMesuresCalculees(int idMesure) {
    Map<String, Double> result = new HashMap<>();
    
    // Requête pour récupérer les mesures nécessaires à partir de la base de données
    String sql = "SELECT t_enc,cdvt, gb, tp, es, tt, tb FROM mesure_robe WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idMesure);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Récupérer les données nécessaires
            double tEnc = rs.getDouble("t_enc");
            double gb = rs.getDouble("gb");
            double tp = rs.getDouble("tp");
            double es = rs.getDouble("es");
            double tt = rs.getDouble("tt");
            double tb = rs.getDouble("tb");
            double cdvt = rs.getDouble("cdvt");
            
            
            //ligne (1)
            // Calcul de l'encolure 
            double encolureHorizontal = (tEnc / 6.0) + 0.5; //devant et dos
            double encolureVertical = (tEnc / 6.0) + 1.0;
            
            //ligne épaule (2)(3)
            // Calcul de l'emmanchure et de la poitrine 
            double carrure_devant = cdvt/2;
            double carrure_dos = carrure_devant +1;
            double emmanchure = (gb + 4) / 2;//devant et dos
            double poitrineDevant = (tp / 4) + 2;
            double poitrineDos = tp / 4;
            
            
            //ligne poitrine (3)(4)
            // Calcul de l'écartement des seins et du tour de taille 
            double ecartementSein = es / 2; //devant et dos
            double tourTailleDevant = ((tt + 2) / 4) + 2.5;
            double tourTailleDos = (tt / 4) + 2.5;
            
            
            //ligne bassin (4)(5)=(6)
            // Calcul du tour de bassin devant et dos 
            double tourBassinDevant = (tb / 4) + 2; // =ligne (6)
            double tourBassinDos = tb / 4; // =ligne (6)

            // Ajouter tous les résultats dans la map
            result.put("encolure_horizontal", encolureHorizontal);
            result.put("encolure_vertical", encolureVertical);
            result.put("emmanchure", emmanchure);
            result.put("poitrine_devant", poitrineDevant);
            result.put("poitrine_dos", poitrineDos);
            result.put("ecartement_sein", ecartementSein);
            result.put("tour_taille_devant", tourTailleDevant);
            result.put("tour_taille_dos", tourTailleDos);
            result.put("tour_bassin_devant", tourBassinDevant);
            result.put("tour_bassin_dos", tourBassinDos);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
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



}
