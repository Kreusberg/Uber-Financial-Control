package com.fin.uberfinancialcontrol

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.Date
import android.Manifest

class Notification : NotificationListenerService() {

    companion object {
        const val DB_COLLECTION = "Uber_Rides"
        const val TAG = "NotificationListener"
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        val extras = sbn.notification.extras
        val title = extras.getString("android.title") ?: "N/A"
        val text = extras.getCharSequence("android.text")?.toString() ?: "N/A"

        val db = Firebase.firestore
        var ref = db.collection(DB_COLLECTION)

        if (title == "Compra no crédito aprovada") {
            val valor = text.substringAfter("$ ").substringBefore(" ")
                .replace(",", ".").toDoubleOrNull()

            val data = hashMapOf(
                "IncludedDate" to Date(),
                "Title" to title,
                "Description" to text,
                "Value" to valor
            )

            if (valor != null) {
                db.collection(DB_COLLECTION)
                    .add(data)
                    .addOnSuccessListener {
                        Log.i(TAG, "✅ Notification successfully saved!")
                        var builder = NotificationCompat.Builder(this, "1")
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Uber Financial Control")
                            .setContentText("Uber ride value saved on firestore successfully!")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true)

                        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.POST_NOTIFICATIONS)
                            == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                            with(NotificationManagerCompat.from(this)) {
                                notify(2, builder.build())
                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "❌ Error on sending notification", e)
                    }
            }
        }
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.i(TAG, "✅ The service was INITIALIZED successfully")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.e(TAG, "❌ The service was DISCONNECTED")
    }
}
