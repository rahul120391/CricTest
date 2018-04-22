package com.example.rahulkumar.test.view.activities.addoreditmedicine

import android.os.Bundle

/**
 * Created by rahulkumar on 21/04/18.
 */
interface IAddOrEditMedicine {

      fun getMedicineName():String
      fun getDoseQunatity():String
      fun getDateTime():String
      fun setDateTime(dateTime:String)
      fun initViews()
      fun moveToBack(id:Int)
      fun setNotification(id:Int,time:Long)
}