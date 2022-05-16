package com.purosurf.minibar.Vista.Empleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.GestionarInventarios;


public class MenuEmpleado extends AppCompatActivity {

    //ELEMENTOS
    TextView tvBienvenidoME;
    FloatingActionButton fabCerrarSesionME;
    CardView cvGestionarInventarioME, cvRegistrarConsumosME; //cardview que simulan los botones de navegacion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_empleado);

        //asignar elementos
        tvBienvenidoME = findViewById(R.id.tvBienvenidoME);
        fabCerrarSesionME = findViewById(R.id.fabCerrarSesionSG);
        cvGestionarInventarioME = findViewById(R.id.cvGestionarInventariosME);
        cvRegistrarConsumosME = findViewById(R.id.cvRegistrarConsumosME);

        tvBienvenidoME.setText("¡Bienvenido \" usuario \" !"); //mensaje de bienvenida con el nombre de usuario

        //boton flotante
        fabCerrarSesionME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //cerrar sesion
            }
        });

        //cardview evento click
        cvGestionarInventarioME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inventario = new Intent(getApplicationContext(), GestionarInventarios.class);
                startActivity(inventario);
            }
        });

        cvRegistrarConsumosME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seleccionar = new Intent(getApplicationContext(), SeleccionarHabitacionEM.class);
                seleccionarHabitacion.launch(seleccionar);
            }
        });
    }

    //lanzar actividad
    ActivityResultLauncher<Intent> seleccionarHabitacion = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if (result.getResultCode() == RESULT_OK){
                        //mensaje de cambio de contraseña exitoso
                        Snackbar.make(findViewById(R.id.constraintME), "Registro de consumo realizado correctamente", Snackbar.LENGTH_LONG)
                                .setTextColor(getColor(R.color.azulOscuro))
                                .setBackgroundTint(getColor(R.color.azulPalido))
                                .show();
                    }
                }
            }
    );


}