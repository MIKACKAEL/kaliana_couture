/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.ui;

import kaliana_couture.DAO.MesureRobeDAO;
import kaliana_couture.modul.Client;
import kaliana_couture.modul.MesureRobe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class InsereRobeForm extends JFrame {

    private JComboBox<Client> clientComboBox;
    private JTextField tEncField, epField, cdvtField, tpField, ttField, tbField, ltdvtField, ljField, hhField, esField, hsField, gbField;
    private JButton saveButton;

    public InsereRobeForm() {
        setTitle("Insertion de Mesure - Robe");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with modern styling
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        // Header with title and subtle separator
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JLabel titleLabel = new JLabel("Formulaire de Mesures pour Robe");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));
        headerPanel.add(separator, BorderLayout.SOUTH);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form panel with improved layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Client selection
        gbc.gridx = 0;
        gbc.gridy = y;
        formPanel.add(createFormLabel("Client :"), gbc);
        clientComboBox = new JComboBox<>();
        clientComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        clientComboBox.setBackground(Color.WHITE);
        clientComboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        gbc.gridx = 1;
        formPanel.add(clientComboBox, gbc);
        y++;

        // Measurement fields
        addFormField("Tour d'Encolure (t_enc) :", tEncField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Épaule à Épaule (ep) :", epField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Carrure Devant (cdvt) :", cdvtField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Tour de Poitrine (tp) :", tpField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Tour de Taille (tt) :", ttField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Tour de Bassin (tb) :", tbField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Longueur Taille-Devant (ltdvt) :", ltdvtField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Longueur Jupe (lj) :", ljField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Hauteur Hanches (hh) :", hhField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Écartement des Seins (es) :", esField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Hauteur Sein (hs) :", hsField = createFormTextField(), y++, formPanel, gbc);
        addFormField("Grosseur Bras (gb) :", gbField = createFormTextField(), y++, formPanel, gbc);

        // Scroll pane for form
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel with modern styling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        saveButton = new JButton("Sauvegarder");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        saveButton.setBackground(new Color(70, 130, 180)); // Steel blue
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(65, 105, 225)); // Royal blue
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(70, 130, 180)); // Steel blue
            }
        });
        
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Load clients
        loadClients();

        // Button action
        saveButton.addActionListener((ActionEvent e) -> saveMesureRobe());
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(80, 80, 80));
        return label;
    }

    private JTextField createFormTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        field.setPreferredSize(new Dimension(180, 30));
        return field;
    }

    private void addFormField(String label, JTextField field, int y, JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(createFormLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void loadClients() {
        try {
            MesureRobeDAO dao = new MesureRobeDAO();
            List<Client> clients = dao.getAllClients();
            for (Client client : clients) {
                clientComboBox.addItem(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Erreur lors du chargement des clients.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveMesureRobe() {
        try {
            Client selectedClient = (Client) clientComboBox.getSelectedItem();
            if (selectedClient == null) {
                JOptionPane.showMessageDialog(this, 
                    "Veuillez sélectionner un client.", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            MesureRobe mesureRobe = new MesureRobe();
            mesureRobe.setIdClient(selectedClient.getIdClient());
            mesureRobe.settEnc(Double.parseDouble(tEncField.getText()));
            mesureRobe.setEp(Double.parseDouble(epField.getText()));
            mesureRobe.setCdvt(Double.parseDouble(cdvtField.getText()));
            mesureRobe.setTp(Double.parseDouble(tpField.getText()));
            mesureRobe.setTt(Double.parseDouble(ttField.getText()));
            mesureRobe.setTb(Double.parseDouble(tbField.getText()));
            mesureRobe.setLtdvt(Double.parseDouble(ltdvtField.getText()));
            mesureRobe.setLj(Double.parseDouble(ljField.getText()));
            mesureRobe.setHh(Double.parseDouble(hhField.getText()));
            mesureRobe.setEs(Double.parseDouble(esField.getText()));
            mesureRobe.setHs(Double.parseDouble(hsField.getText()));
            mesureRobe.setGb(Double.parseDouble(gbField.getText()));

            MesureRobeDAO dao = new MesureRobeDAO();
            boolean success = dao.insertMesureRobe(mesureRobe);

            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Mesure de robe ajoutée avec succès.", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Erreur lors de l'ajout de la mesure.", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez saisir uniquement des valeurs numériques valides.", 
                "Erreur de saisie", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            InsereRobeForm form = new InsereRobeForm();
            form.setVisible(true);
        });
    }
}