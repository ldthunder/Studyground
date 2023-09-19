package com.example.sg.workers

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.sg.R
import com.example.sg.domain.models.Demon
import com.example.sg.domain.use_case.database_use_cases.UpsertAllUseCase
import com.example.sg.domain.use_case.network_use_cases.FetchDemonsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import java.io.IOException

@HiltWorker
class UpdateByNetworkWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val fetchDemonsUseCase: FetchDemonsUseCase,
    private val upsertAllUseCase: UpsertAllUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        setForeground(getForegroundInfo())

        val response: List<Demon> = try {
            fetchDemonsUseCase()
        } catch (e: IOException) {
            return Result.failure()
        }

        return try {
            upsertAllUseCase(response)
            delay(2000)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            NOTIFICATION_ID, createNotification()
        )
    }

    private fun createNotification(): Notification {
        // PendingIntent нужен для отмены извне
        val intent = WorkManager.getInstance(applicationContext)
            .createCancelPendingIntent(id)

        val id = applicationContext.getString(R.string.notification_channel_id)
        val title = applicationContext.getString(R.string.notification_title)
        val cancel = applicationContext.getString(R.string.cancel_download)

        return NotificationCompat.Builder(applicationContext, id)
            .setContentTitle(title)
            .setTicker(title)
            .setContentText("Загружается")
            .setSmallIcon(R.mipmap.elinaicolast)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_delete, cancel, intent)
            .build()
    }

    companion object {
        const val NAME = "UpdateByNetwork"
        const val NOTIFICATION_ID = 1
    }
}