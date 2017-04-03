package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TabHost;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.models.way.WayByTime;
import com.evedev.languageteacher.models.way.WayByVisit;
import com.evedev.languageteacher.services.LocalStore;

/**
 * Fifth part of registration process. Allow users to chose
 * one of learning way: by visit or by time.
 *
 * @author Anastasia.
 * @since 2/16/17.
 */
public class WayActivity extends AppCompatActivity {

    public static final String TAG = "WayActivity";

    // services
    private LocalStore localStore;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way);

        // init services
        localStore = new LocalStore(this);

        // button's listeners
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabHost tabHost = (TabHost) findViewById(R.id.tabhost);

                //check tab
                int currentTab = tabHost.getCurrentTab();
                Log.d(TAG, "current tab tag ==> " + tabHost.getCurrentTabTag());

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

                        // saving data
                        WayByTime wayByTime = WayByTime.newBuilder()
                                .setWordsPerHour(wordsPerHour)
                                .setGetUpHour(getUpHour)
                                .setGetUpMinute(getUpMinute)
                                .setGoBedHour(goBedHour)
                                .setGoBedMinute(goBedMinute)
                                .build();
                        localStore.saveWay(wayByTime);
                        break;
                    // by visit
                    case 1:
                        // words per visit
                        NumberPicker wordsPerVisitPicker =
                                (NumberPicker) findViewById(R.id.words_per_visit_picker);
                        int wordsPerVisit = wordsPerVisitPicker.getValue();

                        // saving data
                        WayByVisit wayByVisit = new WayByVisit(wordsPerVisit);
                        localStore.saveWay(wayByVisit);
                        break;
                }

                // check saving
                Log.d(TAG, "way saved ==> " + localStore.loadWay());

                Intent motivationIntent = new Intent(WayActivity.this, AboutActivity.class);
                startActivity(motivationIntent);
            }
        });
    }
}