package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuWifi extends AppCompatActivity {
    Button tareas;
    Button horario;
    Button qrCodes;
    Button foro;
    Button enlaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wifi);

        tareas= findViewById(R.id.tareas);
        horario=findViewById(R.id.horarios);
        qrCodes=findViewById(R.id.crearqr);
        foro=findViewById(R.id.foro);
        enlaces=findViewById(R.id.enlaces);

        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuWifi.this,Tarea.class));
            }
        });

        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuWifi.this,Foto.class));
            }
        });

        qrCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuWifi.this,qrCodes.class));
            }
        });
        foro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuWifi.this,Foro.class));
            }
        });

        enlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuWifi.this,Enlaces.class));
            }
        });

    }
    }
