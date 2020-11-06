package com.example.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.CaptureActivity;

public class qrCodes extends AppCompatActivity {
    EditText qrvalue;
    Button generar,escan;
    ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_codes);

        qrvalue=findViewById(R.id.input);
        generar=findViewById(R.id.generar);
        escan=findViewById(R.id.escanear);
        qrImage=findViewById(R.id.plantilla);
        escan=findViewById(R.id.escanear);

        escan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();}
        });

        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeWriter qrCodeWriter=new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = qrCodeWriter.encode(qrvalue.getText().toString(), BarcodeFormat.QR_CODE,200,200);
                    Bitmap bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);

                    for (int x=0;x<200;x++){
                        for (int y=0;y<200;y++){
                            bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK: Color.WHITE);
                        }
                    }

                    qrImage.setImageBitmap(bitmap);

                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Escaneando Resultado");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scan();
                    }
                }).setNegativeButton("Terminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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