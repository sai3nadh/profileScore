package innogeeks.inno.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Trinadh on 30-08-2017.
 */

public class Recview extends AppCompatActivity {
    private  static String myJSONs;
     String result = null;
    JSONArray jArray=null;
    InputStream is = null; private static final String TAG_RESULTS="result";
    String usernamed;
    private  static String sd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recview);

        Intent intent = getIntent();
//        String
usernamed = intent.getStringExtra("user");
        sd1=usernamed;
       // TextView textView = (TextView) findViewById(R.id.textView3);
     //   textView.setText("Welcome "+username);
        getsdis();
    }
    public void getsdis(){
        class GetDataJSONs extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Recview.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.8.102/inno/sdis.php?id="+sd1);

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
                myJSONs=result;  loadingDialog.dismiss();
                //   showList();

                if( result!=null){
                    //parse json data
                    try {
                        JSONObject json_data = new JSONObject(myJSONs);// jArray.getJSONObject(i);
                        jArray = json_data.getJSONArray(TAG_RESULTS);
                        //  JSONArray jArray = new JSONArray(TAG_RESULTS);
                       // TableLayout tv = (TableLayout) findViewById(R.id.table1);
                        //tv.removeAllViewsInLayout();
                        LinearLayout rv=(LinearLayout)findViewById(R.id.recv);
                        rv.removeAllViews();
                        int flag = 1;
                        if(jArray!=null && result!=null){
                            for (int i = 0; i < jArray.length() - 1; i++) {
                                json_data = jArray.getJSONObject(i);

                                TextView b = new TextView(Recview.this);
                                String stime = String.valueOf(json_data.get("s_id"));
                                b.setText(stime);
                                rv.addView(b);

                                TextView b1 = new TextView(Recview.this);
                                String stime1 = json_data.getString("user_name");
                                b1.setText("user_name"+stime1);
                                rv.addView(b1);

                                TextView b2 = new TextView(Recview.this);
                                String stime2 = String.valueOf(json_data.get("tech_wrk"));
                                b2.setText("tech_wrk"+stime2);
                                rv.addView(b2);

                                TextView b3 = new TextView(Recview.this);
                                String stime3 = String.valueOf(json_data.get("nontech_wrk"));
                                b3.setText("nontech_wrk"+stime3);
                                rv.addView(b3);

                                TextView b4 = new TextView(Recview.this);
                                String stime4 = //String.valueOf(
                                        json_data.getString("c_name");
                                b4.setText("quantum"+stime4);
                                rv.addView(b4);

                                TextView b5 = new TextView(Recview.this);
                                String stime5 = String.valueOf(json_data.get("percentage"));
                                b5.setText(stime5);
                                rv.addView(b5);

                                TextView b7 = new TextView(Recview.this);
                                String stime7 = //String.valueOf(
                                        json_data.getString("c_name");
                                b7.setText("quantum"+stime7);
                                rv.addView(b7);

                                TextView b8 = new TextView(Recview.this);
                                String stime8 = String.valueOf(json_data.get("percentage"));
                                b8.setText(stime8);
                                rv.addView(b8);



                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "JsonArray lengh null", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("log_tag", "Error parsing data" + e.toString());
                        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "resul lengh null", Toast.LENGTH_SHORT).show();
                }
            }

         //   }
        }
        GetDataJSONs g = new GetDataJSONs();
        g.execute();
    }
}
