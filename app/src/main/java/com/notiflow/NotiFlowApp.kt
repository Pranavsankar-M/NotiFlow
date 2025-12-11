package com.notiflow

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotiFlowApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(NotificationManager::class.java)
            nm?.let {
                val important = NotificationChannel(
                    "channel_important",
                    "Important Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                )
                important.description = "Important notifications from NotiFlow"

                val silent = NotificationChannel(
                    "channel_silent",
                    "Silent Notifications",
                    NotificationManager.IMPORTANCE_LOW
                )
                silent.description = "Silent or low-priority notifications"

                val digest = NotificationChannel(
                    "channel_digest",
                    "Digest Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                digest.description = "Digest summary notifications"

                it.createNotificationChannel(important)
                it.createNotificationChannel(silent)
                it.createNotificationChannel(digest)
            }
        }
    }
}
