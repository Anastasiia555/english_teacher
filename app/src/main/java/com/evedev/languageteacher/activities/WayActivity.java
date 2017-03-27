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
import android.widget.TabHost;

import com.evedev.languageteacher.R;

/**
 * #5
 *
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/16/17.
 */
public class WayActivity extends AppCompatActivity {

    public static final String TAG = "WayActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way);

        // button's listeners
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabHost tabHost = (TabHost) findViewById(R.id.tabhost);

                //check tab
                int currentTab = tabHost.getCurrentTab();
                Log.d(TAG, "current tab ==> " + String.valueOf(currentTab));
                Log.d(TAG, "current tab tag ==> " + tabHost.getCurrentTabTag());

                String preferencesName = getString(R.string.preferences_settings_file);
                SharedPreferences sharedPreferences = getSharedPreferences(
                        preferencesName,
                        Context.MODE_PRIVATE
                );
                SharedPreferences.Editor sharedPreferencesEditor;
                String wayToLearnKey = getString(R.string.way_to_learn_key);
                int wayToLearnValue = Integer.parseInt(getString(R.string.way_to_learn_value));
                switch (currentTab) {
                    // by time
                    case 0:
                        // words per hour
                        NumberPicker wordsPerHourPicker =
                                (NumberPicker) findViewById(R.id.words_per_hour_picker);
                        int wordsPerHour = wordsPerHourPicker.getValue();

                        // get up time
                        NumberPicker getUpHourPicker =
                                (NumberPicker) findViewById(R.id.get_up_hour_picker);
                        int getUpHour = getUpHourPicker.getValue();
                        NumberPicker getUpMinutePicker =
                                (NumberPicker) findViewById(R.id.get_up_minute_picker);
                        int getUpMinute = getUpMinutePicker.getValue();

                        // go bad time
                        NumberPicker goBedHourPicker =
                                (NumberPicker) findViewById(R.id.go_bed_hour_picker);
                        int goBedHour = goBedHourPicker.getValue();
                        NumberPicker goBedMinutePicker =
                                (NumberPicker) findViewById(R.id.go_bed_minute_picker);
                        int goBedMinute = goBedMinutePicker.getValue();

                        // saving data // calculate me
                        sharedPreferencesEditor = sharedPreferences.edit();
                        String wordsPerHourKay = getString(R.string.words_per_hour_key);
                        int wordsPerHourValue = Integer.parseInt(getString(R.string.words_per_hour_value));
                        sharedPreferencesEditor.putInt(wordsPerHourKay, wordsPerHour);

                        String getUpHourKey = getString(R.string.get_up_hour_key);
                        int getUpHourValue = Integer.parseInt(getString(R.string.get_up_hour_value));
                        sharedPreferencesEditor.putInt(getUpHourKey, getUpHour);

                        String getUpMinuteKey = getString(R.string.get_up_minute_key);
                        int getUpMinuteValue = Integer.parseInt(getString(R.string.get_up_minute_value));
                        sharedPreferencesEditor.putInt(getUpMinuteKey, getUpMinute);

                        String goBedHourKey = getString(R.string.go_bed_hour_key);
                        int goBedHourValue = Integer.parseInt(getString(R.string.go_bed_hour_value));
                        sharedPreferencesEditor.putInt(goBedHourKey, goBedHour);

                        String goBedMinuteKey = getString(R.string.go_bed_minute_key);
                        int goBedMinuteValue = Integer.parseInt(getString(R.string.go_bed_minute_value));
                        sharedPreferencesEditor.putInt(goBedMinuteKey, goBedMinute);
                        sharedPreferencesEditor.putInt(wayToLearnKey, 0);
                        sharedPreferencesEditor.apply();

                        // check saving
                        int savedWordsPerHour = sharedPreferences.getInt(wordsPerHourKay, wordsPerHourValue);
                        Log.d(TAG, "words per hour saved ==> " + savedWordsPerHour);
                        int savedGetUpHour = sharedPreferences.getInt(getUpHourKey, getUpHourValue);
                        Log.d(TAG, "get up hour saved ==> " + savedGetUpHour);
                        int savedGetUpMinute = sharedPreferences.getInt(getUpMinuteKey, getUpMinuteValue);
                        Log.d(TAG, "get up Minute saved ==> " + savedGetUpMinute);
                        int savedGoBedHour = sharedPreferences.getInt(goBedHourKey, goBedHourValue);
                        Log.d(TAG, "go bed hour saved ==> " + savedGoBedHour);
                        int savedGoBedMinute = sharedPreferences.getInt(goBedMinuteKey, goBedMinuteValue);
                        Log.d(TAG, "go bed minute saved ==> " + savedGoBedMinute);
                        break;
                    // by visit
                    case 1:
                        // words per visit
                        NumberPicker wordsPerVisitPicker =
                                (NumberPicker) findViewById(R.id.words_per_visit_picker);
                        int wordsPerVisit = wordsPerVisitPicker.getValue();

                        // saving data // calculate me
                        sharedPreferencesEditor = sharedPreferences.edit();
                        String wordsPerVisitKey = getString(R.string.words_per_visit_key);
                        int wordsPerVisitValue = Integer.parseInt(getString(R.string.words_per_visit_value));
                        sharedPreferencesEditor.putInt(wordsPerVisitKey, wordsPerVisit);
                        sharedPreferencesEditor.putInt(wayToLearnKey, 1);
                        sharedPreferencesEditor.apply();

                        // check saving
                        int savedWordsPerVisit = sharedPreferences.getInt(wordsPerVisitKey, wordsPerVisitValue);
                        Log.d(TAG, "words per visit saved ==> " + savedWordsPerVisit);
                        break;
                }

                int savedWordsPerVisit = sharedPreferences.getInt(wayToLearnKey, wayToLearnValue);
                Log.d(TAG, "way to learn saved ==> " + savedWordsPerVisit);

                Intent motivationIntent = new Intent(WayActivity.this, AboutActivity.class);
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
