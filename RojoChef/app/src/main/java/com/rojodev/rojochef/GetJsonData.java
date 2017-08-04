package com.rojodev.rojochef;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

class GetJsonData extends AsyncTask<String, Void, List<Recipe>> implements DownloadData.OnDownloadComplete {
    private static final String TAG = "GetJsonData";

    private List<Recipe> recipeList = null;
    private String baseURL;

    private final OnDataAvailable callBack;
    private boolean runningOnSameThread = false;

    interface OnDataAvailable {
        void onDataAvailable(List<Recipe> data, DownloadStatus status);
    }

    public GetJsonData(OnDataAvailable callBack, String baseURL) {
        Log.d(TAG, "GetJsonData: called");
        this.callBack = callBack;
        this.baseURL = baseURL;
    }

    @Override
    protected void onPostExecute(List<Recipe> recipes) {
        Log.d(TAG, "onPostExecute: starts");

        if (callBack != null) {
            callBack.onDataAvailable(recipeList, DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread: starts");
        runningOnSameThread = true;
        String destinationUri = createUri(searchCriteria);
        DownloadData downloadData = new DownloadData(this);
        downloadData.execute(destinationUri);
        Log.d(TAG, "executeOnSameThread: ends");
    }
    
    private String createUri(String searchCriteria) {
        Log.d(TAG, "createUri: starts");

        if (!searchCriteria.isEmpty()) {
            return Uri.parse(baseURL).buildUpon()
                    .appendQueryParameter("id", searchCriteria)
                    .build().toString();
        } else {
            return Uri.parse(baseURL).buildUpon().build().toString();
        }
    }

    @Override
    protected List<Recipe> doInBackground(String... params) {
        Log.d(TAG, "doInBackground: starts");
        DownloadData downloadData = new DownloadData(this);
        downloadData.runInSameThread(params[0]);
        Log.d(TAG, "doInBackground: ends");
        return recipeList;
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete starts. Status = " + status);

        if (status == DownloadStatus.OK) {
            recipeList = new ArrayList<>();

            Gson gson = new Gson();
            Recipe[] recipes = gson.fromJson(data, Recipe[].class);

            for (Recipe recipe : recipes) {
                recipeList.add(recipe);
                Log.d(TAG, "onDownloadComplete: " + recipe.toString());
            }
        }

        if (runningOnSameThread && callBack != null) {
            //inform the caller that processing is done - possibly returning null if there was an error
            Log.d(TAG, "onDownloadComplete: runningOnSameThread and calling back");
            callBack.onDataAvailable(recipeList, status);
        }

        Log.d(TAG, "onDownloadComplete: ends");
    }
}
