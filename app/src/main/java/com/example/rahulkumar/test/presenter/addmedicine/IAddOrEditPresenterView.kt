package com.example.rahulkumar.test.presenter.addmedicine

import android.arch.lifecycle.LiveData

/**
 * Created by rahulkumar on 21/04/18.
 */
interface IAddOrEditPresenterView {

    fun addData()
    fun checkData():Boolean
    fun showCalendar()
    fun getDataBasedOnId(id:Int)
    fun updateData(id: Int)
}