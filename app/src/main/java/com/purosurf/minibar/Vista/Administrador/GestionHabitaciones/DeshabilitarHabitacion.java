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

import com.purosurf.minibar.R;

public class DeshabilitarHabitacion extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarDH, btnConfirmarDH;
    TextView tvHabitacionDH, tvEnunciadobarDH;
    Switch swActivoDH;

    //BUNDLE
    Bundle datos;

    //VARIABLES
    String accion;

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

        tvHabitacionDH.setText("Habitación: Nombre Habitación");

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        if (accion.equals("minibar")){
            tvEnunciadobarDH.setText("Activar Mini-Bar");
            btnConfirmarDH.setText("Siguiente");
        }


        //evento botones
        btnConfirmarDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("deshabilitar")){
                    setResult(2); //code deshabilitar
                    finish();
                }else if (accion.equals("minibar")){
                   Intent minibar = new Intent(getApplicationContext(), InventarioMB.class);
                   lanzarActividad.launch(minibar);
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