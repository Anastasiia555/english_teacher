package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * Fourth step of registration. User sets number of words
 * that he wants to learn.
 *
 * @author Anastasia.
 * @since 2/16/17.
 */
public class WordsActivity extends AppCompatActivity {

    private static final String TAG = "WordsActivity";

    // services
    private LocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // init view's elements
        final NumberPicker wordsPerDayPicker = (NumberPicker) findViewById(R.id.words_per_day_picker);

        // init services
        localStore = new LocalStore(this);

        // button's listeners
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save data
                int wordsPerDay = wordsPerDayPicker.getValue();
                localStore.saveWordsPerDay(wordsPerDay);

                // check saving
                Log.d(TAG, "words saved ==> " + localStore.loadWordsPerDay());

                // go to next activity
                Intent motivationIntent = new Intent(WordsActivity.this, WayActivity.class);
                startActivity(motivationIntent);
            }
        });
    }
}