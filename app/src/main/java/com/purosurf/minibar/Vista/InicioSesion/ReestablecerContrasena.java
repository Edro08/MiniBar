package com.purosurf.minibar.Vista.InicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Presentador.InicioSesion.ReestablecerContrasenaPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IReestablecerContrasena_View;

public class ReestablecerContrasena extends AppCompatActivity implements IReestablecerContrasena_View {

    //ELEMENTOS
    TextInputLayout tilNuevoPassReesC, tilConfirmarReesC; //contenedor edt
    TextInputEditText edtNuevoPassReesC, edtConfirmarReesC; //edt nueva contraseeña y confirmar
    Button btnConfirmarReesC, btnRegresarReesC;

    //variables
    String nuevaPass, confirmarPass;
    int idUsuario;

    Bundle data;

    ReestablecerContrasenaPresentador reestablecerContrasenaPresentador;

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
        btnRegresarReesC = findViewById(R.id.btnRegresarReesC);

        //Recuperar datos de Bundle
        data = getIntent().getExtras();
        idUsuario = data.getInt("idUsuario");
        //botones
        btnConfirmarReesC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos())
                {
                    reestablecerContrasenaPresentador.ActualizarContrasena(
                            getApplicationContext(),idUsuario,nuevaPass);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        btnRegresarReesC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean validarCampos(){
        Boolean estado = false;
        nuevaPass = edtNuevoPassReesC.getText().toString().trim();
        confirmarPass = edtConfirmarReesC.getText().toString().trim();

        if(TextUtils.isEmpty(nuevaPass)){
            tilNuevoPassReesC.setError("Debe ingresar una nueva contraseña");
            tilNuevoPassReesC.requestFocus();
        }else if(TextUtils.isEmpty(confirmarPass)){
            tilConfirmarReesC.setError("Debe confirmar la nueva contraseña");
            tilConfirmarReesC.requestFocus();
        }
        else
        {
            if(nuevaPass.equals(confirmarPass))
            {
                estado = true;
            }
            else
            {
                tilNuevoPassReesC.setError("La contraseña no coinciden!");
                tilConfirmarReesC.setError("La contraseña no coinciden!");
                tilConfirmarReesC.requestFocus();
            }
        }
        return  estado;
    }
}