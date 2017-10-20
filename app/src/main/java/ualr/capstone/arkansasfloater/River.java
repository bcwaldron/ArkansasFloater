package ualr.capstone.arkansasfloater;

import android.content.res.Resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Bryan on 9/28/2017.
 */

public class River implements Serializable {

    private String rName;
    private String code;
    private int sites;
    private ArrayList<String> siteNames;
    private int maxEntries;
    private List<Boolean> tempBool;
    private List<Boolean> discBool;
    private List<Boolean> heightBool;


    public void setrName(String name){

        rName = name;
        return;
    }

    public String getrName(){

        return(rName);
    }

    public String getCode(){

        return code;
    }

    public void setCode(String s){

        code = s;
    }
    public void setSites(int x){
        sites = x;
    }
    public int getSites(){
        return sites;
    }

    public void setSiteNames(List<String> x){

        siteNames = new ArrayList<String>();
        for(int i = 0; i < x.size(); i++){

            siteNames.add(x.get(i));
        }
    }
    public ArrayList<String> getSiteNames(){

        return siteNames;

    }
    public void setMaxEntries(int x){
        maxEntries = x;
    }
    public int getMaxEntries(){

        return maxEntries;
    }

    public Boolean checkTemp(int i){

        if(!tempBool.get(i))
            return false;
        else return true;
    }

    public Boolean checkHeight(int i){
            if(!heightBool.get(i))
                return false;
                else return true;

    }
    public Boolean checkDisc(int i){

            if (!discBool.get(i))
                return false;
            else return true;
    }


    public void setTempBool(){

        tempBool = new ArrayList<Boolean>();

        switch(rName){

            case "Buffalo National River":
                tempBool.add(true);
                tempBool.add(true);
                tempBool.add(true);
                tempBool.add(false);
                tempBool.add(false);
                break;
            case "Big Piney River":
                tempBool.add(false);
                tempBool.add(false);
                break;
            case "Cadron River":
                tempBool.add(false);
                tempBool.add(false);
                tempBool.add(true);
                tempBool.add(true);
                break;
            case "Ouachita River":
                tempBool.add(false);
                tempBool.add(false);
                tempBool.add(false);
                tempBool.add(false);
                break;
            case "Spring River":
                for(int i = 0; i < 4; i++){
                    tempBool.add(false);
                }
                break;
            case "White River":
                tempBool.add(true);
                tempBool.add(false);
                tempBool.add(false);
                break;
            default:
                tempBool.add(false);
                break;
        }
    }

    public void setHeightBool(){
        heightBool = new ArrayList<Boolean>();

        switch(rName) {
            case "Spring River":
                for(int i = 0; i < 5; i++){
                    heightBool.add(true);
                }
                break;
            case "Buffalo National River":
                for(int i = 0; i < 5; i++){
                    heightBool.add(true);
                }
            case "Big Piney River":
                for(int i = 0; i < 2; i++){
                    heightBool.add(true);
                }
                break;
            case "Cadron River":
                for(int i = 0;i < 4; i++){
                    heightBool.add(true);
                }
                break;
            case "Ouachita River":
                heightBool.add(false);
                heightBool.add(true);
                heightBool.add(false);
                heightBool.add(true);
                break;
            case "White River":
                for(int i = 0; i < 3; i++){
                    heightBool.add(true);
                }
        }
    }
    public void setDiscBool(){

        discBool = new ArrayList<Boolean>();

        switch(rName){

            case "Ouachita River":
                discBool.add(true);
                discBool.add(true);
                discBool.add(true);
                discBool.add(false);
                break;
            case "Spring River":
                discBool.add(true);
                discBool.add(true);
                discBool.add(false);
                discBool.add(true);
                discBool.add(true);
                break;
            case "White River":
                discBool.add(true);
                discBool.add(true);
                discBool.add(false);
                break;
            case "Cadron River":
                for(int i = 0; i < 4; i++){
                    discBool.add(true);
                }
                break;
            case "Big Piney River":
                for(int i = 0; i < 2; i++){
                    discBool.add(true);
                }
                break;
            case "Buffalo National River":
                for(int i = 0; i < 5; i++){
                    discBool.add(true);
                }
                break;
            default:
                for(int i = 0; i < 5;i++){
                    discBool.add(true);
                }

        }
    }
}
