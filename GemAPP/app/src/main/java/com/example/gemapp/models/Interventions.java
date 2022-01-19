package com.example.gemapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "interventions")
public class Interventions {

    public Interventions(int id, String titre, String commentaires, String heuredebutplan, String heurefinplan) {
        this.id = id;
        this.titre = titre;
        this.heuredebutplan = heuredebutplan;
        this.heurefinplan = heurefinplan;
        this.commentaires = commentaires;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "titre")
    private String titre;
    @ColumnInfo(name = "datedebut")
    private String datedebut;
    @ColumnInfo(name = "datefin")
    private String datefin;
    @ColumnInfo(name = "heuredebutplan")
    private  String heuredebutplan;
    @ColumnInfo(name = "heurefinplan")
    private String heurefinplan;
    @ColumnInfo(name = "commentaires")
    private String commentaires;
    @ColumnInfo(name = "dateplanification")
    private String dateplanification;
    @ColumnInfo(name = "heuredebuteffect")
    private String heuredebuteffect;
    @ColumnInfo(name = "heurefineffect")
    private String heurefineffect;
    @ColumnInfo(name = "terminee")
    private int terminee;
    @ColumnInfo(name = "dateterminaison")
    private String dateterminaison;
    @ColumnInfo(name = "validee")
    private int validee;
    @ColumnInfo(name = "datevalidation")
    private String datevalidation;
    @ColumnInfo(name = "priorite_id")
    private int priorite_id;
    @ColumnInfo(name = "site_id")
    private  int site_id;
    @ColumnInfo(name = "valsync")
    private int valsync;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getHeuredebutplan() {
        return heuredebutplan;
    }

    public void setHeuredebutplan(String heuredebutplan) {
        this.heuredebutplan = heuredebutplan;
    }

    public String getHeurefinplan() {
        return heurefinplan;
    }

    public void setHeurefinplan(String heurefinplan) {
        this.heurefinplan = heurefinplan;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getDateplanification() {
        return dateplanification;
    }

    public void setDateplanification(String dateplanification) {
        this.dateplanification = dateplanification;
    }

    public String getHeuredebuteffect() {
        return heuredebuteffect;
    }

    public void setHeuredebuteffect(String heuredebuteffect) {
        this.heuredebuteffect = heuredebuteffect;
    }

    public String getHeurefineffect() {
        return heurefineffect;
    }

    public void setHeurefineffect(String heurefineffect) {
        this.heurefineffect = heurefineffect;
    }

    public int getTerminee() {
        return terminee;
    }

    public void setTerminee(int terminee) {
        this.terminee = terminee;
    }

    public String getDateterminaison() {
        return dateterminaison;
    }

    public void setDateterminaison(String dateterminaison) {
        this.dateterminaison = dateterminaison;
    }

    public int getValidee() {
        return validee;
    }

    public void setValidee(int validee) {
        this.validee = validee;
    }

    public String getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(String datevalidation) {
        this.datevalidation = datevalidation;
    }

    public int getPriorite_id() {
        return priorite_id;
    }

    public void setPriorite_id(int priorite_id) {
        this.priorite_id = priorite_id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public int getValsync() {
        return valsync;
    }

    public void setValsync(int valsync) {
        this.valsync = valsync;
    }
}
