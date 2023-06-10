package innogeeks.inno.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.data.PieData;

import com.github.mikephil.charting.data.PieDataSet;

import com.github.mikephil.charting.formatter.PercentFormatter;

import com.github.mikephil.charting.utils.ColorTemplate;


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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import java.util.ArrayList;

/**
 * Created by Trinadh on 01-08-2017.
 */

public class Result extends AppCompatActivity{
    private String myJSONr;
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<String>();
    JSONArray peoplescore = null;
    private static final String TAG_RESULTS="result";
    PieDataSet dataset ;//= new PieDataSet(entries, "# of Calls");
    PieChart pieChart ;//= (PieChart) findViewById(R.id.pie);
Button re,rec;//=(Button)findViewById(R.id.resb);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        re=(Button)findViewById(R.id.resb);
        rec=(Button)findViewById(R.id.resco);
        re.setVisibility(View.GONE);
        rec.setVisibility(View.GONE);
        //   getscore();
        //   Intent intent = getIntent();
       // String noan,crc,wrng;
     //  try {
       //   noan = intent.getStringExtra(Ques.noanss);
      //     crc = intent.getStringExtra(Ques.resul);
        //wrng = intent.getStringExtra(Ques.wrngg);
        //   getscore();
       // }
        //catch (Exception e)
        //{

           //noan = "10";//intent.getStringExtra(Ques.noanss);
         //  crc ="20";// intent.getStringExtra(Ques.resul);
            //wrng = "70";//intent.getStringExtra(Ques.wrngg);

//        }
        //   getscore();
//        getscore();

        rec.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                getscore();
                re.setText(myJSONr);

            }
        });


