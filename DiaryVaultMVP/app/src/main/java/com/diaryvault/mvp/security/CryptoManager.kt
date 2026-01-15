
package com.diaryvault.mvp.security
import android.util.Base64

class CryptoManager {
    fun encrypt(text: String): String =
        Base64.encodeToString(text.toByteArray(), Base64.DEFAULT)

    fun decrypt(text: String): String =
        String(Base64.decode(text, Base64.DEFAULT))
}
