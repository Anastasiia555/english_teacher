package com.evedev.languageteacher.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.activities.PhotoDetailsActivity;
import com.evedev.languageteacher.adapters.ImageGridViewAdapter;
import com.evedev.languageteacher.models.GridButtonItem;
import com.evedev.languageteacher.models.GridImageItem;
import com.evedev.languageteacher.models.GridItem;
import com.evedev.languageteacher.services.LocalStore;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * @author Anastasia.
 * @since 3/28/17.
 */
public class ImageFragment extends Fragment {
    private static final String SAVED_ITEMS_KEY = "saved_items_key";

    // services
    private LocalStore localStore;

    private ArrayList<GridItem> gridItems;
    private GridView imageGridView;
    private ImageGridViewAdapter gridAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("asd", "ImageFragment");
        View view = inflater.inflate(R.layout.fragment_images, container, true);

        // init services
        localStore = new LocalStore(getActivity());

        // init view's elements
        gridItems = new ArrayList<>();
        if (savedInstanceState != null) {
            ArrayList<String> items = savedInstanceState.getStringArrayList(SAVED_ITEMS_KEY);
            gridItems = new ArrayList<>(items.size() + 1);
            for (String item : items) {
                gridItems.add(new GridImageItem(Uri.parse(item)));
            }
        }
        gridItems.add(new GridButtonItem());
        imageGridView = (GridView) view.findViewById(R.id.image_grid_view);
        gridAdapter = new ImageGridViewAdapter(getActivity(), R.layout.item_photo_grid, gridItems);
        imageGridView.setAdapter(gridAdapter);

        // get name
        String name = localStore.loadName();
        TextView addImageText = (TextView) view.findViewById(R.id.add_image_text);
        addImageText.setText(name + ", add pictures which you associate with learning English");

        // button's listeners
        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                GridItem gridItem = (GridItem) parent.getItemAtPosition(position);

                if (gridItem instanceof GridImageItem) {
                    Uri imageUri = gridItem.getImageUri();

                    // сreate intent
                    Intent intent = new Intent(getActivity(), PhotoDetailsActivity.class);
                    intent.putExtra("imageUri", imageUri);

                    // start details activity
                    startActivity(intent);
                }

            }
        });

        return view;
    }

    public ArrayList<GridItem> getGridItems() {
        return gridItems;
    }

    /**
     * For running image chooser and getting result.
     *
     * @param requestCode         - identificator for know who return result.
     * @param resultCode          - result code of operation (success or fail).
     * @param imageReturnedIntent - data of operation.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Log.d("asdasd", "asdasd");
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    gridItems.add(0, new GridImageItem(selectedImage));
                    imageGridView.setAdapter(gridAdapter);
                }
        }
    }

    /**
     * For saving images after screen rotation.
     *
     * @param outState - bundle for saving data.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> items = new ArrayList<>(gridItems.size() - 1);
        for (GridItem gridItem : gridItems) {
            if (!(gridItem instanceof GridButtonItem)) {
                items.add(gridItem.getImageUri().toString());
            }
        }
        outState.putStringArrayList(SAVED_ITEMS_KEY, items);
    }
}
