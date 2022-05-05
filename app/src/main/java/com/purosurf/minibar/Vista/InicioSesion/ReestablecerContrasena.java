package com.purosurf.minibar.Vista.InicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class ReestablecerContrasena extends AppCompatActivity {

    //ELEMENTOS
    TextInputLayout tilNuevoPassReesC, tilConfirmarReesC; //contenedor edt
    TextInputEditText edtNuevoPassReesC, edtConfirmarReesC; //edt nueva contraseeña y confirmar
    Button btnConfirmarReesC; //confirmar cambio de pss

    //variables
    String nuevaPass, confirmarPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reestablecer_contrasena);

        //Asignar elementos
        tilNuevoPassReesC = findViewById(R.id.tilNuevoPassReesC);
        tilConfirmarReesC = findViewById(R.id.tilConfirmarReesC);
        edtNuevoPassReesC = findViewById(R.id.edtNuevoPassReesC);
        edtConfirmarReesC = findViewById(R.id.edtConfirmarReesC);
        btnConfirmarReesC = findViewById(R.id.btnConfirmarReesC);

        btnConfirmarReesC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    public void validarCampos(){
        nuevaPass = edtNuevoPassReesC.getText().toString().trim();
        confirmarPass = edtConfirmarReesC.getText().toString().trim();

        if(TextUtils.isEmpty(nuevaPass)){
            tilNuevoPassReesC.setError("Debe ingresar una nueva contraseña");
            tilNuevoPassReesC.requestFocus();
        }else if(TextUtils.isEmpty(confirmarPass)){
            tilConfirmarReesC.setError("Debe confirmar la nueva contraseña");
            tilConfirmarReesC.requestFocus();
        }
    }
}