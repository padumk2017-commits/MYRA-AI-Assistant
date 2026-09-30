package com.myra.assistant.ai

class GeminiLiveClient {

    private var connected = false

    fun connect(
        ephemeralToken: String,
        onConnected: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (ephemeralToken.isBlank()) {
            onError("Gemini authentication token is missing")
            return
        }

        // Gemini Live WebSocket connection will be added here.
        // We will use an ephemeral token instead of storing
        // a permanent Gemini API key inside the APK.

        connected = true
        onConnected()
    }

    fun sendText(text: String) {
        if (!connected) return

        // Gemini Live message handling will be added here.
    }

    fun disconnect() {
        connected = false
    }

    fun isConnected(): Boolean {
        return connected
    }
}
