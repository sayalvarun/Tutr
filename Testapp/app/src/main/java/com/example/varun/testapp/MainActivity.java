package com.example.varun.testapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    public static final String USERNAME = "com.example.varun.testapp.USERNAME";
    public static final String PASSWORD = "com.example.varun.testapp.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginAction(View v){
        Intent intent = new Intent(this, LoginUser.class);
        EditText usernameET = (EditText) findViewById(R.id.username_edittext);
        EditText passwordET = (EditText) findViewById(R.id.pass_edittext);
        String usernameVal = usernameET.getText().toString();
        String passVal = passwordET.getText().toString();
        intent.putExtra(USERNAME,usernameVal);
        intent.putExtra(PASSWORD,passVal);
        startActivity(intent);
    }
}
