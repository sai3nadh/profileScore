package innogeeks.inno.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
/**
import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SingleSpinner;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
*/
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.apptik.widget.multiselectspinner.ExpandableMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

/**
 * Created by Trinadh on 14-08-2017.
 */

public class Qualification extends AppCompatActivity  {

private  String myJSON;final ArrayList<String> options = new ArrayList<>();final ArrayList<String> options1 = new ArrayList<>();

     ArrayAdapter<String> adapter5;// = new ArrayAdapter <String>(this, R.layout.custom_item, options);
    MultiSelectSpinner multiSelectSpinner5 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner5);


    JSONArray peoplescore = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qualifca);
        getpref();
       // final ArrayList<String> options = new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        options.add("A");
        options.add("B");
        options.add("C");


        MultiSelectSpinner multiSelectSpinner1 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner1);
        multiSelectSpinner1.setItems(options)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(false)
                .selectItem(0, true)
                .selectItem(1, true)
                .selectItem(2, true)

/*.sele
        @Override

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            //String item = //.getItemAtPosition(position).toString();
            int seld=multiSelectSpinner1.getSelectedItemPosition();
            showdist( seld+1);
            //  TextView selitem=(TextView)findViewById(R.id.textb);
            //    selitem.setText(seld);
        }*/
        ;

        MultiSelectSpinner multiSelectSpinner2 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner2);
        MultiSelectSpinner multiSelectSpinner3 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner3);
        MultiSelectSpinner multiSelectSpinner4 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner4);
      //  MultiSelectSpinner multiSelectSpinner5 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner5);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_multiple_choice, options);
        ArrayAdapter<String> adapter3 = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_checked, options);
        ArrayAdapter<String> adapter4 = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_activated_1, options);
        //final ArrayAdapter<String> adapter5 = new ArrayAdapter <String>(this, R.layout.custom_item, options);


        multiSelectSpinner2
                .setListAdapter(adapter2)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                //deselects item
                .selectItem(2,false)
                .setMinSelectedItems(1);

        multiSelectSpinner3
                .setListAdapter(adapter3)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle(R.string.title)
                .setMinSelectedItems(1);

        multiSelectSpinner4
                .setListAdapter(adapter4)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle(getResources().getString(R.string.title))
                .setMinSelectedItems(1);

    /**    multiSelectSpinner5
                .setListAdapter(adapter5)
                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setSpinnerItemLayout(R.layout.custom_spinner_item)
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle("Custom Types Selector");
               // .setMinSelectedItems(1)* /
        multiSelectSpinner5.setOnItemSelectedListener(//this);
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        Object item = adapterView.getItemAtPosition(position);
                        if (item != null) {
                            Toast.makeText(Qualification.this, item.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Qualification.this, "Selected",
                                Toast.LENGTH_SHORT).show();
                        TextView m5 =(TextView) findViewById(R.id.sel);
                        m5.setText(item.toString() + position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // TODO Auto-generated method stub

                    }
                });
*/




        ExpandableMultiSelectSpinner multiSelectSpinner6 = (ExpandableMultiSelectSpinner) findViewById(R.id.multiselectSpinner6);
        LinkedHashMap<String, List<String>> items =  new LinkedHashMap<>();
        ArrayList<String> items1 = new ArrayList<>();
        items1.add("A");items1.add("B");items1.add("C");items1.add("D");items1.add("E");
        ArrayList<String> items2 = new ArrayList<>();
        items2.add("1");items2.add("2");items2.add("3");items2.add("4");items2.add("5");

        items.put("Abc", items1);
        items.put("123", items2);
        multiSelectSpinner6.setItems(items)
