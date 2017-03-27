package com.evedev.languageteacher.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.evedev.languageteacher.R;

/**
 * #4
 *
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/16/17.
 */
public class WordsActivity extends AppCompatActivity {

    private static final String TAG = "WordsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // init view's elements
        final NumberPicker wordsPerDayPicker = (NumberPicker) findViewById(R.id.words_per_day_picker);
        wordsPerDayPicker.setMaxValue(200);
        wordsPerDayPicker.setMinValue(0);

        // button's listeners
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save data
                int wordsPerDay = wordsPerDayPicker.getValue();
                String preferencesName = getString(R.string.preferences_settings_file);
                String wordsPerDayKey = getString(R.string.words_per_day_key);
                int wordsPerDayDefault = Integer.parseInt(getString(R.string.words_per_day_value));
                SharedPreferences sharedPreferences = WordsActivity.this.getSharedPreferences(
                        preferencesName,
                        Context.MODE_PRIVATE
                );
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.putInt(wordsPerDayKey, wordsPerDay);
                sharedPreferencesEditor.apply();

                // check saving
                int savedWordsPerDay = sharedPreferences.getInt(wordsPerDayKey, wordsPerDayDefault);
                Log.d(TAG, "number of words saved ==> " + savedWordsPerDay);

                // go to next activity
                Intent motivationIntent = new Intent(WordsActivity.this, WayActivity.class);
                startActivity(motivationIntent);
            }
        });

        Button previousButton = (Button) findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
