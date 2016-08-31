package com.monsterlin.gank.bean;

import java.util.ArrayList;

/**
 * Created by monsterLin on 7/15/2016.
 */
public class Data {
    private String error ;
    private ArrayList<Results> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
