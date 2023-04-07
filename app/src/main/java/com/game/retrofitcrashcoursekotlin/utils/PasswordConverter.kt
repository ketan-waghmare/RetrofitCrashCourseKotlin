package com.game.retrofitcrashcoursekotlin.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

sealed class PasswordConverter {

    fun md5(password: String): String? {
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
        return md?.toString()
    }
}