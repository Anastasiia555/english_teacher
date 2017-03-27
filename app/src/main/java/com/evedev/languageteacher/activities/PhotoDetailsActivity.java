package com.evedev.languageteacher.activities;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.evedev.languageteacher.R;

import java.io.IOException;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 2/28/17.
 */
public class PhotoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        Uri imageUri = getIntent().getParcelableExtra("imageUri");
        ImageView imageView = (ImageView) findViewById(R.id.photo);

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
