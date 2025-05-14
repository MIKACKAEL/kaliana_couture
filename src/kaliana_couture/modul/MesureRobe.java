/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaliana_couture.modul;

/**
 *
 * @author TUF
 */

import java.sql.Timestamp;

public class MesureRobe {
    private int id;
    private int idClient;
    private Timestamp dateTimeCreation;
    private double tEnc; // Tour d'encolure
    private double ep; // Épaule à épaule
    private double cdvt; // Carrure devant
    private double tp; // Tour de poitrine
    private double tt; // Tour de taille
    private double tb; // Tour de bassin (ou hanches)
    private double ltdvt; // Longueur taille-devant
    private double lj; // Longueur jupe ou robe
    private double hh; // Hauteur hanches
    private double es; // Écartement des seins (distance entre les deux mamelons)
    private double hs; // Hauteur sein
    private double gb; // Grosseur bras (tour du bras supérieur)
    private java.sql.Timestamp dateCreation;

    // Constructeur par défaut
    public MesureRobe() {}

    // Constructeur avec paramètres
    public MesureRobe(int id, int idClient, Timestamp dateTimeCreation, double tEnc, double ep, double cdvt,
                      double tp, double tt, double tb, double ltdvt, double lj, double hh, double es, double hs, double gb) {
        this.id = id;
        this.idClient = idClient;
        this.dateTimeCreation = dateTimeCreation;
        this.tEnc = tEnc;
        this.ep = ep;
        this.cdvt = cdvt;
        this.tp = tp;
        this.tt = tt;
        this.tb = tb;
        this.ltdvt = ltdvt;
        this.lj = lj;
        this.hh = hh;
        this.es = es;
        this.hs = hs;
        this.gb = gb;
    }
    
    public MesureRobe(int idClient,
                  double tEnc, double ep, double cdvt, double tp, double tt,
                  double tb, double ltdvt, double lj, double hh, double es,
                  double hs, double gb) {
    this.idClient = idClient;
    this.tEnc = tEnc;
    this.ep = ep;
    this.cdvt = cdvt;
    this.tp = tp;
    this.tt = tt;
    this.tb = tb;
    this.ltdvt = ltdvt;
    this.lj = lj;
    this.hh = hh;
    this.es = es;
    this.hs = hs;
    this.gb = gb;
}


    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Timestamp getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(Timestamp dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public double gettEnc() {
        return tEnc;
    }

    public void settEnc(double tEnc) {
        this.tEnc = tEnc;
    }

    public double getEp() {
        return ep;
    }

    public void setEp(double ep) {
        this.ep = ep;
    }

    public double getCdvt() {
        return cdvt;
    }

    public void setCdvt(double cdvt) {
        this.cdvt = cdvt;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getTt() {
        return tt;
    }

    public void setTt(double tt) {
        this.tt = tt;
    }

    public double getTb() {
        return tb;
    }

    public void setTb(double tb) {
        this.tb = tb;
    }

    public double getLtdvt() {
        return ltdvt;
    }

    public void setLtdvt(double ltdvt) {
        this.ltdvt = ltdvt;
    }

    public double getLj() {
        return lj;
    }

    public void setLj(double lj) {
        this.lj = lj;
    }

    public double getHh() {
        return hh;
    }

    public void setHh(double hh) {
        this.hh = hh;
    }

    public double getEs() {
        return es;
    }

    public void setEs(double es) {
        this.es = es;
    }

    public double getHs() {
        return hs;
    }

    public void setHs(double hs) {
        this.hs = hs;
    }

    public double getGb() {
        return gb;
    }

    public void setGb(double gb) {
        this.gb = gb;
    }
    public java.sql.Timestamp getDateCreation() {
    return dateCreation;
}

    public void setDateCreation(java.sql.Timestamp dateCreation) {
    this.dateCreation = dateCreation;
    }

}
