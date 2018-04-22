package com.example.rahulkumar.test.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by rahulkumar on 21/04/18.
 */
@Database(entities = arrayOf(Report::class),version = 1)
abstract class MyDatabase : RoomDatabase(){

   abstract fun getReportDao(): ReportDao
}