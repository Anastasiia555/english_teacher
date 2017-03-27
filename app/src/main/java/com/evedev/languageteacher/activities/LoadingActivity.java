package com.evedev.languageteacher.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.services.LocalStore;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Set;

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

    // services
    private LocalStore localStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // init services
        localStore = new LocalStore(this);

        // init view's elements
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);

        // check registration
        final boolean isRegistered = localStore.loadIsRegistered();
        Log.d(TAG, "is registered ==> " + isRegistered);

        // добавить затимнение/освитление картинки или сетку
        // init background and motivation if user have already registered
        if (isRegistered) {
            Set<String> images = localStore.loadImages();
            Random random = new Random(new Date().getTime());
            if (images != null) {
                int randomNumber = random.nextInt(images.size() - 1);
                String imageURI = (String) images.toArray()[randomNumber];
                try {
                    FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(imageURI));
                    Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                    frameLayout.setBackground(drawable);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e.getCause());
                }
            }
        }

        // run delayed task
        new Handler(new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                // start next activity
                if (isRegistered) {
                    Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
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