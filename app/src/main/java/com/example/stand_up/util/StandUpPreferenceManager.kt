package com.example.stand_up.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.stand_up.BuildConfig

class StandUpPreferenceManager(mContext: Context) {
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
    private val prefsEditor: SharedPreferences.Editor

    var isFirstTimeLaunch: Boolean
        get() = prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            prefsEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            prefsEditor.apply()
        }


    init {
        prefsEditor = prefs.edit()
    }

    fun setAccessToken(token:String){
        toPreference("accessToken",token)
    }

    fun setRefreshToken(token:String){
        toPreference("refreshToken",token)
    }

    fun getAccessToken():String{
        return fromPreference("accessToken","")
    }

    fun getRefreshToken():String{
        return fromPreference("refreshToken","")
    }

    fun getFcmToken():String{
        return fromPreference("refreshToken","")
    }

    fun fromPreference(
        key: String,
        defaultValue: String
    ): String {
        return try {
            prefs.getString(key, defaultValue)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
            defaultValue
        } as String

    }

    fun fromPreference(
        key: String,
        defaultValue: Boolean
    ): Boolean {
        return try {
            prefs.getBoolean(key, defaultValue)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
            defaultValue
        } as Boolean

    }

    fun fromPreference(
        key: String,
        defaultValue: Int
    ): Int {
        return try {
            return prefs.getInt(key, defaultValue)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
            defaultValue
        }

    }

    fun fromPreference(
        key: String,
        defaultValue: Long
    ): Long {
        return try {
            return prefs.getLong(key, defaultValue)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
            defaultValue
        }

    }

    fun toPreference(key: String, value: String) {
        try {
            prefsEditor.putString(key, value)
            prefsEditor.commit()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
        }

    }

    fun toPreference(key: String, value: Boolean) {
        try {
            prefsEditor.putBoolean(key, value)
            prefsEditor.commit()
            prefsEditor.apply()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
        }

    }

    fun toPreference(key: String, value: Int) {
        try {
            prefsEditor.putInt(key, value)
            prefsEditor.commit()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
        }

    }

    fun toPreference(key: String, value: Long) {
        try {
            prefsEditor.putLong(key, value)
            prefsEditor.commit()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
        }

    }

    fun removePreference(key: String) {
        var encryptedKey = ""
        try {
            encryptedKey = key
            prefsEditor.remove(encryptedKey)
            prefsEditor.commit()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("SukiPrefs", "Error -> " + e.message)
            }
        }

    }

    companion object {
        private val PREFS_NAME = "SUKI_PREFS"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }


}