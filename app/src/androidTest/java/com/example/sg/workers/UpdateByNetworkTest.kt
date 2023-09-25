package com.example.sg.workers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkInfo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test

class UpdateByNetworkTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var wmRule = WorkManagerTestRule()

    @Test
    fun testCleanupWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<UpdateByNetworkWorker>()
            .setConstraints(constraints)
            .setExpedited(OutOfQuotaPolicy.DROP_WORK_REQUEST)
            .build()

        wmRule.workManager.enqueue(request).result.get()
        // Get WorkInfo
        val workInfo = wmRule.workManager.getWorkInfoById(request.id).get()

        assertThat(workInfo.state, `is`(WorkInfo.State.ENQUEUED))
    }

}