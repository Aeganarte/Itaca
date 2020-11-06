package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuSinConexion extends AppCompatActivity {
    Button tareas;
    Button horario;
    Button qrCodes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sin_conexion);

        tareas= findViewById(R.id.botonTarea);
        horario=findViewById(R.id.botonHorario);
        qrCodes=findViewById(R.id.botonQr);

        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuSinConexion.this,Tarea.class));
            }
        });

        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MenuSinConexion.this,Foto.class));
            }
        });

        qrCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuSinConexion.this,qrCodes.class));
            }
        });

    }

}