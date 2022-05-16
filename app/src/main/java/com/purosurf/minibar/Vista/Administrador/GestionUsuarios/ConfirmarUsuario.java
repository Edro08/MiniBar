package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class ConfirmarUsuario extends AppCompatActivity {

    //ELEMENTOS
    TextView tvAppBar2ADDU, tvEnunciado2ADDU; //encabezados adicionarr/actualizar
    TextInputLayout tilUsuarioADDU, tilPasswordADDU, tilConfirmarPassADDU;
    TextInputEditText edtUsuarioADDU, edtPasswordADDU, edtConfirmarPassADDU;
    Button btnRegresar2ADDU, btnConfirmarADDU;

    //BUNDLE
    Bundle datos;

    //Variables
    String accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_usuario);

        //ASIGNAR ELEMENTOS
            //TextView
        tvAppBar2ADDU = findViewById(R.id.tvAppBar2ADDU); //encabezado appbar
        tvEnunciado2ADDU = findViewById(R.id.tvEnunciado2ADDU); //enunciado formulario
            //Contenedores EDT
        tilUsuarioADDU = findViewById(R.id.tilUsuarioADDU);
        tilPasswordADDU = findViewById(R.id.tilPasswordADDU);
        tilConfirmarPassADDU = findViewById(R.id.tilConfirmarPassADDU);
            //edt
        edtUsuarioADDU = findViewById(R.id.edtUsuarioADDU);
        edtPasswordADDU = findViewById(R.id.edtPasswordADDU);
        edtConfirmarPassADDU = findViewById(R.id.edtConfirmarPassADDU);
            //botones
        btnRegresar2ADDU = findViewById(R.id.btnRegresar2ADDU);
        btnConfirmarADDU = findViewById(R.id.btnConfirmarADDU);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        if (accion.equals("adicionar")){
            tvAppBar2ADDU.setText("Confirmar nuevo usuario");
            tvEnunciado2ADDU.setText("Registro de Nuevo Usuario");
            btnConfirmarADDU.setText("Confirmar Registro");
        } else if (accion.equals("actualizar")){
            tvAppBar2ADDU.setText("Confirmar actualización usuario");
            tvEnunciado2ADDU.setText("Información Usuario");
            btnConfirmarADDU.setText("Actualizar Datos");
        }


        //evento botones
        btnRegresar2ADDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfirmarADDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("adicionar")){
                    setResult(1); //code adicionar
                    finish();
                } else if (accion.equals("actualizar")){
                    setResult(3); //code actualizar
                    finish();
                }
            }
        });
    }
}