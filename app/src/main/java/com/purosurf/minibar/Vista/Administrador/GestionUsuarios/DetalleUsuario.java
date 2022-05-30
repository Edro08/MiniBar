package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.DetalleUsuarioPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IDetalleUsuario_View;

public class DetalleUsuario extends AppCompatActivity implements IDetalleUsuario_View {

    //ELEMENTOS
    Button btnRegresarDETU;
    TextView tvNombreDETU, tvCorreoDETU, tvUsuarioDETU, tvEstadoDETU;
    DetalleUsuarioPresentador detalleUsuarioPresentador;
    Bundle data;
    int IdUsuario;
    Cursor datos;

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

        //extraemos los datos bungle
        data = getIntent().getExtras();
        IdUsuario = data.getInt("IdUsuario");

        detalleUsuarioPresentador = new DetalleUsuarioPresentador(this);
        datos = detalleUsuarioPresentador.DatosPersonaUsuario(getApplicationContext(),IdUsuario);
        datos.moveToFirst();

        //mostrar datos en pantalla
        tvNombreDETU.setText("Nombre: " + datos.getString(3));
        tvCorreoDETU.setText("Correo: " + datos.getString(4));
        tvUsuarioDETU.setText("Usuario: " + datos.getString(1));
        if (datos.getInt(2) == 1) {
            tvEstadoDETU.setText("Estado: Activo");
        } else {
            tvEstadoDETU.setText("Estado: Desactivado");
        }

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