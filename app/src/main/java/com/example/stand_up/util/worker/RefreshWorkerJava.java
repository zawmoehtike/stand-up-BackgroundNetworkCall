package com.example.stand_up.util.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshWorkerJava extends Worker {

    public RefreshWorkerJava(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {
        Context context = getApplicationContext();

        Log.d("RefreshDataWorker", "refreshing data....");
        return Worker.Result.success();
    }
}
