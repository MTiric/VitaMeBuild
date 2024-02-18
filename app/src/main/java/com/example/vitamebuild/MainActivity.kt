package com.example.vitamebuild

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.AndroidException
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.rememberNavController
import com.example.vitamebuild.classes.AndroidDownloader
import com.example.vitamebuild.classes.CryptoAES
import com.example.vitamebuild.generalFunctions.getFoodList
import com.example.vitamebuild.generalFunctions.loadJsonFileFoodData
import com.example.vitamebuild.graphicalInterfaces.navigation.CustomNavHost
import com.example.vitamebuild.ui.theme.VitaMeBuildTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.delay
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.io.Reader
import java.net.Socket
import java.net.URL

import java.security.KeyPairGenerator
import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.util.Locale
import java.util.Scanner
import java.util.concurrent.TimeUnit

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.vitamebuild.screens.historyScreens.HistoryScreen
import com.example.vitamebuild.screens.historyScreens.timeSinceLastMeal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationService : Service() {

    private var isRunning = false
    private lateinit var backgroundThread: Thread
    val CHANNEL_ID = "channelID"
    val NOTIFICATION_ID = 0


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        backgroundThread = Thread {
            GlobalScope.launch {
                while (isRunning) {
                    Thread.sleep(60000*60*24)
                    sendNotification(this@NotificationService, NOTIFICATION_ID)
                }
            }

        }
        backgroundThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        //isRunning = false
        //backgroundThread.interrupt()
    }

    suspend fun sendNotification(context: Context, NOTIFICATION_ID: Int) {

        var lastRecordedMeal = timeSinceLastMeal()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("When did you last eat?")
            .setContentText(lastRecordedMeal)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(NOTIFICATION_ID, notification)

        Log.i("Test_Service", "background service is running...")
        }
}


class MainActivity : ComponentActivity() {

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            val cryptoAES = CryptoAES()

            VitaMeBuildTheme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    ObjectHolder.settings  = ObjectHolder.settings.loadFromIniFile(context)
                    loadJsonFileFoodData(context)
                    createNotificationChannel()
                    val navController = rememberNavController()
                    startService(Intent(this, NotificationService::class.java))





                    /*
                    var messageToEncrypt by remember { mutableStateOf("") }
                    var messageToDecrypt by remember { mutableStateOf("") }

                    Column (modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)){
                        TextField(value = messageToEncrypt, onValueChange = { messageToEncrypt = it},
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Encrypt a string")})

                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Button(onClick = {
                                val bytes = messageToEncrypt.encodeToByteArray()
                                val file = File(filesDir, "secret.txt")
                                if(!file.exists()){
                                    file.createNewFile()
                                }
                                val fos = FileOutputStream(file)

                                messageToDecrypt = cryptoAES.encrypt(
                                    bytes = bytes,
                                    outputStream = fos
                                ).decodeToString()
                            }) {
                                Text(text = "Encyrpt")

                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                val file = File(filesDir, "secret.txt")
                                messageToEncrypt = cryptoAES.decrypt(
                                    inputStream = FileInputStream(file)
                                ).decodeToString()
                            }) {
                                Text(text = "Decrypt")

                            }
                        }
                        Text(text = messageToDecrypt)


                    }*/




                    CustomNavHost(navController = navController)




                }
            }
        }


    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        ObjectHolder.threadPool.shutdown()
        ObjectHolder.threadPool.awaitTermination(1, TimeUnit.MINUTES)
    }

}


fun generateKeyPair(): KeyPair {
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(2048) // You can adjust the key size as needed
    return keyPairGenerator.generateKeyPair()
}

fun signData(data: ByteArray, privateKey: PrivateKey): ByteArray {
    val signature = Signature.getInstance("SHA256withRSA")
    signature.initSign(privateKey)
    signature.update(data)
    return signature.sign()
}


fun verifySignature(data: ByteArray, signature: ByteArray, publicKey: PublicKey): Boolean {
    val verification = Signature.getInstance("SHA256withRSA")
    verification.initVerify(publicKey)
    verification.update(data)
    return verification.verify(signature)
}

@Composable
fun ListItem() {
    var foodDescription by remember { mutableStateOf(ObjectHolder.newMeal.foodContent.description) }

    Column {
        Button(onClick = {
            getFoodList("egg")
            foodDescription = ObjectHolder.foodApiSearch.foods[0].description
        }) {
            Text(text = "Press this button")
        }

        Text(text = foodDescription)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VitaMeBuildTheme {
        val navController = rememberNavController()
        HistoryScreen(navController)
    }
}