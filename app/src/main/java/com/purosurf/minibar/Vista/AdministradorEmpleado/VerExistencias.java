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
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class VerExistencias extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_existencias);

        //ASIGNAR ELEMENTOS
        btnRegresarVE = findViewById(R.id.btnRegresarVE);
        actvCategoriaVE = findViewById(R.id.actvCategoriaVE);
        rvProductosVE = findViewById(R.id.rvProductosVE);

        //dropdown categoria
        lsCategoria = new ArrayList<String>();
        for(int i=1; i<=6; i++){lsCategoria.add("Categoria "+i);}
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
                int indice = (int) categoriaAdapter.getItemId(i); //Capturamos el indice de la posicion que ocupa en el arraylist
                Toast.makeText(getApplicationContext(), ""+ indice, Toast.LENGTH_SHORT).show(); //mostramos el indice del elemento que ocupa en el arraylist*/
                //llenamos el arreglo segun el indice
                for(int j = 0; j <= 20; j++){
                    lsInventario.add(new Inventario(j, j, 5, 5, 10));
                }
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