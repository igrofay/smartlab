package com.example.data.data.repos

import android.content.SharedPreferences
import android.util.Log
import com.example.data.domain.repos.AppRepos
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppReposImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): AppRepos {
    private val keyIsUserFamiliarWithApp = "keyIsUserFamiliarWithApp"
    override val isUserFamiliarWithApp: Boolean
        get() = sharedPreferences.getBoolean(keyIsUserFamiliarWithApp, false)

    override fun userHasBeenIntroducedWithApp() {
        sharedPreferences
            .edit()
            .putBoolean(keyIsUserFamiliarWithApp, true)
            .apply()
    }

    private val keyToken = "keyToken"
    override val token: String?
        get() = sharedPreferences.getString(keyToken, null)

    override fun setToken(token: String) {
        sharedPreferences
            .edit()
            .putString(keyToken, token)
            .apply()
    }

    private val keyCode = "keyCode"
    override val isCodeSetForEnterApp: Boolean
        get() = sharedPreferences.getString(keyCode, null) != null

    private val algorithm = "SHA-256"
    override fun checkEnteredCode(code: String): Boolean {
        val messageDigest = MessageDigest.getInstance(algorithm)
        val hash = messageDigest.digest(code.toByteArray())
        val localHashCode = sharedPreferences.getString(keyCode, null)
        return localHashCode == hash.toHexString()
    }

    override fun setNewCode(code: String) {
        val messageDigest = MessageDigest.getInstance(algorithm)
        val hash = messageDigest.digest(code.toByteArray())
        sharedPreferences
            .edit()
            .putString(keyCode, hash.toHexString())
            .apply()
    }
    private fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }
}