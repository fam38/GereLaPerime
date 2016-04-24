package com.example.martin.gerelaperime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SqlLite.object.Produit;
import SqlLite.object.ProduitReel;
import SqlLite.object.Stockage;


public class MyActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private FloatingActionButton fab;
    private ListView listViewProduit;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MyActivity.this, AddProduit.class);
                startActivity(addIntent);
            }
        });

        Produit cafe = new Produit("cafe", "cac", "110101", "trucbon");
        Produit the = new Produit("the", "THC", "110101", "trucbon");
        Stockage frigo = new Stockage("frigo", "refregirant");
        ProduitReel cafeDhier = new ProduitReel(cafe, frigo, new Date());
        ((MyApplication) getApplication()).getStorageService().addProduit(this, cafe);
        ((MyApplication) getApplication()).getStorageService().addProduit(this, new Produit("thé", "the", "11010101", "boisson"));
        ((MyApplication) getApplication()).getStorageService().addProduit(this, new Produit("saucisson", "scs", "11011101", "charcuterie"));
        ((MyApplication) getApplication()).getStorageService().addProduit(this, new Produit("jambon blanc", "jbb", "101010101", "charcuterie"));
        ((MyApplication) getApplication()).getStorageService().addProduit(this, new Produit("gruyere", "gre", "00000101", "fromage"));
        ((MyApplication) getApplication()).getStorageService().addStockage(this, frigo);
        ((MyApplication) getApplication()).getStorageService().addStockage(this, new Stockage("placard", "chaud"));
        ((MyApplication) getApplication()).getStorageService().addProduitReel(this, cafeDhier);

        ArrayList<String> listNom = new ArrayList<String>();
        listNom = (ArrayList<String>) ((MyApplication) getApplication()).getStorageService().restoreProduitReelNom(this);
        listViewProduit = (ListView) findViewById(R.id.content_main_listview_produit);
        adapter = new ArrayAdapter<String>(this, R.layout.listview_produit);
        listViewProduit.setAdapter(adapter);

        adapter.clear();
        adapter.addAll(listNom);
        adapter.notifyDataSetChanged();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), (MyApplication) getApplication());
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        ArrayList<String> listestockages = (ArrayList<String>) ((MyApplication) getApplication()).getStorageService().restoreStockageNom(this);
        listestockages.add(0, "Tous les stockages");
        mTitle = listestockages.get(number-1);
        updatelistprod();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Only show items in the action bar relevant to this screen
        // if the drawer is not showing. Otherwise, let the drawer
        // decide what to show in the action bar.
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MyActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatelistprod();
    }

    protected void updatelistprod() {
        List<String> produitList = new ArrayList<String>();
        if (mTitle == "Tous les stockages")
        {
            produitList = (ArrayList<String>) ((MyApplication) getApplication()).getStorageService().restoreProduitReelNom(this);
        }
        else
        {
            produitList = (ArrayList<String>) ((MyApplication) getApplication()).getStorageService().restoreProduitNomByStockage(this,mTitle.toString());
        }
        adapter.clear();
        adapter.addAll(produitList);
        adapter.notifyDataSetChanged();
    }

}
