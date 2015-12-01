package com.example.alexandre.restauration;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> liste;
    private ListView listview;
    private Serveur serveur;
    private Commande commande;
    Gson gson;
    public int notif_id = 1;

    JSONObject data;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.0.104:8080");
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

        mSocket.on("message", onNewMessage);
        mSocket.connect();

        liste = new ArrayList<String>();
        listview = (ListView) findViewById(R.id.listView);

        gson = new GsonBuilder().create();

        serveur = new Serveur();
        serveur.setId("1");
        serveur.setNom("Vanesssa");

        commande = new Commande ();
        commande.setServeur(serveur);
        commande.setTable("1");
        commande.setRqst("New");

        final ArrayList<String> liste = new ArrayList<String>();
        associerValeurs();
        setBoutonVider();
        setBoutonEnvoyer();
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
        commande.ajouterItem(item);
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
        commande.setItems(new ArrayList<Item>());
        rafraichirListe();
    }

    public void setBoutonEnvoyer() {
        Button bouton = (Button) findViewById(R.id.boutonEnvoyer);
        bouton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (commande.getItems().size() > 0) {
                    try {
                        JSONObject json_commande = new JSONObject(gson.toJson(commande));
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
        if (commande_terminee.getRqst() == "ready") {
            sendNotification(commande_terminee);
        }
    }

    public void sendNotification(Commande a_commande_terminee) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.drawable.icone_statut);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_principale));

        /**
         * Set the text of the notification. This sample sets the three most commononly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
         *    anything vital!
         */
        builder.setContentTitle("La commande #" + notif_id+ " est prête!");
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
