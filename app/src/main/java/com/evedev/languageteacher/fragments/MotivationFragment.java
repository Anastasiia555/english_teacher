package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

/**
 * Controller for Motivation Fragment.
 *
 * @author Anastasia.
 * @since 3/30/17.
 */
public class MotivationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motivation, container, true);

        // init services
        LocalStore localStore = new LocalStore(getActivity());

        // get name
        String name = localStore.loadName();

        TextView motivationText = (TextView) view.findViewById(R.id.motivation_text);
        motivationText.setText(name + ", what is motivate you to do it?");

        return view;
    }
}
