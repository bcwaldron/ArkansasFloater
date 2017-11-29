package ualr.capstone.arkansasfloater;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bryan on 11/29/2017.
 */

public class TweetListAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;

    Activity mActivity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;

    public TweetListAdapter(Activity mActivity, ArrayList<HashMap<String, String>> list){
        super();

        this.mActivity = mActivity;
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int poosition){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = mActivity.getLayoutInflater();

        if(convertView == null){

            convertView = inflater.inflate(R.layout.t_list_item, null);

            txtFirst = (TextView) convertView.findViewById(R.id.user);
            txtSecond = (TextView) convertView.findViewById(R.id.rating);
            txtThird = (TextView) convertView.findViewById(R.id.tweet);
        }

        HashMap<String, String> map = list.get(position);
        txtFirst.setText(map.get("First"));
        txtSecond.setText(map.get("Second"));
        txtThird.setText(map.get("Third"));

        return convertView;
    }
}
