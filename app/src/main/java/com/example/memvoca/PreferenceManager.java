package com.example.memvoca;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String PREFERENCES_NAME = "custom_preference";
    private static final String DEFAULT_VALUE_STRING = "";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final int DEFAULT_VALUE_INT = -1;
    private static final int DEFAULT_COUNT = 0;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /** String 값 저장 **/
    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /** Boolean 값 저장 */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /** int 값 저장 */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /** String 값 로드 */
    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(key, DEFAULT_VALUE_STRING);
    }

    /** Boolean 값 로드 */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
    }

    /** int 값 로드 */
    public static int getInt(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(key, DEFAULT_VALUE_INT);
    }

    public static int getCount(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(key, DEFAULT_COUNT);
    }

    /** 키 값 삭제 */
    public static void removeKey(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.remove(key);
        edit.apply();
    }

    /** 모든 데이터 삭제 */
    public static void clear(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.apply();
    }
}
