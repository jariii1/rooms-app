package com.example.tabletapp.concurrency;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class AppExecutors {
    private static final AppExecutors INSTANCE = new AppExecutors();
    private final ExecutorService workers = Executors.newFixedThreadPool(3);
    private final Handler mainThread = new Handler(Looper.getMainLooper());
    private AppExecutors() {}
    public static AppExecutors getInstance(){

        return INSTANCE;
    }
    public void runOnWorker(Runnable task){ //send task to a background thread
        workers.execute(task); // pool picks a free background thread and run task there (no UI)
    }
    public void runOnMain(Runnable task){
        mainThread.post(task);// adds task to the UI thread queue
    }
}
