package com.purosurf.minibar.Vista.Administrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.GestionarHabitaciones;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.GestionarProductos;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.GestionarUsuarios;
import com.purosurf.minibar.Vista.AdministradorEmpleado.GestionarInventarios;
import com.purosurf.minibar.Vista.InicioSesion.IniciarSesion;

public class SeleccionarGestion extends AppCompatActivity {

    //ELEMENTOS
    TextView tvBienvenidoSG; //saludo al usuario conectado
    //CardView que simula botones de desplazamiento
    CardView
            cvGestionarProductoSG,
            cvGestionarHabitacionesSG,
            cvGestionarInventariosSG,
            cvGestionarUsuariosSG;
    FloatingActionButton fabCerrarSesionSG;

    //intent para seleccionar la gestion
    Intent gestion;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_gestion);

        //asignar elementos
        tvBienvenidoSG = findViewById(R.id.tvBienvenidoSG);
        cvGestionarProductoSG = findViewById(R.id.cvGestionarProductosSG);
        cvGestionarHabitacionesSG = findViewById(R.id.cvGestionarHabitacionesSG);
        cvGestionarInventariosSG = findViewById(R.id.cvGestionarInventariosSG);
        cvGestionarUsuariosSG = findViewById(R.id.cvGestionarUsuariosSG);
        fabCerrarSesionSG = findViewById(R.id.fabCerrarSesionSG);

        tvBienvenidoSG.setText("¡Bienvenido \" `" + IniciarSesion.usuario +" \" !");; //mensaje de bienvenida con el nombre de usuario

        //eventos cardview
        cvGestionarProductoSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestion = new Intent(getApplicationContext(), GestionarProductos.class);
                startActivity(gestion);
            }
        });

        cvGestionarHabitacionesSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestion = new Intent(getApplicationContext(), GestionarHabitaciones.class);
                startActivity(gestion);
            }
        });

        cvGestionarInventariosSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestion = new Intent(getApplicationContext(), GestionarInventarios.class);
                startActivity(gestion);
            }
        });

        cvGestionarUsuariosSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gestion = new Intent(getApplicationContext(), GestionarUsuarios.class);
                startActivity(gestion);
            }
        });

        //boton flotante cerrar sesion
        fabCerrarSesionSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}