package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.purosurf.minibar.Modelo.InventarioHabitacion;
import com.purosurf.minibar.Presentador.Adaptadores.InventarioMBAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class InventarioMB extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarMB, btnProcesarMB;
    TextView tvHabitacionMB;
    RecyclerView rvListaInventarioMB;
    FloatingActionButton fabAgregarProductoMB;
    FrameLayout flContenedorProductos; //contenedor oculto, si se agregan productos se muestra el contenedor con el recyclerview en su interior
    TextView tvComentarioMB; //comentario

    //ADAPTADOR
    InventarioMBAdapter inventarioAdapter;

    //LIST
    List<InventarioHabitacion> lsInventario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_mb);

        //ASIGNAR ELEMENTOS
        btnRegresarMB = findViewById(R.id.btnRegresarMB);
        btnProcesarMB = findViewById(R.id.btnProcesarMB);
        tvHabitacionMB = findViewById(R.id.tvHabitacionMB);
        rvListaInventarioMB = findViewById(R.id.rvListaInventarioMB);
        fabAgregarProductoMB = findViewById(R.id.fabAgregarProductoMB);
        flContenedorProductos = findViewById(R.id.flContenedorProductos);
        tvComentarioMB = findViewById(R.id.tvComentarioMB);

        tvHabitacionMB.setText("Habitación: ");


        //Asignar RecyclerView
        lsInventario = new ArrayList<InventarioHabitacion>();
        for (int i = 1; i <= 10; i++){
            lsInventario.add(new InventarioHabitacion(1,1, i, 20, 5));
        }
        //recyclerview
        inventarioAdapter = new InventarioMBAdapter(lsInventario, this);
        rvListaInventarioMB.setHasFixedSize(false);
        rvListaInventarioMB.setLayoutManager(new LinearLayoutManager(this));
        rvListaInventarioMB.setAdapter(inventarioAdapter);

        //EVENTO BOTONES
        btnRegresarMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
            }
        });

            //agregar productos
        fabAgregarProductoMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar = new Intent(getApplicationContext(), AgregarProductoMB.class);
                lanzarActividad.launch(agregar);
            }
        });

            //pasar a pantalla confirmar
        btnProcesarMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent confirmar = new Intent(getApplicationContext(), ConfirmarInventarioMB.class);
                lanzarActividad.launch(confirmar);
            }
        });
    }

    //lanzador de actividades
    /*  code 1 = adicionar
     *   code 2 = deshabilitar
     *   code 3 = actualizar
     *   code 4 = listar
     *   code 5 = editar minibar
     * */

    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                flContenedorProductos.setVisibility(View.VISIBLE);
                tvComentarioMB.setVisibility(View.GONE);
            } else if (result.getResultCode() == 5){
                setResult(5);
                finish();
            }
        }
    });
}