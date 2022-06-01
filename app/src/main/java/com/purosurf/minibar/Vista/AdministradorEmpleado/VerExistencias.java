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

import com.purosurf.minibar.Modelo.Inventario;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.VerExistenciasAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionarInventarios.GestionarInventario;
import com.purosurf.minibar.Presentador.Administrador.GestionarInventarios.Interfaces.IntrfcGestionInventarios;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;
import java.util.List;

public class VerExistencias extends AppCompatActivity implements ISeleccionarProducto_View {

    //ELEMENTOS
    Button btnRegresarVE;
    AutoCompleteTextView actvCategoriaVE;
    RecyclerView rvProductosVE;

    //adaptadores
    VerExistenciasAdapter existenciasAdapter;
    ArrayAdapter<String> categoriaAdapter;

    //Listas
    List<Inventario> lsInventario;
    ArrayList<String> lsCategoria;

    IntrfcGestionInventarios intrfcGestionInventarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_existencias);

        //ASIGNAR ELEMENTOS
        btnRegresarVE = findViewById(R.id.btnRegresarVE);
        actvCategoriaVE = findViewById(R.id.actvCategoriaVE);
        rvProductosVE = findViewById(R.id.rvProductosVE);

        intrfcGestionInventarios = new GestionarInventario(this);

        //dropdown categoria
        lsCategoria = (ArrayList<String>) intrfcGestionInventarios.listaCategorias(getApplicationContext());
        categoriaAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_texto, lsCategoria);
        actvCategoriaVE.setAdapter(categoriaAdapter);

        //recyclerview
        lsInventario = new ArrayList<Inventario>();
        existenciasAdapter = new VerExistenciasAdapter(lsInventario, this);
        rvProductosVE.setHasFixedSize(false);
        rvProductosVE.setLayoutManager(new LinearLayoutManager(this));
        rvProductosVE.setAdapter(existenciasAdapter);


        //evento seleccionar categoria-dropdown
        actvCategoriaVE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lsInventario.clear(); //re-hacemos la lista al cambiar la categoria, caso contrario se van apilando productos de otra categoria a la lista
                String nombreCategoria = (String) adapterView.getItemAtPosition(i);
                lsInventario.addAll(intrfcGestionInventarios.listaProductos(getApplicationContext(),nombreCategoria));
                rvProductosVE.setAdapter(existenciasAdapter);
            }
        });

        //evento boton regresar pantalla anterior
        btnRegresarVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}