package innogeeks.inno.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.renderscript.Int2;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Fragment;

import android.app.Fragment;
/**
 * Created by Trinadh on 04-08-2017.
 */

public class Ques extends AppCompatActivity {

    public static String noanss;
    public static String wrngg;
    public static String resul;
    String myJSON;
public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date strtdat = new Date(System.currentTimeMillis());
   public Date enddate;
public  Date cdate = new Date(System.currentTimeMillis());
    final ArrayList<RadioGroup> rg= new ArrayList<RadioGroup>();
   // RadioGroup radioGroup;
   final ArrayList<RadioButton> allRadio = new ArrayList<RadioButton>();
    final ArrayList<TextView> quesns = new ArrayList<TextView>();
ArrayList<String> namesArray=new ArrayList<String>();
    FragmentTransaction fragmentTransaction;
    // Date parsed = sdf.parse("2016-03-10 22:05:20");

    private static final String TAG_RESULTS="result";
    private static final String TAG_QID = "qid";
    private static final String TAG_QNAME = "qname";
    private static final String TAG_op1 = "op1";
    private static final String TAG_op2 = "op2";
    private static final String TAG_op3 = "op3";
    private static final String TAG_op4 = "op4";
    private static final String TAG_ans = "answer";
    private static final String TAG_Ans ="ans";
    private static   Button rslt;
    private String C,C1,q;
    JSONArray peoples = null;
     int[] anschk; int[] score; int noans=0,wrng=0; int result1=0,atmpt=0; double per=0;
    ArrayList<HashMap<String, String>> personList;

    ListView list;
    Button sbm,prv,nx;
  public   int qid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.sample);
        setContentView(R.layout.dquesns);
        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String,String>>();
        final Intent intent = getIntent();
        C = intent.getStringExtra("C");
        C1 = intent.getStringExtra("C1");
        q = intent.getStringExtra("q");

        getData();
      //  final int[] anschk;//= new int[peoples.length()];
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);



        sbm=(Button)findViewById(R.id.button11);
 prv=(Button)findViewById(R.id.button8);
        prv.setVisibility(View.GONE);
 nx=(Button)findViewById(R.id.button10);

        sbm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent ri = new Intent(Ques.this, Result.class);
                //startActivity(ri);

                try {  anschk[qid]=rg.get(qid).getCheckedRadioButtonId();

                Date cdate12 = new Date(System.currentTimeMillis());
                long difference = cdate.getTime() - cdate12.getTime();

                TextView qq1=  (TextView)findViewById(R.id.ansr);

                for (int ann=0;ann<peoples.length();ann++)
                {
              //      if(anschk[ann]==-1) {
                //        noans = noans + 1;
                  //  }
                    //else
                  //  if (anschk[ann]==rg.get(ann).getCheckedRadioButtonId())//score[ann])
                        if (score[ann]==rg.get(ann).getCheckedRadioButtonId())//score[ann])
                        {
                result1++;
                }
                else if ((score[ann]!=rg.get(ann).getCheckedRadioButtonId())&&rg.get(ann).getCheckedRadioButtonId()!=-1)//score[ann])
                {
                        wrng++;
                }
                    else {
                         noans++;//   wrng++;
                        }

}
qq1.setText("time"+difference+"score"+result1+"/"+peoples.length());

                 //  Thread.sleep(2500);
               }
               catch (Exception r)
               {

               }
               atmpt=result1+wrng;
                per= ( (result1/peoples.length()))*100;
//....
                enterData();
            /**    Intent intent = new Intent(Ques.this, Score.class);
                Bundle bundle = new Bundle();
                //String str1 = noans
                String n=  String.valueOf(noans);// String.valueOf(int noans);
                String n1=  String.valueOf(result);// String.valueOf(int noans);
                String n2=  String.valueOf(wrng);// String.valueOf(int noans);
                intent.putExtra(noanss,n );//noans);
                intent.putExtra(resul, n1);//result);
                intent.putExtra(wrngg,n2);//wrng);
                /**   namesArray.add("Android");
                   namesArray.add("Iphone");
                   namesArray.add("Windows Phone");
                   bundle.putStringArrayList("valuesArray", namesArray);
                   namesFragment myFragment = new namesFragment();
                   myFragment.setArguments(bundle);
                   fragmentTransaction = getSupportFragmentManager().beginTransaction();
                   fragmentTransaction.add(R.id.main_container, myFragment);
                   fragmentTransaction.commit();
   * /
               //  intent.putExtra(USER_NAME, username);
                finish();
                startActivity(intent);
*/
            }
        });

        prv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               anschk[qid]=rg.get(qid).getCheckedRadioButtonId();


                qid--;
                if(qid==0) {
                    prv.setVisibility(View.GONE);

                }else {
                    prv.setVisibility(View.VISIBLE);

                }
               // if(qid<(peoples.length()-1)) {
                    nx.setVisibility(View.VISIBLE);
          //      }else {nx.setVisibility(View.GONE);}
                LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);
                ll.removeAllViews();
                ll.addView(quesns.get(qid));
                //  TextView qq=  quesns.get(qid).getText();

                   ll.addView(rg.get(qid));

            }
        });

        nx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                anschk[qid]=rg.get(qid).getCheckedRadioButtonId();
                LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);

                ll.removeAllViews();
                qid++;
               // }else {prv.setVisibility(View.GONE);}
                ll.addView(quesns.get(qid));
                //  TextView qq=  quesns.get(qid).getText();

                ll.addView(rg.get(qid));

                if(qid<peoples.length()-1) {
                    nx.setVisibility(View.VISIBLE);

                }else {
                    nx.setVisibility(View.GONE);
                }
                prv.setVisibility(View.VISIBLE);

            }
        });


        /**
        rslt = (Button) findViewById(R.id.submit);
        rslt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent ri = new Intent(Ques.this, Result.class);
                startActivity(ri);
            }
        });*/
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
//            final int[]
                    score= new int[peoples.length()];
            anschk= new int[peoples.length()];
          //  RadioGroup rg=new RadioGroup(this);
            //final int[] rg=new int[peoples.length()];
