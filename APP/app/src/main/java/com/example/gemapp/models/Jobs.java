package com.example.gemapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Jobs")
public class Jobs {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nom_entreprise")
    private String nom_entreprise;
    @ColumnInfo(name = "poste")
    private String poste;
    @ColumnInfo(name = "exigences")
    private String exigences;
    @ColumnInfo(name = "contact_recruteur")
    private  String contact_recruteur;

    public Jobs(int id, String nom_entreprise, String poste, String exigences, String contact_recruteur) {
        this.id = id;
        this.nom_entreprise = nom_entreprise;
        this.poste = poste;
        this.exigences = exigences;
        this.contact_recruteur = contact_recruteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

    public String getContact_recruteur() {
        return contact_recruteur;
    }

    public void setContact_recruteur(String contact_recruteur) {
        this.contact_recruteur = contact_recruteur;
    }
}
