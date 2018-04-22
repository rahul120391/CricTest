package com.example.rahulkumar.test.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.rahulkumar.test.utils.Constants
import com.example.rahulkumar.test.utils.Utility

/**
 * Created by rahulkumar on 21/04/18.
 */
class ShowNotificationReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Utility.showNotification(context!! , intent?.getIntExtra(Constants.ID, 0)!!)
    }
}