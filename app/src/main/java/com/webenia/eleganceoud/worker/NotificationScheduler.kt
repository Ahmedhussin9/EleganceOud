package com.webenia.eleganceoud.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.time.Duration
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

object NotificationScheduler {
    fun scheduleDaily(
        context: Context,
        hour:Int,
        minute:Int,
        title:String,
        message:String
    ){
        val name  = uniqueName(hour, minute)
        val delayMs = delayUntilNextOccurrence(hour, minute)

        val data = workDataOf(
            DailyNotifyWorker.KEY_TITLE to title,
            DailyNotifyWorker.KEY_MESSAGE to message,
            DailyNotifyWorker.KEY_HOUR to hour,
            DailyNotifyWorker.KEY_MINUTE to minute
        )

        val request = OneTimeWorkRequestBuilder<DailyNotifyWorker>()
            .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()
        Log.e("Notification", request.toString(), )
        WorkManager.getInstance(context).enqueueUniqueWork(
            name,
            ExistingWorkPolicy.REPLACE,
            request
        )

    }
    fun scheduleDailyTimes(
        context: Context,
        times: List<LocalTime>,
        title: String,
        message: String
    ) {
        times.forEach { t ->
            scheduleDaily(context, t.hour, t.minute, title, message)
        }
    }

    fun cancelDaily(context: Context, hour: Int, minute: Int) {
        val name = uniqueName(hour, minute)
        WorkManager.getInstance(context).cancelUniqueWork(name)
    }
    private fun uniqueName(hour: Int, minute: Int) = "daily_${hour}_${minute}"

    private fun delayUntilNextOccurrence(hour: Int, minute: Int): Long {
        val zone = ZoneId.systemDefault()
        val now = ZonedDateTime.now(zone)
        var next = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0)
        if (!next.isAfter(now)) next = next.plusDays(1)
        return Duration.between(now, next).toMillis().coerceAtLeast(0L)
    }
}