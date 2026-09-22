package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sin

class SoundManager {

    private val sampleRate = 44100
    private val scope = CoroutineScope(Dispatchers.Default)
    var isSoundEnabled: Boolean = true
    var isMusicEnabled: Boolean = true

    fun playClick() {
        if (!isSoundEnabled) return
        scope.launch {
            playTone(800.0, 45, 0.4f, shape = EnvelopeShape.PERCUSSIVE)
        }
    }

    fun playCorrect() {
        if (!isSoundEnabled) return
        scope.launch {
            // Ascending major chord fanfare
            playTone(523.25, 90, 0.5f, EnvelopeShape.CHIME) // C5
            playTone(659.25, 90, 0.5f, EnvelopeShape.CHIME) // E5
            playTone(783.99, 100, 0.6f, EnvelopeShape.CHIME) // G5
            playTone(1046.50, 180, 0.7f, EnvelopeShape.CHIME) // C6
        }
    }

    fun playWrong() {
        if (!isSoundEnabled) return
        scope.launch {
            playTone(220.0, 120, 0.4f, EnvelopeShape.GENTLE)
            playTone(174.61, 160, 0.4f, EnvelopeShape.GENTLE)
        }
    }

    fun playCoin() {
        if (!isSoundEnabled) return
        scope.launch {
            playTone(987.77, 60, 0.45f, EnvelopeShape.CHIME) // B5
            playTone(1318.51, 150, 0.55f, EnvelopeShape.CHIME) // E6
        }
    }

    fun playStar() {
        if (!isSoundEnabled) return
        scope.launch {
            playTone(1174.66, 80, 0.5f, EnvelopeShape.CHIME) // D6
            playTone(1567.98, 140, 0.6f, EnvelopeShape.CHIME) // G6
        }
    }

    fun playLevelComplete() {
        if (!isSoundEnabled) return
        scope.launch {
            val notes = listOf(523.25, 659.25, 783.99, 1046.50, 1318.51)
            for ((index, freq) in notes.withIndex()) {
                val dur = if (index == notes.size - 1) 300 else 90
                playTone(freq, dur, 0.6f, EnvelopeShape.CHIME)
            }
        }
    }

    private enum class EnvelopeShape { PERCUSSIVE, CHIME, GENTLE }

    private fun playTone(
        frequency: Double,
        durationMs: Int,
        volume: Float,
        shape: EnvelopeShape
    ) {
        try {
            val numSamples = (sampleRate * (durationMs / 1000.0)).toInt()
            if (numSamples <= 0) return
            val buffer = ShortArray(numSamples)

            for (i in 0 until numSamples) {
                val time = i.toDouble() / sampleRate
                val tNorm = i.toDouble() / numSamples

                // Envelope calculation
                val envelope = when (shape) {
                    EnvelopeShape.PERCUSSIVE -> (1.0 - tNorm) * (1.0 - tNorm)
                    EnvelopeShape.CHIME -> (1.0 - tNorm) * (0.8 + 0.2 * sin(time * 30.0))
                    EnvelopeShape.GENTLE -> sin(Math.PI * tNorm)
                }

                // Add harmonic overtone for candy/sparkling sweetness
                val primary = sin(2.0 * Math.PI * frequency * time)
                val overtone = 0.25 * sin(4.0 * Math.PI * frequency * time)
                val sample = ((primary + overtone) * envelope * volume * Short.MAX_VALUE).toInt()
                buffer[i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(buffer.size * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            audioTrack.write(buffer, 0, buffer.size)
            audioTrack.play()
            Thread.sleep(durationMs.toLong() + 20)
            audioTrack.stop()
            audioTrack.release()
        } catch (_: Exception) {
            // Graceful fallback if audio is restricted
        }
    }
}
