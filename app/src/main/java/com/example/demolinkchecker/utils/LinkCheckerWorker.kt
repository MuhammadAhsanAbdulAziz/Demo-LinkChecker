package com.example.demolinkchecker.utils

import android.content.Context
import androidx.activity.viewModels
import androidx.hilt.work.HiltWorker
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.demolinkchecker.api.LinkCheckerApi
import com.example.demolinkchecker.repository.CheckLinkRepository
import com.example.demolinkchecker.viewmodel.LinkViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltWorker
class LinkCheckWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val linkCheckerApi: LinkCheckerApi
) : Worker(context, params) {

    override fun doWork(): Result {
        // Retrieve link URL and interval from input data
        val linkUrl = inputData.getString("link_url") ?: return Result.failure()
        val intervalMinutes = inputData.getInt("interval_minutes", 15)

        // Perform the link checking operation
        checkLink(linkUrl)

        // Reschedule the work if needed
        rescheduleWork(linkUrl, intervalMinutes)

        return Result.success()
    }

    private fun checkLink(linkUrl: String) {

    }

    private fun rescheduleWork(linkUrl: String, intervalMinutes: Int) {
        val newWorkRequest = PeriodicWorkRequestBuilder<LinkCheckWorker>(intervalMinutes.toLong(), TimeUnit.MINUTES)
            .setInputData(
                Data.Builder()
                    .putString("link_url", linkUrl)
                    .putInt("interval_minutes", intervalMinutes)
                    .build()
            )
            .build()

        WorkManager.getInstance(applicationContext).enqueue(newWorkRequest)
    }
}