package com.rojodev.rojochef;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum DownloadStatus {IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK}

public class DownloadData extends AsyncTask<String, Void, String> {
    private static final String TAG = "DownloadData";

    private DownloadStatus downloadStatus;
    private final OnDownloadComplete callback;

    interface OnDownloadComplete {
        void onDownloadComplete(String data, DownloadStatus status);
    }

    public DownloadData(OnDownloadComplete callback) {
        this.downloadStatus = downloadStatus.IDLE;
        this.callback = callback;
    }

    void runInSameThread(String s) {
        Log.d(TAG, "runInSameThread: starts");
        if (callback != null) {
            callback.onDownloadComplete(doInBackground(s), downloadStatus);
        }

        Log.d(TAG, "runInSameThread: ends");
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter = " + s);
        if (callback != null) {
            callback.onDownloadComplete(s, downloadStatus);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if (params == null) {
            downloadStatus = downloadStatus.NOT_INITIALISED;
            return null;
        }

        try {
            downloadStatus = downloadStatus.PROCESSING;
//          URL url = new URL(params[0]); //disable for demo
            URL url = new URL("http://10.241.5.181:8080/recipes");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d(TAG, "doInBackground: Response code was " + response);

            StringBuilder result = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            for (String line = reader.readLine(); line != null; line = reader.readLine()) { //restrict scope of variable
                result.append(line).append("\n"); // \n are stripped off, so add back
            }

            downloadStatus = downloadStatus.OK;
            return result.toString();


        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground: Invalid URL " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: IO Exception reading data: " + e.getMessage());
        } catch (SecurityException e) {
            Log.e(TAG, "doInBackground: Security Exception. Needs permission? " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: Error closing stream " + e.getMessage());
                }
            }
        }

        downloadStatus = downloadStatus.FAILED_OR_EMPTY;

        return null;
    }
}
