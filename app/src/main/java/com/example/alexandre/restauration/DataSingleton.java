package com.example.alexandre.restauration;

import java.util.ArrayList;

/**
 * Created by Daniel-Junior on 2015-12-07.
 */
public class DataSingleton {
    private ArrayList<Commande> data = new ArrayList<Commande>();
    public ArrayList getCommandesPretes(){
        return data;
    };
    public void addCommande(Commande a_commande){
        this.data.add(a_commande);
        for (Commande v : this.data) {
            System.out.println(v.toString());
        }
    };
    public void removeCommande(Integer a_index){
        this.data.remove(a_index);
    };
    private static final DataSingleton data_singleton = new DataSingleton();
    public static DataSingleton getInstance() {return data_singleton;}
}
