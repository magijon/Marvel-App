package com.mebeal.marvelapp.presentation.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.sql.Timestamp

class MD5() {

    companion object {
        lateinit var timestamp: String

        private fun getMD5EncryptedString(encTarget: String): String {
            var mdEnc: MessageDigest? = null
            try {
                mdEnc = MessageDigest.getInstance("MD5")
            } catch (e: NoSuchAlgorithmException) {
                println("Exception while encrypting to md5")
                e.printStackTrace()
            } // Encryption algorithm
            mdEnc?.update(encTarget.toByteArray(), 0, encTarget.length)
            var md5: String = BigInteger(1, mdEnc?.digest()).toString(16)
            while (md5.length < 32) {
                md5 = "0$md5"
            }
            return md5
        }

        fun getHashComplete(apiKey: String, privateKey: String): String {
            timestamp = Timestamp(System.currentTimeMillis()).time.toString()
            return getMD5EncryptedString("$timestamp$privateKey$apiKey")
        }
    }
}