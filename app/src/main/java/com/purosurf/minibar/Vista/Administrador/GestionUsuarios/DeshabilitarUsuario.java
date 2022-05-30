package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.DeshabilitarUsuarioPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IDeshabilitarUsuario_Viw;

import java.util.List;

public class DeshabilitarUsuario extends AppCompatActivity implements IDeshabilitarUsuario_Viw {

    //ELEMENTOS
    Button btnRegresarDU, btnConfirmarDU;
    TextView tvUsuarioDU; //nombre usuario
    Switch swActivoDU;
    Bundle data;
    DeshabilitarUsuarioPresentador deshabilitarUsuarioPresentador;
    String nombreUsuario;
    int estado, IdUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshabilitar_usuario);

        //extraemos los datos bungle
        data = getIntent().getExtras();
        IdUsuario = data.getInt("IdUsuario");

        deshabilitarUsuarioPresentador = new DeshabilitarUsuarioPresentador(this);

        //asignar elementos
        btnRegresarDU = findViewById(R.id.btnRegresarDU);
        btnConfirmarDU = findViewById(R.id.btnConfirmarDU);
        tvUsuarioDU = findViewById(R.id.tvUsuarioDU);
        swActivoDU = findViewById(R.id.swActivoDU);

        nombreUsuario = deshabilitarUsuarioPresentador.DatosUsuario(getApplicationContext(), IdUsuario).get(0).getUsuario();
        estado = deshabilitarUsuarioPresentador.DatosUsuario(getApplicationContext(), IdUsuario).get(0).getIdEstado();

        //nombre usuario
        tvUsuarioDU.setText("Usuario: " + nombreUsuario);

        if(estado == 1)
        {
            swActivoDU.setText("Activo");
            swActivoDU.setChecked(true);
        }
        else
        {
            swActivoDU.setText("Inactivo");
            swActivoDU.setChecked(false);
        }


        //evento botones
        btnConfirmarDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean estado = false;
                int code = 2; // code deshabilitar
                if(swActivoDU.isChecked()){
                    estado = true;
                    code = 5;
                }
                deshabilitarUsuarioPresentador.ActivarDesactivarUsuario(getApplicationContext(),IdUsuario,estado);
                setResult(code);
                finish();
            }
        });

        btnRegresarDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        swActivoDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swActivoDU.isChecked())
                {
                    swActivoDU.setText("Activo");
                }
                else
                {
                    swActivoDU.setText("Inactivo");
                }
            }
        });
    }

    @Override
    public void OnDeshabilitarAdmin(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}