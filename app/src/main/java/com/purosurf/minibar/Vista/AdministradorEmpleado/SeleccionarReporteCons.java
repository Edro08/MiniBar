package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Presentador.Adaptadores.ConsumoAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SeleccionarReporteCons extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarSelecCons;
    AutoCompleteTextView actvHabitacionCons;

    //LISTAS
    List<Consumo> lsConsumo;
    ArrayList<String> lsHabitacion;

    //ADAPTADOR
    ConsumoAdapter consumoAdapter;
    ArrayAdapter<String> habitacionAdapter;

    EditText edtFechDesde, edtFechHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_reporte_cons);

        //ASIGNAR ELEMENTOS
        btnRegresarSelecCons = findViewById(R.id.btnRegresarSelecCons);
        actvHabitacionCons = findViewById(R.id.actvHabitacionCons);
        edtFechDesde = findViewById(R.id.edtFechDesdeCnsm);
        edtFechHasta = findViewById(R.id.edtFechHastaCnsm);

        btnRegresarSelecCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year_ = calendar.get(Calendar.YEAR);
        final int mes_ = calendar.get(Calendar.MONTH) + 1;
        final int dia_ = calendar.get(Calendar.DAY_OF_MONTH);

        edtFechDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SeleccionarReporteCons.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = year + "-" + month + "-" + day;
                        edtFechDesde.setText(date);
                    }
                },year_,mes_,dia_);
                datePickerDialog.show();
            }
        });

        edtFechHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SeleccionarReporteCons.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = year + "-" + month + "-" + day;
                        edtFechHasta.setText(date);
                    }
                },year_,mes_,dia_);
                datePickerDialog.show();
            }
        });

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

        actvHabitacionCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lsConsumo.clear();
                for (int indice = 1; indice <= 7; indice++){
                    lsConsumo.add(new Consumo(indice, 1, i, indice+"/05/2022", 20));
                }
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