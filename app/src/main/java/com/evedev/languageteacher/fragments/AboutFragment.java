package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.evedev.languageteacher.R;

/**
 * Fragment controller for displaying info about how this app working.
 *
 * @author Anastasia.
 * @since 4/3/17.
 */
public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, true);

        // button listeners
        Button srsButton = (Button) view.findViewById(R.id.srs_button);
        srsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "srs clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Button gameButton = (Button) view.findViewById(R.id.game_button);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "game clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Button vocabularyButton = (Button) view.findViewById(R.id.vocabulary_button);
        vocabularyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "vocabulary clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Button optionsButton = (Button) view.findViewById(R.id.options_button);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "options clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
