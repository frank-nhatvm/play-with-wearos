package com.frank.firstwear.presentation.common

import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioManager

fun Context.audioOutputAvailable(type: Int) : Boolean {

    if (!packageManager.hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
        return false
    }
    val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    return audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS).any { it.type == type }
}