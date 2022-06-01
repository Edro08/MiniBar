package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.AgregarProductoMBAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class AgregarProductoMB extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresar2MB, btnAgregarProductosMB;
    AutoCompleteTextView actvCategoriaMB;
    RecyclerView rvAgregarProductosMB;

    //ADAPTADORES
    AgregarProductoMBAdapter agregarAdapter;
    ArrayAdapter<String> categoriaAdapter;

    //LISTAS
    List<Producto> lsProducto;
    ArrayList<String> lsCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto_mb);

        //asignar elementos
        btnRegresar2MB = findViewById(R.id.btnRegresar2MB);
        btnAgregarProductosMB = findViewById(R.id.btnAgregarProductosMB);
        actvCategoriaMB = findViewById(R.id.actvCategoriaMB);
        rvAgregarProductosMB = findViewById(R.id.rvAgregarProductosMB);


        //Llenar dropdown menu
        lsCategoria = new ArrayList<String>();
        for (int i = 1; i <= 6; i++){
            lsCategoria.add("Categoría #"+i);
        }
        categoriaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_texto, lsCategoria);
        actvCategoriaMB.setAdapter(categoriaAdapter);


        //recyclerview
        lsProducto = new ArrayList<Producto>();
        agregarAdapter = new AgregarProductoMBAdapter(lsProducto, this);
        rvAgregarProductosMB.setHasFixedSize(false);
        rvAgregarProductosMB.setLayoutManager(new LinearLayoutManager(this));
        //rvAgregarProductosMB.setAdapter(agregarAdapter);


        //evento seleccionar categorías
        actvCategoriaMB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                lsProducto.clear();
                for (int indice = 1; indice <= 15; indice++){
                    lsProducto.add(new Producto(indice, "Producto"+indice, i, 1, 1, "xd",""));
                }
                rvAgregarProductosMB.setAdapter(agregarAdapter);
            }
        });

        //evento botones
            //regresar pantalla anterior
        btnRegresar2MB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
            //agregar productos y mostrar
        btnAgregarProductosMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });


    }

}