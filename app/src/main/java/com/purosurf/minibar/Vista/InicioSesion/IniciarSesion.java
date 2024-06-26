package com.purosurf.minibar.Vista.InicioSesion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Presentador.InicioSesion.IniciarSesionPresentador;
import com.purosurf.minibar.Presentador.InicioSesion.Interfaces.IIniciarSesionPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.SeleccionarGestion;
import com.purosurf.minibar.Vista.Empleado.MenuEmpleado;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IIniciarSesion_View;

public class IniciarSesion extends AppCompatActivity implements IIniciarSesion_View {


    //ELEMENTOS
    TextInputEditText edtUsuarioLogin, edtPasswordLogin; //EditText
    TextInputLayout tilUsuarioLogin, tilPasswordLogin;   //Contenedor del EditText
    Button btnIngresarAdmi,btnRecuperarLogin;

    String user, pass, nombreuser;
    public static int iduser;
    public static String nombreUsuario, usuario;
    IIniciarSesionPresentador iniciarSesionPresentador;


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
        btnRecuperarLogin = findViewById(R.id.btnRecuperarLogin);

        iniciarSesionPresentador = new IniciarSesionPresentador(this);

            //quitar error
        edtPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                    if (edtPasswordLogin.toString().length()>0){
                        tilPasswordLogin.setError(null);
                    }
            }
        });


        //botones
        btnIngresarAdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarCaptura())
                {
                    if (iniciarSesionPresentador.OnIniciarSesion(user,pass,getApplicationContext()))
                    {
                        usuario = user;
                        nombreUsuario = nombreuser;
                        if(iniciarSesionPresentador.typeUser(user))
                        {
                            Intent menu = new Intent(getApplicationContext(), SeleccionarGestion.class);
                            startActivity(menu);
                            finish();
                        }
                        else
                        {
                            Intent menu = new Intent(getApplicationContext(), MenuEmpleado.class);
                            startActivity(menu);
                            finish();
                        }
                    }
                }
            }
        });

        btnRecuperarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCapturaRecuperación())
                {
                    Intent recuperar = new Intent(getApplicationContext(), RecuperarCuenta.class);
                    recuperar.putExtra("IdUser", iduser);
                    recuperarCuenta.launch(recuperar);
                }
            }
        });
    }

    //validar campos vacios
    public boolean validarCaptura(){
        boolean estado = false;
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
        else
        {
            tilPasswordLogin.setError(null);
            estado = true;
        }
        return estado;
    }

    //validar campos vacios
    public boolean validarCapturaRecuperación(){
        boolean estado = false;
        //asignar variables
        user = edtUsuarioLogin.getText().toString().trim();

        //validar campo vacio
        if(TextUtils.isEmpty(user)){
            tilUsuarioLogin.setError("Debe ingresar usuario a recuperar");
            tilUsuarioLogin.requestFocus();
        }
        else
        {
            if(iniciarSesionPresentador.VerificarUsuario(getApplicationContext(),user)){
                estado = true;
            }
            else
            {
                tilUsuarioLogin.setError("Usuario no encontrado!");
                tilUsuarioLogin.requestFocus();
            }
        }
        return estado;
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


    @Override
    public void OnIniciarSesionResult(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void NombreUsuario(String nombreUsuario) {
        nombreuser = nombreUsuario;
    }

    @Override
    public void IdUser(int IdUser)
    {
        iduser = IdUser;
    }
}