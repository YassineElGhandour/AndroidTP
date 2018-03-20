package com.example.yassine.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {
    ListView l;
    List<Etablissement> Etablissements;
    Etablissement Etat;
    ArrayEtablissementAdapter adapter;
    String lat = "23.568845";
    String log = "23.56884523568845";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        final EtabBDD etabBDD = new EtabBDD(this);
        ArrayList<Etablissement> Etablissements = etabBDD.getAllInformationNotDetailed();
        adapter = new ArrayEtablissementAdapter(this, R.layout.item,Etablissements);
        l = (ListView) findViewById(R.id.listview);
        l.setAdapter(adapter);
        l.requestLayout();


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Etablissement item = (Etablissement)l.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext() , item.getLabel() + " selected", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("Name",item.getName());
                intent.putExtra("Desc",item.getLabel());
                intent.putExtra("Latitude",item.getLat());
                intent.putExtra("Longitude",item.getLog());
                startActivity(intent);
            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "à propos",Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "Ajouter un etablissement", Toast.LENGTH_LONG).show();
                Intent t = new Intent(ListActivity.this,AjouterEtablissement.class);
                startActivity(t);
                break;
        }
        return super.onOptionsItemSelected(item); }
}









