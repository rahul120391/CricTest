package com.example.rahulkumar.test.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import com.example.rahulkumar.test.R
import com.example.rahulkumar.test.commoncontroller.CommonUseCaseController
import com.example.rahulkumar.test.database.ReportDao
import com.example.rahulkumar.test.utils.Constants
import com.example.rahulkumar.test.utils.Utility
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by rahulkumar on 21/04/18.
 */
class NotificationReciever : BroadcastReceiver() {

    @Inject
    lateinit var commonUseCaseController: CommonUseCaseController

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this,context)
        if(intent!=null && intent.getStringExtra(Constants.STATUS)!=null){
              Utility.cancelNotification(context!!,intent.getIntExtra(Constants.ID,0))
              commonUseCaseController.updateMedicineStatus(context!!,intent.getIntExtra(Constants.ID,0),intent.getStringExtra(Constants.STATUS))
        }

    }
}