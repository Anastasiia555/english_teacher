package com.evedev.languageteacher.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * #2
 *
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/16/17.
 */
public class MotivationActivity extends AppCompatActivity {

    private static final String TAG = "MotivationActivity";

    private LocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        // init services
        localStore = new LocalStore(this);

        // init view's elements
        final EditText reasonEditText = (EditText) findViewById(R.id.reason_edit_text);

        // get name
        String name = localStore.loadName();

        // init view's elements
        TextView helloText = (TextView) findViewById(R.id.hello_text);
        helloText.setText("Hello, " + name);

        TextView motivationText = (TextView) findViewById(R.id.motivation_text);
        motivationText.setText(name + ", what is motivate you to do it?");

        // button's listeners
        Button previousButton = (Button) findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = reasonEditText.getText().toString();
                Activity activity = MotivationActivity.this;

                if (!reason.equals("")) {
                    // save data
                    localStore.saveMotivation(reason);

                    // check saving
                    String savedMotivation = localStore.loadMotivation();
                    Log.d(TAG, "motivation saved ==> " + savedMotivation);

                    // start next activity
                    Intent motivationIntent = new Intent(MotivationActivity.this, ImagesActivity.class);
                    startActivity(motivationIntent);
                } else {
                    Toast.makeText(activity, "Fill the reason!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}