package fr.parisnanterre.pmoo.androidm2td1.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;


public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new LoggedThread(runnable);
        thread.setPriority(Thread.MAX_PRIORITY);
        return thread;
    }
}
