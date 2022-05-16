package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class DetalleUsuario extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarDETU;
    TextView tvNombreDETU, tvCorreoDETU, tvUsuarioDETU, tvEstadoDETU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        //ASIGNAR ELEMENTOS
        btnRegresarDETU = findViewById(R.id.btnRegresarDETU);
        tvNombreDETU = findViewById(R.id.tvNombreDETU);
        tvCorreoDETU = findViewById(R.id.tvCorreoDETU);
        tvUsuarioDETU = findViewById(R.id.tvUsuarioDETU);
        tvEstadoDETU = findViewById(R.id.tvEstadoDETU);


        //mostrar datos en pantalla
        tvNombreDETU.setText("Nombre: NombreCompletoUsuario");
        tvCorreoDETU.setText("Correo: correo@correo.com");
        tvUsuarioDETU.setText("Usuario: Usuario1");
        tvEstadoDETU.setText("Estado: Activo");


        //evento boton
        btnRegresarDETU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(4);
                finish();
            }
        });


    }
}