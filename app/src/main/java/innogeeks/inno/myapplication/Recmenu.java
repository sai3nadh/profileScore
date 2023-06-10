package innogeeks.inno.myapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.support.v7.app.AppCompatActivity;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Created by Trinadh on 25-08-2017.
 */

 public class Recmenu extends AppCompatActivity   {
    private String myJSON;   String result = null;
    JSONArray jArray=null;
    InputStream is = null; private static final String TAG_RESULTS="result";
    View.OnClickListener onclicklistener;
    TextView qw;
    public static ArrayList<Button> db = new ArrayList<Button>();
    public static ArrayList<String> dbt = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recmenu);
       getDatarec();
//        TextView
                qw=(TextView) findViewById(R.id.in);

//        View.OnClickListener
                onclicklistener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
             //  String itext=db.get( 1);//
               String sd=dbt.get( v.getId() -1 );

               // Button b = (Button)v;
             // final  String text = b.getText().toString();
                Intent intent = new Intent(Recmenu.this, Recview.class);
                    intent.putExtra("user",sd);
                qw.setText(sd);
                             //finish();
                           startActivity(intent);

            }
        };
    }
    public void getDatarec(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.8.102/inno/data.php");

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
                myJSON=result;

                // showList();
                if( result!=null){
                    //parse json data
                    try {
                        JSONObject json_data = new JSONObject(myJSON);// jArray.getJSONObject(i);
                        jArray = json_data.getJSONArray(TAG_RESULTS);
                      //  JSONArray jArray = new JSONArray(TAG_RESULTS);
                        TableLayout tv = (TableLayout) findViewById(R.id.table1);
                        tv.removeAllViewsInLayout();
                        int flag = 1;
                        if(jArray!=null && result!=null){
                            for (int i = //-
                                    0; i < jArray.length() ; i++) {
                                TableRow tr = new TableRow(Recmenu.this);
                                tr.setLayoutParams(new LayoutParams(
                                        LayoutParams.FILL_PARENT,
                                        LayoutParams.WRAP_CONTENT));
                        //        if (flag == 1) {
                                 /** /**  TextView b6 = new TextView(Recmenu.this);
                                    b6.setText("s_id");
                                    b6.setTextColor(Color.BLUE);
                                    b6.setTextSize(15);
                                    tr.addView(b6);
                                    TextView b19 = new TextView(Recmenu.this);
                                    b19.setPadding(10, 0, 0, 0);
                                    b19.setTextSize(15);
                                    b19.setText("Name");
                                    b19.setTextColor(Color.BLUE);
                                    tr.addView(b19);
                                    TextView b29 = new TextView(Recmenu.this);
                                    b29.setPadding(10, 0, 0, 0);
                                    b29.setText("tech_wrk");
                                    b29.setTextColor(Color.BLUE);
                                    b29.setTextSize(15);
                                    tr.addView(b29);
                                    tv.addView(tr);
                                    final View vline = new View(Recmenu.this);
                                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                                    vline.setBackgroundColor(Color.BLUE);
                                    tv.addView(vline);
                                   */ flag = 0;
                                //} else {
                                    //JSONObject
                                            json_data = jArray.getJSONObject(i);
                                 //   Log.i("log_tag", "id: " + json_data.get("Id") + ", Username: " + json_data.getString("name") + ", No: " + json_data.getString("tech_wrk"));
                                    TextView b = new TextView(Recmenu.this);
                                    String stime = String.valueOf(json_data.get("s_id"));
                                    b.setText(stime);
                                    b.setTextColor(Color.RED);
                                    b.setTextSize(15);
                                    tr.addView(b);

                                    TextView b1 = new TextView(Recmenu.this);
                                    b1.setPadding(10, 0, 0, 0);
                                    b1.setTextSize(15);
                                    String stime1 = json_data.getString("user_name");
                                    b1.setText(stime1);
                                    b1.setTextColor(Color.BLACK);
                                    tr.addView(b1);
dbt.add(stime1);
                                Button bu=new Button(Recmenu.this);

                     //     final   String sb = json_data.getString("user_name");
                                bu.setId(i+1);
                                bu.setPadding(10, 0, 0, 0);
                                bu.setTextSize(15);
                                bu.setText(json_data.getString("user_name"));//sb);
                                bu.setTextColor(Color.BLACK);
                                bu.setTag(json_data.getString("user_name"));
                                bu.setOnClickListener(onclicklistener);

                                db.add(bu);
                               /** bu.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        /////Do some job on button click
                       //                 Intent intent = new Intent(Recmenu.this, Recview.class);
                         //.               intent.putExtra("user", sb);
                           //             finish();
                             //           startActivity(intent);

                                    }
                                });*/
                                tr.addView(bu);





                                TextView b2 = new TextView(Recmenu.this);
                                    b2.setPadding(10, 0, 0, 0);
                                    String stime2 ="sdfsdf";// json_data.getString("tech_wrk");
                                    b2.setText(stime2);
                                    b2.setTextColor(Color.BLACK);
                                    b2.setTextSize(15);
                                    tr.addView(b2);
                                    tv.addView(tr);
                                    final View vline1 = new View(Recmenu.this);
                                    vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                                    vline1.setBackgroundColor(Color.WHITE);
                                    tv.addView(vline1);
                              //  }
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
        }

        GetDataJSON g = new GetDataJSON();
        g.execute();
    }


}
