package innogeeks.inno.myapplication;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Trinadh on 22-08-2017.
 */

public class StateVO extends AppCompatActivity {


    private String title;
    private boolean selected;

    public String getTitle1() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {

        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
