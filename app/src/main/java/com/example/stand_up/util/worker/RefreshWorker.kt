package com.example.stand_up.util.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.stand_up.data.model.StandUpAppModel

class RefreshWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {
        val appContext = applicationContext

        Log.d("ResourcesResponse", "refreshing data....")

        StandUpAppModel.initStandUpAppModel(appContext)
        StandUpAppModel.getInstance().callDummyBreedList()

        return Result.success()
    }
}