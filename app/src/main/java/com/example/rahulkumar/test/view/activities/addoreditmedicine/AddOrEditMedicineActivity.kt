package com.example.rahulkumar.test.view.activities.addoreditmedicine

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.rahulkumar.test.R
import com.example.rahulkumar.test.database.Report
import com.example.rahulkumar.test.presenter.addmedicine.AddOrEditMedicinePresenter
import com.example.rahulkumar.test.utils.Constants
import com.example.rahulkumar.test.utils.Utility
import dagger.android.AndroidInjection
import javax.inject.Inject

class AddOrEditMedicineActivity : AppCompatActivity(), IAddOrEditMedicine {


    @BindView(R.id.etMedicineName)
    lateinit var mEtMedicineName: AppCompatEditText

    @BindView(R.id.etDosage)
    lateinit var mEtDosage: AppCompatEditText


    @BindView(R.id.etDateTime)
    lateinit var mEtDateTime: AppCompatEditText

    @BindView(R.id.toolBar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.tvTitle)
    lateinit var mTitle:AppCompatTextView

    @Inject
    lateinit var mAddMedicinePresenter: AddOrEditMedicinePresenter

    val mReportObserver = Observer<Report> { it ->
        mEtMedicineName.setText(it?.name)
        mEtDosage.setText(it?.dosage)
        mEtDateTime.setText(it?.date)
    }
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)
        ButterKnife.bind(this)
        initViews()
    }

    override fun getMedicineName(): String {
        return mEtMedicineName.text.trim().toString()
    }

    override fun getDoseQunatity(): String {
        return mEtDosage.text.trim().toString()
    }

    override fun getDateTime(): String {
        return mEtDateTime.text.toString()
    }

    @OnClick(R.id.btnSubmit)
    fun Submit() {
        if (bundle.getString(Constants.ACTION) != null && bundle.getString(Constants.ACTION).equals(Constants.ADD)) {
            mAddMedicinePresenter.addData()
        } else if (bundle.getString(Constants.ACTION) != null && bundle.getString(Constants.ACTION).equals(Constants.EDIT)) {
            mAddMedicinePresenter.updateData(bundle.getInt(Constants.ID))
        }

    }

    @OnClick(R.id.etDateTime)
    fun showCalendarView() {
        mAddMedicinePresenter.showCalendar()
    }

    override fun setDateTime(dateTime: String) {
        mEtDateTime.setText(dateTime)
    }

    override fun initViews() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        if (intent != null) {
            bundle = intent.getBundleExtra(Constants.BUNDLE)
            if (bundle.getString(Constants.ACTION).equals(Constants.EDIT)) {
                mAddMedicinePresenter.getDataBasedOnId(bundle.getInt(Constants.ID))
                mAddMedicinePresenter.mReportLiveData.observe(this, mReportObserver)
                mTitle.text=getString(R.string.edit_medicine)
            }
            else if(bundle.getString(Constants.ACTION).equals(Constants.ADD)){
                mTitle.text=getString(R.string.add_medicine)
            }


        }

    }

    override fun moveToBack(id: Int) {
        setNotification(id, Utility.getTime(mEtDateTime.text.toString()))
        mEtDateTime.setText("")
        mEtDosage.setText("")
        mEtMedicineName.setText("")
        val intent = Intent()
        intent.putExtra(Constants.ID, id)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


    override fun onDestroy() {
        mAddMedicinePresenter.mReportLiveData.removeObserver(mReportObserver)
        super.onDestroy()
    }

    override fun setNotification(id: Int, time: Long) {
        if (bundle.getString(Constants.ACTION) != null && bundle.getString(Constants.ACTION).equals(Constants.EDIT)) {
            Utility.cancelAlram(this@AddOrEditMedicineActivity, id)
        }
        Utility.prepareAlarm(this@AddOrEditMedicineActivity, time, id)
    }

}
