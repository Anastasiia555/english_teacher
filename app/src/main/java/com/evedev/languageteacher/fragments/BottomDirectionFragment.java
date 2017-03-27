package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evedev.languageteacher.R;

/**
 * @author Anastasia.
 * @since 3/28/17.
 */
public class BottomDirectionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("asd", "BottomDirectionFragment");
        View view = inflater.inflate(R.layout.fragment_bottom_direction, container, true);

        return view;
    }
}
