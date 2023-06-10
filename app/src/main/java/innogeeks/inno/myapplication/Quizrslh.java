package innogeeks.inno.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import java.util.List;
/**
 * Created by my  pc on 13-09-2017.
 */

public class Quizrslh extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Button button,brsl;
    private String myJSON;JSONArray testbuttonscat = null,testbuttonsub=null;

    ArrayList<String> subcatid = new ArrayList<String>();
    ArrayList<Button> aptbtn=new ArrayList<Button>();
    ArrayList<Button> aptbtnS=new ArrayList<Button>();
    View.OnClickListener btnclick,btnclick1;
    Spinner testcat,testsub;
    private int overallper=0;
    //..... for dynamic button creation
    List<String> categories = new ArrayList <String>();
    List<String> categoriesub = new ArrayList <String>();
    ArrayAdapter<String> dataAdapter,dataAdaptersub;// = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.welcome);
        setContentView(R.layout.quizrslh);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdaptersub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesub);

        getData1();
        final Intent intent = getIntent();
        String username = intent.getStringExtra(MainActivity.USER_NAME);

        final TextView textView = (TextView) findViewById(R.id.textView3);

        textView.setText("Welcome "+ username);

        Button qb=(Button)findViewById(R.id.buttonqr);
        qb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Intent intent1=new Intent(Quizrslh.this,Quiz.class);
                //Toast.makeText(Quizrslh.this,String.valueOf( view.getId()) , //Toast.LENGTH_SHORT).show();

            }
        });

        btnclick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(Quizrslh.this,Quiz.class);
                //   intent1.putExtra("C",String.valueOf( 1));
                // intent1.putExtra("C1",String.valueOf(view.getId()));
                //startActivity(intent1);

                //Toast.makeText(Quizrslh.this,String.valueOf( view.getId()) , //Toast.LENGTH_SHORT).show();

            }
        };
        btnclick1 = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(Quizrslh.this, Quiz.class);
                intent1.putExtra("C", String.valueOf(2));
                intent1.putExtra("C1",String.valueOf(view.getId()));
                startActivity(intent1);

                //   switch(view.getId()){

                //}
                //Toast.makeText(Quizrslh.this,String.valueOf( view.getId()) , //Toast.LENGTH_SHORT).show();

            }
        };


        ////
        testcat = (Spinner) findViewById(R.id.spinner);
        testsub = (Spinner) findViewById(R.id.spinner2);
        //  testcat.setOnItemClickListener();
        //  testcat.setOnClickListener(  this);
        //  testcat.setOnItemClickListener( (AdapterView.OnItemClickListener) this);
        testsub.setOnItemSelectedListener(//this);
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        Object item = adapterView.getItemAtPosition(position);
                        if (item != null) {
                            //Toast.makeText(Quizrslh.this, item.toString(),
                                    //Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(Quizrslh.this, "Selected sub cat " + position,
                                //Toast.LENGTH_SHORT).show();
                        int ctgid=Integer.parseInt( subcatid.get(position));

                        if(item.toString().equals("All"))
                        {
                            LinearLayout ll1 = (LinearLayout) findViewById(R.id.dynbut);
                            ll1.removeAllViews();
                            ll1.setVisibility(view.GONE);

                            Button qb=(Button)findViewById(R.id.buttonqr);
                            qb.setVisibility(view.VISIBLE);
                            //testsub.setVisibility(View.GONE);//
                        }
                        else
                        {
                            LinearLayout ll1 = (LinearLayout) findViewById(R.id.dynbut);
                            ll1.removeAllViews();
                            ll1.setVisibility(view.VISIBLE);
                            addquizbtn(ctgid);// showListsub(position);

                            Button qb=(Button)findViewById(R.id.buttonqr);
                            qb.setVisibility(view.GONE);
                        }


                        /// addquizbtn(ctgid);
                        //   double d= Double.parseDouble(getscore(ctgid));
                        // overallper =Integer.parseInt(getscore(ctgid));//.... overall percentage
                        String s1=getscore(ctgid);
                        if(s1 != "") {
                            overallper = (int) Double.parseDouble(getscore(ctgid));
                        }
                        else {
                            overallper=0;
                        }
                        //.... overall percentage
//int a =
                        Log.d("ovrall", String.valueOf(overallper));
                        TextView scoretxt= (TextView)findViewById(R.id.catscr);
                        scoretxt.setText(getscore(ctgid));
                        //Toast.makeText(Quizrslh.this, "Selected sub cat  value" + ctgid,
                                //Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // TODO Auto-generated method stub

                    }
                });

        testcat.setOnItemSelectedListener(//this);
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        Object item = adapterView.getItemAtPosition(position);
                        if (item != null) {
                            //Toast.makeText(Quizrslh.this, item.toString(),
                                    //Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(Quizrslh.this, "Selected" + position,
                                //Toast.LENGTH_SHORT).show();
//                            showListsub(position+1); ///......... added all for caregories...

                        if(item.toString().equals("All"))
                        {
                            testsub.setVisibility(View.GONE);//
                            LinearLayout ll1 = (LinearLayout) findViewById(R.id.dynbut);
                            ll1.removeAllViews();
                            ll1.setVisibility(view.GONE);
                            Button qb=(Button)findViewById(R.id.buttonqr);
                            qb.setVisibility(view.VISIBLE);

                        }
                        else
                        {
                            LinearLayout ll1 = (LinearLayout) findViewById(R.id.dynbut);
                            ll1.removeAllViews();
                            ll1.setVisibility(view.VISIBLE);
                            testsub.setVisibility(View.VISIBLE);//
                            showListsub(position);

                            Button qb=(Button)findViewById(R.id.buttonqr);
                            qb.setVisibility(view.GONE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // TODO Auto-generated method stub

                    }
                });
    }
    @Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        //String item = //.getItemAtPosition(position).toString();
        int seld=testcat.getSelectedItemPosition();
        //showdist( seld+1);
        //  TextView selitem=(TextView)findViewById(R.id.textb);
        //    selitem.setText(seld);
    }
    @Override



    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub



    }
    /**  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_user_profile, menu);
    return true;
    }*/

    /**  @Override
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a paren    public boolean onOptionsItemSelected(MenuItem item) {
    t activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    /*  if (id == R.id.action_settings) {
    return true;
    }

    return super.onOptionsItemSelected(item);
    }
     */

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            testbuttonsub = jsonObj.getJSONArray("result");
            //         testbuttonscat = jsonObj.getJSONArray("result1");
            categories.add("All");
            for(int i=0;i<testbuttonsub.length();i++){
                JSONObject c = testbuttonsub.getJSONObject(i);
                String qid = c.getString("cat_id");
                String catname = c.getString("cat_name");
                categories.add(catname);
            }
            testcat.setAdapter(dataAdapter);
