package com.evedev.languageteacher.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.evedev.languageteacher.R;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 3/26/17.
 */

public class UserActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpecMe = tabHost.newTabSpec("me");
        tabSpecMe.setContent(R.id.name);
        tabSpecMe.setIndicator("Me");
        tabHost.addTab(tabSpecMe);

        TabHost.TabSpec tabSpecMotivation = tabHost.newTabSpec("motivation");
        tabSpecMotivation.setContent(R.id.motivation);
        tabSpecMotivation.setIndicator("Motivation");
        tabHost.addTab(tabSpecMotivation);

        TabHost.TabSpec tabSpecPictures = tabHost.newTabSpec("pictures");
        tabSpecPictures.setContent(R.id.images);
        tabSpecPictures.setIndicator("Pictures");
        tabHost.addTab(tabSpecPictures);
        
        tabHost.setCurrentTab(0);
    }
}
