package com.notiflow

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class NotificationProcessorWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {
    private val TAG = "NotifProcessor"

    override suspend fun doWork(): Result {
        return try {
            val pkg = inputData.getString("pkg")
            val id = inputData.getInt("id", -1)
            // TODO: process notification according to TDD (rules, database, etc.)
            Log.d(TAG, "Processing notification from=$pkg id=$id")
            Result.success()
        } catch (t: Throwable) {
            Log.w(TAG, "Worker failed", t)
            Result.failure()
        }
    }
}