/**            for(int i=0;i<testbuttonscat.length();i++){
 JSONObject c = testbuttonscat.getJSONObject(i);
 // c_id":"1","cat_id":"2","cat_name":
 String qid = c.getString("c_id");
 String catname = c.getString("cat_name");
 Log.d("tag",catname+qid);

 }*/
//          testsub.setAdapter(dataAdaptersub);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    protected void showbuttons(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray result2 = jsonObj.getJSONArray("result2");
            testbuttonscat = jsonObj.getJSONArray("result1");
            //   //Toast.makeText(UserProfile.this, "show lis",                  //Toast.LENGTH_SHORT).show();
            // //Toast.makeText(UserProfile.this, myJSON , //Toast.LENGTH_SHORT).show();


            for(int i=0;i<result2.length();i++){
                JSONObject c = result2.getJSONObject(i);
                String qid = c.getString("cat_id");
                String catname = c.getString("cat_name");
                /**  Button b=new Button(this);
                 aptbtn.add(b);
                 b.setId(i+1);
                 b.setText(catname);
                 b.setOnClickListener(btnclick);

                 ll.addView(b); */
                //   categories.add(catname);
                categories.add(Integer.parseInt(qid),catname);

            }
            testcat.setAdapter(dataAdapter);
            for(int i=0;i<testbuttonscat.length();i++){
                JSONObject c = testbuttonscat.getJSONObject(i);
                // c_id":"1","cat_id":"2","cat_name":
                String qid = c.getString("c_id");
                String catname = c.getString("cat_name");
                /**  Button b=new Button(this);
                 aptbtnS.add(b);
                 b.setId(i+1);
                 b.setText(catname);
                 layoutskl.addView(b);
                 b.setOnClickListener(btnclick1);
                 */
                categoriesub.add(catname);
            }
            testsub.setAdapter(dataAdaptersub);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    protected void showListsub(int s){
        //Toast.makeText(getApplicationContext(),"s value " +s, //Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            testbuttonsub = jsonObj.getJSONArray("result");
            testbuttonscat = jsonObj.getJSONArray("result1");
            //   //Toast.makeText(UserProfile.this, "show lis",                  //Toast.LENGTH_SHORT).show();
            // //Toast.makeText(UserProfile.this, myJSON , //Toast.LENGTH_SHORT).show();


            LinearLayout ll = (LinearLayout) findViewById(R.id.dynbut);
            LinearLayout ll1 = (LinearLayout) findViewById(R.id.dynbut);

            categoriesub.clear();
            ll1.removeAllViews();
            subcatid.clear();
            categoriesub.add("All");//...... add all buon
            subcatid.add("0");
            for(int i=0;i<testbuttonscat.length();i++){
                JSONObject c = testbuttonscat.getJSONObject(i);
                // c_id":"1","cat_id":"2","cat_name":
                String qid = c.getString("cat_id");
                String cid = c.getString("c_id");
                String as=String.valueOf(s);
                if(qid.equals(as)) {

                    String catname = c.getString("cat_name");
                    //                  //Toast.makeText(getApplicationContext(),"s value " +s+catname, //Toast.LENGTH_LONG).show();

                    Button b=new Button(this);
                    aptbtnS.add(b);
                    b.setId(i+1);
                    //b.setId(Integer.parseInt(cid));
                    subcatid.add(cid); //.. for storing id values corresponding subcategories
                    b.setText(catname + cid);
                    // ll1.addView(b);
                    b.setOnClickListener(btnclick1);
                    // ll.addView(b);
                    categoriesub.add(catname);
                    //   categoriesub.add(Integer.parseInt(qid),catname);
                    Log.d("tag", catname + qid);
                }
            }
            testsub.setAdapter(dataAdaptersub);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    protected void addquizbtn(int quiz){
        //Toast.makeText(getApplicationContext(),"s selected quiz button id " +quiz, //Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray catquiz = jsonObj.getJSONArray("result2");
            LinearLayout ll1 = (LinearLayout) findViewById(R.id.dynbut);
            ll1.removeAllViews();
            //Toast.makeText(Quizrslh.this, "catquiz length " +catquiz.length(), //Toast.LENGTH_SHORT).show();
            int chk=0 ,ver=0;
            int compscr=0;
            for(int i=0;i<catquiz.length();i++){
                JSONObject c = catquiz.getJSONObject(i);
                // c_id":"1","cat_id":"2","cat_name":
                String qid = c.getString("qz_id");
                String cid = c.getString("c_id");
                //Toast.makeText(Quizrslh.this, "quiz value " +quiz, //Toast.LENGTH_SHORT).show();

                String as=String.valueOf(quiz);
                if(cid.equals(as))
                {
                    String catname = c.getString("qz_name");
                    //  //Toast.makeText(getApplicationContext(),"s value " +quiz+catname, //Toast.LENGTH_LONG).show();

                    Button b=new Button(this);
                    aptbtnS.add(b);
                    b.setId(Integer.parseInt(qid));
                    b.setOnClickListener(btnclick);
                    //b.setId(Integer.parseInt(cid));
                    //getquizscore( Integer.parseInt(qid));
                    if(getquizscore( Integer.parseInt(qid)) != ""){
                        compscr=Integer.parseInt(getquizscore( Integer.parseInt(qid)));
                    }
                    else {
                        compscr=0;
                    }

                    b.setText(catname + cid + "qzscr"+getquizscore( Integer.parseInt(qid)));
                    if(getquizscore( Integer.parseInt(qid) ) != "")
                    {
                        //Toast.makeText(getApplicationContext(),"loop " , //Toast.LENGTH_LONG).show();

                        // if (//Integer.parseInt(getquizscore(Integer.parseInt(qid)))
                        //       overallper >= 70) {
                        b.setEnabled(true);
                        ll1.addView(b);
                        ver=1;
                        //}
                    }

                    Log.d("asdf",String.valueOf(i));
                    // ll1.addView(b);
                    //      categoriesub.add(catname);
                    //   categoriesub.add(Integer.parseInt(qid),catname);
                    Log.d("tag", catname + qid);
                }
            }
            //  testsub.setAdapter(dataAdaptersub);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/aptQ.php");

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
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    public void getData1(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

//                //Toast.makeText(UserProfile.this, "show lis"+ result, //Toast.LENGTH_SHORT).show();

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/test.php");

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
                ////Toast.makeText(UserProfile.this, "show lis"+ result,
//                        //Toast.LENGTH_SHORT).show();

                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    public  String getscore( int ctid)
    {
        String scr="";
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray ctscr = jsonObj.getJSONArray("result4");

            for(int i=0;i<ctscr.length();i++){
                JSONObject c = ctscr.getJSONObject(i);
                // c_id":"1","cat_id":"2","cat_name":
                String cid = c.getString("category_id");
                String scrt = c.getString("percentage");
                String as=String.valueOf(ctid);
                if(cid.equals(as)) {
                    scr =scrt;
                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  scr;
    }
    public  String getquizscore( int qzid)    {
        String scr="";
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray ctqzscr = jsonObj.getJSONArray("result3");

            for(int i=0;i<ctqzscr.length();i++){
                JSONObject c = ctqzscr.getJSONObject(i);
                // c_id":"1","cat_id":"2","cat_name":
                String cid = c.getString("category_id");
                String qid = c.getString("qz_id");

                String scrt = c.getString("percentage");
                String as=String.valueOf(qzid);
                if(cid.equals(as)) {
                    scr =scrt;
                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  scr;
    }


}
