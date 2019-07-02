package com.qlvb.vnpt.botttt.schedule.domain.repository;

import java.util.HashSet;

public class LoginUserCookies {

    public static final String PREF_KEY_COOKIES = "com.qlvb.vnpt.botttt.app.COOKIES";
    public static final String PREF_KEY_AUTHENTICATION = "com.qlvb.vnpt.botttt.app.AUTHENTICATION";

    private Preferences preferences;

    public LoginUserCookies(Preferences preferences) {
        this.preferences = preferences;
    }

    public HashSet<String> get() {
        return preferences.getHashSet(PREF_KEY_COOKIES);
    }

    public void put(HashSet<String> cookies) {
        preferences.putHashSet(PREF_KEY_COOKIES, cookies);
    }

    public String getAuthent() {
        return preferences.getString(PREF_KEY_AUTHENTICATION,"");
    }

    public void putAuthen(String strAuthen) {
        preferences.putString(PREF_KEY_AUTHENTICATION, strAuthen);
    }

    public void remove() {
        preferences.remove(PREF_KEY_COOKIES);
    }

}