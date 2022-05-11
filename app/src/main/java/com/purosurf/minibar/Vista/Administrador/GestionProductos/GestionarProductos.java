package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.R;

public class GestionarProductos extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarProductos; //regresar al menu anterior
    CardView cvAdicionarProductos, cvDeshabilitarProductos, cvActualizarProductos, cvListarProductos; //simula botones de desplazamiento a otras pantallas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_productos);

        //ASIGNAR ELEMENTOS
        btnRegresarProductos = findViewById(R.id.btnRegresarProductos);
        cvAdicionarProductos = findViewById(R.id.cvAdicionarProductos);
        cvDeshabilitarProductos = findViewById(R.id.cvDeshabilitarProductos);
        cvActualizarProductos = findViewById(R.id.cvActualizarProductos);
        cvListarProductos = findViewById(R.id.cvListarProductos);

        //eventos click
        btnRegresarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
            }
        });

        //cardviews
        cvAdicionarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adicionar = new Intent(getApplicationContext(), AdicionarProductos.class);
                lanzarActividad.launch(adicionar);
            }
        });

        cvDeshabilitarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deshabilitar = new Intent(getApplicationContext(), SeleccionarProducto.class);
                deshabilitar.putExtra("accion", "deshabilitar");
                lanzarActividad.launch(deshabilitar);
            }
        });

        cvActualizarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actualizar = new Intent(getApplicationContext(), SeleccionarProducto.class);
                actualizar.putExtra("accion", "actualizar");
                lanzarActividad.launch(actualizar);
            }
        });

        cvListarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listar = new Intent(getApplicationContext(), SeleccionarProducto.class);
                listar.putExtra("accion","listar");
                lanzarActividad.launch(listar);
            }
        });
    }


    //=====================lanzador de actividades
        /*        resultCode = 1 -> adicionar
                  resultCode = 2 -> deshabilitar
                  resultCode = 3 -> actualizar
                  resultCode = 4 -> listar
        */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode() > 0){
                        String mensaje = "";
                        if (result.getResultCode() == 1){
                            mensaje = "Nuevo producto registrado";
                        }else if(result.getResultCode() == 2){
                            mensaje = "Producto Deshabilitado correctamente";
                        }else if(result.getResultCode() == 3){
                            mensaje = "Producto Actualizado Correctamente";
                        }
                        Snackbar.make(findViewById(R.id.constraintGestionProductos), mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(getColor(R.color.azulOscuro))
                                .setBackgroundTint(getColor(R.color.azulPalido))
                                .show();
                    }
                }
            }
    );

}