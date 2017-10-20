package ualr.capstone.arkansasfloater;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Bryan on 9/28/2017.
 */

public class RList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_list);

        FragmentManager fm = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){

            if(savedInstanceState != null){
                return;
            }

            ListFragment listFrag = new ListFragment();

            fm.beginTransaction().add(R.id.fragment_container, listFrag).commit();
        }

    }
}
