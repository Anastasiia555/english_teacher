package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.evedev.languageteacher.R;

/**
 * Controller for back button on bottom side of display.
 *
 * @author Anastasia.
 * @since 3/28/17.
 */
public class BottomDirectionFragment extends Fragment {

    public static final String TAG = "BottomDirectionFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "start onCreateView");

        View view = inflater.inflate(R.layout.fragment_bottom_direction, container, true);
        Button previousButton = (Button) view.findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }
}
