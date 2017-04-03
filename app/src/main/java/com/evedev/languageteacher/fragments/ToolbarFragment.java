package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.activities.UserSettingsActivity;
import com.evedev.languageteacher.activities.LearningSettingsActivity;
import com.evedev.languageteacher.activities.ProgressActivity;

/**
 * Toolbar controller.
 *
 * @author Anastasia.
 * @since 3/26/17.
 */
public class ToolbarFragment extends Fragment {
    public static final String TAG = "ToolbarFragment";

    private AppCompatActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "start onCreateView");

        final View view = inflater.inflate(R.layout.fragment_toolbar, container, true);

        // init view's elements
        activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_actionbar);
        activity.setSupportActionBar(toolbar);

        // button listeners
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_progress:
                        if (activity.getClass() != ProgressActivity.class) {
                            Intent mainIntent = new Intent(getActivity(), ProgressActivity.class);
                            startActivity(mainIntent);
                        }
                        return true;
                    case R.id.action_learning_way:
                        if (activity.getClass() != LearningSettingsActivity.class) {
                            Intent settingsIntent = new Intent(getActivity(), LearningSettingsActivity.class);
                            startActivity(settingsIntent);
                        }
                        return true;
                    case R.id.action_user_settings:
                        if (activity.getClass() != UserSettingsActivity.class) {
                            Intent learningIntent = new Intent(getActivity(), UserSettingsActivity.class);
                            startActivity(learningIntent);
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });

        return view;
    }
}
