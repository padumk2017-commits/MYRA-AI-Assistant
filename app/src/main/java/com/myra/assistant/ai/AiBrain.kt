package com.myra.assistant.ai

class AiBrain {

    fun process(
        userText: String,
        onResponse: (String) -> Unit
    ) {

        val text = userText.trim().lowercase()

        val response = when {
            text.contains("hello") ||
            text.contains("hi") ||
            text.contains("namaste") -> {
                "Hello! Main MYRA hoon. Aapki kya madad kar sakti hoon?"
            }

            text.contains("who are you") ||
            text.contains("tum kaun ho") -> {
                "Main MYRA hoon, aapki AI voice assistant."
            }

            text.contains("how are you") ||
            text.contains("kaisi ho") -> {
                "Main bilkul ready hoon!"
            }

            else -> {
                "Maine suna: $userText"
            }
        }

        onResponse(response)
    }
}
