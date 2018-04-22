package com.example.rahulkumar.test.presenter.main

import android.arch.lifecycle.LiveData
import android.os.Bundle
import com.example.rahulkumar.test.database.Report

/**
 * Created by rahulkumar on 21/04/18.
 */
interface IMainPresenterView {

    fun openAddOrEditMedicineActivity(bundle: Bundle)
    fun getDataFromDatabase()
    fun getDataBasedOnId(id:Int)
}