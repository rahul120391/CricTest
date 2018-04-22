package com.example.rahulkumar.test.di.component

import android.app.Application
import com.example.rahulkumar.test.MyApplication
import com.example.rahulkumar.test.di.module.ActivityBuilder
import com.example.rahulkumar.test.di.module.MyAppModule
import com.example.rahulkumar.test.di.module.RecieverBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rahulkumar on 21/04/18.
 */

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, MyAppModule::class, ActivityBuilder::class,RecieverBuilder::class))
interface MyAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): MyAppComponent
    }
    fun inject(app: MyApplication)
}