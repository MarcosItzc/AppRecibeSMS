package net.n_21011018.recibirsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSMSStatePermission(); // <-- AÑADE ESTA LÍNEA
    }

    private void checkSMSStatePermission(){
        int permissionCheck= ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECEIVE_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("mensaje", "No tienes permiso de recibir SMS");
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECEIVE_SMS}, 255);
        } else {
            Log.i("Mensaje", "Si tienes permiso para recibir SMS");
        }

        permissionCheck= ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("mensaje", "No tienes permiso de leer SMS");
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.READ_SMS}, 255);
        } else {
            Log.i("Mensaje", "Si tienes permiso para leer SMS");
        }
    }
}