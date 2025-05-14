/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.modul;

/**
 *
 * @author TUF
 */
public class Client {
    // Attributs
private int idClient;
private String nom;
private String surnom;
private String tel;

// Constructeur par défaut
public Client() {}

// Constructeur avec paramètres
public Client(int idClient, String nom, String surnom, String tel) {
this.idClient = idClient;
this.nom = nom;
this.surnom = surnom;
this.tel = tel;
}

// Getters et Setters
public int getIdClient() {
return idClient;
}

public void setIdClient(int idClient) {
this.idClient = idClient;
}

public String getNom() {
return nom;
}

public void setNom(String nom) {
this.nom = nom;
}

public String getSurnom() {
return surnom;
}

public void setSurnom(String surnom) {
this.surnom = surnom;
}

public String getTel() {
return tel;
}

public void setTel(String tel) {
this.tel = tel;
}
    @Override
    public String toString() {
        return nom ;//+ (surnom != null && !surnom.isEmpty() ? " (" + surnom + ")" : "");
    }
}
