package com.example.rahulkumar.test.commoncontroller

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.rahulkumar.test.database.Report

/**
 * Created by rahulkumar on 21/04/18.
 */
interface ICommonView {

    fun addDataToRoom(report: Report):LiveData<Long>
    fun getDataFromDatabase():LiveData<List<Report>>
    fun fetchDataBasedOnId(id:Int):LiveData<Report>
    fun updateData(report: Report) : LiveData<Int>
    fun updateMedicineStatus(context: Context,id:Int,status:String)
}