package ru.startandroid.develop.pendingintent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Receiver: BroadcastReceiver() {
    val LOG_TAG = "myLogs"

    override fun onReceive(ctx: Context?, intent: Intent?) {
        Log.d(LOG_TAG, "onReceive")
        Log.d(LOG_TAG, "action = ${intent!!.action}")
        Log.d(LOG_TAG, "extra = ${intent.getStringExtra("extra")}")
    }
}