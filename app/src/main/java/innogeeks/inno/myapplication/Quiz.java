package innogeeks.inno.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Quiz extends AppCompatActivity {
    Button buttonq;
    private String myJSON;JSONArray testbuttons = null,testbutton=null;
  private   ArrayList<Button> aptbtnq=new ArrayList<Button>();
 //  private ArrayList<Button> aptbtnS=new ArrayList<Button>();
    View.OnClickListener btnclick;//,btnclick1;
    private String C,C1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        final Intent intent = getIntent();
        C = intent.getStringExtra("C");
        C1 = intent.getStringExtra("C1");

        getData();//..... for dynamic button creation

        final Intent intent11 = getIntent();
        String username = intent.getStringExtra(MainActivity.USER_NAME);

  //      TextView textView = (TextView) findViewById(R.id.textView3);

    //    textView.setText("Welcome "+ username);
    /**    brsl=(Button)findViewById(R.id.buttonr);
        brsl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(Quiz.this, Ques.class);
                startActivity(intent1);
                // Code here ex
                // ecutes on main thread after user presses button
            }
        });  */
     /**   buttonq = (Button) findViewById(R.id.button2);
        buttonq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(Quiz.this, Ques.class);
                startActivity(intent1);
                // Code here executes on main thread after user presses button
            }
        });

*/
        btnclick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(Quiz.this,Ques.class);
                intent1.putExtra("C",C);//String.valueOf( 1));
                intent1.putExtra("C1",C1);//String.valueOf(view.getId()));
                intent1.putExtra("q",String.valueOf(view.getId()));
                startActivity(intent1);

                Toast.makeText(Quiz.this,String.valueOf( view.getId()) , Toast.LENGTH_SHORT).show();

            }
        };
      /**  btnclick1 = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Quiz.this, Ques.class);
                intent1.putExtra("C", String.valueOf(2));
                intent1.putExtra("C1",String.valueOf(view.getId()));
                startActivity(intent1);

                //   switch(view.getId()){

                //}
                Toast.makeText(UserProfile.this,String.valueOf( view.getId()) , Toast.LENGTH_SHORT).show();

            }
        };*/
    }



    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            testbuttons = jsonObj.getJSONArray("result");
            testbutton = jsonObj.getJSONArray("result1");
            LinearLayout ll = (LinearLayout) findViewById(R.id.level);
            JSONObject c1 = testbutton.getJSONObject(0);
            String countq = c1.getString("count");
            int qui =Integer.parseInt(countq);
            Toast.makeText(Quiz.this, countq , Toast.LENGTH_SHORT).show();
            for(int i=0;i<testbuttons.length();i++){
                JSONObject c = testbuttons.getJSONObject(i);
              //  String qid = c.getString("c_id");
                String catname = c.getString("Quiz");
                Button b=new Button(this);
              //  aptbtn.add(b);
                b.setId(i+1);
                b.setText(catname);
                if (qui >= i)
                {
                    b.setEnabled(true);
                }
                else{
                    b.setEnabled(false);
                }
                b.setOnClickListener(btnclick);

                ll.addView(b);
            }
             /**       JSONObject c = testbutton.getJSONObject(0);
                String countq = c.getString("count");
                Button b=new Button(this);
                aptbtnS.add(b);
                b.setId(i+1);
                b.setText(catname);
              */



        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                String strSavedMem1 = sharedPreferences.getString("Nameuser", "");

                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/Quiz.php?cid="+C1+"&sid="+strSavedMem1);

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
                myJSON=result; Toast.makeText(Quiz.this, "show lis"+ result,
                        Toast.LENGTH_SHORT).show();

                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

}
