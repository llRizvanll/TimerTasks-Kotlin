package com.timerapp.android.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.timerapp.android.MainActivity
import com.timerapp.android.R
import com.timerapp.android.receiver.SnoozeReceiver

private  val NOTIFICATION_ID = 0
private val REQUEST_ID = 0
private val FLAGS = 0


fun NotificationManager.sendNotification(messageBody : String , applicationContext : Context){
        val contentIntent = Intent(applicationContext,MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        //TODO: add big image
        val taskImage = BitmapFactory.decodeResource(
            applicationContext.resources,
            R.drawable.task_icon
        )

        //TODO: add style
        val bigPicStyle = NotificationCompat.BigPictureStyle()
            .bigPicture(taskImage)
            .bigLargeIcon(null)



        //TODO: add snooze activity meta
        val snoozeIntent = Intent(applicationContext,SnoozeReceiver::class.java)
        val snoozePendingIntent : PendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            REQUEST_ID,
            snoozeIntent,
            FLAGS
        )


        val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.task_notification_channel_id)
        )

            .setSmallIcon(R.drawable.task_icon)
            .setContentTitle(messageBody)
            .setContentText(messageBody)

            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)
            .setStyle(bigPicStyle)
            .setLargeIcon(taskImage)

            .addAction(
                R.drawable.task_icon,
                applicationContext.getString(R.string.snooze),
                snoozePendingIntent
            )

            .setPriority(NotificationCompat.PRIORITY_HIGH)
            notify(NOTIFICATION_ID,builder.build())

}


//TODO: add notification cancell all
fun NotificationManager.cancelNotifications(){
    cancelAll()
}
