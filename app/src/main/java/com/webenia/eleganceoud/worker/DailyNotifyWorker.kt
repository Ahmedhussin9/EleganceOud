package com.webenia.eleganceoud.worker

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.webenia.eleganceoud.MainActivity
import com.webenia.eleganceoud.R

class DailyNotifyWorker (
    appContext :Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams){
    override suspend fun doWork(): Result {
        val title = inputData.getString(KEY_TITLE)?: return Result.failure()
        val message = inputData.getString(KEY_MESSAGE)?: return Result.failure()
        val hour = inputData.getInt(KEY_HOUR, 0)
        val minute = inputData.getInt(KEY_MINUTE, 0)
        val requestCode = (hour * 100) + minute

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!granted) return Result.success()
        }

        NotifyChannels.ensure(applicationContext)

        showNotification(applicationContext, requestCode, title, message)

        if (hour in 0..23 && minute in 0..59){
            NotificationScheduler.scheduleDaily(
                context = applicationContext,
                hour = hour,
                minute = minute,
                title = title,
                message = message
            )
        }
        return Result.success()
    }
    private fun showNotification(context: Context, id: Int, title: String, message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context, id, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, NotifyChannels.DAILY_REMINDERS)
            .setSmallIcon(R.drawable.plain_logo)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(id, notification)
    }

    companion object {
        const val KEY_TITLE  = "key_title"
        const val KEY_MESSAGE = "key_message"
        const val KEY_HOUR   = "key_hour"
        const val KEY_MINUTE = "key_minute"
    }
}