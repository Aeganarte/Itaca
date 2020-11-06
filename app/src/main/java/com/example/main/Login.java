package com.example.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class Login extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private Button escan;
    private int contador=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        name=findViewById(R.id.user);
        password=findViewById(R.id.idPassword);
        info=findViewById(R.id.attempts);
        login= findViewById(R.id.login);
        escan=findViewById(R.id.escanear);

        info.setText("Numero de intentos restantes: "+contador);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });

        escan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();}
        });
    }

    private void validate(String user,String userPassword){
        if (user.equals("Alex") && userPassword.equals("12345")){
            Intent intent= new Intent(Login.this, MenuWifi.class);
            startActivity(intent);
        } else{
            contador--;
            info.setText(userPassword);
            if (contador == 0){
                login.setEnabled(false);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String div=result.getContents();
                String[] campos=div.split(";");
                validate(campos[0],campos[1]);
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