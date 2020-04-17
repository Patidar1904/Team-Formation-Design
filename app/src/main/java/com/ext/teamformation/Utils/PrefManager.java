package com.ext.teamformation.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private Context context;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Team";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static String REGISTERID = "register_id";
    public static String EMAIL = "Email";
    public static String UserImage = "";
    public static String USER_NAME = "user_name";



    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession() {
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
        editor.apply();
    }

    public void deletePrefData() {
        editor.clear();
        editor.commit();
        editor.apply();
    }

    public void savePrefValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        editor.apply();

    }

    public String getPrefValue(String key) {
        String returnValue = "";
        try {
            returnValue = pref.getString(key, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    public boolean checkLogin() {
        return pref.getBoolean(PrefManager.IS_LOGIN, false);
    }

}