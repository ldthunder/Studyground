package com.example.sg.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.sg.domain.use_case.database_use_cases.UpsertAllUseCase
import com.example.sg.domain.use_case.network_use_cases.FetchDemonsUseCase
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(private val fetchDemonsUseCase: FetchDemonsUseCase,
                                              private val upsertAllUseCase: UpsertAllUseCase
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = UpdateByNetworkWorker(appContext, workerParameters, fetchDemonsUseCase, upsertAllUseCase)
}