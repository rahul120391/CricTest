package com.example.rahulkumar.test.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast
import com.example.rahulkumar.test.R
import com.example.rahulkumar.test.recievers.NotificationReciever
import com.example.rahulkumar.test.recievers.ShowNotificationReciever
import java.text.SimpleDateFormat
import java.util.*
import android.media.RingtoneManager




/**
 * Created by rahulkumar on 21/04/18.
 */
object Utility {

    lateinit var alarmManager:AlarmManager

    fun showSnackBar(context: Activity, message: String) {
        Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    fun convertDateToDateTime(date: Date): String {
        val format = SimpleDateFormat(Constants.DATE_FORMAT)
        return format.format(date)
    }

    fun getTime(date: String): Long {
        val format = SimpleDateFormat(Constants.DATE_FORMAT)
        return format.parse(date).time
    }

    /**
     * Start activity for result intent function
     */
    fun startAcivityForResult(context: Activity, classname: Class<*>,bundle: Bundle) {
        val startActivity = Intent(context, classname)
        startActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity.putExtra(Constants.BUNDLE,bundle)
        context.startActivityForResult(startActivity, bundle.getInt(Constants.REQUESTCODE,0))
    }

    fun startActivity(context: Context, classname: Class<*>){
        val startActivity = Intent(context, classname)
        startActivity.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(startActivity)
    }



    /**
     * Show Notification
     */

    fun showNotification(context: Context, id: Int) {
        val v = longArrayOf(500, 1000)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, id.toString())
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle(context.getString(R.string.app_name))
        builder.setAutoCancel(true)
        builder.setContentText(context.getString(R.string.notification_message))
        builder.priority = NotificationManagerCompat.IMPORTANCE_HIGH
        builder.setAutoCancel(false)
        builder.setVibrate(v)
        builder.setSound(uri)

        val intent = Intent(context, NotificationReciever::class.java)
        intent.action=context.getString(R.string.taken)
        intent.putExtra(Constants.STATUS, context.getString(R.string.taken))
        intent.putExtra(Constants.ID, id)
        val mTakenPendingIntent = PendingIntent.getBroadcast(context, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.addAction(R.mipmap.ic_launcher, context.getString(R.string.taken), mTakenPendingIntent)
        val mSkipPendingIntent = PendingIntent.getBroadcast(context,System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)

        intent.putExtra(Constants.STATUS, context.getString(R.string.skip))
        intent.action=context.getString(R.string.skip)
        builder.addAction(R.mipmap.ic_launcher, context.getString(R.string.skip), mSkipPendingIntent)

        NotificationManagerCompat.from(context).notify(id, builder.build())

    }

    fun cancelNotification(context: Context,id: Int){
        NotificationManagerCompat.from(context).cancel(id)
    }




    /**
     * prepare Alarm
     */
    fun prepareAlarm(context: Activity, time: Long,id:Int) {
        val intent = Intent(context, ShowNotificationReciever::class.java)
        intent.putExtra(Constants.ID,id)
        val pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }

    fun cancelAlram(context: Activity,id:Int){
        val intent = Intent(context, ShowNotificationReciever::class.java)
        intent.putExtra(Constants.ID,id)
        val pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }

    fun showToast(context: Activity,message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}