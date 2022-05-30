package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

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

public class GestionarUsuarios extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarUsuarios;
    CardView cvAdicionarUsuarios, cvDeshabilitarUsuarios, cvActualizarUsuarios, cvListarUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_usuarios);

        // ASIGNAR ELEMENTOS
        btnRegresarUsuarios = findViewById(R.id.btnRegresarUsuarios);
        cvAdicionarUsuarios = findViewById(R.id.cvAdicionarUsuarios);
        cvDeshabilitarUsuarios = findViewById(R.id.cvDeshabilitarUsuarios);
        cvActualizarUsuarios = findViewById(R.id.cvActualizarUsuarios);
        cvListarUsuarios = findViewById(R.id.cvListarUsuarios);


        //evento boton regresar
        btnRegresarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //evento cardviews
        cvAdicionarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adicionar = new Intent(getApplicationContext(), AdicionarActualizarUsuario.class);
                adicionar.putExtra("accion", "adicionar");
                lanzarActividad.launch(adicionar);
            }
        });

        cvDeshabilitarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deshabilitar = new Intent(getApplicationContext(), SeleccionarUsuario.class);
                deshabilitar.putExtra("accion", "deshabilitar");
                lanzarActividad.launch(deshabilitar);
            }
        });

        cvActualizarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actualizar = new Intent(getApplicationContext(), SeleccionarUsuario.class);
                actualizar.putExtra("accion", "actualizar");
                lanzarActividad.launch(actualizar);
            }
        });

        cvListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listar = new Intent(getApplicationContext(), SeleccionarUsuario.class);
                listar.putExtra("accion", "listar");
                lanzarActividad.launch(listar);
            }
        });

    }

    //lanzador de actividades
    /*  resultCode = 1 -> adicionar
    *   resultCode = 2 -> deshabilitar
    *   resultCode = 3 -> actualizar
    *   resultCode = 4 -> listar
    * */

    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    String mensaje ="";
                    if(result.getResultCode() > 0){
                        if (result.getResultCode() == 1){
                            mensaje = "Nuevo usuario registrado correctamente";
                        }else if (result.getResultCode() == 2){
                            mensaje = "Usuario deshabilitado correctamente";
                        }else if (result.getResultCode() == 5){
                            mensaje = "Usuario habilitado correctamente";
                        }else if (result.getResultCode() == 3){
                            mensaje = "Usuario actualizado correctamente";
                        }else if (result.getResultCode() == 4){
                            //mensaje = "";
                        }
                        Snackbar.make(findViewById(R.id.constraintGestionUsuarios), mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(getColor(R.color.azulOscuro))
                                .setBackgroundTint(getColor(R.color.azulPalido))
                                .show();
                    }
                }

    });
}