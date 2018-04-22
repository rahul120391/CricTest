package com.example.rahulkumar.test.presenter.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import com.example.rahulkumar.test.commoncontroller.CommonUseCaseController
import com.example.rahulkumar.test.database.Report
import com.example.rahulkumar.test.utils.Constants
import com.example.rahulkumar.test.utils.Utility
import com.example.rahulkumar.test.view.activities.addoreditmedicine.AddOrEditMedicineActivity
import com.example.rahulkumar.test.view.activities.main.MainActivity
import javax.inject.Inject

/**
 * Created by rahulkumar on 21/04/18.
 */
class MainPresenter @Inject constructor(context: MainActivity, commonUseCaseController: CommonUseCaseController) : IMainPresenterView {



    val context = context
    val commonUseCaseController = commonUseCaseController
    val mReportListLievData = MutableLiveData<List<Report>>()
    val mReportLiveData = MutableLiveData<Report>()
    override fun openAddOrEditMedicineActivity(bundle: Bundle) {
        Utility.startAcivityForResult(context, AddOrEditMedicineActivity::class.java,bundle)

    }

    override fun getDataFromDatabase() {
        commonUseCaseController.getDataFromDatabase().observe(context, Observer { it ->
            if (it != null) {
                mReportListLievData.value = it
            }
        })

    }

    override fun getDataBasedOnId(id:Int) {
        commonUseCaseController.fetchDataBasedOnId(id).observe(context, Observer {
            if(it!=null){
                mReportLiveData.value=it
            }
        })
    }

}