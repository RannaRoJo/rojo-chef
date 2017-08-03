package com.rojodev.rojochef;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 * Created by roannakeough on 8/3/17.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    static final String RECIPE_QUERY = "RECIPE_QUERY";
    static final String RECIPE_TRANSFER = "RECIPE_TRANSFER";

    void activateToolbar(boolean enableHome) {
        Log.d(TAG, "activateToolbar: starts");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //inflate toolbar

            if(toolbar != null) {
                setSupportActionBar(toolbar); //put in place at top of screen
                actionBar = getSupportActionBar();
            }
        }

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enableHome); //enable or disable the home button
        }
    }
}
