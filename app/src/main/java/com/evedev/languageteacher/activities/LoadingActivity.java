package com.evedev.languageteacher.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.evedev.languageteacher.R;

/**
 * @since 3/27/17.
 */
public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "MotivationActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);

        // check registration
        String isRegisteredKey = getString(R.string.is_registered_key);
        String preferencesName = getString(R.string.preferences_settings_file);
        boolean isRegisteredValue = Boolean.parseBoolean(getString(R.string.is_registered_value));
        SharedPreferences sharedPreferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        final boolean isRegistered = sharedPreferences.getBoolean(isRegisteredKey, isRegisteredValue);
        Log.d(TAG, "isRegistered ==> " + isRegistered);

        new Handler(new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                // start next activity
                if (isRegistered) {
                    Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                } else {
                    Intent nameIntent = new Intent(LoadingActivity.this, NameActivity.class);
                    startActivity(nameIntent);
                }
                return false;
            }
        }).sendEmptyMessageDelayed(1, 3000);
    }
}
