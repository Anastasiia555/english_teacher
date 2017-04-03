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
import com.evedev.languageteacher.models.images.GridItem;

import java.util.ArrayList;

/**
 * Third part of registration chain.
 * In this activity user adds images for motivation.
 *
 * @author Anastasia.
 * @since 2/16/17.
 */
public class ImagesActivity extends AppCompatActivity {

    private static final String TAG = "ImagesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        Log.w(TAG, "If the this activity is holding many images she may crash!!!");

        final ImageFragment imageFragment =
                (ImageFragment) getFragmentManager().findFragmentById(R.id.images_fragment);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<GridItem> gridItems = imageFragment.getGridItems();

                if (gridItems.size() > 1) {
                    // start next activity
                    Intent motivationIntent = new Intent(ImagesActivity.this, WordsActivity.class);
                    startActivity(motivationIntent);
                } else {
                    Toast.makeText(ImagesActivity.this, "Add image for motivation", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}