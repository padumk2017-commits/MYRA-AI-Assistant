package com.myra.assistant.ui

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        root.addView(title)
        root.addView(status)
        root.addView(orb)
        root.addView(message)
        root.addView(mic)

        setContentView(root)
    }
}
