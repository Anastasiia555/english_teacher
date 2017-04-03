package com.evedev.languageteacher.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TabHost;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * Activity for changing learn process.
 *
 * @author Anastasia.
 * @since 3/26/17.
 */
public class LearningSettingsActivity extends AppCompatActivity {

    // services
    private LocalStore localStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_settings);

        // init services
        localStore = new LocalStore(this);

        // init tabs.
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpecWords = tabHost.newTabSpec("words");
        tabSpecWords.setContent(R.id.words_fragment);
        tabSpecWords.setIndicator("Words");
        tabHost.addTab(tabSpecWords);

        TabHost.TabSpec tabSpecWay = tabHost.newTabSpec("way");
        tabSpecWay.setContent(R.id.way_fragment);
        tabSpecWay.setIndicator("Way");
        tabHost.addTab(tabSpecWay);

        TabHost.TabSpec tabSpecAbout = tabHost.newTabSpec("about");
        tabSpecAbout.setContent(R.id.about_fragment);
        tabSpecAbout.setIndicator("About");
        tabHost.addTab(tabSpecAbout);

        tabHost.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}
