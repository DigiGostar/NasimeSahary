package com.armandl.nasimesahary.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.armandl.nasimesahary.utils.UpdateUtils;
import com.armandl.nasimesahary.utils.Utils;

import java.lang.ref.WeakReference;

/**
 * The Calendar Service that updates widget time and clock and build/update
 * calendar notification.
 *
 * @author Ebrahim Byagowi <ebrahim@byagowi.com>
 */
public class ApplicationService extends Service {

    private static WeakReference<ApplicationService> instance;
    private final BroadcastReceivers receiver = new BroadcastReceivers();

    @Nullable
    public static ApplicationService getInstance() {
        return instance == null ? null : instance.get();
    }

    @Override
    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        instance = new WeakReference<>(this);
        Log.d(ApplicationService.class.getName(), "start");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
//        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(receiver, intentFilter);

        Utils.updateStoredPreference(getApplicationContext());
        Utils.loadApp(this);
        UpdateUtils.update(getApplicationContext(), true);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        try {
            unregisterReceiver(receiver);
        } catch (Exception ignore) {
            // Really can't do much here
        }
        super.onDestroy();
    }
}
