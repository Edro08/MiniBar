 package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.DetalleHabitacionPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IDetalleHabitacion_View;

import java.util.ArrayList;
import java.util.List;

 public class DetalleHabitacion extends AppCompatActivity implements IDetalleHabitacion_View {

     //ELEMENTOS
     Button btnRegresarDEH;
     TextView tvNombreHabitacionDEH, tvEstadoHabitacionDEH;
     List<Habitacion> listaHabitacion;
     Bundle data;
     DetalleHabitacionPresentador detalleHabitacionPresentador;
     int idHabitacion, idEstado;
     String nombreHabitacion;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_habitacion);

        //ASIGNAR ELEMENTOS
        btnRegresarDEH = findViewById(R.id.btnRegresarDEH);
        tvNombreHabitacionDEH = findViewById(R.id.tvNombreHabitacionDEH);
        tvEstadoHabitacionDEH = findViewById(R.id.tvEstadoHabitacionDEH);

        //extraemos los datos bungle
        data = getIntent().getExtras();
        idHabitacion = data.getInt("idhabitacion");
        //Llenar lista
        detalleHabitacionPresentador = new DetalleHabitacionPresentador(this);
        listaHabitacion = new ArrayList<>(detalleHabitacionPresentador.datosHabitacion(getApplicationContext(), idHabitacion));

        nombreHabitacion = listaHabitacion.get(0).getNombreHabitacion();
        idEstado= listaHabitacion.get(0).getIdEstado();

        tvNombreHabitacionDEH.setText("Habitación: " + nombreHabitacion);
        if(idEstado == 1)
            tvEstadoHabitacionDEH.setText("Estado: Activo");
        else {
            tvEstadoHabitacionDEH.setText("Estado: Inactivo");
        }

        btnRegresarDEH.setOnClickListener(view -> {
            setResult(4); //code listar
            finish();
        });


    }
}