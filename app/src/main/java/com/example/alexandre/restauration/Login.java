package com.example.alexandre.restauration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    Button b_connexion;
    EditText et_serveur;
    Intent make_order_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b_connexion = (Button) findViewById(R.id.b_connexion);
        et_serveur = (EditText) findViewById(R.id.et_serveur);
        b_connexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataSingleton.getInstance().definirServeur(et_serveur.getText().toString());
                make_order_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(make_order_intent);
            }
        });
    }
}
