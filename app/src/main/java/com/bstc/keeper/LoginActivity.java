package com.bstc.keeper;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;


public class LoginActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button)findViewById(R.id.loginButton);

    }

    //when the log in button is clicked
    public void loginClick(View view){
        String loginEmail = "";
        String password = "";
        loginEmail = ((EditText)findViewById(R.id.loginEmail)).getText().toString();
        password = ((EditText)findViewById(R.id.password)).getText().toString();

        Log.i("briansdebugging", loginEmail);
        Log.i("briansdebugging", password);

        Intent i = new Intent(this, Home.class);
        i.putExtra("loginEmail", loginEmail);
        i.putExtra("password",password);
        startActivity(i);
    }
}
