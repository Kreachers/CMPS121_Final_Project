package justin.travis.devin.finalproject;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.NumberPicker;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static int minutesSelected;
    private static int hoursSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences prefs = this.getPreferences(MODE_PRIVATE);
        final AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Log.d("buildInfo", "Sdk Version: " + Build.VERSION.SDK_INT);
        Log.d("buildInfo", "Required sdk for DnD: " + Build.VERSION_CODES.M);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mNotificationManager != null && !mNotificationManager.isNotificationPolicyAccessGranted()) {
                Intent intent = new Intent(MainActivity.this, FirstRun.class);
                startActivity(intent);
            }
        }

//------[Power button]------------------------------------------------------------------------------
        android.widget.ImageButton power_image_button = findViewById(R.id.power_image_button);
        power_image_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("buttonClick", "Exit Button Clicked");
                finish();
            }
        });

//------[Initialize Variables]----------------------------------------------------------------------

//        List<Integer> hours = new ArrayList<>();
//        List<Integer> minutes = new ArrayList<>();
//
//        for (int i = 0; i < 24; i++) {
//            hours.add(i);
//            Log.v("spinner", i + " hours in spinner list");
//
//        }
//        for (int i = 0; i < 60; i++) {
//            minutes.add(i);
//            Log.v("buttonClick", i + " Minutes in spinner list");
//
//        }

//------[Spotify Button]----------------------------------------------------------------------------

//        android.widget.ImageButton launch_spotify = (ImageButton)findViewById(R.id.spotify_image_button);
//        launch_spotify.setOnClickListener(new View.OnClickListener() {

//        Button spotifyButton = findViewById(R.id.button_spotify);
        android.widget.ImageButton spotifyButton = findViewById(R.id.spotify_image_button);
        spotifyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("buttonClick", "Spotify Button Clicked");
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.spotify.music");
                if (launchIntent != null) {
                    Log.d("buttonClick", "Spotify Launched");
                    startActivity(launchIntent);//null pointer check in case package name was not found
                } else {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.spotify.music")));
                    } catch (android.content.ActivityNotFoundException e) { // if there is no Google Play on device
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.spotify.music")));
                    }
                }
            }
        });

////-----[Hours spinner stuff]------------------------------------------------------------------------
//
//        // Spinner element
//        Spinner hours_spinner_element = findViewById(R.id.spinner_hours);
//
//        // Spinner click listener
//        hours_spinner_element.setOnItemSelectedListener(this);
//
//        // Creating adapter for spinner
//        ArrayAdapter<Integer> hoursDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
//
//        // Drop down layout style - list view with radio button
//        hoursDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        hours_spinner_element.setAdapter(hoursDataAdapter);
//
////-----[Minutes spinner stuff]----------------------------------------------------------------------
//
//        // Spinner element
//        Spinner minutes_spinner_element = findViewById(R.id.spinner_minutes);
//
//        // Spinner click listener
//        minutes_spinner_element.setOnItemSelectedListener(this);
//
//        // Creating adapter for spinner
//        ArrayAdapter<Integer> minutesDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minutes);
//
//        // Drop down layout style - list view with radio button
//        minutesDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        minutes_spinner_element.setAdapter(minutesDataAdapter);

        Log.v("MainActivity", "spinners created");

//------[Hours Number Picker]-----------------------------------------------------------------------
        NumberPicker hours_picker = (NumberPicker) findViewById(R.id.picker_Hours);

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        hours_picker.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        hours_picker.setMaxValue(24);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        hours_picker.setWrapSelectorWheel(true);

        hoursSelected = 0;
        setDividerColor(hours_picker, getResources().getColor(R.color.colorChalk));

        //Set a value change listener for NumberPicker
        hours_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                hoursSelected = newVal;
                Log.d("picker", minutesSelected + " hours on Android SDK " + Build.VERSION.SDK_INT);
//                Toast.makeText(this, "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });


//------[Minutes Number Picker]---------------------------------------------------------------------
        NumberPicker minutes_picker = (NumberPicker) findViewById(R.id.picker_Minutes);

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        minutes_picker.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        minutes_picker.setMaxValue(59);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        minutes_picker.setWrapSelectorWheel(true);

        minutesSelected = 0;
        setDividerColor(minutes_picker, getResources().getColor(R.color.colorChalk));

        //Set a value change listener for NumberPicker
        minutes_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                minutesSelected = newVal;
                Log.d("picker", minutesSelected + " minutes on Android SDK " + Build.VERSION.SDK_INT);
//                Toast.makeText(this, "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.v("MainActivity", "Pickers Created");

