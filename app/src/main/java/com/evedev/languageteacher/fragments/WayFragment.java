package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TabHost;

import com.evedev.languageteacher.R;

/**
 * Controller for Way Fragment.
 *
 * @author Anastasia.
 * @since 3/25/17.
 */
public class WayFragment extends Fragment {

    public static final String TAG = "WayFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "start onCreateView");
        View view = inflater.inflate(R.layout.fragment_way, container, true);

        // TODO: 4/3/17 calculate max word
        TabHost tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpecByTime = tabHost.newTabSpec("byTime");
        tabSpecByTime.setContent(R.id.words_by_time_tab);
        tabSpecByTime.setIndicator("by time");
        tabHost.addTab(tabSpecByTime);

        TabHost.TabSpec tabSpecByWords = tabHost.newTabSpec("byWords");
        tabSpecByWords.setContent(R.id.words_per_visit_tab);
        tabSpecByWords.setIndicator("by words");
        tabHost.addTab(tabSpecByWords);

        tabHost.setCurrentTab(0);

        // init Tab Host "by Time"
        NumberPicker wordsPerHourPicker = (NumberPicker) view.findViewById(R.id.words_per_hour_picker);
        wordsPerHourPicker.setMaxValue(60);
        wordsPerHourPicker.setMinValue(0);

        NumberPicker getUpHourPicker = (NumberPicker) view.findViewById(R.id.get_up_hour_picker);
        getUpHourPicker.setMaxValue(24);
        getUpHourPicker.setMinValue(0);

        NumberPicker getUpMinutePicker = (NumberPicker) view.findViewById(R.id.get_up_minute_picker);
        getUpMinutePicker.setMaxValue(60);
        getUpMinutePicker.setMinValue(0);

        NumberPicker goBedHourPicker = (NumberPicker) view.findViewById(R.id.go_bed_hour_picker);
        goBedHourPicker.setMaxValue(24);
        goBedHourPicker.setMinValue(0);

        NumberPicker goBedMinutePicker = (NumberPicker) view.findViewById(R.id.go_bed_minute_picker);
        goBedMinutePicker.setMaxValue(60);
        goBedMinutePicker.setMinValue(0);

        // init Tab Host "per visit"
        NumberPicker wordsPerVisitPicker = (NumberPicker) view.findViewById(R.id.words_per_visit_picker);
        wordsPerVisitPicker.setMaxValue(100);
        wordsPerVisitPicker.setMinValue(0);

        return view;
    }
}
