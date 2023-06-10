package innogeeks.inno.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by Trinadh on 03-09-2017.
 */

public class Modpwd extends AppCompatActivity {
private EditText otp,pwd,repwd;  Button cnf; String rrndm="";
    private TextView rsnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modpwd);
        Bundle bundle = getIntent().getExtras();

        Intent intentmp = getIntent();
        final String randotpi =bundle.getString("randotp");// intentmp.getStringExtra("randotp");
        final String mobb =bundle.getString("mob"); // intentmp.getStringExtra("mob");
        rrndm=randotpi;

        /**     f inal TextView tv1 = new TextView(this);
         tv1.setText("Hii Folks");
         tv1.setTextSize(14);
         tv1.setGravity(Gravity.CENTER_VERTICAL);
         LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);
         ll.addView(tv1);
         */
        otp=(EditText) findViewById(R.id.editTextO);
        pwd=(EditText) findViewById(R.id.editTextP);
        repwd=(EditText) findViewById(R.id.editTextR);
rsnd=(TextView)findViewById(R.id.textViewrsnd);

        rsnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

sendsms(randotpi,mobb); Toast.makeText(getApplicationContext(), randotpi +mobb, Toast.LENGTH_LONG).show();

            }
        });

       // Button

        cnf = (Button) findViewById(R.id.buttoncnf);
        cnf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
          //      Toast.makeText(getApplicationContext(), randotpi +mobb, Toast.LENGTH_LONG).show();

                int i=0; String toastmsg="";
                String otpp=otp.getText().toString().trim();
                if(otpp.length()==0 ) {
                    toastmsg= "Enter one time password"+"\n";
                    otp.setError("Enter one time password");
                    i=1;
                }
                if(! otpp.equals( randotpi )) {
                    toastmsg= "Enter valid one time password"+"\n";
                    otp.setError("wrong OTP ");
                    i=1;
                }
                String pwdd=pwd.getText().toString();
                String rpd=repwd.getText().toString();
                if(  !pwdd.equals(rpd))
                {
                    toastmsg= "check password"+"\n";
                    repwd.setError("password doen't match");
                    i=1;
                    Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();

                }
                if(i==0)
                {
                    modp(otpp,pwdd);
                }

            }
        });

    }
    private void sendsms(final String rand,  final String mobil) {

        class SignAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Modpwd.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                //     String uname = params[0];
                //   String pass = params[1];

                InputStream is = null;
                String result = null;

                try{
                    String rand = //(String)
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
                    String msgg="&msg=" + "one time password for your mobile number is "+rand+" dont Share with anyone";
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
                    Toast.makeText(getApplicationContext(), "Send sms ", Toast.LENGTH_LONG).show();

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
        la.execute(rand,mobil);

    }

    private void modp(final String Otp,  final String pwd) {

        class SignAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Modpwd.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                //     String uname = params[0];
                //   String pass = params[1];

                InputStream is = null;
                String result = null;

                try{

                    String oop = //(String)
                            params[0];
                    String pwd1 = //(String)
                            params[1];
                    String ad="http://192.168.0.20:86/innog/updt.php";//.toString();
                    String link1;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;
                    SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                    String strSavedMem1 = sharedPreferences.getString("Nameuser", "");
                 //   Toast.makeText(getApplicationContext(), oop+ strSavedMem1 + pwd1, Toast.LENGTH_LONG).show();


                    data1 = "?uname=" + strSavedMem1;
                    data1 += "&otp=" + oop;
                    data1 += "&pwd=" + pwd1;
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
                if(s.equalsIgnoreCase("updated")){
                    Intent ri = new Intent(Modpwd.this, UserProfile.class);
                   finish();
                    startActivity(ri);
                }
                //else if(s.equalsIgnoreCase("wrng")){
                //     Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_LONG).show();
                //  }
                else
                {
                    Toast.makeText(getApplicationContext(), "not Upadated Password "+s, Toast.LENGTH_LONG).show();
                }
            }
        }

        SignAsync la = new SignAsync();
        la.execute(Otp,pwd);

    }
}
