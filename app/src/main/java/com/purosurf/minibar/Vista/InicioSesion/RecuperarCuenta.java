package com.purosurf.minibar.Vista.InicioSesion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;


public class RecuperarCuenta extends AppCompatActivity {

    //ELEMENTOS
    FrameLayout flRecuperarCuenta; //contenedor que muestra/oculta la pregunta de seguridad si se tiene o no acceso a internet
    TextInputLayout tilCorreoRC, tilRespuestaRC; //contenedor EDT
    TextView tvPreguntaRC; // enunciado de la pregunta de seguridad
    TextInputEditText edtCorreoRC, edtRespuestaRC; //EditText
    Button btnReestablecerRC, btnRegresarRC; //Boton para pasar a la siguiente pantalla

    //variables
    String correo, respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);

        //Asignar Elementos
        flRecuperarCuenta = findViewById(R.id.flRecuperarCuenta);
        tilCorreoRC = findViewById(R.id.tilCorreoRC);
        tilRespuestaRC = findViewById(R.id.tilRespuestaRecuperarCuenta);
        tvPreguntaRC = findViewById(R.id.tvPreguntaRecuperarCuenta);
        edtRespuestaRC = findViewById(R.id.edtRespuestaRecuperar);
        edtCorreoRC = findViewById(R.id.edtCorreoRecuperarCuenta);
        btnReestablecerRC = findViewById(R.id.btnReestablecerRecuperarCuenta);
        btnRegresarRC = findViewById(R.id.btnRegresarRecuperarCuenta);

        //asignar visibilidad a la pregunta de seguridad
        // VISIBLE - visible
        // GONE - OCULTO
        flRecuperarCuenta.setVisibility(View.VISIBLE);

        //metodo del boton
        btnReestablecerRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCorreo();
                if (flRecuperarCuenta.getVisibility() == View.VISIBLE){
                    Intent verificar = new Intent(getApplicationContext(), Verificar.class);
                    lanzarActividad.launch(verificar);
                } else {
                    Intent reestablecer = new Intent(getApplicationContext(), ReestablecerContrasena.class);
                    lanzarActividad.launch(reestablecer);
                }
            }
        });

        btnRegresarRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //validar campos vacios
    public void validarCorreo(){
        correo = edtCorreoRC.getText().toString().trim();
        //validar que no este vacio
        if(TextUtils.isEmpty(correo)){
            tilCorreoRC.setError("Debe ingresar un correo");
            tilCorreoRC.requestFocus();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            tilCorreoRC.setError("Correo ingresado no válido");
            tilCorreoRC.requestFocus();
        } if (flRecuperarCuenta.getVisibility() == View.VISIBLE){
            // si esta visible validara la pregunta, caso contrario se ignora
            respuesta = edtRespuestaRC.getText().toString();
            if (TextUtils.isEmpty(respuesta)){
                tilRespuestaRC.setError("Debe ingresar respuesta");
                tilCorreoRC.requestFocus();
            }
        }
    }

    //lanzar actividad
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
    );
}