package innogeeks.inno.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    private EditText editTextUserName;
    private EditText editTextPassword;

    public static final String USER_NAME = "USERNAME";

    String username;
    String password;
   Button rslt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        TextView frg= (TextView)findViewById(R.id.frgt);

        /**     final TextView tv1 = new TextView(this);
             tv1.setText("Hii Folks");
             tv1.setTextSize(14);
             tv1.setGravity(Gravity.CENTER_VERTICAL);
             LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);
             ll.addView(tv1);
     */
      rslt = (Button) findViewById(R.id.button1);
        rslt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent ri = new Intent(MainActivity.this, regis.class);
                startActivity(ri);
            }
        });
frg.setOnClickListener(new View.OnClickListener() {
                           public void onClick(View view) {
                               username = editTextUserName.getText().toString();
                          //     Random random1=new Random();
                            //   int randno =random1.nextInt(99999) +100000;
                            //   Intent intent = new Intent(MainActivity.this,Modpwd.class);// Qspin.class);// Qualification.class);

                              // intent.putExtra("randotp", String.valueOf( randno));
                              // intent.putExtra("mob", username);
                               if(username!=null)
                               {
                                   Toast.makeText(getApplicationContext(), "server "+username, Toast.LENGTH_LONG).show();
                                   SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                                   SharedPreferences.Editor editor = sharedpreferences.edit();


                                   editor.putString("Nameuser",username);
                                   editor.putString("role","1");
                                   editor.commit();
                                   SavePreferences("Nameuser",username);
                              //     Intent intent = new Intent(MainActivity.this, UserProfile.class);
                               //    intent.putExtra(USER_NAME, username);
                              //     finish();
                              //     startActivity(intent);
                               //   upref(username,username);
                                    loginfrg1(username);
                               }
                               else{
                                   editTextUserName.setError("enter user name");
                           }
                           }
                       }
);



    }
    public void invokeLogin(View view){
        username = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();
        username=username.trim();
        login(username,password);
    }

    private void login(final String username, String password) {

        class LoginAsync extends AsyncTask<String, Void, String>{

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];

                InputStream is = null;
              String result = null;

                try{
                    String username = (String)params[0];
                    String password = (String)params[1];

                    String ad="http://192.168.0.20:86/inn.php";//.toString();
//                    String ad="http://localhost/inn.php";//.toString();

                    String link1;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;

                        data1 = "?id=" + username;
                        data1 += "&name=" + password;

                      link1 = ad + data1;
//link1="http://localhost:80/inn.php?id="+username+"&name="+password;"http://192.168.8.100:80/hi.php");//
                        URL url1 = new URL(link1);
                        HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();

                        bufferedReader = new BufferedReader(new InputStreamReader(con1.getInputStream()));
                        result = bufferedReader.readLine();


                    Log.d("ProfileScore",""+result);

                        return result;

                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override

            protected void onPostExecute(String result){
                String s = result.trim();
                loadingDialog.dismiss();
                SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                if(s.equalsIgnoreCase("1"))
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Nameuser",username);
                    editor.putString("role",s );
                    editor.commit();
                    SavePreferences("Nameuser",username);
                    Intent intent = new Intent(MainActivity.this, UserProfile.class);
                    intent.putExtra(USER_NAME, username);
                    finish();
                    startActivity(intent);
                }if(s.equalsIgnoreCase("2"))
                {

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    SavePreferences("Nameuser",username);
                    editor.putString("Nameuser",username);
                    editor.putString("role",s );
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, Recmenu.class);
                    intent.putExtra(USER_NAME, username);
                    SavePreferences("Nameuser",username);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password"+s, Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }
    private void loginfrg(final String username) {

        class LoginAsync extends AsyncTask<String, Void, String>{

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];

                InputStream is = null;
                String result = null;

                try{
                    String username1 = //(String)
                            params[0];

                    String ad="http://192.168.8.100:80/innog/frgpwd.php";//.toString();
//                    String ad="http://localhost/inn.php";//.toString();

                    String link14;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;

                    data1 = "?uname=" + username1;

                    link14 = "http://192.168.8.100:80/innog/frgpwd.php?uname=" + username1;//ad + data1;
//link1="http://localhost:80/inn.php?id="+username+"&name="+password;"http://192.168.8.100:80/hi.php");//
                    URL url14 = new URL(link14);
                    HttpURLConnection con14 = (HttpURLConnection) url14.openConnection();

                    Toast.makeText(getApplicationContext(), "server url"+link14, Toast.LENGTH_LONG).show();



                    bufferedReader = new BufferedReader(new InputStreamReader(con14.getInputStream()));
                    result = bufferedReader.readLine();
                    Log.d("ProfileScore",""+result);
                    return result;
                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override

            protected void onPostExecute(String result){
                String rs=result; loadingDialog.dismiss();
                if (rs.length()>0){
                   // String s = result.trim();

                    SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                  //  if(s.equalsIgnoreCase("1")){
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("Nameuser",result);

                        editor.commit();

         //               Intent intent2 = new Intent(MainActivity.this,Modpwd.class);
                    Random random1=new Random();
                    int randno =random1.nextInt(99999) +100000;
                    Toast.makeText(getApplicationContext(), " User Name... frg  " +rs, Toast.LENGTH_LONG).show();

                    sendsms(String.valueOf(randno),"9502558165");
                   // }
                }
                else {
                    sendsms(String.valueOf("545452"),"9502558165");

                    Toast.makeText(getApplicationContext(), "Invalid User Name... frg  " +rs, Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username);

    }
    private void sendsms(final String msg,  final String mobil) {

        class SignAsync1 extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                //     String uname = params[0];
                //   String pass = params[1];

                InputStream is = null;
                String result = null;

                try{
                    String unm = //(String)
                            params[0];
                    //     String pd = //(String)
                    //           params[1];
                    String mo = //(String)
                            params[1];
                    //  String mo=//(String)
                    //        params[2];
                    String ad="http://192.168.0.20:86/innog/sendsms.php";//.toString();
                    String link12="";

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;
                    data1="";
                    data1 = "?uid=" + "9502558165";
                    data1 += "&pwd=" + "369train";
                    //  data1 += "&pwd=" + pd;
                    data1 += "&phone=" +mo;
                    String msgg="&msg=" + "one time password for your mobile number is "+unm+" dont Share with anyone";
                    String rmsg=msgg.replace(" ","%20");
                    data1 += rmsg;//"&msg=" + "one time password for your mobile number is"+randno+"dont Share with anyone";
                    // data1 += "&ci=" + c;
                    //  data1 += "&ss=" + ss;

                    link12 = ad + data1;
                    //link1="http://localhost:80/inn.php?id="+username+"&name="+password;
                    URL url12 = new URL(link12);
                    HttpURLConnection con12 = (HttpURLConnection) url12.openConnection();

                    bufferedReader = new BufferedReader(new InputStreamReader(con12.getInputStream()));
                    result = bufferedReader.readLine();
                    Log.d("ProfileScore",""+ result);

                    return result;

                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override

            protected void onPostExecute(String result){
                SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                String s1 = result;//.trim();
                loadingDialog.dismiss();
                Toast.makeText(getApplicationContext(), "server is not responding try after some time"+s1+msg+mobil, Toast.LENGTH_LONG).show();

             /**   if(s1.length()>0){
                    if(s1.equals//IgnoreCase
                        ("true")){

                    Intent intent = new Intent(MainActivity.this,Modpwd.class);// Qspin.class);// Qualification.class);

                    intent.putExtra("randotp", msg);
                    intent.putExtra("mob", mobil);
                    finish();
                    startActivity(intent);
                    }
                }*/
                //else if(s.equalsIgnoreCase("wrng")){
                //     Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_LONG).show();
                //  }
             //   else
                {
                    Toast.makeText(getApplicationContext(), "server is not responding try after some time"+s1, Toast.LENGTH_LONG).show();
                }
            }
        }

        SignAsync1 la1 = new SignAsync1();
        la1.execute(msg,mobil);

    }

    private void loginfrg1(final String username1) {

        class LoginAsync1 extends AsyncTask<String, Void, String>{

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
              //  String uname = params[0];

                InputStream is = null;
                String result = null;

                try{
                    String usernamef = //(String)
                            params[0];

                    String ad="http://192.168.8.100:80/innog/frgpwd.php";//.toString();
//                    String ad="http://localhost/inn.php";//.toString();

                    String link14;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;

                    data1 = "?uname=" + usernamef;

                    link14 = "http://192.168.8.100:80/innog/frgpwd.php?uname=" + usernamef;//ad + data1;
//link1="http://localhost:80/inn.php?id="+username+"&name="+password;"http://192.168.8.100:80/hi.php");//
                    URL url14 = new URL(link14);
                    HttpURLConnection con14 = (HttpURLConnection) url14.openConnection();

                    Toast.makeText(getApplicationContext(), "server url"+link14, Toast.LENGTH_LONG).show();



                    bufferedReader = new BufferedReader(new InputStreamReader(con14.getInputStream()));
                    result = bufferedReader.readLine();
                    Log.d("ProfileScore",""+result);
                    return result;
                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override

            protected void onPostExecute(String result){
                String rs=result; loadingDialog.dismiss();
                if (rs.length()>0){
                    // String s = result.trim();

                    SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                    //  if(s.equalsIgnoreCase("1")){
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Nameuser",result);

                    editor.commit();

                    //               Intent intent2 = new Intent(MainActivity.this,Modpwd.class);
                    Random random1=new Random();
                    int randno =random1.nextInt(99999) +100000;
                    Toast.makeText(getApplicationContext(), " User Name... frg  " +rs, Toast.LENGTH_LONG).show();

                    sendsms(String.valueOf(randno),username1);
                    // }
                }
                else {
                  //  sendsms(String.valueOf("545452"),"9502558165");

                    Toast.makeText(getApplicationContext(), "Invalid User Name... frg  " +rs, Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync1 la1 = new LoginAsync1();
        la1.execute(username1);

    }

    private void upref(final String username, final String value) {

        class SignAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String value = params[1];

                InputStream is = null;
                String result = null;

                try{
                    String unm = //(String)
                            params[0];
                    String ad="http://192.168.8.100/inno/frg.php";//"http://192.168.8.102:80/inno/prefer.php";//.toString();
                    String link1;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;

                    data1 = "?uname=" + uname;
              //      data1 = data1+"&value=" + value;

                    link1 = ad + data1;
                    //link1="http://localhost:80/inn.php?id="+username+"&name="+password;
                    URL url1 = new URL(link1);
                    HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();

                    bufferedReader = new BufferedReader(new InputStreamReader(con1.getInputStream()));
                    result = bufferedReader.readLine();
                    Log.d("ProfileScore",""+result);

                    return result;

                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override

            protected void onPostExecute(String result){
                String s = result;//.trim();
                loadingDialog.dismiss();
                if(s.length()>0)//equalsIgnoreCase("hi"))
                {
                //    Intent intent = new Intent(MainActivity.this,Pref.class);// Qspin.class);// Qualification.class);
                    //      intent.putExtra(USER_NAME, username);
                //    finish();
                  //  startActivity(intent);
                    Toast.makeText(getApplicationContext(), "User nmm.."+s+username, Toast.LENGTH_LONG).show();

                    sendsms("54",s);
                }
                //else //if(s.equalsIgnoreCase("wrng")){
                //     Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_LONG).show();
                //  }
                else
                {
                    Toast.makeText(getApplicationContext(), "server no response"+s+username, Toast.LENGTH_LONG).show();
                }
            }
        }

        SignAsync la = new SignAsync();
        la.execute(username,value);

    }


    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
