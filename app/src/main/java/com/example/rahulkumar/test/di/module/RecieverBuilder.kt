package com.example.rahulkumar.test.di.module

import com.example.rahulkumar.test.recievers.NotificationReciever
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rahulkumar on 21/04/18.
 */

@Module
public abstract class RecieverBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindNotificationReciever(): NotificationReciever
}