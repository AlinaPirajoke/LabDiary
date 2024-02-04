package com.example.labdiary.model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log

class MySharedPreferences(context: Context) {
    val TAG = "MySharedPreferences"
    val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var semester: Int = 0
        get() {
            val v = sp.getInt("semester", 6)
            Log.i(TAG, "semester: $v")
            return v
        }
        set(sem: Int) {
            field = sem
            sp.edit().putInt("semester", sem).apply()
            Log.i(TAG, "semester: $sem")
        }
}