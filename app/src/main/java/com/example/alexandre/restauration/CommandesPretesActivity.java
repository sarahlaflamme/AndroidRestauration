package com.example.alexandre.restauration;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sarah on 2015-12-04.
 */
public class CommandesPretesActivity extends Activity {

    private Button bouton_retour;
    private ArrayList<Commande> liste_commandes;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes_pretes);
    }
}