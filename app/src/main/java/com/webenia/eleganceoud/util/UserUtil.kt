package com.elegance_oud.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

import dagger.hilt.android.qualifiers.ApplicationContext


object UserUtil {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var privateSharedPreferences: SharedPreferences

    private const val IS_FIRST_TIME = "isFirsTime"
    private const val TOKEN = "token"
    private const val USER_ID = "userId"
    private const val USER_NAME = "userName"
    private const val USER_EMAIL = "userEmail"
    private const val USER_PHONE = "userPhone"
    private const val IS_LOGIN = "isLogin"

    private fun getEncryptedSharedPreferences(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            "secure_prefs",  // File name
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("local", Context.MODE_PRIVATE)
        privateSharedPreferences = getEncryptedSharedPreferences(context)
    }

    fun saveIsLogin(isLogin: Boolean) =
        sharedPreferences.edit().putBoolean(IS_LOGIN, isLogin).apply()

    fun isLogin() = sharedPreferences.getBoolean(IS_LOGIN, false)

    fun saveFirstTime(isFirstTime: Boolean) =
        sharedPreferences.edit().putBoolean(IS_FIRST_TIME, isFirstTime).apply()

    fun isFirstTime() = sharedPreferences.getBoolean(IS_FIRST_TIME, true)

    // 🔒 Securely Save Token
    fun saveToken(token: String) {
        privateSharedPreferences.edit().putString(TOKEN, token).apply()
    }

    // 🔒 Retrieve Token
    fun getToken(): String? = privateSharedPreferences.getString(TOKEN, "")

    // ❌ Properly Clear Token
    fun clearToken() = privateSharedPreferences.edit().remove(TOKEN).apply()

    fun saveUserName(userName: String) =
        sharedPreferences.edit().putString(USER_NAME, userName).apply()

    fun getUserName() = sharedPreferences.getString(USER_NAME, "")

    fun saveUserEmail(userEmail: String) =
        sharedPreferences.edit().putString(USER_EMAIL, userEmail).apply()

    fun getUserEmail() = sharedPreferences.getString(USER_EMAIL, "")


    fun saveUserId(userId: String) = sharedPreferences.edit().putString(USER_ID, userId).apply()
    fun getUserId() = sharedPreferences.getString(USER_ID, "")
}