
package com.diaryvault.mvp.speech
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.*

class SpeechToTextManager(
    private val context: Context,
    private val onResult: (String) -> Unit
) {
    private val recognizer = SpeechRecognizer.createSpeechRecognizer(context)

    fun start() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
        }

        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(bundle: Bundle) {
                bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull()?.let { onResult(it) }
            }
            override fun onError(error: Int) {}
            override fun onReadyForSpeech(p0: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(p0: Bundle?) {}
            override fun onRmsChanged(p0: Float) {}
            override fun onEvent(p0: Int, p1: Bundle?) {}
            override fun onBufferReceived(p0: ByteArray?) {}
        })
        recognizer.startListening(intent)
    }
}
