package com.example.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static java.security.AccessController.getContext;

public class Reminder extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"notify")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Ey")
                .setContentText("Aviso!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(200,builder.build());
    }
}
