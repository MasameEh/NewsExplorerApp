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
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Save string value
    fun saveString(key: String?, value: String?) {
        Log.i(TAG, "saveString: $value for key: $key")
        editor.putString(key, value)
        editor.commit()
    }

    // Get string value
    fun getString(key: String): String? {
        Log.i(TAG, "getString: ${sharedPreferences.getString(key, null)}")
        return sharedPreferences.getString(key, null)
    }

    // Save boolean value
    fun saveBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    // Get boolean value
    fun getBoolean(key: String?): Boolean {
        return sharedPreferences.getBoolean(key, true)
    }

    // Remove a specific key
    fun remove(key: String?) {
        editor.remove(key)
        editor.commit()
    }

    // Clear all data
    fun clear() {
        editor.clear()
        editor.commit()
    }

    companion object {
        private const val PREF_NAME = "weather_prefs"
        private var cacheHelper: CacheHelper? = null

        fun getInstance(context: Context): CacheHelper {
            if (cacheHelper == null) {
                cacheHelper = CacheHelper(context)
            }
            return cacheHelper as CacheHelper
        }
    }
}