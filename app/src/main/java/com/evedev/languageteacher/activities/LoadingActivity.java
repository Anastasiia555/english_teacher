package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jp.wasabeef.blurry.Blurry;

/**
 * Checks data in local store(SharedPreferences).
 * If all necessary registration data have already saved then opens Main Activity.
 * In other case opens Name Activity that first part of registration chain.
 * <p>
 * If necessary registration data have not already saved then just shows logo.
 * If data already saved displayed motivation and some of images on background
 * which user add during registration.
 *
 * @author Anastasia.
 * @since 3/27/17.
 */
public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "MotivationActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // init services
        LocalStore localStore = new LocalStore(this);

        // init view's elements
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);

        // init background
        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        Set<String> imageUris = localStore.loadImages();
        if (imageUris.size() > 0) {
            int rand = (int) (Math.random() * imageUris.size());
            List<String> uriList = new ArrayList<String>(imageUris);
            Uri imageUri = Uri.parse(uriList.get(rand));
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                Blurry.with(this)
                        .radius(3)
                        .sampling(5)
                        .color(Color.argb(100, 0, 0, 0))
                        .from(bitmap)
                        .into(backgroundImage);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e.getCause());
                Set<String> imageUrls = localStore.loadImages();
                imageUrls.remove(imageUri.toString());
                localStore.saveImages(imageUrls);
            }
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.germany);
            Blurry.with(this)
                    .radius(1)
                    .sampling(2)
                    .color(Color.argb(80, 0, 0, 0))
                    .from(bitmap)
                    .into(backgroundImage);
        }

        // check registration
        final boolean isRegistered = localStore.loadIsRegistered();
        Log.d(TAG, "is registered ==> " + isRegistered);

        // set motivation
        if (isRegistered) {
            ImageView logo = (ImageView) findViewById(R.id.logo_image);
            TextView motivationText = (TextView) findViewById(R.id.motivation_text);
            logo.setVisibility(View.GONE);
            motivationText.setVisibility(View.VISIBLE);
            motivationText.setText(localStore.loadMotivation());
        }

        // run delayed task
        new Handler(new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                // start next activity
                if (isRegistered) {
                    Intent mainIntent = new Intent(LoadingActivity.this, ProgressActivity.class);
                    startActivity(mainIntent);
                } else {
                    Intent nameIntent = new Intent(LoadingActivity.this, NameActivity.class);
                    startActivity(nameIntent);
                }
                return false;
            }
        }).sendEmptyMessageDelayed(0, 3000);
    }
}