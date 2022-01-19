package com.example.gemapp.models;

public class Taches {
    private int id;
    private String refernce;
    private String nom;
    private String duree;
    private String prixheure;
    private String dateaction;
    private int intervention_id;
    private int valsync;

    public Taches(int id, String refernce, String nom, String duree, String prixheure, String dateaction, int intervention_id, int valsync) {
        this.id = id;
        this.refernce = refernce;
        this.nom = nom;
        this.duree = duree;
        this.prixheure = prixheure;
        this.dateaction = dateaction;
        this.intervention_id = intervention_id;
        this.valsync = valsync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefernce() {
        return refernce;
    }

    public void setRefernce(String refernce) {
        this.refernce = refernce;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getPrixheure() {
        return prixheure;
    }

    public void setPrixheure(String prixheure) {
        this.prixheure = prixheure;
    }

    public String getDateaction() {
        return dateaction;
    }

    public void setDateaction(String dateaction) {
        this.dateaction = dateaction;
    }

    public int getIntervention_id() {
        return intervention_id;
    }

    public void setIntervention_id(int intervention_id) {
        this.intervention_id = intervention_id;
    }

    public int getValsync() {
        return valsync;
    }

    public void setValsync(int valsync) {
        this.valsync = valsync;
    }
}
