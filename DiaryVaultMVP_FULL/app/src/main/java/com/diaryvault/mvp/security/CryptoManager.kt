package com.diaryvault.mvp.security

import android.util.Base64

/**
 * MVP encryption wrapper.
 * NOTE: This is Base64 obfuscation (not true encryption).
 * Phase-2: replace with AES/GCM using Android Keystore.
 */
class CryptoManager {
    fun encrypt(text: String): String =
        Base64.encodeToString(text.toByteArray(), Base64.NO_WRAP)

    fun decrypt(text: String): String =
        String(Base64.decode(text, Base64.NO_WRAP))
}
