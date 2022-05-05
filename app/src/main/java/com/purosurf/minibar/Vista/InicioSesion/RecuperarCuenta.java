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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;


public class RecuperarCuenta extends AppCompatActivity {

    //ELEMENTOS
    TextInputLayout tilCorreoRC; //contenedor EDT
    TextInputEditText edtCorreoRC; //EditText
    Button btnReestablecerRC; //Boton para pasar a la siguiente pantalla

    //variables
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);

        //Asignar Elementos
        tilCorreoRC = findViewById(R.id.tilCorreoRC);
        edtCorreoRC = findViewById(R.id.edtCorreoRecuperarCuenta);
        btnReestablecerRC = findViewById(R.id.btnReestablecerRecuperarCuenta);


        //metodo del boton
        btnReestablecerRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCorreo();
                Intent reestablecer = new Intent(getApplicationContext(), ReestablecerContrasena.class);
                lanzarActividad.launch(reestablecer);
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
        }
    }

    //lanzar actividad
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        finish();
                        Toast.makeText(getApplicationContext(), "Cambio de contraseña existoso", Toast.LENGTH_LONG).show();
                    }
                }
            }
    );
}