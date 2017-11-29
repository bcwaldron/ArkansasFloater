package ualr.capstone.arkansasfloater;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bryan on 11/15/2017.
 */

public class TList extends AppCompatActivity{

    private ArrayList<Tweet> matched;
    private ArrayList<HashMap<String, String>> list;
    private HashMap<String, String> header;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_list);
        matched = new ArrayList<Tweet>();

        ListView lv = (ListView) findViewById(R.id.t_list_view);

        list = new ArrayList<HashMap<String, String>>();
        header = new HashMap<String, String>();

        Compile();

        TweetListAdapter adapter = new TweetListAdapter(this, list);
        lv.setAdapter(adapter);
    }

    private void Compile(){

        ArrayList<String> texts = (ArrayList<String>) getIntent().getSerializableExtra("text");
        ArrayList<String> users = (ArrayList<String>) getIntent().getSerializableExtra("users");
        double[] ratings = (double[]) getIntent().getSerializableExtra("ratings");



        //create a header for the columns
        header.put("First", "USER");
        header.put("Second", "RATING");
        header.put("Third", "TWEET");
        list.add(header);

        if(texts.isEmpty()){
            HashMap<String, String> temp = new HashMap<>();
            temp.put("First", "System");
            temp.put("Second", "N/A");
            temp.put("Third", "There were no matching tweets within the last two weeks");
            list.add(temp);
        } else
            for(int i = 0; i < texts.size();i++){

            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("First", users.get(i));
            temp.put("Second",Double.toString(ratings[i]));
            temp.put("Third", texts.get(i));

            list.add(temp);
        }
    }


}
