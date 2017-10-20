package ualr.capstone.arkansasfloater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Bryan on 9/28/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button welcome = (Button) findViewById(R.id.continuebutton);

        welcome.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent i = new Intent(v.getContext(), RList.class);
                startActivity(i);
            }
        });
    }
}
