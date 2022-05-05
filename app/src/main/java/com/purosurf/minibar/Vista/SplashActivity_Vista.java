package com.purosurf.minibar.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.purosurf.minibar.Vista.InicioSesion.IniciarSesion;

@SuppressLint("CustomSplashScreen")
public class SplashActivity_Vista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent Login_Vista  = new Intent(this , IniciarSesion.class);
        startActivity(Login_Vista );
        finish();
    }
}