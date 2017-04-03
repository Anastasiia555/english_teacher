package com.evedev.languageteacher.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.evedev.languageteacher.R;

/**
 * Activity that displays data of users progress.
 *
 * @author Anastasia.
 * @since 2/16/17.
 */
public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        // init view's elements
        BottomNavigationView bottomNavigation =
                (BottomNavigationView) findViewById(R.id.bottom_navigation);

        // button's listeners
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        return true;
                    case R.id.action_teach:
                        // do something here
                        return true;
                    case R.id.action_play:
                        // do something here
                        return true;
                    case R.id.action_user_settings:
                        // do something here
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}
