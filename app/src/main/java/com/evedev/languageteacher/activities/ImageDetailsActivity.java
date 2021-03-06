package com.evedev.languageteacher.activities;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

import java.io.IOException;
import java.util.Set;

/**
 * Activity that displays chosen Image in Image Activity.
 * Also removes image from local store.
 *
 * @author Anastasia.
 * @since 2/28/17.
 */
public class ImageDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ImageDetailsActivity";

    // services
    private LocalStore localStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        // init services
        localStore = new LocalStore(this);

        final Uri imageUri = getIntent().getParcelableExtra("imageUri");
        ImageView imageView = (ImageView) findViewById(R.id.photo);

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            Set<String> imageUrls = localStore.loadImages();
            imageUrls.remove(imageUri.toString());
            localStore.saveImages(imageUrls);
        }

        // button's listeners
        Button backBack = (Button) findViewById(R.id.back_button);
        backBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> uris = localStore.loadImages();
                uris.remove(imageUri.toString());
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
