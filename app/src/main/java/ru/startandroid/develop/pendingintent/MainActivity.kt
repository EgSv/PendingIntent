package ru.startandroid.develop.pendingintent

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    var nm: NotificationManager? = null
    var am: AlarmManager? = null
    var intent1: Intent? = null
    var intent2: Intent? = null
    var pIntent1: PendingIntent? = null
    var pIntent2: PendingIntent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        am = getSystemService(ALARM_SERVICE) as AlarmManager
    }

    fun onClick1(view: View?) {
        intent1 = createIntent("action 1", "extra 1")
        pIntent1 = PendingIntent.getBroadcast(this, 0, intent1!!, 0)
        intent2 = createIntent("action 2", "extra 2")
        pIntent2 = PendingIntent.getBroadcast(this, 0, intent2!!, PendingIntent.FLAG_UPDATE_CURRENT)
        //pIntent2 = PendingIntent.getBroadcast(this, 0, intent2!!, PendingIntent.FLAG_CANCEL_CURRENT)
        //pIntent2 = PendingIntent.getBroadcast(this, 0, intent2!!, 0)
        compare()
        sendNotif(1, pIntent1)
        sendNotif(2, pIntent2)
    }

    fun onClick2(view: View?) {}
    fun createIntent(action: String?, extra: String?): Intent {
        val intent = Intent(this, Receiver::class.java)
        intent.action = action
        intent.putExtra("extra", extra)
        return intent
    }

    fun compare() {
        Log.d(LOG_TAG, "intent1 = intent2: " + intent1!!.filterEquals(intent2))
        Log.d(LOG_TAG, "pIntent1 = pIntent2: " + (pIntent1 == pIntent2))
    }

    fun sendNotif(id: Int, pIntent: PendingIntent?) {
        val notif = Notification(R.drawable.ic_launcher, "Notif "
                + id, System.currentTimeMillis())
        notif.flags = notif.flags or Notification.FLAG_AUTO_CANCEL
        notif.setLatestEventInfo(this, "Title $id", "Content $id", pIntent)
        nm!!.notify(id, notif)
    }
}