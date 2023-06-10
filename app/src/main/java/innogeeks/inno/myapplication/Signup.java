package innogeeks.inno.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Trinadh on 13-08-2017.
 */

public class Signup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
      //  editTextUserName = (EditText) findViewById(R.id);
    //    editTextPassword = (EditText) findViewById(R.id.button13);
        /**     final TextView tv1 = new TextView(this);
         tv1.setText("Hii Folks");
         tv1.setTextSize(14);
         tv1.setGravity(Gravity.CENTER_VERTICAL);
         LinearLayout ll = (LinearLayout) findViewById(R.id.ll1);
         ll.addView(tv1);
         */
        Button rec = (Button) findViewById(R.id.button13);
        rec.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                Intent ri = new Intent(Signup.this, Recregis.class);
                finish();
                startActivity(ri);
            }
        });
        Button sdn = (Button) findViewById(R.id.button12);
        sdn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent rs = new Intent(Signup.this, regis.class);
                finish();
                startActivity(rs);
            }
        });

    }
}
