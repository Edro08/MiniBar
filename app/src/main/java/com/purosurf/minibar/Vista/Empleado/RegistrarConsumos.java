package com.purosurf.minibar.Vista.Empleado;

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
import android.widget.TextView;
import android.widget.Toast;


import com.purosurf.minibar.Modelo.DetalleConsumo;
import com.purosurf.minibar.Presentador.Adaptadores.RegistrarConsumoAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrarConsumos extends AppCompatActivity {

    //ELEMENTOS DECLARAR
    TextView tvHabitacionRegCons;
    RecyclerView rvInventarioRegCons;
    TextView tvTotalRegCons;
    Button btnProcesarRegCons, btnRegresarRegCons;

    //Lista de productos de la habitacion
    List<DetalleConsumo> listadoInventario;

    //Adaptador para RecyclerView
    RegistrarConsumoAdapter lsInventarioRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_consumos);

        //asignar elementos
        tvHabitacionRegCons = findViewById(R.id.tvHabitacionRegCons);
        rvInventarioRegCons = findViewById(R.id.rvInventarioRegCons);
        tvTotalRegCons = findViewById(R.id.tvTotalRegCons);
        btnProcesarRegCons = findViewById(R.id.btnProcesarRegCons);
        btnRegresarRegCons = findViewById(R.id.btnRegresarRegCons);

        //llenar listado
        listadoInventario = new ArrayList<DetalleConsumo>();
        for(int i = 0; i <=5;i++ ){
            listadoInventario.add(new DetalleConsumo(i, 1, i, i, (float) (1.50 * i)) );
        }

        //asinar adaptador al RecyclerView
        lsInventarioRV = new RegistrarConsumoAdapter(listadoInventario, this); //asignamos el adaptador
        rvInventarioRegCons.setHasFixedSize(false);
        rvInventarioRegCons.setLayoutManager(new LinearLayoutManager(this));
        rvInventarioRegCons.setAdapter(lsInventarioRV); //asignamos adaptador al recyclerview


        //botones
        btnRegresarRegCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnProcesarRegCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detalleConfirmar = new Intent(getApplicationContext(), DetalleConfirmarConsumo.class);
                lanzarDetalle.launch(detalleConfirmar);
                //Toast.makeText(getApplicationContext(),"Procesar Factura", Toast.LENGTH_LONG).show();
            }
        });

        //seleccionar elemento de la lista
        lsInventarioRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), listadoInventario.get(rvInventarioRegCons.getChildAdapterPosition(view)).getIdProducto(), Toast.LENGTH_LONG).show();
            }
        });
    }

    ActivityResultLauncher<Intent> lanzarDetalle = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
    );
}