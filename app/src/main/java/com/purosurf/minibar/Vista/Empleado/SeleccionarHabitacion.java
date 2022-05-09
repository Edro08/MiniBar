package com.purosurf.minibar.Vista.Empleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Adaptadores.HabitacionAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;

public class SeleccionarHabitacion extends AppCompatActivity {

    //ELEMENTOS
    RecyclerView rvSeleccionarSH; //
    Button btnRegresarSH; //boton regresar pantalla anterior

    //Adaptador RecyclerView
    HabitacionAdapter lsHabitacionesRV;

    //Listado de habitaciones
    ArrayList <Habitacion> listadoHabitacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_habitacion);

        //asignar elementos
        rvSeleccionarSH = findViewById(R.id.rvSeleccionarSH);
        btnRegresarSH = findViewById(R.id.btnRegresarSH);

        //llenar listado
        listadoHabitacion = new ArrayList <Habitacion> (); //asignar arraylist
        for(int i = 0; i <= 10; i++){
            listadoHabitacion.add(new Habitacion(i, "Habitacion"+ i, 1));
        }


        //Asignar adaptador a RecyclerView
        lsHabitacionesRV = new HabitacionAdapter(listadoHabitacion, this); //asignamos el adaptador
        rvSeleccionarSH.setHasFixedSize(false);
        rvSeleccionarSH.setLayoutManager(new LinearLayoutManager(this));
        rvSeleccionarSH.setAdapter(lsHabitacionesRV); //asignamos adaptador a RecyclerView

    // seleccionar elemento del recyclerview
        lsHabitacionesRV.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent registrar = new Intent(getApplicationContext(), RegistrarConsumos.class);
              registrarConsumo.launch(registrar);
              Toast.makeText(getApplicationContext(), listadoHabitacion.get(rvSeleccionarSH.getChildAdapterPosition(view)).getNombreHabitacion(), Toast.LENGTH_LONG).show();
          }
        });

        //boton
        btnRegresarSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //lanzar actividad
    ActivityResultLauncher<Intent> registrarConsumo = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if (result.getResultCode() == RESULT_OK){
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
    );





}