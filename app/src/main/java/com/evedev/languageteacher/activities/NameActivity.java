package com.evedev.languageteacher.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * #1
 * @since 2/16/17.
 */
public class NameActivity extends AppCompatActivity {

    private static final String TAG = "NameActivity";

    private LocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        // init services
        localStore = new LocalStore(this);

        // init view elements
        final EditText editText = (EditText) findViewById(R.id.name_edit_text);

        // button's listeners
        Button previousButton = (Button) findViewById(R.id.previous_button);
        previousButton.setVisibility(View.GONE);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString();
                Activity activity = NameActivity.this;

                if (!name.equals("")) {
                    // save data
                    localStore.saveName(name);

                    // check saving
                    String savedName = localStore.loadName();
                    Log.d(TAG, "name saved ==> " + savedName);

                    // start next activity
                    Intent motivationIntent = new Intent(NameActivity.this, MotivationActivity.class);
                    startActivity(motivationIntent);
                } else {
                    Toast.makeText(activity, "Fill the name!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}