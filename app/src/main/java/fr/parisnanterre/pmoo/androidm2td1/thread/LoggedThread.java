package fr.parisnanterre.pmoo.androidm2td1.thread;

import android.util.Log;

public class LoggedThread extends Thread{
    public LoggedThread(Runnable target) {
        super(target);
        android.os.Process.myTid();
        this.setPriority(android.os.Process.myTid());
    }

    @Override
    public void run() {
        Log.d("DEBUG", "Running tread!");
        super.run();
    }

    @Override
    public void interrupt() {
        Log.d("DEBUG", "Interrupting thread!");
        super.interrupt();
    }
}
