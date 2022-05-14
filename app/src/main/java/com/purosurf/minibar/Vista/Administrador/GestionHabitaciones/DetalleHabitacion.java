 package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.purosurf.minibar.R;

 public class DetalleHabitacion extends AppCompatActivity {

     //ELEMENTOS
     Button btnRegresarDEH;
     TextView tvNombreHabitacionDEH, tvEstadoHabitacionDEH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_habitacion);

        //ASIGNAR ELEMENTOS
        btnRegresarDEH = findViewById(R.id.btnRegresarDEH);
        tvNombreHabitacionDEH = findViewById(R.id.tvNombreHabitacionDEH);
        tvEstadoHabitacionDEH = findViewById(R.id.tvEstadoHabitacionDEH);

        tvNombreHabitacionDEH.setText("Habitación: Nombre Habitación");
        tvEstadoHabitacionDEH.setText("Estado: Activo");

        btnRegresarDEH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(4); //code listar
                finish();
            }
        });


    }
}