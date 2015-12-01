package com.example.alexandre.restauration;


import com.google.gson.annotations.Expose;

/**
 * Personne qui prend les commandes et les apporte aux clients
 * Created by sarah on 2015-11-02.
 */
public class Serveur {

    @Expose
    private String id = null;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    @Expose
    private String nom = null;
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
}
