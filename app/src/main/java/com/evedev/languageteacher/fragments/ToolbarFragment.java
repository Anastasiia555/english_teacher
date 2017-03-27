package com.evedev.languageteacher.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.activities.UserActivity;
import com.evedev.languageteacher.activities.SettingsActivity;
import com.evedev.languageteacher.activities.MainActivity;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 3/26/17.
 */

public class ToolbarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_toolbar, container, true);

        Toolbar mActionBarToolbar = (Toolbar) view.findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_study:
                        Intent mainIntent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(mainIntent);
                        return true;
                    case R.id.action_learning:
                        Intent learningIntent = new Intent(view.getContext(), UserActivity.class);
                        startActivity(learningIntent);
                        return true;
                    case R.id.action_user:
                        Intent settingsIntent = new Intent(view.getContext(), SettingsActivity.class);
                        startActivity(settingsIntent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        return view;
    }
}
