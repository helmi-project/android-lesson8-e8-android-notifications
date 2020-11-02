package com.androidatc.lesson08_e8_androidnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun courseupdate(view: View) {
        val id = "my_channel_01"

        // The user-visible name of the channel
        val name = getString(R.string.abc_action_bar_home_description)

        // The user-visible description of the channel
        val description = getString(R.string.abc_action_bar_home_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)

        // Configure the notification channel
        mChannel.description = description
        mChannel.enableLights(true)

        // Sets the notification light color for notifications posted to this channel,
        //if the device supports this feature
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)

        /* Generate the notification channel and the notification message */

        // The id of the channel
        val CHANNEL_ID = "my_channel_01"

        // Use NotificationCompat.Builder to add the notification objects
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentTitle("Android ATC Notification")
            .setContentText("Check Android ATC New Course !!")

        // Creates an explicit intent for an Activity in your app
        val resultIntent = Intent(this, ResultActivity::class.java)

        /*
        The stack builder object will contain an artificial back stack for the started Activity.
        This ensures that navigating backward from the Activity leads out of your app to the
        Home Screen.
         */
        val stackBuilder = TaskStackBuilder.create(this)

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        mBuilder.setContentIntent(resultPendingIntent)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Tambahkan code untuk membuat notification channel
        mNotificationManager.createNotificationChannel(mChannel)

        /*
        mNotificationId is a unique integer your app uses to identify the notification.
        For example, to cancel the notification, you can pass its ID number to
        NotificationManager.cancel().
         */
        mNotificationManager.notify(1, mBuilder.build())
    }
}
