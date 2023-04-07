package com.game.retrofitcrashcoursekotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.game.retrofitcrashcoursekotlin.models.UserResponse
import com.game.retrofitcrashcoursekotlin.utils.Constants.LOGIN_RESPONSE
import com.game.retrofitcrashcoursekotlin.utils.Constants.PREF_TOKEN
import com.game.retrofitcrashcoursekotlin.utils.Constants.USER_NAME
import com.game.retrofitcrashcoursekotlin.utils.Constants.USER_TOKEN
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private var pref : SharedPreferences =
        context.getSharedPreferences(PREF_TOKEN, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = pref.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun getToken(): String? {
        return pref.getString(USER_TOKEN,null)
    }

    fun saveUserName(name: String) {
        val editor = pref.edit()
        editor.putString(USER_NAME,name)
        editor.apply()
    }

    fun getUserName(): String? {
        return pref.getString(USER_NAME,null)
    }

    fun setLogin(userResponse: UserResponse) {
        val editor = pref.edit()
        editor.putString(LOGIN_RESPONSE,Gson().toJson(userResponse))
        editor.apply()
    }

    fun getLogin() : UserResponse {
        return Gson().fromJson(pref.getString(LOGIN_RESPONSE,null),UserResponse::class.java)
    }

    fun saveString(name : String,value : String) {
        val editor = pref.edit()
        editor.putString(name,value)
        editor.apply()
    }

    fun getStringFromPref(name : String) : String?{
        return pref.getString(name,null)
    }
 }