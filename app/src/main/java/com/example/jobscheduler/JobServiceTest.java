package com.example.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class JobServiceTest extends JobService {

    private static final String TAG ="JobTest";
    private boolean durdur =  false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Başladı");
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i<10;i++){
                    Log.d(TAG,"çalışıyor"+i);
                    if (durdur){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Tamamlanmadan bitti !!");
                jobFinished(params,false);
            }
        }).start();

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "durduruldu !!");
        durdur = true;

        return true;
    }
}
