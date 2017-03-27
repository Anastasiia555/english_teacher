package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.adapters.PhotoGridViewAdapter;
import com.evedev.languageteacher.models.GridButtonItem;
import com.evedev.languageteacher.models.GridImageItem;
import com.evedev.languageteacher.models.GridItem;
import com.evedev.languageteacher.services.LocalStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * #3
 * @since 2/16/17.
 */
public class ImagesActivity extends AppCompatActivity {

    private static final String TAG = "ImagesActivity";

    private LocalStore localStore;

    private ArrayList<GridItem> gridItems;
    private GridView photoGridView;
    private PhotoGridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        // init services
        localStore = new LocalStore(this);

        // init view's elements
        gridItems = new ArrayList<>();
        gridItems.add(new GridButtonItem());

        photoGridView = (GridView) findViewById(R.id.photo_grid_view);
        gridAdapter = new PhotoGridViewAdapter(this, R.layout.item_photo_grid, gridItems);
        photoGridView.setAdapter(gridAdapter);

        // get name
        String name = localStore.loadName();
        TextView addImageText = (TextView) findViewById(R.id.add_image_text);
        addImageText.setText(name + ", add pictures which you associate with learning English");

        // button's listeners
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                GridItem gridItem = (GridItem) parent.getItemAtPosition(position);

                if (gridItem instanceof GridImageItem) {
                    Uri imageUri = gridItem.getImageUri();

                    //сreate intent
                    Intent intent = new Intent(ImagesActivity.this, PhotoDetailsActivity.class);
                    intent.putExtra("imageUri", imageUri);

                    //start details activity
                    startActivity(intent);
                }

            }
        });

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> set = new HashSet<>();
                for (int i = 1; i < gridItems.size(); i++) {
                    set.add(gridItems.get(i).toString());
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
                    Toast.makeText(ImagesActivity.this, "add image for motivation", Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    gridItems.add(new GridImageItem(selectedImage));
                    photoGridView.setAdapter(gridAdapter);
                }
        }
    }
}