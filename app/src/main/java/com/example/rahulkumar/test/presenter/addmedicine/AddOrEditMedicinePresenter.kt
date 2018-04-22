package com.example.rahulkumar.test.presenter.addmedicine

import android.arch.lifecycle.MutableLiveData
import com.example.rahulkumar.test.R
import com.example.rahulkumar.test.commoncontroller.CommonUseCaseController
import com.example.rahulkumar.test.database.Report
import com.example.rahulkumar.test.utils.Utility
import com.example.rahulkumar.test.view.activities.addoreditmedicine.AddOrEditMedicineActivity
import com.example.rahulkumar.test.view.activities.addoreditmedicine.IAddOrEditMedicine
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment
import javax.inject.Inject
import java.util.*


/**
 * Created by rahulkumar on 21/04/18.
 */
class AddOrEditMedicinePresenter @Inject constructor(context: AddOrEditMedicineActivity, iAddMedicine: IAddOrEditMedicine, commonUseCaseController: CommonUseCaseController) : IAddOrEditPresenterView {


    val context = context
    val mAddMedicine = iAddMedicine
    val commonUseCaseController = commonUseCaseController
    val mReportLiveData = MutableLiveData<Report>()
    override fun addData() {
        if (checkData()) {
            var report = Report()
            report.name = mAddMedicine.getMedicineName()
            report.dosage = mAddMedicine.getDoseQunatity()
            report.date = mAddMedicine.getDateTime()
            report.status = context.getString(R.string.pending)
            commonUseCaseController.addDataToRoom(report).observe(context, android.arch.lifecycle.Observer { it ->
                if (it?.toInt() != -1) {
                    Utility.showToast(context, context.getString(R.string.success_add))
                    mAddMedicine.moveToBack(it?.toInt()!!)
                } else {
                    Utility.showToast(context, context.getString(R.string.error))
                }
            })
        }
    }

    override fun updateData(id: Int) {
        if (checkData()) {
            var report = Report()
            report.name = mAddMedicine.getMedicineName()
            report.dosage = mAddMedicine.getDoseQunatity()
            report.id = id
            report.date = mAddMedicine.getDateTime()
            commonUseCaseController.updateData(report).observe(context, android.arch.lifecycle.Observer {
                if (it != -1) {
                    Utility.showToast(context, context.getString(R.string.success_update))
                    mAddMedicine.moveToBack(id)
                } else {
                    Utility.showToast(context, context.getString(R.string.error))
                }
            })
        }
    }

    override fun checkData(): Boolean {
        if (mAddMedicine.getMedicineName() == null || mAddMedicine.getMedicineName().equals("")) {
            Utility.showSnackBar(context, context.getString(R.string.enter_medicine_error))
            return false
        } else if (mAddMedicine.getDoseQunatity() == null || mAddMedicine.getDoseQunatity().equals("")) {
            Utility.showSnackBar(context, context.getString(R.string.enter_dosage_error))
            return false
        } else if (mAddMedicine.getDateTime() == null || mAddMedicine.getDateTime().equals("")) {
            Utility.showSnackBar(context, context.getString(R.string.enter_date_time_error))
            return false
        }
        return true
    }


    override fun showCalendar() {
        val mDateTimeCalendar = SwitchDateTimeDialogFragment.newInstance(context.getString(R.string.set_date_time), context.getString(R.string.ok), context.getString(R.string.cancel))
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        mDateTimeCalendar.startAtCalendarView()
        mDateTimeCalendar.set24HoursMode(true)
        mDateTimeCalendar.minimumDateTime = GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)).time
        mDateTimeCalendar.maximumDateTime = GregorianCalendar(2031, Calendar.DECEMBER, 31).time
        mDateTimeCalendar.setDefaultDateTime(GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)).time)

        mDateTimeCalendar.setOnButtonClickListener(object : SwitchDateTimeDialogFragment.OnButtonClickListener {
            override fun onPositiveButtonClick(date: Date) {
                mAddMedicine.setDateTime(Utility.convertDateToDateTime(date))
            }

            override fun onNegativeButtonClick(date: Date) {
                mAddMedicine.setDateTime(Utility.convertDateToDateTime(date))
            }
        })

        mDateTimeCalendar.show(context.supportFragmentManager, context.getString(R.string.date_time))
    }

    override fun getDataBasedOnId(id: Int) {
        commonUseCaseController.fetchDataBasedOnId(id).observe(context, android.arch.lifecycle.Observer {
            mReportLiveData.value = it
        })
    }


}