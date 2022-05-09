package com.purosurf.minibar.Vista.Administrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.purosurf.minibar.R;

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

    FrameLayout flGestionarProductosSG;

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

        flGestionarProductosSG = findViewById(R.id.flGestionarProductosSG);

        tvBienvenidoSG.setText("¡Bienvenido \" usuario \" !"); //mensaje de bienvenida con el nombre de usuario

        //eventos cardview
        cvGestionarProductoSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        cvGestionarHabitacionesSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cvGestionarInventariosSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cvGestionarUsuariosSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    //
    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.menu_seleccionar_productos);
        FrameLayout flGestionarProductosSG = bottomSheetDialog.findViewById(R.id.flGestionarProductosSG);
        CardView primero = bottomSheetDialog.findViewById(R.id.primero);
        primero.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "a",Toast.LENGTH_SHORT).show();
          }
        });

        bottomSheetDialog.show();
    }

}