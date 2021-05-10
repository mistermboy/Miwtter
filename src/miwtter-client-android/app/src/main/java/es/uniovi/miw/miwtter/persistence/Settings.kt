package es.uniovi.miw.miwtter.persistence

import android.content.Context
import android.content.SharedPreferences
import com.example.miwtter.ui.profile.ProfileFragment

class Settings(context: Context) {
    companion object {
        const val PREFS_NAME = "MiwtterPrefs"
        const val PREF_USERNAME = "PREF_USERNAME"
        const val PREF_USER_NAME = "PREF_USER_NAME"
        const val PREF_USER_SURNAME = "PREF_USER_SURNAME"
    }
    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var username: String
        get() = preferences.getString(PREF_USERNAME,"") ?: ""
        set(value) {
            val editor = preferences.edit()
            editor.putString(PREF_USERNAME, value)
            editor.apply()
        }

    var name: String
        get() = preferences.getString(PREF_USER_NAME,"") ?: ""
        set(value) {
            val editor = preferences.edit()
            editor.putString(PREF_USER_NAME, value)
            editor.apply()
        }

    var surname: String
        get() = preferences.getString(PREF_USER_SURNAME,"") ?: ""
        set(value) {
            val editor = preferences.edit()
            editor.putString(PREF_USER_SURNAME, value)
            editor.apply()
        }
}