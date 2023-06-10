package innogeeks.inno.myapplication;

import android.widget.AdapterView;

/**
 * Created by Trinadh on 01-09-2017.
 */

public class Model {
    String name;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    Model(String name, int value){
        this.name = name;
        this.value = value;
    }



    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
}
