package ualr.capstone.arkansasfloater;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.View;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bryan on 9/28/2017.
 */

public class InfoActivity extends Activity {

    private String url;
    private ProgressDialog dialog;
    private List<String> temps;
    private List<String> heights;
    private List<String> discharge;
    private ArrayList<String> siteNames;
    private int counterMax;
    private String code;
    private int c;
    private int numSites;
    private River r;
    private int tempIndex;
    private int discIndex;
    private int heightIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView rName = (TextView) findViewById(R.id.river);

        Intent i = getIntent();
        r = (River) i.getSerializableExtra("river");
        siteNames = r.getSiteNames();
        c = 0;

        temps = new ArrayList<>();
        heights = new ArrayList<>();
        discharge = new ArrayList<>();


        rName.setText(r.getrName());

        url = r.getCode();
        counterMax = r.getMaxEntries(); //buffalo only returns 13 values?
        siteNames = r.getSiteNames();
        numSites = r.getSites();

        //call the json parser
        new GetInfo().execute();
    }

    public void updateUI() {
        //update the UI after parsing JSON

        TextView site1 = (TextView) findViewById(R.id.site1);
        TextView temp1 = (TextView) findViewById(R.id.temp1);
        TextView disc1 = (TextView) findViewById(R.id.discharge1);
        TextView height1 = (TextView) findViewById(R.id.height1);

        TextView site2 = (TextView) findViewById(R.id.site2);
        TextView temp2 = (TextView) findViewById(R.id.temp2);
        TextView disc2 = (TextView) findViewById(R.id.discharge2);
        TextView height2 = (TextView) findViewById(R.id.height2);

        TextView site3 = (TextView) findViewById(R.id.site3);
        TextView temp3 = (TextView) findViewById(R.id.temp3);
        TextView disc3 = (TextView) findViewById(R.id.discharge3);
        TextView height3 = (TextView) findViewById(R.id.height3);

        TextView site4 = (TextView) findViewById(R.id.site4);
        TextView temp4 = (TextView) findViewById(R.id.temp4);
        TextView disc4 = (TextView) findViewById(R.id.discharge4);
        TextView height4 = (TextView) findViewById(R.id.height4);

        TextView site5 = (TextView) findViewById(R.id.site5);
        TextView temp5 = (TextView) findViewById(R.id.temp5);
        TextView disc5 = (TextView) findViewById(R.id.discharge5);
        TextView height5 = (TextView) findViewById(R.id.height5);


        site1.setText(siteNames.get(0));
        tempIndex = 0;
        discIndex = 0;
        heightIndex = 0;

        if(!temps.isEmpty()) { //add this to all
            if (r.checkTemp(0)) {
                temp1.setText("Temperature:             " + temps.get(tempIndex++));
                temp1.setVisibility(View.VISIBLE);
            }
        }

        if(!discharge.isEmpty()) {
            if (r.checkDisc(0)) {
                disc1.setText("Water Discharge:         " + discharge.get(discIndex++));
                disc1.setVisibility(View.VISIBLE);

            }
        }

        if(!heights.isEmpty()){
            if(r.checkHeight(0)){
                height1.setText("Water Height:          " + heights.get(heightIndex++));
                height1.setVisibility(View.VISIBLE);
            }
        }



        site2.setText(siteNames.get(1));

        if(!temps.isEmpty()) {
            if (r.checkTemp(1)) {
                temp2.setText("Temperature:             " + temps.get(tempIndex++));
                temp2.setVisibility(View.VISIBLE);
            }
        }
        if(!discharge.isEmpty()) {
            if (r.checkDisc(1)) {
                disc2.setText("Water Discharge:         " + discharge.get(discIndex++));
                disc2.setVisibility(View.VISIBLE);
            }
        }

        if(!heights.isEmpty()){
            if(r.checkHeight(1)) {
                if (heights.size() >= 2) {
                    height2.setText("Water Height:          " + heights.get(heightIndex++));
                    height2.setVisibility(View.VISIBLE);
                }
            }
        }

        if(numSites >= 3){

            site3.setText(siteNames.get(2));
           if(!temps.isEmpty()) {
               if (r.checkTemp(2)) {
                   temp3.setText("Temperature:             " + temps.get(tempIndex++));
                   temp3.setVisibility(View.VISIBLE);
               }
           }
           if(!discharge.isEmpty()) {
               if (r.checkDisc(2)) {
                   if (discharge.size() >= 3) {
                       disc3.setText("Water Discharge:         " + discharge.get(discIndex++));
                       disc3.setVisibility(View.VISIBLE);
                   }
               }
           }
            if(!heights.isEmpty()){
                if(r.checkHeight(2)) {
                    if (heights.size() >= 3) {
                        height3.setText("Water Height:          " + heights.get(heightIndex++));
                    }
                }
            }
        }

        if (numSites >= 4) {
            site4.setText(siteNames.get(3));
            site4.setVisibility(View.VISIBLE);

            if(!discharge.isEmpty()) {
                if (r.checkDisc(3)) {
                    if (discharge.size() >= 4) {
                        disc4.setText("Water Discharge:         " + discharge.get(discIndex++));
                        disc4.setVisibility(View.VISIBLE);
                    }
                }
            }

            if(!heights.isEmpty()){

                if(r.checkHeight(3)) {
                    if (heights.size() >= 4) {
                        height4.setText("Water Height:          " + heights.get(heightIndex++));
                        height4.setVisibility(View.VISIBLE);
                    }
                }
            }
            if(!temps.isEmpty()) {
                if (r.checkTemp(3)) {

                    temp4.setText("Temperature:         " + temps.get(tempIndex++));
                    temp4.setVisibility(View.VISIBLE);
                }
            }
        }

        if (numSites == 5) {
            site5.setText(siteNames.get(4));
            site5.setVisibility(View.VISIBLE);
            if (!discharge.isEmpty()) {
                if (r.checkDisc(4)) {
                    if(discharge.size() >= 5) {
                        disc5.setText("Water Discharge:         " + discharge.get(discIndex++));
                        disc5.setVisibility(View.VISIBLE);
                    }
                }
            }
            if(!heights.isEmpty()){
                if(r.checkHeight(4)) {
                    if (heights.size() >= 5) {
                        height5.setText("Water Height:          " + heights.get(heightIndex++));
                        height5.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (!temps.isEmpty()) {
                if (r.checkTemp(4)) {

                    temp5.setText("Temperature:         " + temps.get(tempIndex++));
                    temp5.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private class GetInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(InfoActivity.this);
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            //making a request to URL
            HttpURLConnection urlConnection = null;

            try {
                URL data = new URL(url);
                urlConnection = (HttpURLConnection) data.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    readJsonStream(urlConnection.getInputStream());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //dismiss progress dialog
            if (dialog.isShowing())
                dialog.dismiss();

            updateUI();
        }

        public List<Message> readJsonStream(InputStream in) throws IOException {

            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

            try {
                return Parse(reader);
            } finally {
                reader.close();
                in.close();
            }
        }


        protected List<Message> Parse(JsonReader reader) throws IOException {
            List<Message> messages = new ArrayList<>();
            reader.beginObject();
            String next = reader.nextName();

            while (reader.hasNext()) {

                if (next.equals("value")) {

                    reader.beginObject(); //starting 'value' object

                    next = reader.nextName();

                    while (!next.equals("timeSeries")) {
                        if (reader.hasNext()) {
                            reader.skipValue();
                            next = reader.nextName();
                        }
                    }

                    if (next.equals("timeSeries")) {

                        reader.beginArray();
                        reader.beginObject();
                        next = reader.nextName();

                        while (!next.equals("variable")) {// first pass will be site name

                            if (reader.hasNext()) {
                                reader.skipValue();
                                next = reader.nextName();
                            }

                        }

                        if (next.equals("variable")) {

                            reader.beginObject();
                            next = reader.nextName();

                            while (!next.equals("variableCode")) {

                                if (reader.hasNext()) {
                                    reader.skipValue();
                                    next = reader.nextName();
                                }
                            }

                            if (next.equals("variableCode")) {
                                reader.beginArray();
                                reader.beginObject();
                                next = reader.nextName();

                                if (next.equals("value")) {

                                    code = reader.nextString(); //determines what data we're getting next

                                    while (reader.hasNext()) {
                                        reader.skipValue();
                                    }

                                    reader.endObject();
                                    reader.endArray();  //next == variableName

                                    while (reader.hasNext()) {
                                        next = reader.nextName();
                                        reader.skipValue();
                                    }
                                    reader.endObject(); // next will == "values"
                                    if (code.equals("00010")) { //call method for retrieving appropriate data type

                                        getTemp(reader);
                                    }

                                    if (code.equals("00060")) {
                                        getDischarge(reader);
                                    }
                                    if (code.equals("00065")) {
                                        getHeight(reader);
                                    }

                                }
                            }
                        }
                    }
                    return messages;
                } else {
                    reader.skipValue();
                    next = reader.nextName();
                }
            }
            return messages;
        }

        protected void getTemp(JsonReader reader) throws IOException {

            String next = reader.nextName();

            if (next.equals("values")) {

                reader.beginArray();
                reader.beginObject();

                next = reader.nextName();

                if (next.equals("value")) {

                    reader.beginArray();
                    reader.beginObject();

                    next = reader.nextName();

                    if (next.equals("value")) {

                        temps.add(reader.nextString());
                        c++;

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }
                        reader.endObject(); //ends "value"
                        reader.endArray();

                        while (reader.hasNext()) {

                            //next == "censorCode"
                            reader.skipValue();
                        }

                        reader.endObject();
                        reader.endArray();

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }
                        reader.endObject();

                    }
                }
            }

            if (c < counterMax) {
                getNext(reader);
            }


        }

        protected void getDischarge(JsonReader reader) throws IOException {

            String next = reader.nextName();

            if (next.equals("values")) {

                reader.beginArray();
                reader.beginObject();

                next = reader.nextName();

                if (next.equals("value")) {

                    reader.beginArray();
                    reader.beginObject();

                    next = reader.nextName();

                    if (next.equals("value")) {

                        discharge.add(reader.nextString());
                        c++;

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }
                        reader.endObject(); //ends "value"
                        reader.endArray();

                        while (reader.hasNext()) {

                            //next == "censorCode"
                            reader.skipValue();
                        }

                        reader.endObject();
                        if(reader.peek() == JsonToken.END_ARRAY)
                            reader.endArray();

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }
                        if(reader.peek() == JsonToken.END_ARRAY)
                            reader.endArray();
                        if(reader.peek() == JsonToken.END_OBJECT)
                            reader.endObject();

                    }
                }
            }

            if (c < counterMax) {
                getNext(reader);
            }
        }

        protected void getHeight(JsonReader reader) throws IOException {

            String next = reader.nextName();

            if (next.equals("values")) {

                reader.beginArray();
                reader.beginObject();

                next = reader.nextName();

                if (next.equals("value")) {

                    reader.beginArray();
                    reader.beginObject();

                    next = reader.nextName();

                    if (next.equals("value")) {

                        heights.add(reader.nextString());
                        c++;
                        code = "00000";

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }
                        reader.endObject(); //ends "value"
                        reader.endArray();

                        while (reader.hasNext()) {

                            //next == "censorCode"
                            reader.skipValue();
                        }

                        reader.endObject();
                        reader.endArray();

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }
                        reader.endObject();

                    }
                }
            }

            if (c < counterMax) {
                getNext(reader);
            }

        }

        protected void getNext(JsonReader reader) throws IOException {

            String next = null;

            while(reader.peek() == JsonToken.END_ARRAY)
                reader.endArray();
            while(reader.peek() == JsonToken.END_OBJECT)
                reader.endObject();

            if(reader.peek() == JsonToken.BEGIN_OBJECT)
                reader.beginObject();
            if(reader.peek() == JsonToken.NAME)
                next = reader.nextName();


            while (!next.equals("variable")) {

                if (reader.hasNext()) {
                    reader.skipValue();
                    next = reader.nextName();
                }
            }

            if (next.equals("variable")) {

                reader.beginObject();
                next = reader.nextName();

                while (!next.equals("variableCode")) {

                    if (reader.hasNext()) {
                        reader.skipValue();
                        next = reader.nextName();
                    }
                }

                if (next.equals("variableCode")) {
                    reader.beginArray();
                    reader.beginObject();
                    next = reader.nextName();

                    if (next.equals("value")) {

                        code = reader.nextString(); //determines what data we're getting next

                        while (reader.hasNext()) {
                            reader.skipValue();
                        }

                        reader.endObject();
                        reader.endArray();  //next == variableName

                        while (reader.hasNext()) {
                            next = reader.nextName();
                            reader.skipValue();
                        }
                        reader.endObject(); // next will == "values"
                        if (code.equals("00010")) { //call method for retrieving appropriate data type
                            getTemp(reader);
                        }

                        if (code.equals("00060")) {
                            getDischarge(reader);
                        }
                        if (code.equals("00065")) {
                            getHeight(reader);
                        }
                    }


                }


            }
        }
    }
}




