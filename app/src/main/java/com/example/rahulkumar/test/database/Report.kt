package com.example.rahulkumar.test.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.rahulkumar.test.utils.Constants

/**
 * Created by rahulkumar on 21/04/18.
 */

@Entity(tableName = Constants.TABLE_NAME)
class Report {

    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @ColumnInfo(name=Constants.MEDICINE_NAME)
    var name:String?=null

    @ColumnInfo(name=Constants.DOSAGE)
    var dosage:String?=null

    @ColumnInfo(name=Constants.DATE)
    var date:String?=null

    @ColumnInfo(name=Constants.STATUS)
    var status:String="Pending"

}