package ualr.capstone.arkansasfloater;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.IClassifier;
import net.sf.classifier4J.bayesian.BayesianClassifier;
import net.sf.classifier4J.bayesian.IWordsDataSource;
import net.sf.classifier4J.bayesian.SimpleWordsDataSource;
import net.sf.classifier4J.bayesian.WordsDataSourceException;
import net.sf.classifier4J.vector.VectorClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bryan on 11/29/2017.
 */

public class BayesClassifier extends AsyncTask<Void, Void, ArrayList<Tweet>> {

    public ProgressDialog pBar;
    private Context mContext;
    private MainActivity mActivity;
    private List<String> matches;
    private List<String> nonMatches;
    private ArrayList<Tweet> matchedTweets;
    private BayesianClassifier bc;
    private ArrayList<Tweet> tweets;
    private ArrayList<String> texts;
    private ArrayList<String> users;


    public BayesClassifier(Context context, MainActivity a, ArrayList<String> u, ArrayList<String> t){

        mContext = context;
        mActivity = a;
        texts = t;
        users = u;
    }

    private void Teach() throws ClassifierException{

        for(int i = 0; i < matches.size();i++){

           // wds.addMatch(matches.get(i));
            bc.teachMatch(matches.get(i));
        }

        for(int i = 0; i < nonMatches.size();i++){

           // wds.addNonMatch(nonMatches.get(i));
            bc.teachNonMatch(matches.get(i));
        }
    }

    private void Classify() throws ClassifierException{

        double rating;

        for(int i = 0; i < tweets.size();i++){

            rating = bc.classify(tweets.get(i).getText());
            if(bc.isMatch(tweets.get(i).getText())){

                tweets.get(i).setRating(rating);
                matchedTweets.add(tweets.get(i));
            }
        }
    }

    @Override
    protected void onPreExecute(){

        super.onPreExecute();
        tweets = new ArrayList<Tweet>();
        matchedTweets = new ArrayList<Tweet>();

        //show progress dialog
        pBar = new ProgressDialog(mActivity);
        pBar.setMessage("Comparing/Searching Tweets...");
        pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pBar.setCancelable(false);
        pBar.show();
    }

    @Override
    protected ArrayList<Tweet> doInBackground(Void...args){

        matches = Arrays.asList(mContext.getResources().getStringArray(R.array.matches));
        nonMatches = Arrays.asList(mContext.getResources().getStringArray(R.array.notMatches));

        bc = new BayesianClassifier();
        Tweet tw = new Tweet();

        for(int i = 0; i < texts.size();i++){

            tw.setText(texts.get(i));
            tw.setUser(users.get(i));

            tweets.add(tw);
        }


        //teach the classifier with sample tweets
        try{
            Teach();
        } catch (ClassifierException e){
            Log.e("Teach", "Classifier Exception: " + e.getMessage());
        }

        //collect the matching tweets
        try{
            Classify();
        } catch(ClassifierException e){
            Log.e("Classify", "Classifier Exception: " + e.getMessage());
        }

        return(matchedTweets);
    }

    protected void onPostExecute(ArrayList<Tweet> tw){



        pBar.dismiss();
        mActivity.launchList(tw);
    }

}
