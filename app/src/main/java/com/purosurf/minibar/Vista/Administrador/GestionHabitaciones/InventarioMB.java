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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.purosurf.minibar.Modelo.InventarioHabitacion;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.InventarioMBAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class InventarioMB extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarMB;
    TextView tvHabitacionMB;
    RecyclerView rvListaInventarioMB;
    AutoCompleteTextView actvFiltroMB; //filtrar lista de productos (si se encuentran o no en el inventario)

    //ADAPTADOR
    InventarioMBAdapter inventarioAdapter;
    ArrayAdapter<String> filtroAdapter;

    //LIST
    List<Producto> lsProducto;
    ArrayList<String> lsFiltro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_mb);

        //ASIGNAR ELEMENTOS
        btnRegresarMB = findViewById(R.id.btnRegresarMB);
        tvHabitacionMB = findViewById(R.id.tvHabitacionMB);
        rvListaInventarioMB = findViewById(R.id.rvListaInventarioMB);
        actvFiltroMB = findViewById(R.id.actvFiltroMB);

        tvHabitacionMB.setText("Habitación: ");

        //llenar drop down
        lsFiltro = new ArrayList<String>();
        for(int i = 1; i < 3; i++){
            lsFiltro.add("Filtro: "+i);
        }
        filtroAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_texto, lsFiltro);
        actvFiltroMB.setAdapter(filtroAdapter);

        //Asignar RecyclerView
        lsProducto = new ArrayList<Producto>();
        for (int i = 1; i <= 10; i++){
            lsProducto.add(new Producto(i, "Producto "+i, 1, 20, 1, "htttpX", "XD"));
        }
        //recyclerview
        inventarioAdapter = new InventarioMBAdapter(lsProducto, this);
        rvListaInventarioMB.setHasFixedSize(false);
        rvListaInventarioMB.setLayoutManager(new LinearLayoutManager(this));
        rvListaInventarioMB.setAdapter(inventarioAdapter);

            //evento seleccionar elemento
        inventarioAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inventario = new Intent(getApplicationContext(), AgregarProductoMB.class);
                lanzarActividad.launch(inventario);
            }
        });

        //EVENTO BOTONES
        btnRegresarMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
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
            } else if (result.getResultCode() == 5){
                setResult(5);
                finish();
            }
        }
    });
}