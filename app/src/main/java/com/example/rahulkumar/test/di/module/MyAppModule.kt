package com.example.rahulkumar.test.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.rahulkumar.test.commoncontroller.CommonUseCaseController
import com.example.rahulkumar.test.database.MyDatabase
import com.example.rahulkumar.test.database.ReportDao
import com.example.rahulkumar.test.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by rahulkumar on 21/04/18.
 */

@Module
class MyAppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


    @Singleton
    @Provides
    fun provideDatabase(context: Application): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, Constants.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideReportDao(myDatabase: MyDatabase): ReportDao {
        return myDatabase.getReportDao()
    }

    @Singleton
    @Provides
    fun provideCommonController(reportDao: ReportDao):CommonUseCaseController{
        return CommonUseCaseController(reportDao)
    }


}