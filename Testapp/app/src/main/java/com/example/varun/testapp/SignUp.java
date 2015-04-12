package com.example.varun.testapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class SignUp extends ActionBarActivity {

    public final static String USERNAME = "com.example.varun.testapp.USERNAME";
    public final static String PASSWORD = "com.example.varun.testapp.PASSWORD";
    public final static String FIRSTNAME = "com.example.varun.testapp.FIRSTNAME";
    public final static String LASTNAME = "com.example.varun.testapp.LASTNAME";
    public final static String EMAIL = "com.example.varun.testapp.EMAIL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    public void submitFormAction(View v) {
        Intent intent = new Intent(this, SubmitForm.class);
        EditText usernameET = (EditText) findViewById(R.id.username_submit);
        EditText passwordET = (EditText) findViewById(R.id.password_submit);
        EditText firstNameET = (EditText) findViewById(R.id.first_name_submit);
        EditText lastNameET = (EditText) findViewById(R.id.last_name_submit);
        EditText emailET = (EditText) findViewById(R.id.email_submit);
        String usernameVal = usernameET.getText().toString();
        String passwordVal = passwordET.getText().toString();
        String firstNameVal = firstNameET.getText().toString();
        String lastNameVal = lastNameET.getText().toString();
        String emailVal = emailET.getText().toString();
        intent.putExtra(USERNAME, usernameVal);
        intent.putExtra(PASSWORD, passwordVal);
        intent.putExtra(FIRSTNAME, firstNameVal);
        intent.putExtra(LASTNAME, lastNameVal);
        intent.putExtra(EMAIL, emailVal);

        startActivity(intent);
    }

    public void clearText(View v) {
        EditText et = (EditText) v;
        et.setText("");
    }

    public void clearPassText(View v) {
        //EditText et = (EditText) v;
        //et.setText("");
        ((EditText)v).setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}
