package com.myra.assistant.voice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.util.Locale

class SpeechRecognizerManager(
    private val context: Context
) {

    private var recognizer: SpeechRecognizer? = null

    fun startListening(
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            onError("Speech recognition is not available")
            return
        }

        recognizer?.destroy()

        recognizer = SpeechRecognizer.createSpeechRecognizer(context)

        recognizer?.setRecognitionListener(object : RecognitionListener {

            override fun onReadyForSpeech(params: Bundle?) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onPartialResults(
                partialResults: Bundle?
            ) {}

            override fun onEvent(
                eventType: Int,
                params: Bundle?
            ) {}

            override fun onResults(results: Bundle?) {

                val matches = results?.getStringArrayList(
                    SpeechRecognizer.RESULTS_RECOGNITION
                )

                val text = matches?.firstOrNull()

                if (!text.isNullOrBlank()) {
                    onResult(text)
                } else {
                    onError("I could not understand that")
                }
            }

            override fun onError(error: Int) {
                onError("Speech recognition error: $error")
            }
        })

        val intent = Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        ).apply {

            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            putExtra(
                RecognizerIntent.EXTRA_PARTIAL_RESULTS,
                true
            )
        }

        recognizer?.startListening(intent)
    }

    fun stopListening() {
        recognizer?.stopListening()
    }

    fun destroy() {
        recognizer?.destroy()
        recognizer = null
    }
}
