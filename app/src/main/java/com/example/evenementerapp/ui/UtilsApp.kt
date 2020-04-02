package com.example.evenementerapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.common.hash.Hashing
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class UtilsApp {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun encryptPassword(password: String, key: String): String {
            val keySpec = SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), "HmacSHA256")
            val mac = Mac.getInstance("HmacSHA256")
            mac.init(keySpec)

            val hmac = mac.doFinal(password.toByteArray(StandardCharsets.UTF_8))
            var sb = StringBuffer()
            hmac.forEach { b ->
                sb.append(String.format("%02x", b))
            }
            return sb.toString()
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun encryptData(json: String, key: String): String {
            val IV = ByteArray(16)
            val random: SecureRandom
            random = SecureRandom()
            random.nextBytes(IV)
            val keySpec = SecretKeySpec(Hashing.sha256()
                .hashString(key, StandardCharsets.UTF_8).asBytes(), "AES")
            val mac = Cipher.getInstance("AES")

            val ivSpec = IvParameterSpec(IV)
            mac.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

            val hmac = mac.doFinal(json.toByteArray(StandardCharsets.UTF_8))
            var sb = StringBuffer()
            hmac.forEach { b ->
                sb.append(String.format("%02x", b))
            }
            return sb.toString()
        }

    }

    fun encryptSha256(key: String) {

    }

}