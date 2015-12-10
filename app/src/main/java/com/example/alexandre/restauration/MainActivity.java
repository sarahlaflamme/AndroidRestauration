package com.example.alexandre.restauration;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bCommandes;
    Intent commande_pretes;
    private ArrayList<String> liste;
    private ListView listview;
    private Spinner spinner;
    private ArrayList<Serveur> serveurs;
    Gson gson;
    JSONObject data;
    public int notif_id = 1;
    DataSingleton data_manager;


    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.0.0.43:8080");
            JSONObject message_initial = new JSONObject();
            try {
                message_initial.put("rqst", "sendall");
                mSocket.emit("message", message_initial);
            }
            catch (JSONException e) {
                System.out.println("Impossible d'envoyer le message initial");
            }
        } catch (URISyntaxException e) {
            System.out.println("Impossible de se connecter au socket");
        }
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        recevoir_commande(data);
                    } catch (Exception e) {
                        System.out.println("Emitter a planté");
                        return;
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_manager = DataSingleton.getInstance();
        mSocket.on("message", onNewMessage);
        mSocket.connect();

        liste = new ArrayList<String>();
        listview = (ListView) findViewById(R.id.listView);

        bCommandes = (Button) findViewById(R.id.bCommandes);
        gson = new GsonBuilder().create();

        setListeServeurs();

        data_manager.commande.setServeur(data_manager.serveur);
        data_manager.commande.setTable("1");
        data_manager.commande.setRqst("new");

        final ArrayList<String> liste = new ArrayList<String>();
        associerValeurs();
        setBoutonVider();
        setBoutonEnvoyer();
        bCommandes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                commande_pretes = new Intent(getApplicationContext(), CommandesPretesActivity.class);
                startActivity(commande_pretes);
            }
        });
    }

    private void setListeServeurs() {
        spinner = (Spinner) findViewById(R.id.spinner);
        serveurs = new ArrayList<Serveur>();
        Serveur vanessa = new Serveur();
        vanessa.setNom("Vanesssa");
        vanessa.setId("1");
        serveurs.add(vanessa);
        Serveur rogere = new Serveur();
        rogere.setId("2");
        rogere.setNom("Rogère");
        serveurs.add(rogere);
        ArrayList<String> nomsServeurs = new ArrayList<String>();
        nomsServeurs.add("Vanesssa");
        nomsServeurs.add("Rogère");
        ArrayAdapter<String> adapteurServeurs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomsServeurs);
        spinner.setAdapter(adapteurServeurs);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //commande.setServeur(serveurs.get(spinner.getSelectedItemPosition()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView){
            }
        });
    }

    private void associerValeurs() {
        setBouton((Button) findViewById(R.id.b2Oeufs), "2 oeufs", "", 250);
        setBouton((Button) findViewById(R.id.b2Oeufsbacon), "2 oeufs bacon", "", 400);
        setBouton((Button) findViewById(R.id.bAcide), "Acide", "", 40000);
        setBouton((Button) findViewById(R.id.bCafe), "Café", "", 100);
        setBouton((Button) findViewById(R.id.bCalifornienne), "Californienne", "", 1000);
        setBouton((Button) findViewById(R.id.bCesar), "César", "", 999);
        setBouton((Button) findViewById(R.id.bConcombre), "Concombre", "", 1);
        setBouton((Button) findViewById(R.id.bCrepes), "Crêpes", "", 600);
        setBouton((Button) findViewById(R.id.bForetNoire), "Fôret noire", "", 1514);
        setBouton((Button) findViewById(R.id.bFruits), "Fruits", "", 1234);
        setBouton((Button) findViewById(R.id.bGauffres), "Gauffres", "", 4500);
        setBouton((Button) findViewById(R.id.bGrilledCheese), "Grilled cheese", "", 253);
        setBouton((Button) findViewById(R.id.bHamburger), "Hamburger", "", 45678);
        setBouton((Button) findViewById(R.id.bJello), "Jello", "", 987654321);
        setBouton((Button) findViewById(R.id.bJusOrange), "Jus d'orange", "", -314);
        setBouton((Button) findViewById(R.id.bMacedoine), "Macédoine", "Région d'Europe", 45000000);
        setBouton((Button) findViewById(R.id.bOeuf), "oeuf", "Tourné", 150);
        setBouton((Button) findViewById(R.id.bPouding), "Pouding", "", 5);
        setBouton((Button) findViewById(R.id.bPoutine), "Poutine", "Miam!", 750);
        setBouton((Button) findViewById(R.id.bSaladeFruit), "Salade de fruit", "Juste du cantaloup", 1212);
        setBouton((Button) findViewById(R.id.bSaladePoulet), "Poulet grillé", "Pas vraiment du poulet", 10);
        setBouton((Button) findViewById(R.id.bSandwich), "Sandwich", "Make me a sandwich B****!", 4507);
        setBouton((Button) findViewById(R.id.bSmokedMeat), "Smoked meat", "Fumé au monoxide", 10051);
        setBouton((Button) findViewById(R.id.bTaco), "Taco", "Fait de vrais mexicains", 980023);
        setBouton((Button) findViewById(R.id.bThé), "Thé", "Trop 'Fancy'", 100003400);
        setBouton((Button) findViewById(R.id.bTiramisu), "Tiramisu", "Kossé c ça?", 70450);
        setBouton((Button) findViewById(R.id.bTisane), "Tisane", "Moi, j'aurais pas choisi ça c'est trop 'mainstream'", 23400);
        setBouton((Button) findViewById(R.id.bVodka), "Vodka", "100% russe et servi entre 8 et 10 heure", 7534);
        setBouton((Button) findViewById(R.id.bVolonte), "Volonté", "Ça vous prendrait un sourire aussi", 500);
        setBouton((Button) findViewById(R.id.bXiaolongbao), "Xiaolongbao", "Ça goûte exotique. C'est comme si la Chine entière était venue dans ma bouche.", 403);
    }

    private void setBouton(Button bouton, final String nom, final String commentaire, final int prix) {
        bouton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterItem(nom, commentaire, prix);
            }
        });
    }

    public void ajouterItem(String a_nom, String a_commentaire, int a_prix)  {
        Item item = new Item(a_nom, a_commentaire, a_prix);
        data_manager.commande.ajouterItem(item);
        String nom = String.format("%.2f", (float) a_prix/100) + "$ " + item.getNom();
        liste.add(nom);
        rafraichirListe();
    }

    public void rafraichirListe() {
        ArrayAdapter<String> adapteur = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, liste);
        listview.setAdapter(adapteur);
    }

    public void setBoutonVider() {
        Button bouton = (Button) findViewById(R.id.boutonRetirer);
        bouton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viderListes();
            }
        });
    }

    public void viderListes() {
        liste = new ArrayList<String>();
        data_manager.commande.setItems(new ArrayList<Item>());
        rafraichirListe();
    }

    public void setBoutonEnvoyer() {
        Button bouton = (Button) findViewById(R.id.boutonEnvoyer);
        bouton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (data_manager.commande.getItems().size() > 0) {
                    try {
                        JSONObject json_commande = new JSONObject(gson.toJson(data_manager.commande));
                        mSocket.emit("message", json_commande);
                        viderListes();
                    } catch (JSONException e) {
                        System.out.println("Impossible d'envoyer la commande");
                    }
                }
            }
        });
    }


    public void recevoir_commande(JSONObject data){
        Commande commande_terminee = gson.fromJson(data.toString(), Commande.class);
        if (commande_terminee.getRqst().equals("ready") && commande_terminee.getServeur().getNom().equals(data_manager.serveur.getNom())) {
            DataSingleton.getInstance().ajouterCommande(commande_terminee);
            sendNotification(commande_terminee);
        }
    }

    public void sendNotification(Commande a_commande_terminee) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.drawable.icone_statut2);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_principale));
        builder.setContentTitle("Commande prête!");
        builder.setContentText("Table " + a_commande_terminee.getTable());
        builder.setPriority(2);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        notif_id = notif_id + 1;

        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         */
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.notify(notif_id, builder.build());
        // END_INCLUDE(send_notification)
    }

}
