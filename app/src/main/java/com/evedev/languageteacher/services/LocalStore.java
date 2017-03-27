package com.evedev.languageteacher.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.evedev.languageteacher.R;

import java.util.Set;

/**
 * Service for work with SharedPreferences.
 * Saves and loads fields from local store.
 *
 * @author Anastasia.
 * @since 3/27/17.
 */
public class LocalStore {

    private SharedPreferences sharedPreferences;
    private Context context;

    public LocalStore(Context context) {
        this.context = context;
        String preferencesName = context.getString(R.string.preferences_settings_file);
        sharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }

    public void saveIsRegistered(boolean isRegistered){
        String isRegisteredKey = context.getString(R.string.is_registered_key);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean(isRegisteredKey, isRegistered);
        sharedPreferencesEditor.apply();
    }

    public boolean loadIsRegistered(){
        String isRegisteredKey = context.getString(R.string.is_registered_key);
        boolean isRegisteredValue = Boolean.parseBoolean(context.getString(R.string.is_registered_value));
        return sharedPreferences.getBoolean(isRegisteredKey, isRegisteredValue);
    }

    public void saveName(String name) {
        String nameKey = context.getString(R.string.name_key);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(nameKey, name);
        sharedPreferencesEditor.apply();
    }

    public String loadName() {
        String namePreferencesKey = context.getString(R.string.name_key);
        String nameValue = context.getString(R.string.name_value);
        return sharedPreferences.getString(namePreferencesKey, nameValue);
    }

    public void saveMotivation(String motivation) {
        String motivationKey = context.getString(R.string.motivation_key);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(motivationKey, motivation);
        sharedPreferencesEditor.apply();
    }

    public String loadMotivation() {
        String motivationKey = context.getString(R.string.motivation_key);
        String motivationValue = context.getString(R.string.motivation_value);
        return sharedPreferences.getString(motivationKey, motivationValue);
    }

    public void saveImages(Set<String> set) {
        String imagesKey = context.getString(R.string.images_key);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putStringSet(imagesKey, set);
        sharedPreferencesEditor.apply();
    }

    @Nullable
    public Set<String> loadImages() {
        String imagesKey = context.getString(R.string.images_key);
        return sharedPreferences.getStringSet(imagesKey, null);
    }
}