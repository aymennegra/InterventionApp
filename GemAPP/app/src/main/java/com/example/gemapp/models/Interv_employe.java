package com.example.gemapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employes_interventions")
public class Interv_employe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "employe_id")
    private int employe_id;
    @ColumnInfo(name = "intervention_id")
    private int intervention_id;

    public Interv_employe(int id, int employe_id, int intervention_id) {
        this.id = id;
        this.employe_id = employe_id;
        this.intervention_id = intervention_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public int getIntervention_id() {
        return intervention_id;
    }

    public void setIntervention_id(int intervention_id) {
        this.intervention_id = intervention_id;
    }
}
