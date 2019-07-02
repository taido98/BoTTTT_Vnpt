package com.qlvb.vnpt.botttt.schedule.domain.repository;

/**
 * Created by linhl on 4/13/2018.
 */

public class AppState {

    public static final String PREF_KEY_INTRODUCE_SEARCH_MAP = "pm1.vnpt.com.SEARCH_MAP";
    public static final String PREF_KEY_STATUS_LOGIN_USER = "pm1.vnpt.com.STATUS_LOGIN";
    public static final String PREF_KEY_TOKEN_LOGIN_USER = "pm1.vnpt.com.TOKEN_LOGIN_USER";
    public static final String PREF_KEY_UNIT_ID_LOGIN_USER = "pm1.vnpt.com.UNIT_ID_LOGIN_USER";
    public static final String PREF_KEY_ACCOUNT_USER = "pm1.vnpt.ACCOUNT_LOGIN_USER";
    public static final String PREF_KEY_PASSWORD_USER = "pm1.vnpt.com.PASS_LOGIN_USER";
    public static final String PREF_AUTO_LOGIN_USER = "pm1.vnpt.com.AUTO_LOGIN_USER";
    public static final String PREF_IS_LOGOUT_USER = "pm1.vnpt.com.IS_LOGOUT_USER";

    private Preferences preferences;

    public AppState(Preferences preferences) {
        this.preferences = preferences;
    }

    public int getState(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void setState(String key, int state) {
        preferences.putInt(key, state);
    }

    public String getState(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void setState(String key, String state) {
        preferences.putString(key, state);
    }

    public boolean getState(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void setState(String key, boolean state) {
        preferences.putBoolean(key, state);
    }
    public void removeAllValue() {
        preferences.removeAllValue();
    }
    public void removeValue(String key) {
        preferences.remove(key);
    }
}
