package innogeeks.inno.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Trinadh on 20-08-2017.
 */

public class Recregis extends AppCompatActivity {
Button rm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recregis);
/**
        rm = (Button) findViewById(R.id.button14);
        rm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent rm = new Intent(Recregis.this, Recmenu.class);
                startActivity(rm);
            }
        });

 */
    }

}
