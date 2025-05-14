/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.ui;

import kaliana_couture.DAO.ClientDAO;
import kaliana_couture.modul.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ClientForm extends JFrame {
    private final ClientDAO clientDAO = new ClientDAO();

    private final JTextField nomField = new JTextField(20);
    private final JTextField surnomField = new JTextField(20);
    private final JTextField telField = new JTextField(20);

    private final JButton submitButton = new JButton("Enregistrer");
    private final JButton cancelButton = new JButton("Annuler");
    private final JToggleButton fullscreenButton = new JToggleButton("Plein écran");

    private boolean isFullscreen = false;
    private final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public ClientForm() {
        configureWindow();
        initUI();
        setupKeyboardShortcuts();
    }

    private void configureWindow() {
        setTitle(" Kaliana Couture");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

private void initUI() {
    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // En-tête
    JPanel headerPanel = new JPanel(new BorderLayout());
    JLabel titleLabel = new JLabel("Nouveau Client", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
    headerPanel.add(titleLabel, BorderLayout.CENTER);
    mainPanel.add(headerPanel, BorderLayout.NORTH);

    // Formulaire
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Informations Client"),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
    ));

    formPanel.add(createFormRow("Nom  :", nomField));
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(createFormRow("Surnom :", surnomField));
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(createFormRow("Téléphone  :", telField));

    // Boutons (centrés et proches du formulaire)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    submitButton.setPreferredSize(new Dimension(150, 40));
    submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
    submitButton.addActionListener(this::handleSubmit);

    cancelButton.setPreferredSize(new Dimension(150, 40));
    cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    cancelButton.addActionListener(e -> resetForm());

    buttonPanel.add(submitButton);
    buttonPanel.add(cancelButton);

    // Panel centre contenant formulaire + boutons
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.add(formPanel);
    centerPanel.add(Box.createVerticalStrut(10)); // espace vertical réduit entre formulaire et boutons
    centerPanel.add(buttonPanel);

    // Centrer verticalement dans la fenêtre
    JPanel centerWrapper = new JPanel(new GridBagLayout());
    centerWrapper.add(centerPanel);

    mainPanel.add(centerWrapper, BorderLayout.CENTER);

    add(mainPanel);
}



    private JPanel createFormRow(String labelText, JTextField textField) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 30));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setMaximumSize(new Dimension(400, 35));

        row.add(label, BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        return row;
    }

//    private void toggleFullscreen() {
//        isFullscreen = !isFullscreen;
//        dispose();
//
//        if (isFullscreen) {
//            setUndecorated(true);
//            device.setFullScreenWindow(this);
//            fullscreenButton.setText("Fenêtré");
//        } else {
//            setUndecorated(false);
//            device.setFullScreenWindow(null);
//            fullscreenButton.setText("Plein écran");
//            setSize(800, 600);
//            setLocationRelativeTo(null);
//        }
//
//        setVisible(true);
//    }

    private void setupKeyboardShortcuts() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "toggleFullscreen");
        actionMap.put("toggleFullscreen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullscreenButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitFullscreen");
        actionMap.put("exitFullscreen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFullscreen) {
                    fullscreenButton.doClick();
                }
            }
        });
    }

    private void handleSubmit(ActionEvent e) {
        if (!validateForm()) return;

        Client client = new Client();
        client.setNom(nomField.getText().trim());
        client.setSurnom(surnomField.getText().trim().isEmpty() ? null : surnomField.getText().trim());
        client.setTel(telField.getText().trim());

        try {
            clientDAO.insert(client);
            showMessage("Client enregistré avec succès. ID : " + client.getIdClient(), "Succès", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
        } catch (Exception ex) {
            showMessage("Erreur lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateForm() {
        if (nomField.getText().trim().isEmpty() || telField.getText().trim().isEmpty()) {
            showMessage("Les champs marqués d'une * sont obligatoires.", "Champs requis", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void resetForm() {
        nomField.setText("");
        surnomField.setText("");
        telField.setText("");
        nomField.requestFocus();
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            ClientForm clientForm = new ClientForm();
            clientForm.setVisible(true);
        });
    }
}
