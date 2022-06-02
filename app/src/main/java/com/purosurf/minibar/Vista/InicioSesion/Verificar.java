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
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IVerificar_View;

public class Verificar extends AppCompatActivity implements IVerificar_View {

    //ELEMENTOS
    Button btnRegresarVerificar, btnVerificar; // regresar - verificar/pantalla siguiente
    TextInputLayout tilCodigoVerificar; //contenedor
    TextInputEditText edtCodigoVerificar; //edt

    //VARIABLES
    String codigoVerificar;
    int idUsuario,codigo;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        btnRegresarVerificar = findViewById(R.id.btnRegresarVerificar);
        btnVerificar = findViewById(R.id.btnVerificar);
        tilCodigoVerificar = findViewById(R.id.tilCodigoVerificar);
        edtCodigoVerificar = findViewById(R.id.edtCodigoVerificar);

        //Recuperar datos de Bundle
        data = getIntent().getExtras();
        idUsuario = data.getInt("idUsuario");
        codigo = data.getInt("codigo");

        //evento botones
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampo())
                {
                    Intent reestablecer = new Intent(getApplicationContext(), ReestablecerContrasena.class);
                    reestablecer.putExtra("idUsuario",idUsuario);
                    lanzarActividad.launch(reestablecer);
                }
            }
        });

        btnRegresarVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
            }
        });
    }

    @Override
    public boolean validarCampo(){
        boolean estado = false;
        codigoVerificar = edtCodigoVerificar.getText().toString().trim();

        if (TextUtils.isEmpty(codigoVerificar)){
            tilCodigoVerificar.setError("Debe ingresar código de seguridad");
            tilCodigoVerificar.requestFocus();
        }
        else
        {
            if(codigo == Integer.parseInt(codigoVerificar)){
                estado = true;
            }
            else
            {
                tilCodigoVerificar.setError("Código de seguridad erroneo!");
                tilCodigoVerificar.requestFocus();
            }
        }
        return estado;
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
