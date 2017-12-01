package ualr.capstone.arkansasfloater;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Bryan on 11/15/2017.
 */

public class GetAccessToken extends AsyncTask<String, Void, ArrayList<String>> {


    public ProgressDialog progressBar;
    private Twitter mTwitter;
    private String consumerKey;
    private String consumerSecret;
    private String accessKey;
    private String accessSecret;
    private Context mContext;
    private MainActivity mActivity;
    private String queryString;
    private String twoWeeksOld;
    private ArrayList<String> statusTexts;
    private ArrayList<String> users;

    //constructor
    public GetAccessToken(Context context, MainActivity activity){

        mContext = context;
        mActivity = activity;
    }

    @Override
    protected void onPreExecute(){

        super.onPreExecute();

        //show progress dialog

        progressBar = new ProgressDialog(mActivity);
        progressBar.setMessage("Connecting...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);
        progressBar.show();

        statusTexts = new ArrayList<String>();

        getDates();
        queryString = mContext.getString(R.string.QUERY);
        mTwitter = getTwitter();
    }

    @Override
    protected ArrayList<String> doInBackground(String...args){

        List<twitter4j.Status> statuses = new ArrayList<twitter4j.Status>();
        users = new ArrayList<String>();

        //building the query
        GeoLocation geo = new GeoLocation(34.7465, -92.2896);
        Query q = new Query(queryString);
        q.setLang("en");
        q.setSince(twoWeeksOld);
        q.setGeoCode(geo, 400, Query.Unit.mi);
        QueryResult result;

        //collecting the tweets, and the users they came from
        try{
            do{
                result = mTwitter.search(q);
                statuses = result.getTweets();

                for(twitter4j.Status s : statuses){
                    statusTexts.add(s.getText());
                    users.add(s.getUser().getScreenName());
                }
            } while ((q = result.nextQuery()) != null);
        } catch (TwitterException e){
            Log.e("AsyncTask","Twitter Query Failed");
            statusTexts.add("Twitter Query Failed");
        }
        return statusTexts;
    }

    //connecting to the twitter API and establishing OAUTH
    private Twitter getTwitter(){

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(mContext.getString(R.string.CONSUMER_KEY));
        cb.setOAuthConsumerSecret(mContext.getString(R.string.CONSUMER_SECRET));
        cb.setOAuthAccessToken(mContext.getString(R.string.ACCESS));
        cb.setOAuthAccessTokenSecret(mContext.getString(R.string.ACCESS_SECRET));
        cb.setUser("bwall1128");

        return new TwitterFactory(cb.build()).getInstance();
    }

    protected void onPostExecute(ArrayList<String> texts){

        //dismiss the progressbar and launch activity to display tweets
        progressBar.dismiss();
        mActivity.launchBayes(texts, users);
    }

    //calculating and formatting the date of two weeks ago from today
    protected void getDates(){

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DAY_OF_YEAR, -14);
        twoWeeksOld = format1.format(c.getTime());

    }

}





