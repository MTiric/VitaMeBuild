package com.example.vitamebuild.classes

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.example.vitamebuild.generalFunctions.Downloader

class DownloadCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if(id != -1L) {
                Log.i("Test_Download", "download $id finished!")
            }
        }
    }

}
class AndroidDownloader(
    private val context: Context
): Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("application/pdf")
            //.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("recipeMeals.pdf")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "recipeMeals.pdf")
        return downloadManager.enqueue(request)
    }


}