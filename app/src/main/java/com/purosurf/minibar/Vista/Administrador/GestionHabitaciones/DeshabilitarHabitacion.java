package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.DeshabilitarHabitacionPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IDeshabilitarHabitacion_View;

import java.util.ArrayList;
import java.util.List;

public class DeshabilitarHabitacion extends AppCompatActivity implements IDeshabilitarHabitacion_View {

    //ELEMENTOS
    Button btnRegresarDH, btnConfirmarDH;
    TextView tvHabitacionDH, tvEnunciadobarDH;
    Switch swActivoDH;

    //VARIABLES
    String accion, nombrehabitacion;
    int idHabitacion, idEstado;
    List<Habitacion> datosHabitacion; //Lista para datos de habitacion

    Bundle datos;//BUNDLE
    DeshabilitarHabitacionPresentador deshabilitarHabitacionPresentador; //Objeto de la clase DeshabilitarHabitacionPresentador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshabilitar_habitacion);

        //Asignar elementos
        btnRegresarDH = findViewById(R.id.btnRegresarDH);
        btnConfirmarDH = findViewById(R.id.btnConfirmarDH);
        tvHabitacionDH = findViewById(R.id.tvHabitacionDH);
        tvEnunciadobarDH = findViewById(R.id.tvEnunciadobarDH);
        swActivoDH = findViewById(R.id.swActivoDH);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        idHabitacion = datos.getInt("idhabitacion");

        deshabilitarHabitacionPresentador = new DeshabilitarHabitacionPresentador(this);
        datosHabitacion = new ArrayList<>(deshabilitarHabitacionPresentador.DatosHabitacion(getApplicationContext(),
                idHabitacion));
        nombrehabitacion = datosHabitacion.get(0).getNombreHabitacion();
        idEstado = datosHabitacion.get(0).getIdEstado();

        tvHabitacionDH.setText("Habitación: " + nombrehabitacion);

        if (accion.equals("minibar")){
            tvEnunciadobarDH.setText("Activar Habitacion y Mini-Bar");
            btnConfirmarDH.setText("Siguiente");
        }

        if(idEstado == 1)
        {
            swActivoDH.setText("Activo");
            swActivoDH.setChecked(true);
        }
        else
        {
            swActivoDH.setText("Inactivo");
            swActivoDH.setChecked(false);
        }

        swActivoDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swActivoDH.isChecked())
                {
                    swActivoDH.setText("Activo");
                }
                else
                {
                    swActivoDH.setText("Inactivo");
                }
            }
        });


        //evento botones
        btnConfirmarDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("deshabilitar")){
                    boolean estado = false;
                    int code = 2; // code deshabilitar
                    if(swActivoDH.isChecked()){
                        estado = true;
                        code = 6;// code habilitar
                    }
                    deshabilitarHabitacionPresentador.ActivarDesactivarHabitacion(getApplicationContext(),
                            idHabitacion,estado);
                    setResult(code); //code deshabilitar
                    finish();
                }else if (accion.equals("minibar")){

                    boolean estado = false;
                    if(swActivoDH.isChecked()){
                        estado = true;
                        deshabilitarHabitacionPresentador.ActivarDesactivarHabitacion(getApplicationContext(),
                                idHabitacion,estado);
                        Intent minibar = new Intent(getApplicationContext(), InventarioMB.class);
                        lanzarActividad.launch(minibar);
                    } else{
                        Toast.makeText(getApplicationContext(),"Se necesita habilitar la habitacion para editar el mini bar",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegresarDH.setOnClickListener(new View.OnClickListener() {
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
                     if(result.getResultCode() == 5) {
                        setResult(5);
                        finish();
                    }
                }
            }
    });


}