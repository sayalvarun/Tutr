package com.example.varun.testapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SubmitForm extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);
        Intent intent = getIntent();
        String username = intent.getStringExtra(SignUp.USERNAME);
        String password = intent.getStringExtra(SignUp.PASSWORD);
        String firstName = intent.getStringExtra(SignUp.FIRSTNAME);
        String lastName = intent.getStringExtra(SignUp.LASTNAME);
        String email = intent.getStringExtra(SignUp.EMAIL);
        TextView uname = (TextView) findViewById(R.id.show_username);
        TextView pass = (TextView) findViewById(R.id.show_password);
        TextView firstn = (TextView) findViewById(R.id.show_first_name);
        TextView lastn = (TextView) findViewById(R.id.show_last_name);
        uname.setText(username);
        pass.setText(password);
        firstn.setText(firstName);
        lastn.setText(lastName);
        User newUser = new User(username, password);
        newUser.setFirstName(firstName);
        newUser.setFirstName(lastName);
        newUser.setEmail(email);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_submit_form, menu);
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


}
