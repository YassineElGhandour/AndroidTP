package com.example.yassine.test;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity
{

    Button btn;
    Button signupp;
    Login user;
    EditText login,pass, pass2;
    LoginBDD logbdd;
    LoginBDD del;
    Button supp;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupp = (Button)findViewById(R.id.signupbut);
        supp =(Button) findViewById(R.id.supprimer);
        login = (EditText)findViewById(R.id.nom);
        pass = (EditText)findViewById(R.id.mdp);
        pass2 = (EditText) findViewById(R.id.mdp2);


        signupp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                String log = login.getText().toString();
                String password1 = pass.getText().toString();
                String password2 = pass2.getText().toString();

                user = new Login(log , password1);
                logbdd = new LoginBDD(SignUp.this);
                Login result = new Login();
                logbdd.open();
                result = logbdd.getLoginWithlogin( log, password1);
                if(result==null)
                {
                    logbdd.insertlogin(user);
                    logbdd.close();
                    Toast.makeText(getApplicationContext(),"Succès: Utilisateur est crée",Toast.LENGTH_LONG).show();
                }
                else if(!password1.equals(password2))
                {
                    Toast.makeText(getApplicationContext(),"Vous n'avez pas bien confirmer le MDP",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Utilisateur déjà existant",Toast.LENGTH_LONG).show();
                }

                logbdd.close();

            }
        });

        supp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                String log = login.getText().toString();
                String password1 = pass.getText().toString();
                String password2 = pass2.getText().toString();


                user = new Login(log , password1);
                del = new LoginBDD(SignUp.this);
                Login result = new Login();
                del.open();
                result = del.getLoginWithlogin( log, password1);
                if(result==null)
                {
                    Toast.makeText(getApplicationContext(),"Cet utilisateur n'existe pas",Toast.LENGTH_LONG).show();
                }
                else if(!password1.equals(password2))
                {
                    Toast.makeText(getApplicationContext(),"Vous n'avez pas bien confirmer le MDP",Toast.LENGTH_LONG).show();
                }
                else
                {
                    del.delete(result);
                    Toast.makeText(getApplicationContext(),"Utilisateur supprimé",Toast.LENGTH_LONG).show();

                }
                del.close();
            }
        });
    }
}
