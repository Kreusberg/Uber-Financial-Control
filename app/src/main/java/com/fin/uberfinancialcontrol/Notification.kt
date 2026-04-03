package com.fin.uberfinancialcontrol

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.google.firebase.Firebase

class Notification : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        val extras = sbn.notification.extras
        val title = extras.getString("android.title") ?: "Sem título"
        val text = extras.getCharSequence("android.text")?.toString() ?: "Sem conteúdo"
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.i("NotificationListener", "✅ O serviço foi CONECTADO com sucesso")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.e("NotificationListener", "❌ O serviço foi DESCONECTADO")
    }
}
