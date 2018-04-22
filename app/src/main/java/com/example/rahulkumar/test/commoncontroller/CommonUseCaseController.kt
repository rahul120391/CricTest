package com.example.rahulkumar.test.commoncontroller

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.rahulkumar.test.database.Report
import com.example.rahulkumar.test.database.ReportDao
import com.example.rahulkumar.test.utils.Utility
import com.example.rahulkumar.test.view.activities.main.MainActivity
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * Created by rahulkumar on 21/04/18.
 */
class CommonUseCaseController @Inject constructor(reportDao: ReportDao) : ICommonView {


    val reportDao = reportDao

    override fun addDataToRoom(report: Report): LiveData<Long> {
        val mLiveData = MutableLiveData<Long>()
        async {
            val value = reportDao.insertData(report)
            mLiveData.postValue(value)
        }
        return mLiveData
    }

    override fun getDataFromDatabase(): LiveData<List<Report>> {
        val mLiveData = MutableLiveData<List<Report>>()
        async {
            val data = reportDao.getReport()
            mLiveData.postValue(data)
        }
        return mLiveData
    }

    override fun fetchDataBasedOnId(id: Int): LiveData<Report> {
        val mLiveData = MutableLiveData<Report>()
        async {
            val data = reportDao.getData(id)
            mLiveData.postValue(data)

        }
        return mLiveData
    }

    override fun updateData(report: Report): LiveData<Int> {
        val mLiveData = MutableLiveData<Int>()
        async {
            val data = reportDao.updateData(report)
            mLiveData.postValue(data)

        }
        return mLiveData
    }

    override fun updateMedicineStatus(context: Context,id: Int, status: String) {
        async {
            val data = reportDao.getData(id);
            data.status = status
            val update = reportDao.updateData(data)

            if (update == 1) {
                 async(UI) {
                 Utility.startActivity(context,MainActivity::class.java)
                }
            }
        }
    }


}