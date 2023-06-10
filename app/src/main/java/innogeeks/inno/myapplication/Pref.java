package innogeeks.inno.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Trinadh on 01-09-2017.
 */

public class Pref extends AppCompatActivity {
    Button pre,qu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref);


        /**     final TextView tv1 = new TextView(this);
         tv1.setText("Hii Folks");
         tv1.setTextSize(14);
         tv1.setGravity(Gravity.CENTER_VERTICAL);
         LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);
         ll.addView(tv1);
         */
        pre = (Button) findViewById(R.id.buttonp);
        qu = (Button) findViewById(R.id.buttonq);
        pre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent ri = new Intent(Pref.this, Qspin.class);
                startActivity(ri);
            }
        });

    }
}
