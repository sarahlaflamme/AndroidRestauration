package com.example.alexandre.restauration;
import com.google.gson.annotations.Expose;

/**
 * Plat ou breuvage commandé par un client
 * Created by sarah on 2015-11-02.
 */
public class Item {

    public Item (String a_nom, int a_prix) {
        nom = a_nom;
        commentaire = "";
        prix = a_prix;
    }

    public Item (String a_nom, String a_commentaire, int a_prix) {
        nom = a_nom;
        commentaire = a_commentaire;
        prix = a_prix;
    }

    @Expose
    private String nom = null;
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    @Expose
    private String commentaire = null;
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public String getCommentaire() {
        return commentaire;
    }

    @Expose
    private int prix = 0;
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public int getPrix() {
        return prix;
    }

}
