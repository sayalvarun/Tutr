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
import android.view.View;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Home extends ActionBarActivity {

    static final String scriptLink = "http://45.55.151.194/Tutr/api.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String user = intent.getStringExtra(MainActivity.USERNAME);
        String pass = intent.getStringExtra(MainActivity.PASSWORD);
        TextView txv = (TextView) findViewById(R.id.textView);
        txv.setText("Hello, " + user + "!");

        try {
            String resp = String.valueOf(getOpenMessages(user));
            Log.d("HOME", "resp = " + resp);
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        catch (InterruptedException i){
            i.printStackTrace();
        }
        //Ask for new home page here
    }

    private Object getUserID(final String username) throws ExecutionException, InterruptedException {
        return new AsyncTask() {
            protected String doInBackground(Object[] params) {
                String msg = "";
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(scriptLink);
                    httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                    nameValuePairs.add(new BasicNameValuePair("command","getUserID"));
                    nameValuePairs.add(new BasicNameValuePair("uname",username));

                    Log.d("HOME","username = "+username);

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity ent = response.getEntity();
                    String s = EntityUtils.toString(ent, "utf-8");

                    Log.d("HOME","userid = "+s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return msg;
            }
        }.execute(null,null,null).get();
    }

    private Object getOpenMessages(final String username) throws ExecutionException, InterruptedException {

       return new AsyncTask() {

            @Override
            protected String doInBackground(Object[] params) {
                String msg = "";
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(scriptLink);
                    httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                    nameValuePairs.add(new BasicNameValuePair("command","getUserID"));
                    nameValuePairs.add(new BasicNameValuePair("uname",username));

                    Log.d("HOME", "username = " + username);

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity ent = response.getEntity();
                    String s = EntityUtils.toString(ent, "UTF-8");

                    Log.d("HOME","userid = "+s);

                    nameValuePairs.clear();

                    nameValuePairs.add(new BasicNameValuePair("command","getOpenMessages"));
                    nameValuePairs.add(new BasicNameValuePair("userID",String.valueOf(2)));
                    //nameValuePairs.add(new BasicNameValuePair("sortBy",null));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                    response = httpclient.execute(httppost);
                    ent = response.getEntity();
                    s = EntityUtils.toString(ent,"UTF-8");
                    try {
                        //Log.d("HOME","S = "+ s);
                        JSONObject j = new JSONObject(s);
                        for(int i = 0; i<j.names().length(); i++){
                            Log.d("HOME", "key = " + j.names().getString(i) + " value = " + j.get(j.names().getString(i)));
                        }
                    }
                    catch(JSONException e) {
                        e.printStackTrace();
                    }



                    Log.d("TUTR", "Response is = " + s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return msg;
            }
        }.execute(null,null,null).get();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public void logoutAction(View w){
        SharedPreferences preferences = getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();
        Intent returnIntent = new Intent(this, MainActivity.class);
        startActivity(returnIntent);
    }
}
