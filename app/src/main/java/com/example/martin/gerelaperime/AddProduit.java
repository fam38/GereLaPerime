package com.example.martin.gerelaperime;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SqlLite.object.Produit;
import SqlLite.object.ProduitReel;
import SqlLite.object.Stockage;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Martin on 21/04/2016.
 */

public class AddProduit extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produit);
        final Spinner listeStockage = (Spinner) findViewById(R.id.activity_add_liststock);
        final Spinner listeProduit = (Spinner) findViewById(R.id.activity_add_listproduit);

        final List<Stockage> tlStockage = ((MyApplication) getApplication()).getStorageService().restoreStockage(AddProduit.this);
        final List<Produit> tlProduit = ((MyApplication) getApplication()).getStorageService().restoreProduit(AddProduit.this);
        List<String> stockages = new ArrayList();
        for (int i = 0; i < tlStockage.size(); i++) {
            stockages.add(tlStockage.get(i).getNomStockage());
        }
        List<String> produits = new ArrayList();
        for (int i = 0; i < tlProduit.size(); i++) {
            produits.add(tlProduit.get(i).getNomProduit());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stockages);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,produits);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listeStockage.setAdapter(adapter);
        listeProduit.setAdapter(adapter2);
        Button button = (Button) findViewById(R.id.activity_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ((MyApplication) getApplication()).getStorageService().addProduitReel(AddProduit.this, new ProduitReel(tlProduit.get(listeProduit.getSelectedItemPosition()), tlStockage.get(listeStockage.getSelectedItemPosition()), new Date()));
                    AddProduit.this.finish();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddProduit Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.martin.gerelaperime/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddProduit Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.martin.gerelaperime/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

