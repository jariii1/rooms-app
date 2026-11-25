package com.example.tabletapp.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "app_session";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private final SharedPreferences pref;

    public SessionManager(Context context) {
        pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        pref.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }
}