re.setOnClickListener(new View.OnClickListener()
{
    public void onClick(View view) {


        try {
            if(myJSONr!=null)
            {
            JSONObject jsonObj = new JSONObject(myJSONr);
            peoplescore = jsonObj.getJSONArray(TAG_RESULTS);
            for(int i=0;i<peoplescore.length();i++){
               JSONObject c = peoplescore.getJSONObject(i);
                String sid = c.getString("s_id");
                String cname = c.getString("category");
                String per=c.getString("avgper");

                entries.add(new Entry( Float.valueOf(per), i));
             //   dataset = new PieDataSet(entries, "# of Calls");
               // labels.add(cname);
                labels.add(cname);
            }
            dataset = new PieDataSet(entries, "# of Calls");
         /**   for(int i=0;i<peoplescore.length();i++){
             JSONObject c = peoplescore.getJSONObject(i);
             String sid = c.getString("s_id");
             String cname = c.getString("category");
             String per=c.getString("avgper");
             //   entries.add(new Entry( Float.valueOf(per), i));
             labels.add(cname);
             }*/
        //    final com.github.mikephil.charting.charts.PieChart a1=new  com.github.mikephil.charting.charts.PieChart;// findViewById(R.id.pie);
            LinearLayout p1=(LinearLayout) findViewById(R.id.pie1);
            pieChart =// a1
             (PieChart) findViewById(R.id.chart);

                PieData data = new PieData(labels, dataset);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                     pieChart.setCenterText("Categories");
                pieChart.setData(data);
                data.setValueFormatter(new PercentFormatter());
                pieChart.animateY(5000);
                pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

             //   p1.addView(a1);

            }
            else {
                Toast.makeText(getApplicationContext(), "server is not responding... try after some time", Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
    });
    }

/**
 labels.add("Aptitude");

 labels.add("Verbal");

 labels.add("Communication skills");

 labels.add("Quant");
 //
 labels.add("Psychometric");
 entries.add(new Entry(20.0f, 0));

 entries.add(new Entry(12.7f, 1));

 entries.add(new Entry(27.3f, 2));
 entries.add(new Entry(35.0f, 3));

 entries.add(new Entry(4.5f, 4));
 */




        // if(peoples.length()>0){
  //      com.github.mikephil.charting.charts.PieChart a1= ( com.github.mikephil.charting.charts.PieChart) findViewById(R.id.pie);
      //  LinearLayout p1=(LinearLayout) findViewById(R.id.pie1);
    //        pieChart = (PieChart) findViewById(R.id.pie);

        //    p1.addView(a1);
  //      }
       // PieChart pieChart = (PieChart) findViewById(R.id.chart);

   //     entries.add(new Entry(20.0f, 0));

//        entries.add(new Entry(12.7f, 1));

  //      entries.add(new Entry(27.3f, 2));

    //   entries.add(new Entry(35.0f, 3));

//        entries.add(new Entry(4.5f, 4));

//Float a,b,c,d;

      /*  d=(Float.valueOf(noan)+Float.valueOf(crc)+Float.valueOf(wrng))/5;
        entries.add(new Entry( Float.valueOf(noan)*20, 0));

        entries.add(new Entry( Float.valueOf(crc)*20, 1));

        entries.add(new Entry( Float.valueOf(wrng)*20, 2));
*/

        //PieDataSet dataset = new PieDataSet(entries, "# of Calls");


     /*       labels.add("noanswer");

         labels.add("correc");

         labels.add("wrong");


*/
    /**          labels.add("Aptitude");

              labels.add("Verbal");

              labels.add("Communication skills");

              labels.add("Quant");
      //
              labels.add("Psychometric");
*

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieChart.setCenterText("Categories");
        pieChart.setData(data);
        data.setValueFormatter(new PercentFormatter());
        pieChart.animateY(5000);
        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image*/
    //}
//}

    public void getscore(){
        class GetDataJSONs extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Result.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
           /**     String result = null;
                try {


                String ad="http://192.168.8.100:80/inno/score.php";//.toString();

                String link1;

                String data1;
                BufferedReader bufferedReader;

                link1 = ad ;
//link1="http://localhost:80/inn.php?id="+username+"&name="+password;"http://192.168.8.100:80/hi.php");//
                URL url1 = new URL(link1);
                HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();

                bufferedReader = new BufferedReader(new InputStreamReader(con1.getInputStream()));
                result = bufferedReader.readLine();

                }
catch (Exception ed){
}*/
              //  Log.d("ProfileScore",""+result);

             //   return result;

              DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
          //      HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/score.php");


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
                myJSONr=result;  loadingDialog.dismiss();
                //   showList();

                //..........
                try {
                if(myJSONr!=null)
                {
                    JSONObject jsonObj = new JSONObject(myJSONr);
                    peoplescore = jsonObj.getJSONArray(TAG_RESULTS);
                    for(int i=0;i<peoplescore.length();i++){
                        JSONObject c = peoplescore.getJSONObject(i);
                        String sid = c.getString("s_id");
                        String cname = c.getString("c_name");
                        String per=c.getString("avgper");
                        entries.add(new Entry( Float.valueOf(per), i));
                        //   dataset = new PieDataSet(entries, "# of Calls");
                        // labels.add(cname);
                        labels.add(cname);
                    }
                    dataset = new PieDataSet(entries, "# of Calls");
                    /**   for(int i=0;i<peoplescore.length();i++){
                     JSONObject c = peoplescore.getJSONObject(i);
                     String sid = c.getString("s_id");
                     String cname = c.getString("category");
                     String per=c.getString("avgper");
                     //   entries.add(new Entry( Float.valueOf(per), i));
                     labels.add(cname);
                     }*/
                    //    final com.github.mikephil.charting.charts.PieChart a1=new  com.github.mikephil.charting.charts.PieChart;// findViewById(R.id.pie);
                    LinearLayout p1=(LinearLayout) findViewById(R.id.pie1);
                    pieChart =// a1
                            (PieChart) findViewById(R.id.chart);

                    PieData data = new PieData(labels, dataset);
                    dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                    pieChart.setCenterText("Categories");
                    pieChart.setData(data);
                    data.setValueFormatter(new PercentFormatter());
                    pieChart.animateY(5000);
                    pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

                    //   p1.addView(a1);

                }
                else {
                    Toast.makeText(getApplicationContext(), "server is not responding... try after some time", Toast.LENGTH_LONG).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();

                }
            }

                //.............

        }
        GetDataJSONs g = new GetDataJSONs();
        g.execute();
    }}
