package com.evedev.languageteacher.models;

import android.net.Uri;

/**
 * @author Alexander Eveler, alexander.eveler@gmail.com
 * @since 3/2/17.
 */

public class GridImageItem implements GridItem {

    private Uri imageUri;

    public GridImageItem() {
    }

    public GridImageItem(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return "GridImageItem{" +
                "imageUri=" + imageUri +
                '}';
    }
}
