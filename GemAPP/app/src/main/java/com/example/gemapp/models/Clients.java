package com.example.gemapp.models;

public class Clients {
    private int id;
    private String nom;
    private String adresse;
    private String tel;
    private String fax;
    private String email;
    private String contact;
    private String telcontact;
    private int valsync;

    public Clients(int id, String nom, String adresse, String tel, String fax, String email, String contact, String telcontact, int valsync) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.fax = fax;
        this.email = email;
        this.contact = contact;
        this.telcontact = telcontact;
        this.valsync = valsync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelcontact() {
        return telcontact;
    }

    public void setTelcontact(String telcontact) {
        this.telcontact = telcontact;
    }

    public int getValsync() {
        return valsync;
    }

    public void setValsync(int valsync) {
        this.valsync = valsync;
    }
}
