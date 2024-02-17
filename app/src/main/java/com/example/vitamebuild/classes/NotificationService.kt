package com.example.vitamebuild.classes

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class NotificationService : Service() {

    private var isRunning = false
    private lateinit var backgroundThread: Thread

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        backgroundThread = Thread {
            while (isRunning) {
                sendNotification()
                Thread.sleep(1000)
            }
        }
        backgroundThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        //isRunning = false
        //backgroundThread.interrupt()
    }

    private fun sendNotification() {
        // Implement your notification sending logic here
        // This is just a placeholder method
        Log.i("Test_Service", "background service is running...")
    }
}