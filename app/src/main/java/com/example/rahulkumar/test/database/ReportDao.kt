package com.example.rahulkumar.test.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.rahulkumar.test.utils.Constants

/**
 * Created by rahulkumar on 21/04/18.
 */

@Dao
interface ReportDao {

    @Query("select * from "+Constants.TABLE_NAME)
    fun getReport():List<Report>

    @Query("select * from "+Constants.TABLE_NAME+" where id =:id")
    fun getData(id:Int):Report

    @Insert
    fun insertData(myreport: Report):Long

    @Update
    fun updateData(myreport: Report):Int
}