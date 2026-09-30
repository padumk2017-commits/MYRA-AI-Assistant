package com.myra.assistant.audio

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder

class AudioRecorder {

    private var audioRecord: AudioRecord? = null
    private var isRecording = false

    fun start() {
        val sampleRate = 16000

        val bufferSize = AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )

        audioRecord?.startRecording()
        isRecording = true

        val buffer = ShortArray(bufferSize)

        Thread {
            while (isRecording) {
                audioRecord?.read(
                    buffer,
                    0,
                    buffer.size
                )
            }
        }.start()
    }

    fun stop() {
        isRecording = false

        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }
}
