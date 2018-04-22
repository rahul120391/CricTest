package com.example.rahulkumar.test.view.activities.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.rahulkumar.test.R
import com.example.rahulkumar.test.database.Report
import com.example.rahulkumar.test.presenter.main.MainPresenter
import com.example.rahulkumar.test.utils.Constants
import com.example.rahulkumar.test.view.adapters.ReportAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainView {


    @Inject
    lateinit var mainPresenter: MainPresenter


    @BindView(R.id.toolBar)
    lateinit var mToolbar: Toolbar


    @BindView(R.id.rvView)
    lateinit var mRvView: RecyclerView


    lateinit var adapter: ReportAdapter

    var mList = mutableListOf<Report>()

    val mListObserver = Observer<List<Report>?> { it ->
        mList = it?.toMutableList()!!
        adapter.setList(mList)
    }

    val mReportObserver = Observer<Report> { it ->
        if (mRequestCode == Constants.ADD_REQUEST_CODE) {
            mList.add(it!!)
            adapter.setList(mList)
        } else if (mRequestCode == Constants.EDIT_REQUEST_CODE) {
            mList.set(mRowPosition, it!!)
            adapter.setList(mList)
        }
    }
    var mRowPosition = 0
    var mRequestCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        mainPresenter.getDataFromDatabase()
        mainPresenter.mReportListLievData.observe(this, mListObserver)
        initViews()
    }

    @OnClick(R.id.imgAddMedicine)
    fun openAddMedicineActivity() {
        val bundle = Bundle()
        bundle.putInt(Constants.REQUESTCODE, Constants.ADD_REQUEST_CODE)
        bundle.putString(Constants.ACTION, Constants.ADD)
        mainPresenter.openAddOrEditMedicineActivity(bundle)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mRequestCode = requestCode
        if (requestCode == Constants.ADD_REQUEST_CODE || requestCode == Constants.EDIT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val id = data?.getIntExtra(Constants.ID, 0)
                mainPresenter.getDataBasedOnId(id!!)
                mainPresenter.mReportLiveData.observe(this, mReportObserver)
            }
        }
    }

    override fun initViews() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        adapter = ReportAdapter(this)
        val mLayoutManager = LinearLayoutManager(this)
        mRvView.layoutManager = mLayoutManager
        mRvView.adapter = adapter
    }

    override fun onDestroy() {
        mainPresenter.mReportListLievData.removeObserver(mListObserver)
        mainPresenter.mReportLiveData.removeObserver(mReportObserver)
        super.onDestroy()
    }

    override fun editRow(id: Int, position: Int) {
        mRowPosition = position
        val bundle = Bundle()
        bundle.putInt(Constants.REQUESTCODE, Constants.EDIT_REQUEST_CODE)
        bundle.putString(Constants.ACTION, Constants.EDIT)
        bundle.putInt(Constants.ID, id)
        mainPresenter.openAddOrEditMedicineActivity(bundle)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mainPresenter.getDataFromDatabase()
        mainPresenter.mReportListLievData.observe(this, mListObserver)
    }
}
