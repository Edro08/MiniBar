package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class DeshabilitarHabitacion extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarDH, btnConfirmarDH;
    TextView tvHabitacionDH;
    Switch swActivoDH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshabilitar_habitacion);

        btnRegresarDH = findViewById(R.id.btnRegresarDH);
        btnConfirmarDH = findViewById(R.id.btnConfirmarDH);
        tvHabitacionDH = findViewById(R.id.tvHabitacionDH);
        swActivoDH = findViewById(R.id.swActivoDH);

        tvHabitacionDH.setText("Habitación: Nombre Habitación");

        //evento botones
        btnConfirmarDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2); //code deshabilitar
                finish();
            }
        });

        btnRegresarDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}