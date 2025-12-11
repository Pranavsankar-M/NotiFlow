package com.notiflow

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class NotiFlowNotificationListener : NotificationListenerService() {
    private val TAG = "NotiFlowNLS"

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        try {
            sbn ?: return
            Log.d(TAG, "Notification posted: ${sbn.packageName} / ${sbn.id}")

            // Enqueue background work to process notification off main thread
            val input = Data.Builder()
                .putString("pkg", sbn.packageName)
                .putInt("id", sbn.id)
                .build()

            val work = OneTimeWorkRequestBuilder<NotificationProcessorWorker>()
                .setInputData(input)
                .build()

            WorkManager.getInstance(applicationContext).enqueue(work)
        } catch (t: Throwable) {
            Log.w(TAG, "Error handling notification", t)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        // no-op for now
    }
}
