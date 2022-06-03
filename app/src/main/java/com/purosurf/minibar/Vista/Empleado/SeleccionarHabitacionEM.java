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
import com.purosurf.minibar.Presentador.Adaptadores.SeleccionarHabitacionEMAdapter;
import com.purosurf.minibar.Presentador.Empleado.SeleccionarHabitacionEMPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Empleado.Interfaces.ISeleccionarHabitacionEM_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarHabitacionEM extends AppCompatActivity implements ISeleccionarHabitacionEM_View {

    //ELEMENTOS
    RecyclerView rvSeleccionarSH; //
    Button btnRegresarSH; //boton regresar pantalla anterior

    //Adaptador RecyclerView
    SeleccionarHabitacionEMAdapter lsHabitacionesRV;
    SeleccionarHabitacionEMPresentador seleccionarHabitacionEMPresentador;

    //Listado de habitaciones
    List<Habitacion> listadoHabitacion;
    int IdHabitaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_habitacion_em);

        //asignar elementos
        rvSeleccionarSH = findViewById(R.id.rvSeleccionarSH);
        btnRegresarSH = findViewById(R.id.btnRegresarSH);
        seleccionarHabitacionEMPresentador = new SeleccionarHabitacionEMPresentador(this);

        //llenar listado
        listadoHabitacion = new ArrayList <Habitacion> (
                seleccionarHabitacionEMPresentador.listaHabitacion(getApplicationContext())); //asignar arraylist

        //Asignar adaptador a RecyclerView
        lsHabitacionesRV = new SeleccionarHabitacionEMAdapter(listadoHabitacion, this); //asignamos el adaptador
        rvSeleccionarSH.setHasFixedSize(false);
        rvSeleccionarSH.setLayoutManager(new LinearLayoutManager(this));
        rvSeleccionarSH.setAdapter(lsHabitacionesRV); //asignamos adaptador a RecyclerView

        // seleccionar elemento del recyclerview (metodo del adaptador)
        lsHabitacionesRV.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent registrar = new Intent(getApplicationContext(), RegistrarConsumos.class);
              IdHabitaccion = listadoHabitacion.get(rvSeleccionarSH.getChildAdapterPosition(view)).getIdHabitaccion();
              registrar.putExtra("IdHabitaccion",IdHabitaccion);
              listadoHabitacion.get(rvSeleccionarSH.getChildAdapterPosition(view)).getIdHabitaccion();  // obtener ID de la habitacion seleccionada
              Toast.makeText(getApplicationContext(), ""+listadoHabitacion.get(rvSeleccionarSH.getChildAdapterPosition(view)).getNombreHabitacion(), Toast.LENGTH_SHORT).show();
              registrarConsumo.launch(registrar);
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