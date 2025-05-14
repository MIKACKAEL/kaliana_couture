/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.ui;

/**
 *
 * @author TUF
 */

import kaliana_couture.DAO.ClientDAO;
import kaliana_couture.DAO.MesureRobeDAO;
import kaliana_couture.modul.Client;
import kaliana_couture.modul.MesureRobe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// ... (importations existantes)

public class MesureRobeListForm extends JFrame {

    private JTextField rechercheField;
    private JTextArea infoClientTextArea;
    private JTable mesureRobeTable;
    private JTextArea calculTextArea; // ➕ Zone pour afficher les calculs

    public MesureRobeListForm() {
        setTitle("Recherche Mesures de Robe - Kaliana Couture");
        setSize(800, 700); // ➕ plus haut pour inclure la zone de calcul
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        // 🔍 Recherche
        JPanel recherchePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        recherchePanel.setBorder(new TitledBorder("Recherche de client"));
        recherchePanel.setBackground(new Color(240, 240, 240));

        JLabel rechercheLabel = new JLabel("Nom ou Surnom :");
        rechercheField = new JTextField(25);
        JButton rechercherButton = new JButton("Rechercher");
        rechercherButton.addActionListener(e -> rechercherClient());

        recherchePanel.add(rechercheLabel);
        recherchePanel.add(rechercheField);
        recherchePanel.add(rechercherButton);
        mainPanel.add(recherchePanel, BorderLayout.NORTH);

        // 👤 Informations Client
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(new TitledBorder("Informations du client"));

        infoClientTextArea = new JTextArea(5, 40);
        infoClientTextArea.setEditable(false);
        infoPanel.add(new JScrollPane(infoClientTextArea), BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // 📏 Mesures
        JPanel mesurePanel = new JPanel(new BorderLayout());
        mesurePanel.setBorder(new TitledBorder("Mesures de robe"));

        String[] columnNames = {"ID", "T_Enc", "Ep", "Cdvt", "TP", "TT", "TB", "LTDVT", "L-J", "HH", "ES", "HS", "GB"};
        mesureRobeTable = new JTable(new Object[0][13], columnNames);
        mesureRobeTable.setRowHeight(25);
        JScrollPane tableScrollPane = new JScrollPane(mesureRobeTable);
        mesurePanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(mesurePanel, BorderLayout.SOUTH);

        // ➕ Zone de calculs
        calculTextArea = new JTextArea(10, 40);
        calculTextArea.setEditable(false);
        calculTextArea.setBorder(new TitledBorder("Résultats des calculs"));
        calculTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        calculTextArea.setBackground(new Color(245, 245, 245));
        mainPanel.add(new JScrollPane(calculTextArea), BorderLayout.EAST);
    }

    private void rechercherClient() {
        String searchTerm = rechercheField.getText().trim();
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom ou un surnom", "Champ vide", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Client client = new ClientDAO().getByNameOrSurname(searchTerm);

        if (client != null) {
            afficherInfoClient(client);
            afficherMesures(client);
        } else {
            JOptionPane.showMessageDialog(this, "Client non trouvé", "Résultat", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void afficherInfoClient(Client client) {
        String info = String.format("Nom: %s\nSurnom: %s\nTéléphone: %s", 
            client.getNom(), client.getSurnom(), client.getTel());
        infoClientTextArea.setText(info);
    }

    private void afficherMesures(Client client) {
        List<MesureRobe> mesures = new MesureRobeDAO().getMesuresByClient(client.getIdClient());

        Object[][] data = new Object[mesures.size()][13];
        for (int i = 0; i < mesures.size(); i++) {
            MesureRobe m = mesures.get(i);
            data[i] = new Object[] {
                m.getId(), m.gettEnc(), m.getEp(), m.getCdvt(), m.getTp(),
                m.getTt(), m.getTb(), m.getLtdvt(), m.getLj(), m.getHh(),
                m.getEs(), m.getHs(), m.getGb()
            };
        }

        mesureRobeTable.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[] {"ID", "T_Enc", "Ep", "Cdvt", "TP", "TT", "TB", "LTDVT", "LJ", "HH", "ES", "HS", "GB"})
        );

        // ➕ Afficher les calculs pour la première mesure (par exemple)
        if (!mesures.isEmpty()) {
            afficherCalculsMesures(mesures.get(0));
        } else {
            calculTextArea.setText("Aucune mesure disponible pour ce client.");
        }
    }

    private void afficherCalculsMesures(MesureRobe m) {
        double tEnc = m.gettEnc();
        double tp = m.getTp();
        double Cdvt = m.getCdvt();
        double tt = m.getTt();
        double tb = m.getTb();
        double gb = m.getGb();
        double es = m.getEs();

        // ⚙️ Calculs
        double encolureHorizontal = (tEnc / 6.0) + 0.5;
        double encolureVertical = (tEnc / 6.0) + 1.0;
        double emmanchure = (gb + 4) / 2;
        double poitrineDevant = (tp / 4) + 2;
        double poitrineDos = tp / 4;
        double ecartementSein = es / 2;
        double tourTailleDevant = ((tt + 2) / 4) + 2.5;
        double tourTailleDos = (tt / 4) + 2.5;
        double tourBassinDevant = (tb / 4) + 2;
        double tourBassinDos = tb / 4;
        double carrure_devant = Cdvt/2;
        double carrure_dos = carrure_devant +1;
        
        
        // 🖨️ Affichage
        StringBuilder sb = new StringBuilder();
        sb.append("🟣 Encolure:\n");
        sb.append(String.format("  - Horizontale : %.2f cm\n", encolureHorizontal));
        sb.append(String.format("  - Verticale   : %.2f cm\n", encolureVertical));
        sb.append("🟠 Emmanchure et Poitrine:\n");
        sb.append(String.format("  - carrure devant       : %.2f cm\n", carrure_devant));
        sb.append(String.format("  - carrure_dos       : %.2f cm\n", carrure_dos));
        sb.append(String.format("  - Emmanchure       : %.2f cm\n", emmanchure));
        sb.append(String.format("  - Poitrine Devant  : %.2f cm\n", poitrineDevant));
        sb.append(String.format("  - Poitrine Dos     : %.2f cm\n", poitrineDos));
        sb.append("🟡 Taille:\n");
        sb.append(String.format("  - Écartement seins : %.2f cm\n", ecartementSein));
        sb.append(String.format("  - Taille Devant    : %.2f cm\n", tourTailleDevant));
        sb.append(String.format("  - Taille Dos       : %.2f cm\n", tourTailleDos));
        sb.append("🟢 Bassin:\n");
        sb.append(String.format("  - Bassin Devant    : %.2f cm\n", tourBassinDevant));
        sb.append(String.format("  - Bassin Dos       : %.2f cm\n", tourBassinDos));

        calculTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MesureRobeListForm form = new MesureRobeListForm();
            form.setVisible(true);
        });
    }
}