//                .setListAdapter(adapter5, "All Types", "none selected",
//                        new MultiSelectSpinner.MultiSpinnerListener() {
//                    @Override
//                    public void onItemsSelected(boolean[] checkedItems) {
//                    }
//                })
                //
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")

                // .setSelectAll(true)
                .setTitle("Select Types from Groups")
                .setVisibility(View.GONE);


        ;

        MultiSelectSpinner multiSelectSpinner7 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner7);
        ArrayAdapter<String> adapter7 = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_checked, options);
        MultiSelectSpinner multiSelectSpinner8 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner8);
        ArrayAdapter<String> adapter8 = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_checked, options);
        MultiSelectSpinner multiSelectSpinner9 = (MultiSelectSpinner) findViewById(R.id.multiselectSpinner9);
        ArrayAdapter<String> adapter9 = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_checked, options);
        MultiSelectSpinner multiSelectSpinner10 = (MultiSelectSpinner) findViewById(R.id
                .multiselectSpinner10);
        ArrayAdapter<String> adapter10 = new ArrayAdapter <String>(this, android.R.layout
                .simple_list_item_checked, options);

        multiSelectSpinner7
                .setListAdapter(adapter7)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle(R.string.title)
                .setMinSelectedItems(1) .setVisibility(View.GONE);

        multiSelectSpinner8
                .setListAdapter(adapter8)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle(R.string.title)
                .setMinSelectedItems(1)
                .setVisibility(View.GONE);

        multiSelectSpinner9
                .setListAdapter(adapter9)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle(R.string.title)
                .setMinSelectedItems(1)
                .setVisibility(View.GONE);

        multiSelectSpinner10
                .setListAdapter(adapter10)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("none selected")
                .setSelectAll(true)
                .setTitle(R.string.title)
                .setMinSelectedItems(1)
                .setVisibility(View.GONE);

    }



    public void getpref(){
        class GetDataJSONs extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Qualification.this, "Please wait", "Loading...");
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
                HttpPost httppost = new HttpPost("http://192.168.8.100/inno/pref.php");

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
                myJSON=result;  loadingDialog.dismiss();
                //   showList();



                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    peoplescore = jsonObj.getJSONArray("result");
                    for(int i=0;i<peoplescore.length();i++){
                        JSONObject c = peoplescore.getJSONObject(i);
                      //  String sid = c.getString("s_id");
                        String pref = c.getString("preference");
                        String prev=c.getString("pref_value");
                        options1.add(pref);
                    }
                 //   final ArrayAdapter<String>
                            adapter5 = new ArrayAdapter <String>(Qualification.this, R.layout.custom_item, options1);
                    multiSelectSpinner5
                            .setListAdapter(adapter5)
                            .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                                @Override
                                public void onItemsSelected(boolean[] selected) {

                                }
                            })
                            .setSpinnerItemLayout(R.layout.custom_spinner_item)
                            .setAllCheckedText("All types")
                            .setAllUncheckedText("none selected")
                            .setSelectAll(true)
                            .setTitle("Custom Types Selector");
                    multiSelectSpinner5.setOnItemSelectedListener(//this);
                            new AdapterView.OnItemSelectedListener() {

                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view,
                                                           int position, long id) {
                                    Object item = adapterView.getItemAtPosition(position);
                                    if (item != null) {
                                        Toast.makeText(Qualification.this, item.toString(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(Qualification.this, "Selected",
                                            Toast.LENGTH_SHORT).show();
                                    TextView m5 =(TextView) findViewById(R.id.sel);
                                    m5.setText(item.toString() + position);

                                   }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    // TODO Auto-generated method stub

                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();

                }

        }



        }
        GetDataJSONs g = new GetDataJSONs();
        g.execute();
    }


}
/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/





   /** MultiSelectionSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Multi spinner
        spinner = (MultiSelectionSpinner) findViewById(R.id.getSelected);

        Button bt = (Button) findViewById(R.id.btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = spinner.getSelectedItemsAsString();
                Log.e("getSelected", s);
            }
        });

    }
 /**   final List<String> list = Arrays.asList(getResources().getStringArray(R.array.sports_array));

    final List<KeyPairBoolData> listArray0 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
        KeyPairBoolData h = new KeyPairBoolData();
        h.setId(i + 1);
        h.setName(list.get(i));
        h.setSelected(false);
        listArray0.add(h);
    }

    final List<KeyPairBoolData> listArray1 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
        KeyPairBoolData h = new KeyPairBoolData();
        h.setId(i + 1);
        h.setName(list.get(i));
        h.setSelected(false);
        listArray1.add(h);
    }

    final List<KeyPairBoolData> listArray2 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
        KeyPairBoolData h = new KeyPairBoolData();
        h.setId(i + 1);
        h.setName(list.get(i));
        h.setSelected(false);
        listArray2.add(h);
    }
    final List<KeyPairBoolData> listArray3 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
        KeyPairBoolData h = new KeyPairBoolData();
        h.setId(i + 1);
        h.setName(list.get(i));
        h.setSelected(false);
        listArray3.add(h);
    }
    /**
     * Simple MultiSelection Spinner (Without Search/Filter Functionality)
     *
     *  Using MultiSpinner class
     */
        /*MultiSpinner simpleSpinner = (MultiSpinner) findViewById(R.id.simpleMultiSpinner);

        simpleSpinner.setItems(listArray0, -1, new MultiSpinnerListener() {

            @Override
            public void onItemsSelected(boolean[] selected) {
            }

        });*/

    /**
     * Search MultiSelection Spinner (With Search/Filter Functionality)
     *
     *  Using MultiSpinnerSearch class
     */
 /**   MultiSpinnerSearch searchMultiSpinnerUnlimited = (MultiSpinnerSearch) findViewById(R.id.searchMultiSpinnerUnlimited);
    MultiSpinnerSearch searchMultiSpinnerLimit = (MultiSpinnerSearch) findViewById(R.id.searchMultiSpinnerLimit);
    SingleSpinnerSearch searchSingleSpinner = (SingleSpinnerSearch) findViewById(R.id.searchSingleSpinner);
    SingleSpinner singleSpinner = (SingleSpinner) findViewById(R.id.singleSpinner);

        searchMultiSpinnerUnlimited.setItems(listArray0, -1, new SpinnerListener() {

        @Override
        public void onItemsSelected(List<KeyPairBoolData> items) {

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isSelected()) {
                    Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                }
            }
        }
    });

    /***
     * -1 is no by default selection
     * 0 to length will select corresponding values
     /
        searchMultiSpinnerLimit.setItems(listArray1, -1, new SpinnerListener() {

        @Override
        public void onItemsSelected(List<KeyPairBoolData> items) {

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isSelected()) {
                    Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                }
            }
        }
    });

        searchMultiSpinnerLimit.setLimit(2, new MultiSpinnerSearch.LimitExceedListener() {
        @Override
        public void onLimitListener(KeyPairBoolData data) {
            Toast.makeText(getApplicationContext(),
                    "Limit exceed ", Toast.LENGTH_LONG).show();
        }
    });

        searchSingleSpinner.setItems(listArray2, -1, new SpinnerListener() {

        @Override
        public void onItemsSelected(List<KeyPairBoolData> items) {

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isSelected()) {
                    Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                }
            }
        }
    });

        singleSpinner.setItems(listArray3, -1, new SpinnerListener() {

        @Override
        public void onItemsSelected(List<KeyPairBoolData> items) {

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isSelected()) {
                    Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                }
            }
        }
    });
}*/
