package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * Last activity of registration sequence. Contains information for reference.
 *
 * @author Anastasia.
 * @since 2/16/17.
 */
public class AboutActivity extends AppCompatActivity {

    // services
    private LocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // init services
        localStore = new LocalStore(this);

        // button's listeners
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localStore.saveIsRegistered(true);

                Intent motivationIntent = new Intent(AboutActivity.this, ProgressActivity.class);
                startActivity(motivationIntent);
            }
        });
    }
}
