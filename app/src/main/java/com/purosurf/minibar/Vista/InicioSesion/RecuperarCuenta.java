package com.purosurf.minibar.Vista.InicioSesion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Presentador.InicioSesion.RecuperarCuentaPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IRecuperarCuenta_View;

import java.util.Random;


public class RecuperarCuenta extends AppCompatActivity implements IRecuperarCuenta_View {

    //ELEMENTOS
    FrameLayout flRecuperarCuenta; //contenedor que muestra/oculta la pregunta de seguridad si se tiene o no acceso a internet
    TextInputLayout tilCorreoRC, tilRespuestaRC; //contenedor EDT
    TextView tvPreguntaRC; // enunciado de la pregunta de seguridad
    TextInputEditText edtCorreoRC, edtRespuestaRC; //EditText
    Button btnReestablecerRC, btnRegresarRC; //Boton para pasar a la siguiente pantalla

    //variables
    String correo, respuesta;
    int idUsuario;

    Bundle data;
    RecuperarCuentaPresentador recuperarCuentaPresentador;

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
        recuperarCuentaPresentador = new RecuperarCuentaPresentador(this);
        //extraemos los datos bungle
        data = getIntent().getExtras();
        idUsuario = data.getInt("IdUser");
        tvPreguntaRC.setText(recuperarCuentaPresentador.PreguntaPersona(getApplicationContext(),idUsuario));

        //asignar visibilidad a la pregunta de seguridad
        // VISIBLE - visible
        // GONE - OCULTO
        //Verificar conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            flRecuperarCuenta.setVisibility(View.VISIBLE);
        } else {
            // No hay conexión a Internet en este momento
            flRecuperarCuenta.setVisibility(View.GONE);
        }

        //metodo del boton
        btnReestablecerRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarDatos();
            }
        });

        btnRegresarRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

  // validar campos vacios
  @Override
  public void ValidarDatos() {
        respuesta = edtRespuestaRC.getText().toString();

        if (flRecuperarCuenta.getVisibility() == View.VISIBLE) {

            if(TextUtils.isEmpty(respuesta))
            {
                correo = edtCorreoRC.getText().toString();

                if (TextUtils.isEmpty(correo)) {
                    tilCorreoRC.setError("Debe ingresar correo para Recuperar cuenta");
                    tilRespuestaRC.setError("O debe ingresar respuesta a la pregunta");
                    tilCorreoRC.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    tilCorreoRC.setError("Debe ingresar un correo válido");
                    tilCorreoRC.requestFocus();
                }
                else
                {
                    RecuperarConCorreo(correo);
                }
            }
            else
            {
                RecuperarConPregunta(respuesta);
            }
        } else {
            if (TextUtils.isEmpty(respuesta)) {
                tilRespuestaRC.setError("Debe ingresar respuesta");
                tilCorreoRC.requestFocus();
            }
            else
            {
                RecuperarConPregunta(respuesta);
            }
        }
    }

    @Override
    public void RecuperarConCorreo(String correo) {
        if(recuperarCuentaPresentador.VerificarCorreo(getApplicationContext(),
                correo, idUsuario))
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                int codigo = recuperarCuentaPresentador.NumeroAleatorio();
                if(recuperarCuentaPresentador.EnviarCorreo(correo, codigo))
                {
                    Intent verificar = new Intent(getApplicationContext(), Verificar.class);
                    verificar.putExtra("codigo",codigo);
                    verificar.putExtra("idUsuario",idUsuario);
                    lanzarActividad.launch(verificar);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fallo la Conexion! Intente nuevamente",Toast.LENGTH_SHORT).show();
                }
            } else {
                // No hay conexión a Internet en este momento
                Toast.makeText(getApplicationContext(), "Conexión a Internet no disponible",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            tilCorreoRC.setError("Correo Electronico Incorrecto!");
            tilCorreoRC.requestFocus();
        }
    }


    @Override
    public void RecuperarConPregunta(String respuesta) {
        if(recuperarCuentaPresentador.VerificarRespuesta(
                getApplicationContext(), respuesta, idUsuario))
        {
            Intent reestablecer = new Intent(getApplicationContext(), ReestablecerContrasena.class);
            reestablecer.putExtra("respuesta",respuesta);
            reestablecer.putExtra("idUsuario",idUsuario);
            lanzarActividad.launch(reestablecer);
        }
        else
        {
            tilRespuestaRC.setError("Respuesta incorrecta!");
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
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
    );
}