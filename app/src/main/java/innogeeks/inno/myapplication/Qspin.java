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
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.apptik.widget.multiselectspinner.ExpandableMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import android.view.Menu;
/**
 * Created by Trinadh on 31-08-2017.
 */

public class Qspin extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ListView lv;
    Button p;
    int af=0;
    TextView seli;
    Model[] modelItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qspin);
        p=(Button)findViewById(R.id.pp);
        lv = (ListView) findViewById(R.id.listView8);
        seli=(TextView)findViewById(R.id.selval);
        modelItems = new Model[5];
        modelItems[0] = new Model("pizza", 0);
        modelItems[1] = new Model("burger", 1);
        modelItems[2] = new Model("olives", 1);
        modelItems[3] = new Model("orange", 0);
        modelItems[4] = new Model("tomato", 1);
        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);

        lv.setOnItemSelectedListener(this );
        //  );
        p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                af=0;
                for (int i=0;i<modelItems.length;i++)
                {
                    if(modelItems[i].getValue()==1)
                    {
                        af=af+(int)(Math.pow(2,(i+1)));
                    }
                    else if (modelItems[i].getValue()== 0)
                    {
                        af=af+(int)(Math.pow(2,(i+1)));
                    }

                //    seli.setText(af);
                }

         //       SharedPreferences pref = getApplicationContext().getSharedPreferences("usersessoin", MODE_PRIVATE);
           //     SharedPreferences.Editor editor = pref.edit();
  ///              SharedPreferences sharedpreferences = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);

             //   SharedPreferences prefs = getSharedPreferences("usersessoin", Context.MODE_PRIVATE);
 ////              String restoredText = sharedpreferences.getString("Nameuser", null);
            //    if (restoredText != null) {
              //      String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
              //      int idName = prefs.getInt("idName", 0); //0 is the default value.
               // }
                SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
                String strSavedMem1 = sharedPreferences.getString("Nameuser", "");

                upref(String.valueOf(af),strSavedMem1);

            }
        });
    }
    private void upref(final String username, final String value) {

        class SignAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Qspin.this, "Please wait", "Loading...");
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
                    String ad="http://192.168.8.102:80/inno/prefer.php";//.toString();
                    String link1;

                    String data1;
                    BufferedReader bufferedReader;
                    //String result;

                    data1 = "?name=" + uname;
                    data1 = data1+"&value=" + value;

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
                if(s.equalsIgnoreCase("hi")){
                    Intent intent = new Intent(Qspin.this,Pref.class);// Qspin.class);// Qualification.class);
                    //      intent.putExtra(USER_NAME, username);
                    finish();
                    startActivity(intent);
                }
                //else if(s.equalsIgnoreCase("wrng")){
                //     Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_LONG).show();
                //  }
                else
                {
                    Toast.makeText(getApplicationContext(), "server no response"+s, Toast.LENGTH_LONG).show();
                }
            }
        }

        SignAsync la = new SignAsync();
        la.execute(username,value);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        af=0;
for (int i=0;i<modelItems.length;i++)
{
if(modelItems[i].getValue()==1)
{
 af=af+(int)(Math.pow(2,(i+1)));
}
seli.setText(af);
}
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        af=0;
        for (int i=0;i<modelItems.length;i++)
        {
            if(modelItems[i].getValue()==1)
            {
                af=af+(int)(Math.pow(2,(i+1)));
            }

        }  seli.setText(af+"" + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**final ArrayList<String> options = new ArrayList<>();final ArrayList<String> options1 = new ArrayList<>();

    ArrayAdapter<String> adapter5;// = new ArrayAdapter <String>(this, R.layout.custom_item, options);
    MultiSelectSpinner multiSelectSpinner5 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qspin);
        // final ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("A");
        options.add("B");
        options.add("C");
        multiSelectSpinner5.setItems(options);
        multiSelectSpinner5.setOnItemSelectedListener(//this);
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        Object item = adapterView.getItemAtPosition(position);
                        if (item != null) {
                            Toast.makeText(Qspin.this, item.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Qspin.this, "Selected",
                                Toast.LENGTH_SHORT).show();
                        TextView me5 =(TextView) findViewById(R.id.sel);
                        me5.setText(item.toString() + position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // TODO Auto-generated method stub

                    }
                });



    }
*/

}
