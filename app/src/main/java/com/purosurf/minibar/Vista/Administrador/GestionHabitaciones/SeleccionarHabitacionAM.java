package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Adaptadores.SeleccionarHabitacionAMAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarHabitacionAM extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarSelectGH;
    TextView tvSeleccionarGH;
    RecyclerView rvHabitacionesGH;

    //List<Habitacion>
    List<Habitacion> listadoHabitaciones;

    //Adaptador RecyclerView
    SeleccionarHabitacionAMAdapter rvHabitacionesAdapter;

    //Variables
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_habitacion_am);

        btnRegresarSelectGH = findViewById(R.id.btnRegresarSelecGH);
        tvSeleccionarGH = findViewById(R.id.tvSeleccionarGH);
        rvHabitacionesGH = findViewById(R.id.rvHabitacionesGH);

        //obtener intent
        datos = getIntent().getExtras();
        String enunciado="";
        if(datos.getString("accion").equals("deshabilitar")){
            enunciado = "Deshabilitar Habitaciones";
        }else if(datos.getString("accion").equals("actualizar")){
            enunciado = "Actualizar Habitaciones";
        }else if(datos.getString("accion").equals("listar")){
            enunciado = "Listado de Habitaciones";
        }else if(datos.getString("accion").equals("minibar")){
            enunciado = "Editar Mini-Bar";
        }
        tvSeleccionarGH.setText(enunciado); //enunciado habitación

        //Llenar lista
        listadoHabitaciones = new ArrayList<Habitacion>();
        for(int i = 0; i <= 10; i++ ){
            listadoHabitaciones.add(new Habitacion(i, "Habitacion #"+i, 1));
        }
        //Asignar adaptador al RecyclerView
        rvHabitacionesAdapter = new SeleccionarHabitacionAMAdapter(listadoHabitaciones, this);
        rvHabitacionesGH.setHasFixedSize(false);
        rvHabitacionesGH.setLayoutManager(new LinearLayoutManager(this));
        rvHabitacionesGH.setAdapter(rvHabitacionesAdapter); //asignamos adaptado

        //evento click o seleccionar habitacion
        rvHabitacionesAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id habitacion
                Toast.makeText(getApplicationContext(), ""+listadoHabitaciones.get(rvHabitacionesGH.getChildAdapterPosition(view)).getIdHabitaccion(), Toast.LENGTH_SHORT).show();

                Intent accion;
                if(datos.getString("accion").equals("deshabilitar")){
                     accion = new Intent(getApplicationContext(), DeshabilitarHabitacion.class);
                     accion.putExtra("accion","deshabilitar"); //accion = deshabilitar
                     lanzarActividad.launch(accion);
                }else if(datos.getString("accion").equals("actualizar")){
                    accion = new Intent(getApplicationContext(), ActualizarHabitacion.class);
                    lanzarActividad.launch(accion);
                }else if(datos.getString("accion").equals("listar")){
                    accion = new Intent(getApplicationContext(), DetalleHabitacion.class);
                    lanzarActividad.launch(accion);
                }else if(datos.getString("accion").equals("minibar")){
                    accion = new Intent(getApplicationContext(), DeshabilitarHabitacion.class);
                    accion.putExtra("accion","minibar"); //accion = minibar
                    lanzarActividad.launch(accion);
                }
            }
        });

        btnRegresarSelectGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //lanzador de actividades
    /*  code 1 = adicionar
     *   code 2 = deshabilitar
     *   code 3 = actualizar
     *   code 4 = listar
     *   code 5 = editar minibar
     * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        } if(result.getResultCode() == 2){
                            setResult(2);
                            finish();
                        }else if(result.getResultCode() == 3){
                            setResult(3);
                            finish();
                        }else if(result.getResultCode() == 4){
                            finish();
                        }else if(result.getResultCode() == 5) {
                            setResult(5);
                            finish();
                        }

                    }
                }
    );

}