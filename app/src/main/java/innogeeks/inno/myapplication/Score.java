package innogeeks.inno.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Trinadh on 04-09-2017.
 */

public class Score extends AppCompatActivity {
Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        TextView s=(TextView)findViewById(R.id.score);
        TextView per=(TextView)findViewById(R.id.per);
        final Intent intent = getIntent();
        String scr = intent.getStringExtra("score");
        String per1 = intent.getStringExtra("per");
s.setText(scr);
        per.setText(per1);
        home=(Button)findViewById(R.id.home);
        home.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(Score.this,UserProfile.class);
                finish();
                startActivity(intent1);

                Toast.makeText(Score.this,"home page" , Toast.LENGTH_SHORT).show();

            }
        });

    }
}
