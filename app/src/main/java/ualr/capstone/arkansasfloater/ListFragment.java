package ualr.capstone.arkansasfloater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.res.Resources;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bryan on 9/28/2017.
 */

public class ListFragment extends Fragment {

    private RecyclerView RV;
    public List<River> rivers;
    private myAdapter adapter;
    private  String preurl;
    private  String posturl;
    private List<String> siteNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.frag_list, container, false);

        rivers = build();
        RV = (RecyclerView) view.findViewById(R.id.recycler_view);
        RV.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI(){

        adapter = new myAdapter(rivers);
        RV.setAdapter(adapter);
    }

    private class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView rName;
        private River r;

        public ListHolder(View itemView){

            super(itemView);
            itemView.setOnClickListener(this);
            rName = (TextView) itemView.findViewById(R.id.r_name);
        }

        public void bindRiver(River river){

            r = river;
            rName.setText(r.getrName());
        }

        @Override
        public void onClick(View v){


            //hardcoded # of sites for each river
            int[] sites = new int[6];
            sites[0] = 5; //buffalo
            sites[1] = 2; //big piney
            sites[2] = 4; //cadron
            sites[3] = 4; //ouachita
            sites[4] = 5; //spring
            sites[5] = 3; //white


            //hardcoded # of responses per river(not all sites measure all 3 variables)
            int[] maxEntries = new int[6];
            maxEntries[0] = 13; //buffalo
            maxEntries[1] =  4; //big piney
            maxEntries[2] = 8; //cadron
            maxEntries[3] = 4; //ouachita
            maxEntries[4] = 9; //spring
            maxEntries[5] = 6; //white

            //launch next activity

            int position;
            River river;
            position = getAdapterPosition();
           int numSites = sites[position];
            river = rivers.get(position);
            river.setSites(numSites);
            river.setSites(sites[position]);
            river.setMaxEntries(maxEntries[position]);
            river.setDiscBool();
            river.setTempBool();
            river.setHeightBool();

            switch(position){

                case 0:
                    siteNames = Arrays.asList(getResources().getStringArray(R.array.buffalo));
                    river.setSiteNames(siteNames);
                    break;
                case 1:
                    siteNames = Arrays.asList(getResources().getStringArray(R.array.big_piney));
                    river.setSiteNames(siteNames);
                    break;
                case 2:
                    siteNames = Arrays.asList(getResources().getStringArray(R.array.cadron));
                    river.setSiteNames(siteNames);
                    break;
                case 3:
                    siteNames = Arrays.asList(getResources().getStringArray(R.array.ouachita));
                    river.setSiteNames(siteNames);
                    break;
                case 4:
                    siteNames = Arrays.asList(getResources().getStringArray(R.array.spring));
                    river.setSiteNames(siteNames);
                    break;
                case 5:
                    siteNames = Arrays.asList(getResources().getStringArray(R.array.white));
                    river.setSiteNames(siteNames);
                    break;
                default:
                    siteNames.add("Error Getting Site Names");
                    river.setSiteNames(siteNames);
                    break;
            }


            Intent i = new Intent(getActivity(), InfoActivity.class);
            i.putExtra("river",river);
            startActivity(i);


        }
    }

    private List<River> build(){

        List<River> rivers = new ArrayList<>();
        Resources res = getResources();

        String[] names = res.getStringArray(R.array.river_names);
        String[] codes = res.getStringArray(R.array.codes);
        String code;
        preurl = "https://waterservices.usgs.gov/nwis/iv/?format=json&sites=";
        posturl = "&parameterCd=00060,00065,00010&siteStatus=all";

        for(int i = 0; i < names.length;i++){

            River y = new River();
            y.setrName(names[i]);
            code = preurl + codes[i] + posturl;
            y.setCode(code);


            rivers.add(y);
        }

        return rivers;
    }

    private class myAdapter extends RecyclerView.Adapter<ListHolder>{

        private List<River> mRivers;

        public myAdapter(List<River> r){

            mRivers = r;
        }

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType){

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ListHolder(view);
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position){

            River river = mRivers.get(position);
            holder.bindRiver(river);
        }

        @Override
        public int getItemCount() { return mRivers.size();}
    }
}
