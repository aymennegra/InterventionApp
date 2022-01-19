package com.example.gemapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employes")
public class Employes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "login")
    private  String login;
    @ColumnInfo(name = "pwd")
    private String pwd;
    @ColumnInfo(name = "prenom")
    private String prenom;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "actif")
    private int actif;
    @ColumnInfo(name = "valsync")
    private int valsync;

    public Employes(String login, String pwd, String prenom, String nom, String email, int actif, int valsync) {
        this.login = login;
        this.pwd = pwd;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.actif = actif;
        this.valsync = valsync;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public int getValsync() {
        return valsync;
    }

    public void setValsync(int valsync) {
        this.valsync = valsync;
    }
}
