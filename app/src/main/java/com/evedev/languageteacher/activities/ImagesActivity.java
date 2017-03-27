package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.fragments.ImageFragment;
import com.evedev.languageteacher.models.GridButtonItem;
import com.evedev.languageteacher.models.GridImageItem;
import com.evedev.languageteacher.models.GridItem;
import com.evedev.languageteacher.services.LocalStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Third part of registration chain.
 * In this activity user adds images for motivation.
 *
 * @author Anastasia.
 * @since 2/16/17.
 */
public class ImagesActivity extends AppCompatActivity {

    private static final String TAG = "ImagesActivity";

    // services
    private LocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        Log.w(TAG, "If the Activity holds many images she may crash!!!");

        // init services
        localStore = new LocalStore(this);

        final ImageFragment imageFragment = (ImageFragment) getFragmentManager().findFragmentById(R.id.image_fragment);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<GridItem> gridItems = imageFragment.getGridItems();
                Set<String> set = new HashSet<>();
                for (int i = 0; i < gridItems.size(); i++) {
                    if (gridItems.get(i) instanceof GridImageItem) {
                        set.add(gridItems.get(i).toString());
                    }
                }

                if (set.size() > 0) {
                    // save data
                    localStore.saveImages(set);

                    // check saving
                    Set<String> savedMotivation = localStore.loadImages();
                    Log.d(TAG, "Images saved ==> " + savedMotivation);

                    // start next activity
                    Intent motivationIntent = new Intent(ImagesActivity.this, WordsActivity.class);
                    startActivity(motivationIntent);
                } else {
                    Toast.makeText(ImagesActivity.this, "Add image for motivation", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button previousButton = (Button) findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}