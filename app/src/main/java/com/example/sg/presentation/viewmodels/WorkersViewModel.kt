package com.example.sg.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.sg.workers.UpdateByNetworkWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkersViewModel @Inject constructor(
    application: Application,
    private val workManager: WorkManager
): ViewModel() {

    internal val state: LiveData<List<WorkInfo>> = workManager.getWorkInfosForUniqueWorkLiveData(
        UpdateByNetworkWorker.NAME
    )

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val updateByNetworkRequest = OneTimeWorkRequestBuilder<UpdateByNetworkWorker>()
        .setConstraints(constraints)
        .build()

    internal fun startWorker(){
        workManager.enqueueUniqueWork(
            UpdateByNetworkWorker.NAME,
            ExistingWorkPolicy.KEEP,
            updateByNetworkRequest
        )
    }

    internal fun cancelWorker(){
        workManager.cancelUniqueWork(UpdateByNetworkWorker.NAME)
    }

}