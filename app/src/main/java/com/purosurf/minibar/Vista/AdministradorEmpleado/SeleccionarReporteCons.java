package com.purosurf.minibar.Vista.AdministradorEmpleado;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Presentador.Adaptadores.ConsumoAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarReporteCons extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarSelecCons;
    AutoCompleteTextView actvHabitacionCons;
    RecyclerView rvSeleccionarReporteCons;

    //LISTAS
    List<Consumo> lsConsumo;
    ArrayList<String> lsHabitacion;

    //ADAPTADOR
    ConsumoAdapter consumoAdapter;
    ArrayAdapter<String> habitacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_reporte_cons);

        //ASIGNAR ELEMENTOS
        btnRegresarSelecCons = findViewById(R.id.btnRegresarSelecCons);
        actvHabitacionCons = findViewById(R.id.actvHabitacionCons);
        rvSeleccionarReporteCons = findViewById(R.id.rvSeleccionarReporteCons);

        //llenar dropdown menu
        lsHabitacion = new ArrayList<String>();
            //llenar datos
        for (int i = 1; i <= 6; i++){
            lsHabitacion.add("Habitación #"+i);
        }
        habitacionAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_texto, lsHabitacion);
        actvHabitacionCons.setAdapter(habitacionAdapter);

        //recyclerview
        lsConsumo = new ArrayList<Consumo>();
        consumoAdapter = new ConsumoAdapter(lsConsumo, this);
        rvSeleccionarReporteCons.setHasFixedSize(false);
        rvSeleccionarReporteCons.setLayoutManager(new LinearLayoutManager(this));


        actvHabitacionCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lsConsumo.clear();
                for (int indice = 1; indice <= 7; indice++){
                    lsConsumo.add(new Consumo(indice, 1, i, indice+"/05/2022", 20));
                }
                rvSeleccionarReporteCons.setAdapter(consumoAdapter);
            }
        });

        consumoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reporteConsumo = new Intent(getApplicationContext(), DetalleReporteCons.class);
                lanzarActividad.launch(reporteConsumo);
            }
        });
    }

    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        if(result.getResultCode() == 5){
                            setResult(5);
                            finish();
                        }
                    }
                }
            });

}