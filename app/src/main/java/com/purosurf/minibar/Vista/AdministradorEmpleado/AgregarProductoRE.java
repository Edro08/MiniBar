package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.AgregarProductoREAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.ISeleccionarProductoPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.SeleccionarProductoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;
import java.util.List;

public class AgregarProductoRE extends AppCompatActivity implements ISeleccionarProducto_View {

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

    ISeleccionarProductoPresentador seleccionarProductoPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto_re);

        //ASIGNAR ELEMENTOS
        btnAgregarProductoRE = findViewById(R.id.btnAgregarProductoRE);
        btnRegresar2RE = findViewById(R.id.btnRegresar2RE);
        actvCategoriaRE = findViewById(R.id.actvCategoriaRE);
        rvListaProductosRE = findViewById(R.id.rvListaProductosRE);

        seleccionarProductoPresentador = new SeleccionarProductoPresentador(this);

        //bundle
        datos = getIntent().getExtras();

        lsCategoría = (ArrayList<String>) seleccionarProductoPresentador.listaCategorias(getApplicationContext());

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
                String nombreCategoria = (String) adapterView.getItemAtPosition(i); //Capturamos el indice de la posicion que ocupa en el arraylist
                lsProducto.addAll(seleccionarProductoPresentador.listaProductos(getApplicationContext(),nombreCategoria));
                rvListaProductosRE.setAdapter(agregarProductoREAdapter);
            }
        });




    // eventos botones
    btnAgregarProductoRE.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            //Toast.makeText(AgregarProductoRE.this, ""+lsProducto.get(agregarProductoREAdapter.get), Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK); // mostrar contenedor en el registro
            //finish();
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