//-----[Start Button]-------------------------------------------------------------------------------
        Button startButton = findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("buttonClick", "Start button Pressed");
                if(minutesSelected !=0 || hoursSelected != 0) {
                    Intent intent = new Intent(MainActivity.this, CountdownActivity.class);
                    intent.putExtra("hours", hoursSelected);
                    intent.putExtra("minutes", minutesSelected);

                    Log.d("buildInfo", "Sdk Version: " + Build.VERSION.SDK_INT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Log.d("buildInfo", "Build check passed");
//                    Log.d("buildInfo", "" + mNotificationManager);
                        assert mNotificationManager != null;
                        Log.d("buildInfo", "" + mNotificationManager.isNotificationPolicyAccessGranted());
                        if (mNotificationManager.isNotificationPolicyAccessGranted()) {
                            mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALARMS);
                            Log.d("notificationManager", "Do not disturb enabled");
                        }
                    } else if (audio != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !audio.isVolumeFixed()) {
                        //mute audio

                        int notifications = audio.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
                        int alarm = audio.getStreamVolume(AudioManager.STREAM_ALARM);
                        int ring = audio.getStreamVolume(AudioManager.STREAM_RING);
                        int system = audio.getStreamVolume(AudioManager.STREAM_SYSTEM);
//                    int music = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

                        Log.d("audioManager", "Notifications Volume: " + notifications);
                        Log.d("audioManager", "Alarm Volume: " + alarm);
                        Log.d("audioManager", "Ring Volume: " + ring);
                        Log.d("audioManager", "System Volume: " + system);
//                    Log.d("audioManager", "Music Volume: " + music);

                        prefs.edit().putInt("notifications", notifications).apply();
                        prefs.edit().putInt("alarm", alarm).apply();
                        prefs.edit().putInt("ring", ring).apply();
                        prefs.edit().putInt("system", system).apply();
//                    prefs.edit().putInt("music", music).apply();

                        audio.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
                        audio.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
                        audio.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
                        audio.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
//                    audio.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

                        Log.d("audioManager", "Notifications Volume: " + audio.getStreamVolume(AudioManager.STREAM_NOTIFICATION));
                        Log.d("audioManager", "Alarm Volume: " + audio.getStreamVolume(AudioManager.STREAM_ALARM));
                        Log.d("audioManager", "Ring Volume: " + audio.getStreamVolume(AudioManager.STREAM_RING));
                        Log.d("audioManager", "System Volume: " + audio.getStreamVolume(AudioManager.STREAM_SYSTEM));
//                    Log.d("audioManager", "Music Volume: " + audio.getStreamVolume(AudioManager.STREAM_MUSIC));

                        Log.d("audioManager", "All audio muted except Music");

                    }
                    startActivity(intent);
                }
            }
        });

//------[Settings Button]---------------------------------------------------------------------------

        android.widget.ImageButton settingsButton = findViewById(R.id.settings_image_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        Spinner hours = (Spinner) parent;
//        Spinner minutes = (Spinner) parent;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (hours.getId() == R.id.spinner_hours && !Objects.equals(parent.getItemAtPosition(position).toString(), "0")) {
//                hoursSelected = Integer.parseInt(parent.getSelectedItem().toString());
//                Log.d("spinner", hoursSelected + " hours on Android SDK " + Build.VERSION.SDK_INT);
//                Toast.makeText(this, "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//            }
//        } else if (hours.getId() == R.id.spinner_hours) {
//            hoursSelected = Integer.parseInt(parent.getSelectedItem().toString());
//            Log.d("spinner", hoursSelected + " hours on Android SDK " + Build.VERSION.SDK_INT);
//            Toast.makeText(this, "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (minutes.getId() == R.id.spinner_minutes && !Objects.equals(parent.getItemAtPosition(position).toString(), "0")) {
//                minutesSelected = Integer.parseInt(parent.getSelectedItem().toString());
//                Log.d("spinner", minutesSelected + " minutes on Android SDK " + Build.VERSION.SDK_INT);
////                Toast.makeText(this, "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//            }
//        } else if (minutes.getId() == R.id.spinner_minutes) {
//            minutesSelected = Integer.parseInt(parent.getSelectedItem().toString());
//            Log.d("spinner", minutesSelected + " minutes on Android SDK " + Build.VERSION.SDK_INT);
////            Toast.makeText(this, "Selected: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    public void onNothingSelected(AdapterView<?> arg0) {
//        Log.d("spinner", "Nothing Selected");
//    }

    public void onResume() {

        final SharedPreferences prefs = this.getPreferences(MODE_PRIVATE);
        final AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Log.d("buildInfo", "" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d("buildInfo", "build check passed");
            if (mNotificationManager != null && mNotificationManager.isNotificationPolicyAccessGranted() && mNotificationManager.getCurrentInterruptionFilter() != NotificationManager.INTERRUPTION_FILTER_ALL) {
                mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
                Log.d("notificationManager", "Notifications unmuted");
            }
        } else if (audio != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !audio.isVolumeFixed()) {
            //mute audio

            int notifications = prefs.getInt("notifications", 0);
            int alarm = prefs.getInt("alarm", 0);
            int ring = prefs.getInt("ring", 0);
            int system = prefs.getInt("system", 0);
//            int music = prefs.getInt("music", 0);

            Log.d("audioManager", "Notifications Shared Volume: " + notifications);
            Log.d("audioManager", "Alarm Shared Volume: " + alarm);
            Log.d("audioManager", "Ring Shared Volume: " + ring);
            Log.d("audioManager", "System Shared Volume: " + system);
//            Log.d("audioManager", "Music Shared Volume: " + music);

            audio.setStreamVolume(AudioManager.STREAM_NOTIFICATION, notifications, 0);
            audio.setStreamVolume(AudioManager.STREAM_ALARM, alarm, 0);
            audio.setStreamVolume(AudioManager.STREAM_RING, ring, 0);
            audio.setStreamVolume(AudioManager.STREAM_SYSTEM, system, 0);
//            audio.setStreamVolume(AudioManager.STREAM_MUSIC, music, 0);

            Log.d("audioManager", "Notifications Volume: " + audio.getStreamVolume(AudioManager.STREAM_NOTIFICATION));
            Log.d("audioManager", "Alarm Volume: " + audio.getStreamVolume(AudioManager.STREAM_ALARM));
            Log.d("audioManager", "Ring Volume: " + audio.getStreamVolume(AudioManager.STREAM_RING));
            Log.d("audioManager", "System Volume: " + audio.getStreamVolume(AudioManager.STREAM_SYSTEM));
//            Log.d("audioManager", "Music Volume: " + audio.getStreamVolume(AudioManager.STREAM_MUSIC));

            Log.d("audioManager", "All audio unmuted");

        }
        Log.d("MainActivity", "Resume Main activity");

//        hoursSelected = 0;
//        minutesSelected = 0;

        super.onResume();
    }

    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}