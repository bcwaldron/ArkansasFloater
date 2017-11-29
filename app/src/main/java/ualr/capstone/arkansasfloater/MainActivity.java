package ualr.capstone.arkansasfloater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Bryan on 9/28/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button welcome = (Button) findViewById(R.id.continuebutton);
        Button twitterB = (Button) findViewById(R.id.twitterButton);
        final MainActivity A = this;

        welcome.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent i = new Intent(v.getContext(), RList.class);
                startActivity(i);
            }
        });

        twitterB.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                GetAccessToken getToken = new GetAccessToken(getApplicationContext(), A);
                getToken.execute();
            }
        });
    }

    public void launchList(ArrayList<Tweet> tweets){

        ArrayList<String> tw = new ArrayList<String>();
        ArrayList<String> users = new ArrayList<String>();
        double[] ratings = new double[tweets.size()];

        for(int i = 0; i < tweets.size(); i++){

            tw.add(tweets.get(i).getText());
            users.add(tweets.get(i).getText());
            ratings[i] = tweets.get(i).getRating();
        }

        Intent i = new Intent(this, TList.class);
        i.putExtra("text", tw);
        i.putExtra("users",users);
        i.putExtra("ratings", ratings);
        startActivity(i);


    }

    public void launchBayes(ArrayList<String> texts, ArrayList<String> users){

        BayesClassifier bc = new BayesClassifier(getApplicationContext(), this, users, texts);
        bc.execute();
    }
}
