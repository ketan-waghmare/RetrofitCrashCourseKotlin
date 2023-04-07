package com.game.retrofitcrashcoursekotlin.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

object Constants {

    const val TAG = "RETROFIT_APP"
    const val PREF_TOKEN = "PREF_TOKEN"
    const val USER_TOKEN = "USER_TOKEN"
    const val USER_NAME = "USER_NAME"
    const val LOGIN_RESPONSE = "LOGIN_RESPONSE"
    const val BASE_URL = "http://ketanwaghmare.in/retrofiDemo/"

    fun md5(password: String): String {
        var md: MessageDigest? = null
        try {
            md = MessageDigest.getInstance("MD5")
            md.update(password.toByteArray())
            val byteData = md.digest()
            val sb = StringBuffer()
            for (i in byteData.indices) {
                sb.append(((byteData[i] and 0xff.toByte()) + 0x100).toString(16).substring(1))
            }
            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return md!!.toString()
    }
}