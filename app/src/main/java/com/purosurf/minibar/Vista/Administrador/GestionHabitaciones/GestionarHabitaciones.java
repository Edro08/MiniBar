package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.R;

public class GestionarHabitaciones extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarGH;
    CardView cvAdicionarHabitaciones, cvDeshabilitarHabitaciones, cvActualizarHabitaciones, cvListarHabitaciones, cvEditarMiniBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_habitaciones);

        //ASIGNAR ELEMENTOS
        btnRegresarGH = findViewById(R.id.btnRegresarGH);
        cvAdicionarHabitaciones = findViewById(R.id.cvAdicionarHabitaciones);
        cvDeshabilitarHabitaciones = findViewById(R.id.cvDeshabilitarHabitaciones);
        cvActualizarHabitaciones = findViewById(R.id.cvActualizarHabitaciones);
        cvListarHabitaciones = findViewById(R.id.cvListarHabitaciones);
        cvEditarMiniBar = findViewById(R.id.cvEditarMiniBar);


        //evento boton regresar pantalla anterior
        btnRegresarGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //evento seleccionar cardview
        cvAdicionarHabitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adicionar = new Intent(getApplicationContext(), AdicionarHabitacion.class);
                lanzarActividad.launch(adicionar);
            }
        });

        cvDeshabilitarHabitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deshabilitar = new Intent(getApplicationContext(), SeleccionarHabitacionAM.class);
                deshabilitar.putExtra("accion","deshabilitar");
                lanzarActividad.launch(deshabilitar);
            }
        });

        cvActualizarHabitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actualizar = new Intent(getApplicationContext(), SeleccionarHabitacionAM.class);
                actualizar.putExtra("accion","actualizar");
                lanzarActividad.launch(actualizar);
            }
        });

        cvListarHabitaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listar = new Intent(getApplicationContext(), SeleccionarHabitacionAM.class);
                listar.putExtra("accion","listar");
                lanzarActividad.launch(listar);
            }
        });

        cvEditarMiniBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent minibar = new Intent(getApplicationContext(), SeleccionarHabitacionAM.class);
                minibar.putExtra("accion","minibar");
                lanzarActividad.launch(minibar);
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
                        String mensaje = "";
                        if(result.getResultCode() == 1){
                            mensaje = "Nueva habitación registrada correctamente";
                        }else if(result.getResultCode() == 2){
                            mensaje = "Habitación deshabilitada correctamente";
                        }
                        else if(result.getResultCode() == 6){
                            mensaje = "Habitación habilitada correctamente";
                        }else if(result.getResultCode() == 3){
                            mensaje = "Habitación actualizada correctamente";
                        }else if(result.getResultCode() == 4){
                            mensaje = "";
                        }else if(result.getResultCode() == 5){
                            mensaje = "Cambios al Mini-Bar realizados correctamente";
                        }
                        Snackbar.make(findViewById(R.id.constraintGestionHabitaciones), mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(getColor(R.color.azulOscuro))
                                .setBackgroundTint(getColor(R.color.azulPalido))
                                .show();
                    }

                }
            }
    );
}