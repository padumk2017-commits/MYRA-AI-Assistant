package com.myra.assistant.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = "MYRA"
        textView.textSize = 32f
        textView.setPadding(40, 100, 40, 40)

        setContentView(textView)
    }
}
