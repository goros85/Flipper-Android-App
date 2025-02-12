package com.flipperdevices.core.progress

import java.lang.Float.min

private const val MAX_PERCENT = 1.0f
private const val MIN_PERCENT = 0f

class ProgressWrapperTracker(
    private val progressListener: ProgressListener,
    private val min: Float = MIN_PERCENT,
    private val max: Float = MAX_PERCENT
) : ProgressListener {
    override suspend fun onProgress(current: Float) {
        val diff = max - min
        if (diff <= 0) { // This means that our min and max are originally incorrect
            return
        }

        val currentPercent = min + current * diff
        if (BuildConfig.DEBUG && currentPercent > max) {
            error("Incorrect current percent (min: $min, current: $current, diff: $diff)")
        }

        progressListener.onProgress(min(min(currentPercent, max), MAX_PERCENT))
    }

    suspend fun report(current: Long, max: Long) {
        if (current > max) {
            onProgress(MAX_PERCENT)
            if (BuildConfig.DEBUG) {
                error("Current larger then max (current: $current, max: $max)")
            }
            return
        }
        val percent = current.toDouble() / max
        onProgress(percent.toFloat())
    }
}
