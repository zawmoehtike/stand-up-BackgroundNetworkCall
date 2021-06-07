package com.example.stand_up.util.worker

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

object RefreshScheduler {
    fun refreshCouponOneTimeWork() {

        //worker input
        val source = Data.Builder()
            .build()

        //One time work request
        val refreshCpnWork = OneTimeWorkRequest.Builder(RefreshWorker::class.java)
            .setInputData(source)
            .build()

        //enqueue the work request
        WorkManager.getInstance().enqueue(refreshCpnWork)
    }
}