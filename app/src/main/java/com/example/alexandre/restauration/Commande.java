package com.example.alexandre.restauration;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    /**
     * Ensemble des items commandés par une table
     * Created by sarah on 2015-11-02.
     */

    public class Commande {

        public Commande () {
            items = new ArrayList<Item>();
        }

        public Commande (Serveur a_serveur, String a_table, String a_rqst) {
            items = new ArrayList<Item>();
            serveur = a_serveur;
            table = a_table;
            Date a_date = new Date();
            heure_commande = a_date.toString();
            rqst = a_rqst;
        }

        public Commande (List<Item> a_items, Serveur a_serveur, String a_table) {
            items = a_items;
            serveur = a_serveur;
            table = a_table;
            Date a_date = new Date();
            heure_commande = a_date.toString();
        }

        public void ajouterItem(Item a_item) {
            items.add(a_item);
        }

        @Expose
        private Serveur serveur;
        public void setServeur(Serveur serveur) {
            this.serveur = serveur;
        }
        public Serveur getServeur() {
            return serveur;
        }

        @Expose
        private String table;
        public void setTable(String table) {
            this.table = table;
        }
        public String getTable() {
            return table;
        }

        @Expose
        private String heure_commande;
        public void setHeureCommande(String heure_commande) {
            this.heure_commande = heure_commande;
        }
        public String getHeureCommande() {
            return heure_commande;
        }

        @Expose
        private List<Item> items;
        public void setItems(List<Item> items) {
            this.items = items;
        }
        public List<Item> getItems() {
            return items;
        }

        // Type de requête échangée entre les machines du serveur
        @Expose
        private String rqst;
        public void setRqst(String rqst) {
            this.rqst = rqst;
        }
        public String getRqst() {
            return rqst;
        }
    }