package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Categoria;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.SeleccionarProductoAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.ISeleccionarProductoPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.SeleccionarProductoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarProducto extends AppCompatActivity implements ISeleccionarProducto_View {

    //ELEMENTOS
    Button btnRegresarSP;
    TextView tvEnunciadoSP; //enunciado (deshabilitar, actualizar, listar)
    RecyclerView rvSeleccionarProductoSP; //RecyclerView para el listado de productos
    AutoCompleteTextView actvCategoriaSP;

    //Adaptador para el RecyclerView
    SeleccionarProductoAdapter lsProductosRV;


    //ArrayList de datos para llenar el dropdown menu y filtar los productos por categoria
    ArrayList<String> listaCategoria;

    //ArrayList de datos para llenar datos de productos por categoria
    ArrayList<Producto> listadoProducto;

    //Bundle
    Bundle datos;

    //Variables
    String accion; // variable que almacena el extra de la pantalla que invoco la pantalla

    ISeleccionarProductoPresentador seleccionarProductoPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_producto);

        //======ASIGNAR ELEMENTOS
        btnRegresarSP = findViewById(R.id.btnRegresarSP);
        tvEnunciadoSP = findViewById(R.id.tvEnunciadoSP);
        rvSeleccionarProductoSP = findViewById(R.id.rvSeleccionarProductoSP);
        actvCategoriaSP = findViewById(R.id.actvCategoriaSP);

        seleccionarProductoPresentador = new SeleccionarProductoPresentador(this);

        //======Traemos los datos de la pantalla que invoco la activity
        datos = getIntent().getExtras();

        //======Enunciado de la pantalla
        accion = datos.getString("accion");
        if(accion.equals("deshabilitar")){
            tvEnunciadoSP.setText("Deshabilitar Productos");
        }else if(accion.equals("actualizar")){
            tvEnunciadoSP.setText("Actualizar Productos");
        }else if(accion.equals("listar")){
            tvEnunciadoSP.setText("Listado de Productos");
        }


        //=======DROPDOWN MENU
        listaCategoria = (ArrayList<String>) seleccionarProductoPresentador.listaCategorias(getApplicationContext());

        ArrayAdapter categoriaAdapter = new ArrayAdapter //adaptador para llenar el dropdown menu
                (
                  this, //contexto
                  R.layout.dropdown_texto, //diseño del texto
                  listaCategoria //listado de datos
                );
        actvCategoriaSP.setAdapter(categoriaAdapter);


        //=======RECYCLERVIEW
        //declaramos el ArrayList para el adaptador
        listadoProducto = new ArrayList<Producto>();

        //asignamos el adaptador y el listado que usara
        lsProductosRV = new SeleccionarProductoAdapter(listadoProducto, this);
        // le asignamos el adaptador al recyclerview
        rvSeleccionarProductoSP.setHasFixedSize(false);
        rvSeleccionarProductoSP.setLayoutManager(new LinearLayoutManager(this));
        rvSeleccionarProductoSP.setAdapter(lsProductosRV);


        //evento botones
        btnRegresarSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Evento Seleccionar la categoria
        //el listado se llenara dependiendo de la categoria del producto
        actvCategoriaSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listadoProducto.clear(); //re-hacemos la lista al cambiar la categoria, caso contrario se van apilando productos de otra categoria a la lista
                String nombreCategoria = (String) adapterView.getItemAtPosition(i); //Capturamos el indice de la posicion que ocupa en el arraylist
                Toast.makeText(getApplicationContext(),""+ nombreCategoria, Toast.LENGTH_SHORT).show(); //mostramos el indice del elemento que ocupa en el arraylist*/
                //llenamos el arreglo segun la categoria
                Cursor datos = seleccionarProductoPresentador.CursorProductos(getApplicationContext(),nombreCategoria);
                while(datos.moveToNext()){
                    listadoProducto.add(new Producto(datos.getInt(0), datos.getString(1), datos.getInt(2), (float) datos.getDouble(3), datos.getInt(4), datos.getString(5)));
                }
                rvSeleccionarProductoSP.setAdapter(lsProductosRV);
            }
        });

        //evento seleccionar adaptador
        lsProductosRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //capturar id del producto del modelado
                int idProducto = listadoProducto.get(rvSeleccionarProductoSP.getChildAdapterPosition(view)).getIdProducto();
                Toast.makeText(getApplicationContext(), "IdProducto "+ idProducto, Toast.LENGTH_SHORT).show();

                Intent accionProducto;
                if (accion.equals("deshabilitar")){
                    accionProducto = new Intent(getApplicationContext(), DeshabilitarProductos.class);
                    lanzarActividad.launch(accionProducto);
                }else if(accion.equals("actualizar")){
                    accionProducto = new Intent(getApplicationContext(), ActualizarProducto.class);
                    accionProducto.putExtra("accion","actualizar");
                    lanzarActividad.launch(accionProducto);
                }else if(accion.equals("listar")){
                    accionProducto = new Intent(getApplicationContext(), ConfirmarDetalleProducto.class);
                    accionProducto.putExtra("accion","listar");
                    lanzarActividad.launch(accionProducto);
                }
            }
        });

    }
    //=====================lanzador de actividades
                /*resultCode = 1 -> adicionar
                  resultCode = 2 -> deshabilitar
                * resultCode = 3 -> actualizar
                * resultCode = 4 -> listar
                * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if (result.getResultCode() == 2){
                        setResult(2);
                        finish();
                    }else if (result.getResultCode() == 3){
                        setResult(3);
                        finish();
                    }else if (result.getResultCode() == 4){
                        finish();
                    }
                }
            }
    );
}