package com.purosurf.minibar.Vista.InicioSesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class IniciarSesion extends AppCompatActivity {


    //ELEMENTOS
    TextInputEditText edtUsuarioLogin, edtPasswordLogin; //EditText
    TextInputLayout tilUsuarioLogin, tilPasswordLogin;   //Contenedor del EditText
    Button btnIngresarLogin, btnRecuperarLogin;

    String user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        //Asignar elementos
        edtUsuarioLogin = findViewById(R.id.edtUsuarioLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        tilUsuarioLogin = findViewById(R.id.tilUsuarioLogin);
        tilPasswordLogin = findViewById(R.id.tilPasswordLogin);
        btnIngresarLogin = findViewById(R.id.btnIngresarLogin);
        btnRecuperarLogin = findViewById(R.id.btnRecuperarLogin);


        //botones
        btnIngresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCaptura();

            }
        });

        btnRecuperarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recuperar = new Intent(getApplicationContext(), RecuperarCuenta.class);
                startActivity(recuperar);
            }
        });
    }

    //validar campos vacios
    public void validarCaptura(){
        //asignar variables
        user = edtUsuarioLogin.getText().toString().trim();
        pass = edtPasswordLogin.getText().toString().trim();

        //validar campo vacio
        if(TextUtils.isEmpty(user)){
            tilUsuarioLogin.setError("Debe ingresar usuario");
            tilUsuarioLogin.requestFocus();
        }else if (TextUtils.isEmpty(pass)){
            tilPasswordLogin.setError("Debe ingresar su contraseña");
            tilPasswordLogin.requestFocus();
        }

    }


}