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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.InventarioMBAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.InventarioMBPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IInventarioMB_View;

import java.util.ArrayList;
import java.util.List;

public class InventarioMB extends AppCompatActivity implements IInventarioMB_View {

    //ELEMENTOS
    Button btnRegresarMB;
    TextView tvHabitacionMB;
    RecyclerView rvListaInventarioMB;
    AutoCompleteTextView actvFiltroMB; //filtrar lista de productos (si se encuentran o no en el inventario)

    //ADAPTADOR
    InventarioMBAdapter inventarioAdapter;
    ArrayAdapter<String> filtroAdapter;
    InventarioMBPresentador inventarioMBPresentador;

    //LIST
    List<Producto> lsProducto;
    ArrayList<String> lsFiltro;
    int idHabitacion, idProducto;
    String nombreHabitacion, filtro, nombreProducto;
    Bundle data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_mb);

        //ASIGNAR ELEMENTOS
        btnRegresarMB = findViewById(R.id.btnRegresarMB);
        tvHabitacionMB = findViewById(R.id.tvHabitacionMB);
        rvListaInventarioMB = findViewById(R.id.rvListaInventarioMB);
        actvFiltroMB = findViewById(R.id.actvFiltroMB);
        inventarioMBPresentador = new InventarioMBPresentador(this);
        //Extraer datos Bundle
        data = getIntent().getExtras();
        idHabitacion = data.getInt("idhabitacion");
        nombreHabitacion = data.getString("nombreHabitacion");

        tvHabitacionMB.setText("Habitación: " + nombreHabitacion);

        //llenar drop down
        lsFiltro = new ArrayList<String>(inventarioMBPresentador.FiltoProductos());
        filtroAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_texto, lsFiltro);
        actvFiltroMB.setAdapter(filtroAdapter);

        //recyclerview Productos
        lsProducto = new ArrayList<Producto>();
        inventarioAdapter = new InventarioMBAdapter(lsProducto, this);
        rvListaInventarioMB.setHasFixedSize(false);
        rvListaInventarioMB.setLayoutManager(new LinearLayoutManager(this));
        rvListaInventarioMB.setAdapter(inventarioAdapter);

        actvFiltroMB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                filtro = lsFiltro.get(i);
                lsProducto.clear();
                lsProducto.addAll(inventarioMBPresentador.listaProductos(
                        getApplicationContext(), filtro, idHabitacion));
                rvListaInventarioMB.setAdapter(inventarioAdapter);
            }
        });

            //evento seleccionar elemento
        inventarioAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idProducto = lsProducto.get(rvListaInventarioMB.getChildAdapterPosition(view)).getIdProducto();
                nombreProducto = lsProducto.get(rvListaInventarioMB.getChildAdapterPosition(view)).getProductoNombre();
                Intent inventario = new Intent(getApplicationContext(), AgregarProductoMB.class);
                inventario.putExtra("idHabitacion",idHabitacion);
                inventario.putExtra("nombreHabitacion",nombreHabitacion);
                inventario.putExtra("idProducto",idProducto);
                inventario.putExtra("nombreProducto",nombreProducto);
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