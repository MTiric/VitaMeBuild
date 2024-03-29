package com.example.vitamebuild.classes

import com.example.vitamebuild.ObjectHolder
import java.math.BigInteger
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


    fun generateHash(password: String, salt: String, pepper: String): String {
        val newSalt = getFirstFourCharactersAndHashThem(salt)
        val combinedSalt = "$newSalt$pepper".toByteArray()
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), combinedSalt, ITERATIONS, KEY_LENGTH)
        val key: SecretKey = factory.generateSecret(spec)
        val hash: ByteArray = key.encoded
        return hash.toHexString()
    }

    fun generateRandomLetter(): Char {
        val randomIndex = (0 until ObjectHolder.alphabet.length).random()
        return ObjectHolder.alphabet[randomIndex]
    }
    fun getFirstFourCharactersAndHashThem(input: String): String {
        val firstFourChars = input.take(4)
        val md = MessageDigest.getInstance("MD5")
        val hashBytes = md.digest(firstFourChars.toByteArray())
        val hashString = BigInteger(1, hashBytes).toString(16)
        return hashString.padStart(32, '0')
    }

    fun generateRandomToken(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt.toHexString()
    }


}