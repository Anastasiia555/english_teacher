package com.evedev.languageteacher.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.evedev.languageteacher.R;
import com.evedev.languageteacher.models.way.Way;
import com.evedev.languageteacher.models.way.WayByTime;
import com.evedev.languageteacher.models.way.WayByVisit;

import java.util.HashSet;
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

    public void saveIsRegistered(boolean isRegistered) {
        String isRegisteredKey = context.getString(R.string.is_registered_key);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean(isRegisteredKey, isRegistered);
        sharedPreferencesEditor.apply();
    }

    public boolean loadIsRegistered() {
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

    public Set<String> loadImages() {
        String imagesKey = context.getString(R.string.images_key);
        return sharedPreferences.getStringSet(imagesKey, new HashSet<String>());
    }

    public void saveWordsPerDay(int wordsPerDay) {
        String wordsPerDayKey = context.getString(R.string.words_per_day_key);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt(wordsPerDayKey, wordsPerDay);
        sharedPreferencesEditor.apply();
    }

    public int loadWordsPerDay() {
        String wordsPerDayKey = context.getString(R.string.words_per_day_key);
        int wordsPerDayValue = Integer.parseInt(context.getString(R.string.words_per_day_value));
        return sharedPreferences.getInt(wordsPerDayKey, wordsPerDayValue);
    }

    public void saveWay(Way way) {
        String wayToLearnKey = context.getString(R.string.way_to_learn_key);

        if (way.getWayCode() == 0) {
            WayByTime wayByTime = (WayByTime) way;
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            String wordsPerHourKay = context.getString(R.string.words_per_hour_key);
            sharedPreferencesEditor.putInt(wordsPerHourKay, wayByTime.getWordsPerHour());

            String getUpHourKey = context.getString(R.string.get_up_hour_key);
            sharedPreferencesEditor.putInt(getUpHourKey, wayByTime.getGetUpHour());

            String getUpMinuteKey = context.getString(R.string.get_up_minute_key);
            sharedPreferencesEditor.putInt(getUpMinuteKey, wayByTime.getGetUpMinute());

            String goBedHourKey = context.getString(R.string.go_bed_hour_key);
            sharedPreferencesEditor.putInt(goBedHourKey, wayByTime.getGoBedHour());

            String goBedMinuteKey = context.getString(R.string.go_bed_minute_key);
            sharedPreferencesEditor.putInt(goBedMinuteKey, wayByTime.getGoBedMinute());
            sharedPreferencesEditor.putInt(wayToLearnKey, 0);
            sharedPreferencesEditor.apply();
        }

        if (way.getWayCode() == 1) {
            WayByVisit wayByVisit = (WayByVisit) way;
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            String wordsPerVisitKey = context.getString(R.string.words_per_visit_key);
            sharedPreferencesEditor.putInt(wordsPerVisitKey, wayByVisit.getWordsPerVisit());
            sharedPreferencesEditor.putInt(wayToLearnKey, 1);
            sharedPreferencesEditor.apply();
        }
    }

    public Way loadWay() {
        String wayToLearnKey = context.getString(R.string.way_to_learn_key);
        int wayToLearnValue = Integer.parseInt(context.getString(R.string.way_to_learn_value));
        int wayToLearn = sharedPreferences.getInt(wayToLearnKey, wayToLearnValue);

        if (wayToLearn == 0) {
            String wordsPerHourKay = context.getString(R.string.words_per_hour_key);
            int wordsPerHourValue = Integer.parseInt(context.getString(R.string.words_per_hour_value));
            int wordsPerHour = sharedPreferences.getInt(wordsPerHourKay, wordsPerHourValue);

            String getUpHourKey = context.getString(R.string.get_up_hour_key);
            int getUpHourValue = Integer.parseInt(context.getString(R.string.get_up_hour_value));
            int getUpHour = sharedPreferences.getInt(getUpHourKey, getUpHourValue);

            String getUpMinuteKey = context.getString(R.string.get_up_minute_key);
            int getUpMinuteValue = Integer.parseInt(context.getString(R.string.get_up_minute_value));
            int getUpMinute = sharedPreferences.getInt(getUpMinuteKey, getUpMinuteValue);

            String goBedHourKey = context.getString(R.string.go_bed_hour_key);
            int goBedHourValue = Integer.parseInt(context.getString(R.string.go_bed_hour_value));
            int goBedHour = sharedPreferences.getInt(goBedHourKey, goBedHourValue);

            String goBedMinuteKey = context.getString(R.string.go_bed_minute_key);
            int goBedMinuteValue = Integer.parseInt(context.getString(R.string.go_bed_minute_value));
            int goBedMinute = sharedPreferences.getInt(goBedMinuteKey, goBedMinuteValue);

            return WayByTime.newBuilder()
                    .setWordsPerHour(wordsPerHour)
                    .setGetUpHour(getUpHour)
                    .setGetUpMinute(getUpMinute)
                    .setGoBedHour(goBedHour)
                    .setGoBedMinute(goBedMinute)
                    .build();
        }

        if (wayToLearn == 1) {
            String wordsPerVisitKey = context.getString(R.string.words_per_visit_key);
            int wordsPerVisitValue = Integer.parseInt(context.getString(R.string.words_per_visit_value));
            int wordsPerVisit = sharedPreferences.getInt(wordsPerVisitKey, wordsPerVisitValue);

            return new WayByVisit(wordsPerVisit);
        }

        return null;
    }
}