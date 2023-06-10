package innogeeks.inno.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.util.ArrayList;

/**
 * Created by Trinadh on 06-09-2017.
 */

public class Resulhome extends AppCompatActivity {
    Button homeo;
    Button button,brsl;
    private String myJSON;JSONArray testbuttons = null,testbuttonSQ=null;
    ArrayList<Button> aptbtn=new ArrayList<Button>();
    ArrayList<Button> aptbtnS=new ArrayList<Button>();
    View.OnClickListener btnclick,btnclick1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
         homeo=(Button)findViewById(R.id.home);
      //  getData1();
        homeo.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(Resulhome.this,Result.class);
                finish();
                startActivity(intent1);

                Toast.makeText(Resulhome.this,"home page" , Toast.LENGTH_SHORT).show();

            }
        });
        btnclick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(Resulhome.this,Ques.class);
              //  intent1.putExtra("C",C);//String.valueOf( 1));
                //intent1.putExtra("C1",C1);//String.valueOf(view.getId()));
                intent1.putExtra("q",String.valueOf(view.getId()));
                startActivity(intent1);

                Toast.makeText(Resulhome.this,String.valueOf( view.getId()) , Toast.LENGTH_SHORT).show();

            }
        };

    }
    protected void showList1(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            testbuttons = jsonObj.getJSONArray("result");
           // testbuttonSQ = jsonObj.getJSONArray("result1");
            //   Toast.makeText(UserProfile.this, "show lis",                  Toast.LENGTH_SHORT).show();
            // Toast.makeText(UserProfile.this, myJSON , Toast.LENGTH_SHORT).show();


            LinearLayout ll = (LinearLayout) findViewById(R.id.scoreca);
        //    LinearLayout layoutskl=(LinearLayout)findViewById(R.id.layoutbuttoskill);
            for(int i=0;i<testbuttons.length();i++){
                JSONObject c = testbuttons.getJSONObject(i);
                String qid = c.getString("category");
                String catname = c.getString("c_name");
                Log.d("rsl",catname);
                Button b=new Button(this);
                aptbtn.add(b);
                b.setId(Integer.parseInt(qid));
                b.setText(catname);
                b.setOnClickListener(btnclick);

                ll.addView(b);
            }
        /**    for(int i=0;i<testbuttonSQ.length();i++){
                JSONObject c = testbuttonSQ.getJSONObject(i);
                String qid = c.getString("techQ_id");
                String catname = c.getString("tech_cat");
                Button b=new Button(this);
                aptbtnS.add(b);
                b.setId(i+1);
                b.setText(catname);
                layoutskl.addView(b);
                b.setOnClickListener(btnclick1);

            }

*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData1(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                String strSavedMem1 = sharedPreferences.getString("Nameuser", "");
Log.d("username",strSavedMem1);
                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/resulbu.php?sid="+strSavedMem1);

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
                myJSON=result; Toast.makeText(Resulhome.this, "show lis"+ result,
                        Toast.LENGTH_SHORT).show();

                showList1();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

}
