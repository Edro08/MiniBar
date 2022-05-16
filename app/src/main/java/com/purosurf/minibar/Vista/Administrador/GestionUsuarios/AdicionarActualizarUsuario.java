package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class AdicionarActualizarUsuario extends AppCompatActivity {

    //ELEMENTOS
    TextView tvEnunciado1ADDU, tvAppBar1ADDU;
    Button btnRegresarADDU, btnSiguienteADDU;
    TextInputLayout tilNombresADDU, tilApellidosADDU, tilEmailADDU, tilPreguntaADDU, tilRespuestaADDU; //contenedores edt
    TextInputEditText edtNombresADDU, edtApellidosADDU, edtEmailADDU, edtPreguntaADDU, edtRespuestaADDU; //edit text

    //Bundle
    Bundle datos;

    //VARIABLES
    String accion; //si viene dee adicionar o actualizar un usuario
    String nombres, apellidos, email, pregunta, respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_actualizar_usuario);

        //asignar elementos
        tvEnunciado1ADDU = findViewById(R.id.tvEnunciado1ADDU); // encabezado si viene de adicionar o actualizar
        tvAppBar1ADDU = findViewById(R.id.tvAppbar1ADDU); //enunciado de appbar adicionar / actualizar
            //botones
        btnRegresarADDU = findViewById(R.id.btnRegresarADDU);
        btnSiguienteADDU = findViewById(R.id.btnSiguienteADDU);
            //contenedores edt
        tilNombresADDU = findViewById(R.id.tilNombresADDU);
        tilApellidosADDU = findViewById(R.id.tilApellidosADDU);
        tilEmailADDU = findViewById(R.id.tilEmailADDU);
        tilPreguntaADDU = findViewById(R.id.tilPreguntaADDU);
        tilRespuestaADDU = findViewById(R.id.tilRespuestaADDU);
            // edt
        edtNombresADDU = findViewById(R.id.edtNombresADDU);
        edtApellidosADDU = findViewById(R.id.edtApellidosADDU);
        edtEmailADDU = findViewById(R.id.edtEmailADDU);
        edtPreguntaADDU = findViewById(R.id.edtPreguntaADDU);
        edtRespuestaADDU = findViewById(R.id.edtRespuestaADDU);

        //capturar intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        if(accion.equals("adicionar")){
            tvEnunciado1ADDU.setText("Registro de nuevo usuario");
            tvAppBar1ADDU.setText("Adicionar usuario");
        } else if (accion.equals("actualizar")){
            tvEnunciado1ADDU.setText("Infomación usuario");
            tvAppBar1ADDU.setText("Actualizar usuario");
        }


       btnSiguienteADDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
                if(accion.equals("adicionar")){
                    Intent adicionar = new Intent(getApplicationContext(), ConfirmarUsuario.class);
                    adicionar.putExtra("accion", "adicionar");
                    lanzarActividad.launch(adicionar);
                } else if (accion.equals("actualizar")){
                    Intent actualizar = new Intent(getApplicationContext(), ConfirmarUsuario.class);
                    actualizar.putExtra("accion", "actualizar");
                    lanzarActividad.launch(actualizar);
                }
            }
        });

      btnRegresarADDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });
    }

    //validar campos vacios
    public void validarCampos(){
        nombres = edtNombresADDU.getText().toString();
        apellidos = edtApellidosADDU.getText().toString();
        email = edtEmailADDU.getText().toString();
        pregunta = edtPreguntaADDU.getText().toString();
        respuesta = edtRespuestaADDU.getText().toString();

        if (TextUtils.isEmpty(nombres)){
            tilNombresADDU.setError("Debe ingresar los nombres");
            tilNombresADDU.requestFocus();
        } else if (TextUtils.isEmpty(apellidos)){
            tilApellidosADDU.setError("Debe ingresar los apellidos");
            tilApellidosADDU.requestFocus();
        } else if (TextUtils.isEmpty(email)){
            tilEmailADDU.setError("Debe ingresar el correo");
            tilEmailADDU.requestFocus();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmailADDU.setError("Debe ingresar un correo válido");
            tilEmailADDU.requestFocus();
        } else if(TextUtils.isEmpty(pregunta)){
            tilPreguntaADDU.setError("Debe ingresar una pregunta");
            tilPreguntaADDU.requestFocus();
        } else if(TextUtils.isEmpty(respuesta)){
            tilRespuestaADDU.setError("Debe Ingresar respuesta a la pregunta");
            tilRespuestaADDU.requestFocus();
        }

    }

    //lanzador de actividades
    /*  resultCode = 1 -> adicionar
     *   resultCode = 2 -> deshabilitar
     *   resultCode = 3 -> actualizar
     *   resultCode = 4 -> listar
     * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        if (result.getResultCode() == 1){
                            setResult(1); //code adicionar
                            finish();
                        }else if(result.getResultCode() == 3){
                            setResult(3); //code actualizar
                            finish();
                        }
                    }
                }

            });
}