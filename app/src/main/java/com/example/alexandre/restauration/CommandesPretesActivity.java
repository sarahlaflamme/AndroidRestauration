package com.example.alexandre.restauration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sarah on 2015-12-04.
 */
public class CommandesPretesActivity extends Activity {

    private Button b_retirer;
    private Button b_update;
    private ArrayList<Commande> liste_commandes;
    private ListView listview;
    ArrayAdapter<String> adapteurCommandes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes_pretes);
        b_update = (Button) findViewById(R.id.b_update);
        b_retirer = (Button) findViewById(R.id.b_retirer);
        listview = (ListView) findViewById(R.id.listeCommandes);
        adapteurCommandes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataSingleton.getInstance().getCommandesPretes());
        listview.setAdapter(adapteurCommandes);
        b_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapteurCommandes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, DataSingleton.getInstance().getCommandesPretes());
                listview.setAdapter(adapteurCommandes);
            }
        });
    }

}