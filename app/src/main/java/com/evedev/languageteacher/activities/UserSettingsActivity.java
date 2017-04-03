package com.evedev.languageteacher.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * Activity for changing saved user's data.
 *
 * @author Anastasia.
 * @since 3/26/17.
 */
public class UserSettingsActivity extends AppCompatActivity {

    private static final String TAG = "UserSettingsActivity";

    // services
    private LocalStore localStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        // init services
        localStore = new LocalStore(this);

        // init tabs.
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpecMe = tabHost.newTabSpec("me");
        tabSpecMe.setContent(R.id.name_fragment);
        tabSpecMe.setIndicator("Me");
        tabHost.addTab(tabSpecMe);

        TabHost.TabSpec tabSpecMotivation = tabHost.newTabSpec("motivation");
        tabSpecMotivation.setContent(R.id.motivation_fragment);
        tabSpecMotivation.setIndicator("Motivation");
        tabHost.addTab(tabSpecMotivation);

        TabHost.TabSpec tabSpecPictures = tabHost.newTabSpec("pictures");
        tabSpecPictures.setContent(R.id.images_fragment);
        tabSpecPictures.setIndicator("Pictures");
        tabHost.addTab(tabSpecPictures);

        tabHost.setCurrentTab(0);

        // init view's elements.
        final EditText nameText = (EditText) findViewById(R.id.name_edit_text);
        final EditText reasonText = (EditText) findViewById(R.id.reason_edit_text);

        // button listeners
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String reason = reasonText.getText().toString();

                if (!name.equals("")) {
                    localStore.saveName(name);
                    Log.d(TAG, "saved name == > " + localStore.loadName());
                }

                if (!reason.equals("")) {
                    localStore.saveMotivation(reason);
                    Log.d(TAG, "saved motivation == > " + localStore.loadMotivation());
                }

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}
