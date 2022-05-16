package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class DeshabilitarUsuario extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarDU, btnConfirmarDU;
    TextView tvUsuarioDU; //nombre usuario
    Switch swActivoDU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshabilitar_usuario);


        //asignar elementos
        btnRegresarDU = findViewById(R.id.btnRegresarDU);
        btnConfirmarDU = findViewById(R.id.btnConfirmarDU);
        tvUsuarioDU = findViewById(R.id.tvUsuarioDU);
        swActivoDU = findViewById(R.id.swActivoDU);

        //nombre usuario
        tvUsuarioDU.setText("Usuario: Usuario1");


        //evento botones
        btnConfirmarDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2); // code deshabilitar
                finish();
            }
        });

        btnRegresarDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}