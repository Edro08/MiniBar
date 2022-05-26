package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.AgregarProductoREAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class AgregarProductoRE extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresar2RE, btnAgregarProductoRE;
    AutoCompleteTextView actvCategoriaRE;
    RecyclerView rvListaProductosRE;

    //Adaptadores
    AgregarProductoREAdapter agregarProductoREAdapter; //adaptador recyclerview
    ArrayAdapter<String> categoriasAdapter; //adaptador dropdown menu

    //Listas
    List<Producto> lsProducto;
    ArrayList<String> lsCategoría;

    //bundle
    Bundle datos;

    //VARIABLES
    String accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto_re);

        //ASIGNAR ELEMENTOS
        btnAgregarProductoRE = findViewById(R.id.btnAgregarProductoRE);
        btnRegresar2RE = findViewById(R.id.btnRegresar2RE);
        actvCategoriaRE = findViewById(R.id.actvCategoriaRE);
        rvListaProductosRE = findViewById(R.id.rvListaProductosRE);

        //bundle
        datos = getIntent().getExtras();

        //llenar dropdown menu
        lsCategoría = new ArrayList<String>();
        for (int i = 1; i <= 6; i++){
            lsCategoría.add("Categoría: "+i);
        }
        categoriasAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_texto, lsCategoría);
        actvCategoriaRE.setAdapter(categoriasAdapter);

        //adaptador recyclerview
        lsProducto = new ArrayList<Producto>();
        agregarProductoREAdapter = new AgregarProductoREAdapter(lsProducto, this);
        rvListaProductosRE.setHasFixedSize(false);
        rvListaProductosRE.setLayoutManager(new LinearLayoutManager(this));
        rvListaProductosRE.setAdapter(agregarProductoREAdapter);

        //evento seleccionar Categoria
        actvCategoriaRE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lsProducto.clear(); //re-hacemos la lista al cambiar la categoria, caso contrario se van apilando productos de otra categoria a la lista
                int indice = (int) categoriasAdapter.getItemId(i); //Capturamos el indice de la posicion que ocupa en el arraylist
                Toast.makeText(getApplicationContext(), ""+ indice, Toast.LENGTH_SHORT).show(); //mostramos el indice del elemento que ocupa en el arraylist*/
                //llenamos el arreglo segun el indice
                for(int j = 0; j <= 9; j++){
                    lsProducto.add(new Producto(j,"producto"+ j +" Categoria "+indice, indice, 4 * j, 1, "XD"));
                }
                rvListaProductosRE.setAdapter(agregarProductoREAdapter);
            }
        });

        //evento recyclerview
        agregarProductoREAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //eventos botones
        btnAgregarProductoRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK); //mostrar contenedor en el registro
                finish();
            }
        });

        btnRegresar2RE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}