package com.myra.assistant.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.myra.assistant.ai.AiBrain
import com.myra.assistant.audio.MyraSpeaker
import com.myra.assistant.voice.SpeechRecognizerManager

class MainActivity : ComponentActivity() {

    private val microphoneRequestCode = 100

    private lateinit var speechRecognizer: SpeechRecognizerManager
    private lateinit var myraSpeaker: MyraSpeaker
    private lateinit var aiBrain: AiBrain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        speechRecognizer = SpeechRecognizerManager(this)
        myraSpeaker = MyraSpeaker(this)
        aiBrain = AiBrain()

        showMyraUI()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                microphoneRequestCode
            )
        }
    }

    private fun showMyraUI() {

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER
        root.setPadding(32, 32, 32, 32)
        root.setBackgroundColor(Color.rgb(8, 10, 18))

        val title = TextView(this)
        title.text = "MYRA"
        title.textSize = 32f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER

        val status = TextView(this)
        status.text = "●  Idle"
        status.textSize = 18f
        status.setTextColor(Color.LTGRAY)
        status.gravity = Gravity.CENTER

        val orb = TextView(this)
        orb.text = "◉"
        orb.textSize = 110f
        orb.setTextColor(Color.CYAN)
        orb.gravity = Gravity.CENTER

        val message = TextView(this)
        message.text = "Hello, I am MYRA"
        message.textSize = 20f
        message.setTextColor(Color.WHITE)
        message.gravity = Gravity.CENTER

        val mic = TextView(this)
        mic.text = "🎙  TAP TO TALK"
        mic.textSize = 18f
        mic.setTextColor(Color.WHITE)
        mic.gravity = Gravity.CENTER
        mic.setPadding(40, 30, 40, 30)

        mic.setOnClickListener {

            status.text = "●  Listening..."
            message.text = "Listening..."

            speechRecognizer.startListening(

                onResult = { recognizedText ->

                    status.text = "●  Thinking..."
                    message.text = recognizedText

                    aiBrain.process(
                        recognizedText
                    ) { aiResponse ->

                        runOnUiThread {

                            status.text = "●  Speaking..."
                            message.text = aiResponse

                            myraSpeaker.speak(aiResponse)
                        }
                    }
                },

                onError = { errorMessage ->

                    status.text = "●  Idle"
                    message.text = errorMessage

                    myraSpeaker.speak(
                        "Sorry, I did not understand."
                    )
                }
            )
        }

        root.addView(title)
        root.addView(status)
        root.addView(orb)
        root.addView(message)
        root.addView(mic)

        setContentView(root)
    }

    override fun onDestroy() {
        speechRecognizer.destroy()
        myraSpeaker.release()
        super.onDestroy()
    }
}
