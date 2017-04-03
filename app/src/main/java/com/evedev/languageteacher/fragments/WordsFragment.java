package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.evedev.languageteacher.R;

/**
 * Words Fragment controller.
 *
 * @author Anastasia.
 * @since 4/3/17.
 */
public class WordsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words, container, true);

        // init view's elements
        final NumberPicker wordsPerDayPicker = (NumberPicker) view.findViewById(R.id.words_per_day_picker);
        wordsPerDayPicker.setMaxValue(200);
        wordsPerDayPicker.setMinValue(0);

        return view;
    }
}
