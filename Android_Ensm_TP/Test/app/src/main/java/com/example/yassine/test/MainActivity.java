package com.example.yassine.test;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    private TextView signup;
    private Button login;
    private EditText pass;
    private EditText pseudo;
    Login user;
    LoginBDD logbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pseudo = (EditText)findViewById(R.id.pseudo);
        pass = (EditText)findViewById(R.id.password);



        login = (Button) findViewById(R.id.button);

        signup = (TextView) findViewById(R.id.signupclickable);

        signup.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                String log = pseudo.getText().toString();
                String password = pass.getText().toString();

                user = new Login(log , password);
                logbdd = new LoginBDD(MainActivity.this);
                Login result = new Login();
                logbdd.open();
                result = logbdd.getLoginWithlogin(login.getText().toString(),pass.getText().toString());
                if(result != null)
                {
                    Toast.makeText(getApplicationContext(),"Mauvais Utilisateur ou MDP.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(i);
                }
                logbdd.close();

            }
        });


    }

}

