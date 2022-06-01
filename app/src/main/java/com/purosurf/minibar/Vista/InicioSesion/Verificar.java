package com.purosurf.minibar.Vista.InicioSesion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class Verificar extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarVerificar, btnVerificar; // regresar - verificar/pantalla siguiente
    TextInputLayout tilCodigoVerificar; //contenedor
    TextInputEditText edtCodigoVerificar; //edt

    //VARIABLES
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        btnRegresarVerificar = findViewById(R.id.btnRegresarVerificar);
        btnVerificar = findViewById(R.id.btnVerificar);
        tilCodigoVerificar = findViewById(R.id.tilCodigoVerificar);
        edtCodigoVerificar = findViewById(R.id.edtCodigoVerificar);

        //evento botones
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampo();
                Intent reestablecer = new Intent(getApplicationContext(), ReestablecerContrasena.class);
                lanzarActividad.launch(reestablecer);
            }
        });

        btnRegresarVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
            }
        });
    }

    public void validarCampo(){
        codigo = edtCodigoVerificar.getText().toString().trim();
        if (TextUtils.isEmpty(codigo)){
            tilCodigoVerificar.setError("Debe ingresar código de seguridad");
            tilCodigoVerificar.requestFocus();
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