package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tarea extends AppCompatActivity {
    Button manual;
    Button scanTarea;
    EditText insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
        createNotificationChannel();
        insert=findViewById(R.id.date);
        manual=findViewById(R.id.addTarea);
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Tarea.this,Reminder.class);
                PendingIntent pendingInent=PendingIntent.getBroadcast(Tarea.this,0,intent,0);
                AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

                String clave= insert.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(clave);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long millis = date.getTime();
                long timeAtButtonClick= System.currentTimeMillis();

                alarmManager.set(AlarmManager.RTC_WAKEUP,millis,pendingInent);
            }
        });
        scanTarea=findViewById(R.id.qrtarea);
        scanTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    scan();
            }
        });
    }
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name= "Canal de Tareas";
            String description= "Canal para las tareas";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel= null;
            channel = new NotificationChannel("notify",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String div=result.getContents();

                Intent intent= new Intent(Tarea.this,Reminder.class);
                PendingIntent pendingInent=PendingIntent.getBroadcast(Tarea.this,0,intent,0);
                AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(div);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long millis = date.getTime();
                long timeAtButtonClick= System.currentTimeMillis();

                alarmManager.set(AlarmManager.RTC_WAKEUP,millis,pendingInent);
            } else {
                Toast.makeText(this, "No hay resulatdos", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void scan(){
        IntentIntegrator integrator=new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
}