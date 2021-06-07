package com.example.stand_up.activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.stand_up.R
import com.example.stand_up.util.Constants
import com.example.stand_up.util.StandUpPreferenceManager
import com.example.stand_up.util.alarm.StandUpAlarmReceiver
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // Notification ID.
    private val NOTIFICATION_ID = 0
    // Notification channel ID.
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private var mNotificationManager: NotificationManager? = null

    private lateinit var mStandUpShared: StandUpPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStandUpShared = StandUpPreferenceManager(
            applicationContext
        )

        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val alarmToggle = findViewById<ToggleButton>(R.id.alarmToggle)

        // Set up the Notification Broadcast Intent.
        val notifyIntent = Intent(this, StandUpAlarmReceiver::class.java)

        val alarmUp = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null
        alarmToggle.isChecked = alarmUp

        val notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val startedTime = mStandUpShared.fromPreference(Constants.STARTED_TIME, "00-00-00 00:00:00")
        tvLabel.text = getString(R.string.every_60_seconds_napp_will_made_network_call_s, startedTime)

        alarmToggle.setOnClickListener {
            if(alarmToggle.isChecked) {
                val cal: Calendar = Calendar.getInstance(TimeZone.getDefault())
                val currentLocalTime: Date = cal.getTime()
                val date: DateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                date.timeZone = TimeZone.getDefault()
                val localTime: String = date.format(currentLocalTime)

                mStandUpShared.toPreference(Constants.STARTED_TIME, localTime)

                val startedTime = mStandUpShared.fromPreference(Constants.STARTED_TIME, "00-00-00 00:00:00")
                tvLabel.text = getString(R.string.every_60_seconds_napp_will_made_network_call_s, startedTime)
            }
        }

        // Set the click listener for the toggle button.
        alarmToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            val toastMessage: String
            toastMessage = if (isChecked) {
                val INTERVAL_15_SECONDS = 60 * 1000;
                val repeatInterval = INTERVAL_15_SECONDS
                val triggerTime = (SystemClock.elapsedRealtime()
                        + repeatInterval)

                // If the Toggle is turned on, set the repeating alarm with
                // a 15 minute interval.
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        triggerTime.toLong(), repeatInterval.toLong(),
                        notifyPendingIntent)
                // Set the toast message for the "on" case.
                getString(R.string.alarm_on_toast)
            } else {
                mStandUpShared.toPreference(Constants.STARTED_TIME, "00-00-00 00:00:00")
                val startedTime = mStandUpShared.fromPreference(Constants.STARTED_TIME, "00-00-00 00:00:00")
                tvLabel.text = getString(R.string.every_60_seconds_napp_will_made_network_call_s, startedTime)

                mStandUpShared.toPreference(Constants.COUNTER, "0")
                val counter = mStandUpShared.fromPreference(Constants.COUNTER, "0")
                tvCounter.text = counter

                // Cancel notification if the alarm is turned off.
                mNotificationManager!!.cancelAll()
                alarmManager?.cancel(notifyPendingIntent)
                // Set the toast message for the "off" case.
                getString(R.string.alarm_off_toast)
            }

            // Show a toast to say the alarm is turned on or off.
            Toast.makeText(this@MainActivity, toastMessage,
                    Toast.LENGTH_SHORT).show()
        }

        // Create the notification channel.
        createNotificationChannel()

        btnRefreshCounter.setOnClickListener {
            val counter = mStandUpShared.fromPreference(Constants.COUNTER, "0")
            tvCounter.text = counter
        }
    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    fun createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Stand up notification",
                    NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifies every 15 minutes to " +
                    "stand up and walk"
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }
    }
}