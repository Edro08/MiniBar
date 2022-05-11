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

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.SeleccionarGestion;
import com.purosurf.minibar.Vista.Empleado.MenuEmpleado;

public class IniciarSesion extends AppCompatActivity {


    //ELEMENTOS
    TextInputEditText edtUsuarioLogin, edtPasswordLogin; //EditText
    TextInputLayout tilUsuarioLogin, tilPasswordLogin;   //Contenedor del EditText
    Button btnIngresarAdmi, btnIngresarEmpleado,btnRecuperarLogin;

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
        btnIngresarAdmi = findViewById(R.id.btnIngresarAdmi);
        btnIngresarEmpleado = findViewById(R.id.btnIngresarEmpleado);
        btnRecuperarLogin = findViewById(R.id.btnRecuperarLogin);


        //botones
        btnIngresarAdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCaptura();
                Intent menu = new Intent(getApplicationContext(), SeleccionarGestion.class);
                startActivity(menu);
            }
        });
        btnIngresarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCaptura();
                Intent menu = new Intent(getApplicationContext(), MenuEmpleado.class);
                startActivity(menu);
            }
        });

        btnRecuperarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recuperar = new Intent(getApplicationContext(), RecuperarCuenta.class);
                recuperarCuenta.launch(recuperar);
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
    ActivityResultLauncher<Intent> recuperarCuenta = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>(){
                        @Override
                        public void onActivityResult(ActivityResult result){
                            if (result.getResultCode() == RESULT_OK){
                                //mensaje de cambio de contraseña exitoso
                                Snackbar.make(findViewById(R.id.constraintIniciarSesion), "Cambio de contraseña existoso", Snackbar.LENGTH_LONG)
                                        .setTextColor(getColor(R.color.azulOscuro))
                                        .setBackgroundTint(getColor(R.color.azulPalido))
                                        .show();
                            }
                        }
                    }
            );


}