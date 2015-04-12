package com.example.varun.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class SubmitForm extends ActionBarActivity {

    static final String scriptLink = "http://45.55.151.194/Tutr/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);
        Intent intent = getIntent();
        String username = intent.getStringExtra(SignUp.USERNAME);
        String password = intent.getStringExtra(SignUp.PASSWORD);
        SharedPreferences preferences = getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();


        String firstName = intent.getStringExtra(SignUp.FIRSTNAME);
        String lastName = intent.getStringExtra(SignUp.LASTNAME);
        String email = intent.getStringExtra(SignUp.EMAIL);
        String phoneNum = intent.getStringExtra(SignUp.PHONENUM);
        String schoolVal = intent.getStringExtra(SignUp.SCHOOL);

        TextView uname = (TextView) findViewById(R.id.show_username);
        TextView pass = (TextView) findViewById(R.id.show_password);
        TextView firstn = (TextView) findViewById(R.id.show_first_name);
        TextView lastn = (TextView) findViewById(R.id.show_last_name);
        TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber_submit);

        /*
        uname.setText(username);
        pass.setText(password);
        firstn.setText(firstName);
        lastn.setText(lastName);
        phoneNumber.setText(phoneNum);
        */
        User newUser = new User(username, password);
        newUser.setFirstName(firstName);
        newUser.setFirstName(lastName);
        newUser.setEmail(email);
        newUser.setPhoneNum(phoneNum);
        newUser.setSchool(schoolVal);

        createInDB(username, firstName, lastName, email, phoneNum, schoolVal, password, getRegId());
    }

    private void createInDB(final String uname, final String fname, final String lname, final String email, final String phone,
                            final String school, final String pass, final String regID){
        new AsyncTask() {
            @Override
            protected String doInBackground(Object[] params) {
                String msg = "";
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(scriptLink);
                    httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                    Log.d("SFORM:","regID = "+regID);

                    nameValuePairs.add(new BasicNameValuePair("command","createUser"));
                    nameValuePairs.add(new BasicNameValuePair("uname", uname));
                    nameValuePairs.add(new BasicNameValuePair("fname", fname));
                    nameValuePairs.add(new BasicNameValuePair("lname", lname));
                    nameValuePairs.add(new BasicNameValuePair("email", email));
                    nameValuePairs.add(new BasicNameValuePair("phone", phone));
                    nameValuePairs.add(new BasicNameValuePair("school", school));
                    nameValuePairs.add(new BasicNameValuePair("pass", pass));
                    nameValuePairs.add(new BasicNameValuePair("regID", regID));


                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity ent = response.getEntity();
                    String s = EntityUtils.toString(ent, "UTF-8");
                    Log.d("TUTR", "Response is = " + s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return msg;
            }
        }.execute(null,null,null);
    }

    private String getRegId(){
        return MainActivity.regid;
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
