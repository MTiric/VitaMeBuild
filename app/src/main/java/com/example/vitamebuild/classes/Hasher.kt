package com.example.vitamebuild.classes

import java.security.MessageDigest
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

private fun ByteArray.toHexString(): String =
    joinToString (separator = "") { eachByte -> "%02x".format(eachByte) }
class Hasher () {

    private val ALGORITHM = "PBKDF2WithHmacSHA512"
    private val ITERATIONS = 120_000
    private val KEY_LENGTH = 256
    private val PEPPER = "RandomSecret"


    fun generateHash(password: String, salt: String): String {
        val combinedSalt = "$salt$PEPPER".toByteArray()
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        return hash.toHexString()
    }

    fun generateRandomSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt.toHexString()
    }


}