package com.example.omri.showoff;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Asaf on 24-Jul-15.
 */
public class ShowOffApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "eZRYLXLuygD9LuFkiMqKmw90cvbwCg9nSwVquY0A", "vy6POE7Sphex2POh1eWnnED2KhaW2F9JvEIWGtFA");
    }
}
