package com.example.rahulkumar.test

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import com.example.rahulkumar.test.di.component.DaggerMyAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import javax.inject.Inject

/**
 * Created by rahulkumar on 21/04/18.
 */
class MyApplication() : Application(), HasActivityInjector,HasBroadcastReceiverInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidInjectorReciever: DispatchingAndroidInjector<BroadcastReceiver>


    override fun onCreate() {
        super.onCreate()
        DaggerMyAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
        return dispatchingAndroidInjectorReciever
    }



}