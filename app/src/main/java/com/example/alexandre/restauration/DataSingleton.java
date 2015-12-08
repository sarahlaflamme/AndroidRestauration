package com.example.alexandre.restauration;

import java.util.ArrayList;

/**
 * Created by Daniel-Junior on 2015-12-07.
 */
public class DataSingleton {
    private ArrayList<Commande> data = new ArrayList<Commande>();
    public Serveur serveur = new Serveur();
    public Commande commande = new Commande();
    public ArrayList getCommandesPretes(){
        return data;
    };
    public void ajouterCommande(Commande a_commande){
        this.data.add(a_commande);
    };
    public void supprimerCommande(Integer a_index){
        this.data.remove(a_index);
    };
    public void definirServeur(String a_id){
        this.serveur.setNom(a_id);
    };
    private static final DataSingleton data_singleton = new DataSingleton();
    public static DataSingleton getInstance() {return data_singleton;}
}
