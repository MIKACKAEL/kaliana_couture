/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.ui;

/**
 *
 * @author TUF
 */
import javax.swing.*;
import java.awt.*;



class MaFenetre extends JFrame {
    public MaFenetre() {
        // Configuration de base de la fenêtre
        setTitle("Fenêtre avec fermeture");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'application quand on clique sur la croix

        // Créer un JPanel vide et l'ajouter à la fenêtre
        JPanel panel = new JPanel();
        add(panel);

        // Optionnel: configurer d'autres propriétés du panel
        panel.setBackground(Color.WHITE);
    }
}
public class FenetreAvecFermeture {
    public static void main(String[] args) {
        // Créer et afficher la fenêtre dans l'EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            MaFenetre fenetre = new MaFenetre();
            fenetre.setVisible(true);
        });
    }
}