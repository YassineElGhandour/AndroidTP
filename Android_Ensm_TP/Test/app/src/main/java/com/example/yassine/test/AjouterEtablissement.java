package com.example.yassine.test;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterEtablissement extends Activity
{

    Button btn;
    Button addEtab;
    Etablissement etab, etab2;
    EditText nomEtab, labelEtab, latEtab, logEtab;
    EtabBDD etabbdd;
    EtabBDD del;
    Button supp;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_etab);

        addEtab = (Button)findViewById(R.id.addEtab);
        nomEtab = (EditText) findViewById(R.id.nomEtab);
        labelEtab = (EditText)findViewById(R.id.labelEtab);
        latEtab = (EditText) findViewById(R.id.latEtab);
        logEtab = (EditText) findViewById(R.id.logEtab);


       addEtab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                String nomEtabString = nomEtab.getText().toString();
                String labelEtabString = labelEtab.getText().toString();
                double latEtabDouble = Double.parseDouble(latEtab.getText().toString());
                double logEtabDouble = Double.parseDouble(logEtab.getText().toString());

                etab = new Etablissement(nomEtabString , labelEtabString, 1, latEtabDouble, logEtabDouble);
                etabbdd = new EtabBDD(AjouterEtablissement.this);
                Etablissement result = new Etablissement();
                etabbdd.open();
                result = etabbdd.getAllInformation(nomEtabString, labelEtabString);
                if(result == null)
                {
                    etabbdd.insertEtab(etab);
                    etabbdd.close();
                    Toast.makeText(getApplicationContext(),"Succès: Etablissement est crée",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AjouterEtablissement.this, ListActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Etablissement déjà existante",Toast.LENGTH_LONG).show();
                }

                etabbdd.close();

            }
        });

    }
}
