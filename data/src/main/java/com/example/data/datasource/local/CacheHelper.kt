package com.example.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val TAG = "CacheHelper"

class CacheHelper @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // Save string value
    fun saveString(key: String?, value: String?) {
        Log.i(TAG, "saveString: $value for key: $key")
        sharedPreferences.edit().putString(key, value).apply()
    }

    // Get string value
    fun getString(key: String): String? {
        val value = sharedPreferences.getString(key, null)
        Log.i(TAG, "getString: $value")
        return value
    }

    // Save boolean value
    fun saveBoolean(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    // Get boolean value
    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, true)
    }

    // Remove a specific key
    fun remove(key: String?) {
        sharedPreferences.edit().remove(key).apply()
    }

    // Clear all data
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "news_prefs"
    }
}
