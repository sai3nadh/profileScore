package innogeeks.inno.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.widget.AdapterView.OnItemSelectedListener;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Trinadh on 01-08-2017.
 */

public class regis extends AppCompatActivity // implements OnItemSelectedListener
         {
private Button bttn,reg,regg;
    private EditText UserName;
             int randno=0;
    private EditText email;
    private EditText Password,mobile,citi,sta;
    String uname; String pwd;String eid,mob,ci,stae;
    Spinner spinner,spinerdist;
    private static final String TAG_RESULTS="result";
    private static final String TAG_QID = "St.no";
    private static final String TAG_QNAME = "State";
    private static final String TAG_Ans ="ans";
    private static   Button rslt;
    JSONArray peoples = null;
    JSONArray dists = null;

    String myJSON,myJSONd;
    List<String> categories = new ArrayList <String>();
    ArrayList<String> dist = new ArrayList <String>();
    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter ;//= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

    ArrayAdapter<String> dataAdapterd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regform);

       // reg = (Button) findViewById(R.id.button3);
regg=(Button)findViewById(R.id.buttonreg) ;

        bttn = (Button) findViewById(R.id.button9);
        bttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            //    Intent ri = new Intent(regis.this, MainActivity.class);
              //  startActivity(ri);
                //........
  /**              UserName=(EditText)findViewById(R.id.editText);
                email=(EditText)findViewById(R.id.editText2);
                //  Password=(EditText)findViewById(R.id.editText3);
                mobile =(EditText)findViewById(R.id.editText6);
                //    citi=(EditText)findViewById(R.id.editText4);
                //          sta=(EditText)findViewById(R.id.editText7);
                try {
                    uname = UserName.getText().toString();//.trim();
                    //               pwd=Password.getText().toString();//.trim();
                    eid = email.getText().toString();//.trim();
                    mob = mobile.getText().toString();
                    signup(uname, eid,mob);//sendsms(mob,"");

//             ci=citi.getText().toString();
                    //     stae=sta.getText().toString();int i=0;
                }
                catch(Exception e)
                {
                    signup(uname, eid,mob);  sendsms("","9502558165");

                }
                    String a="";

//.......... gene code
*/
                finish();
               onBackPressed();
            }
        });
        regg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               UserName=(EditText)findViewById(R.id.editText);
                email=(EditText)findViewById(R.id.editText2);
                mobile =(EditText)findViewById(R.id.editText6);
                try {
                    uname=UserName.getText().toString();//.trim();
                eid=email.getText().toString();//.trim();
               mob=mobile.getText().toString();
                 //   stae=sta.getText().toString();
                    int i=0;
                    String a="";
                //    signup(uname, eid,mob);sendsms("fjlfjlone ijekf password",mob);

                    if( uname.equals("")||uname.length()<3||uname.length()>20)
                    {
                        i=1;
                        a=a+"User name is empty.."+" \n";UserName.setError( "First name is required! min and max length of 3 and 20" );
                    }

                    if( eid.equals("")||eid.length()<7||eid.length()>20)
                    {
                        i=1;
                        a=a+ "email is empty"+" \n";
                        email.setError( "email is required!" );
                    }

                    if( mob.equals("")||(mob.length() !=10))
                    {
                        i=1;
                        a=a+"mobile is empty";//+" \n";

                        mobile.setError( "mobile is required! check lengh no 10  "  );
                    }

                    if(i==1) {
                    Toast.makeText(getApplicationContext(), a , Toast.LENGTH_LONG).show();
                }
                    Random random1=new Random();
                    randno =random1.nextInt(99999) +100000;

                    if(i==0)
                {
                    signup(uname, eid,mob);

                }}
                catch (Exception d){
            }
            }
        });
    }
    public void getstates(){
        class GetDataJSON extends AsyncTask<String, Void, String>{
            ProgressDialog loadingDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
          //      ProgressDialog
                        loadingDialog = ProgressDialog.show(regis.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.8.102/inno/ss.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;loadingDialog.dismiss();
             //   showList();
                if(result!=null && result.length()>0)
                {

                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    peoples = jsonObj.getJSONArray(TAG_RESULTS);
                    for(int i=0;i<peoples.length();i++){
                        JSONObject c = peoples.getJSONObject(i);
                        String qid = c.getString(TAG_QID);
                        String qname = c.getString(TAG_QNAME);

                        categories.add(qname);

                        // Drop down layout style - list view with radio button
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // attaching data adapter to spinner
                        spinner.setAdapter(dataAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "server busy try after some time...", Toast.LENGTH_LONG).show();

                }

            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    public void Districts(){
        class GetDataJSONd extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.8.102/inno/dist.php");

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSONd=result;
                //   showList();
                //showdist();
                /**
                try {
                    JSONObject jsonObj = new JSONObject(myJSONd);
                    dists = jsonObj.getJSONArray(TAG_RESULTS);
                    for(int i=0;i<dists.length();i++){
                        JSONObject c = dists.getJSONObject(i);
                        String Did = c.getString("Did");
                        String dname1 = c.getString("distname");
                        String  sid= c.getString("St.no");
                      //  if(sid=)

                        dist.add(Did);

                        dist.add(sid);

                        dist.add(//Integer.parseInt(Did),
                                dname1);

                        // Drop down layout style - list view with radio button
                        dataAdapterd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // attaching data adapter to spinner
                        spinerdist.setAdapter(dataAdapterd);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        }
        GetDataJSONd g = new GetDataJSONd();
        g.execute();
    }
    public  void showdist( int stid){
try {
dist.clear();
        JSONObject jsonObj = new JSONObject(myJSONd);
        dists = jsonObj.getJSONArray(TAG_RESULTS);
        for(int i=0;i<dists.length();i++){
            JSONObject c = dists.getJSONObject(i);
            String Did = c.getString("Did");
            String dname1 = c.getString("distname");
            String  sid= c.getString("St.no");
            //  if(sid=)

           /// dist.add(Did);

//            dist.add(sid);
if(Integer.parseInt(sid) == stid) {
    dist.add(//Integer.parseInt(Did),
            dname1);
}
            // Drop down layout style - list view with radio button
            dataAdapterd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinerdist.setAdapter(dataAdapterd);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }}
    private void signup(final String username,  final String email,final String mobil) {

        class SignAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(regis.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
           //     String uname = params[0];
             //   String pass = params[1];

                InputStream is = null;
                String result = null;

          //      Random random1=new Random();
            //    randno =random1.nextInt(99999) +100000;


                try{

                    String unm = //(String)
                            params[0];
                    //     String pd = //(String)
                    //           params[1];
                    String eml = //(String)
                            params[1];
                    String mo=//(String)
                            params[2];
                    String ad="http://192.168.0.20:86/inno/regis.php";//.toString();
                    String link1;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;

                    data1 = "?name=" + unm;
                    data1 += "&email=" + eml;
                      data1 += "&rand=" + randno;
                    data1 += "&mob=" + mo;

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
                SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                String s = result;//.trim();
                loadingDialog.dismiss();
                if(s.equalsIgnoreCase("hi")){
                /**    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Nameuser",username);
                    editor.putString("role",s );
                    editor.commit();
*/ SavePreferences("Nameuser", email);
                    LoadPreferences();

                    sendsms("",mobil);


          //          Intent intent = new Intent(regis.this,Pref.class);// Qspin.class);// Qualification.class);
              //      intent.putExtra(USER_NAME, username);
            //        finish();
              //      startActivity(intent);
                }
                //else if(s.equalsIgnoreCase("wrng")){
               //     Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_LONG).show();
              //  }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password"+s, Toast.LENGTH_LONG).show();
                }//sendsms("",mobil);
            }
        }

        SignAsync la = new SignAsync();
        la.execute(username,email,mobil);

    }
    private void sendsms(final String msg,  final String mobil) {

                 class SignAsync extends AsyncTask<String, Void, String> {

                     private Dialog loadingDialog;

                     @Override
                     protected void onPreExecute() {
                         super.onPreExecute();
                         loadingDialog = ProgressDialog.show(regis.this, "Please wait", "Loading...");
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
                             String msgg="&msg=" + "one time password for your mobile number is "+randno+" dont Share with anyone";
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
                             Log.d("ProfileScore",""+result);

                             return result;

                         } catch(Exception e){
                             return new String("Exception: " + e.getMessage());
                         }
                     }

                     @Override

                     protected void onPostExecute(String result){
                         SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

                         String s = result;//.trim();
                         loadingDialog.dismiss();
                         if(s.equalsIgnoreCase("true")){
                             /**    SharedPreferences.Editor editor = sharedpreferences.edit();

                              editor.putString("Nameuser",username);
                              editor.putString("role",s );
                              editor.commit();
                              */
                             Intent intent = new Intent(regis.this,Modpwd.class);// Qspin.class);// Qualification.class);

                             intent.putExtra("randotp", String.valueOf( randno));
                             intent.putExtra("mob", mob);
                             finish();
                             startActivity(intent);
                         }
                         //else if(s.equalsIgnoreCase("wrng")){
                         //     Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_LONG).show();
                         //  }
                         else
                         {
                             Toast.makeText(getApplicationContext(), "server is not responding try after some time"+s, Toast.LENGTH_LONG).show();
                         }
                     }
                 }

                 SignAsync la = new SignAsync();
                 la.execute(msg,mobil);

             }

    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
        String strSavedMem1 = sharedPreferences.getString("Nameuser", "");
      //  String strSavedMem2 = sharedPreferences.getString("MEM2", "");
      //  textSavedMem1.setText(strSavedMem1);
       // textSavedMem2.setText(strSavedMem2);
    }
}
