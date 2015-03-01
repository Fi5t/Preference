package ru.freedomlogic.preference

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import java.util.HashSet

private val defaultInit: Any.() -> Unit = {}


public fun <T : SharedPreferences> processPreferences(preferences: T, init: T.() -> Unit): T {
    preferences.init()
    return preferences
}

public fun SharedPreferences.getString(key: String, defValue: String = ""): String = getString(key, defValue)
public fun SharedPreferences.getStringSet(key: String, defValues: Set<String> = HashSet<String>()): Set<String> = getStringSet(key, defValues)
public fun SharedPreferences.getInt(key: String, defValue: Int = 0): Int = getInt(key, defValue)
public fun SharedPreferences.getLong(key: String, defValue: Long = 0): Long = getLong(key, defValue)
public fun SharedPreferences.getFloat(key: String, defValue: Float = 0.0f): Float = getFloat(key, defValue)
public fun SharedPreferences.getBoolean(key: String, defValue: Boolean = false): Boolean = getBoolean(key, defValue)

public fun preferences(context: Context, init: SharedPreferences.() -> Unit = defaultInit): SharedPreferences
    = processPreferences(PreferenceManager.getDefaultSharedPreferences(context), init)