//            final ArrayList<RadioGroup> rg= new ArrayList<RadioGroup>();
            //final ArrayList<RadioGroup> rg= new ArrayList<RadioGroup>();
             RadioGroup radioGroup;
           // final ArrayList<RadioButton> allRadio = new ArrayList<RadioButton>();
             RadioButton radioButton;
            RadioButton rdbtn;


            int gscor=0;
        //    LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);
            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String qid = c.getString(TAG_QID);
                String qname = c.getString(TAG_QNAME);

                final TextView tv1 = new TextView(this);
                tv1.setText("Q"+(i+1)+". "+qname);
                tv1.setId(i+1);
                tv1.setTextSize(14);
                tv1.setGravity(Gravity.CENTER_VERTICAL);
                quesns.add(tv1);
           //     ll.addView(tv1);//................no
              //rg.setId(i+1);//R.id.rgroup);
                radioGroup = new RadioGroup(this);
                radioGroup.setId(i+1);
                rg.add(radioGroup);
                String op1 = c.getString(TAG_op1);
                String op2 = c.getString(TAG_op2);
                String op3 = c.getString(TAG_op3);
                String ans = c.getString(TAG_ans);
                String op4 = c.getString(TAG_op4);
int opn=Integer.parseInt(ans);
                score[i] = ((i+1)*10)+opn;
                Log.i("Clicked", ""+score);

                String[] opns = {op1 ,op2,op3,op4};
                Log.e("mbk", "lhl" );
                for (int j=1;j<5;j++) {
                   // RadioButton
                            rdbtn = new RadioButton(this);
                    rdbtn.setId(((i+1)*10)+j);
                   // String a="op"+j;
                    rdbtn.setText(opns[j-1]);
                    radioGroup.addView(rdbtn);
                    allRadio.add(rdbtn);

                    // radioGroup.addView(rdbtn);

                }
            ///..................    ll.addView(radioGroup);


            }
            LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);

            ll.addView(quesns.get(0));
            //  TextView qq=  quesns.get(qid).getText();

            ll.addView(rg.get(0));


            Button sbbtn = new Button(this);
            //rdbtn.setId(((i+1)*10)+j);
            // String a="op"+j;
            sbbtn.setId(R.id.submit);
            sbbtn.setText("submit");
     //..............       ll.addView(sbbtn);

            sbbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("Clicked", ""+v.getTag());
                     int ss=0;
                    for(int ans=0;ans<score.length;ans++){
                        int chk=score[ans];
                       //RadioGroup ang=rg.get(ans+1);//(RadioGroup)findViewById(ans);//R.id.(ans+1));

                   int sa= rg.get(ans).getCheckedRadioButtonId();
                     //   rg.get(ans).getCheckedRadioButton();
                       // int anss=(((ans+1)*10)+aaf);
                        if(chk==sa){
                           ss=ss+1;

                            allRadio.get((((ans+1)*4)-4+(chk%10))-1).setBackgroundColor(Color.parseColor("#00FF00") );
                            allRadio.get(((ans*4)+(chk%10))-1).setBackgroundColor(Color.parseColor("#00FF00") );

                        }
                    else
                        {
                        //    allRadio.get(sa).setBackgroundColor(Color.parseColor("#ffdc58"));
                        }
                       // RadioButton r1 = (RadioButton) findViewById(R.id.aaf);

                    }

                    Date cdate1 = new Date(System.currentTimeMillis());
                    long difference = cdate.getTime() - cdate1.getTime();
                    enddate = new Date(System.currentTimeMillis());
                  //  Date td= strtdat.compareTo(enddate);//enddate;
                    TextView a;
                    a = (TextView)findViewById(R.id.resulttext);
                    a.setText(ss+"/"+score.length+"  time in milllisec"+difference);

                }
            });
            final TextView t = new TextView(this);
            t.setText("");
            t.setId(R.id.resulttext);
            t.setTextSize(14);
            t.setGravity(Gravity.CENTER_VERTICAL);

   //..........         ll.addView(t);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/jrc.php?c="+C+"&c1="+C1);

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
               Toast.makeText(Ques.this,"C value"+C+"c1 vlue"+C1 , Toast.LENGTH_SHORT).show();

               showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    public void enterData(){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {
                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                String strSavedMem1 = sharedPreferences.getString("Nameuser", "");

                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
//                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/scoreupdt.php?cid="+C1+"&qid="+q+"&sid="+strSavedMem1+"&atmpt="+atmpt+"&crct="+result1+"&per="+per);
                HttpPost httppost = new HttpPost("http://192.168.0.20:86/inno/scrchng.php?cid="+C1+"&qid="+q+"&sid="+strSavedMem1+"&atmpt="+atmpt+"&crct="+result1+"&per="+per);
//...... prefer link removed replaced
                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
  //              SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
//                String strSavedMem1 = sharedPreferences.getString("Nameuser", "");

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
                if(result.length()>0)
                {
                    Intent intent = new Intent(Ques.this, Score.class);
                    intent.putExtra("score",String.valueOf(peoples.length()-wrng));
                    intent.putExtra("per",String.valueOf(per));

                    finish();
                     startActivity(intent);
                }
                else
                {
                    Toast.makeText(Ques.this,result+"server no response... press submi" , Toast.LENGTH_SHORT).show();

                }
                //showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    }//


