package com.fin.uberfinancialcontrol

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.fin.uberfinancialcontrol.ui.theme.UberFinancialControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("1", "Main Channel", NotificationManager.IMPORTANCE_DEFAULT)
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }

        enableEdgeToEdge()
        setContent {
            UberFinancialControlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NotificationPermissionScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationPermissionScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val isEnabled = isNotificationServiceEnabled(context)
    
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isEnabled) {
            Text(text = "The Notification Service is ON!")
            Text(text = "Waiting notifications...")
        } else {
            Text(text = " The Notification Service is OFF!")
            Button(
                onClick = {
                    context.startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Enable access to notifications")
            }
        }
    }
}

private fun isNotificationServiceEnabled(context: Context): Boolean {
    val packageNames = NotificationManagerCompat.getEnabledListenerPackages(context)
    return packageNames.contains(context.packageName)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UberFinancialControlTheme {
        NotificationPermissionScreen()
    }
}
