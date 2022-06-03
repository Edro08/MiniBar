package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.AdministradorEmpleado.SeleccionarReporteConsumoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.ISeleccionarReporteConsumo_View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SeleccionarReporteConsumo extends AppCompatActivity implements ISeleccionarReporteConsumo_View {

    //ELEMENTOS
    Button btnRegresarSelecCons, btnConfRepCsm;
    AutoCompleteTextView actvHabitacionCons;

    //LISTAS
    ArrayList<String> lsHabitacion;
    List<Habitacion> datosHabitacion;
    SeleccionarReporteConsumoPresentador seleccionarReporteComprasPresentador;

    //ADAPTADOR
    ArrayAdapter<String> habitacionAdapter;

    EditText edtFechDesde, edtFechHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_reporte_consumo);

        //ASIGNAR ELEMENTOS
        btnRegresarSelecCons = findViewById(R.id.btnRegresarSelecCons);
        actvHabitacionCons = findViewById(R.id.actvHabitacionCons);
        edtFechDesde = findViewById(R.id.edtFechDesdeCnsm);
        edtFechHasta = findViewById(R.id.edtFechHastaCnsm);
        btnConfRepCsm = findViewById(R.id.btnConfRepCsm);
        seleccionarReporteComprasPresentador = new SeleccionarReporteConsumoPresentador(this);

        //llenar dropdown menu
        lsHabitacion = new ArrayList<String>();
        datosHabitacion = new ArrayList<>(seleccionarReporteComprasPresentador.DatosHabitacion(getApplicationContext()));

        habitacionAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_texto, lsHabitacion);
        actvHabitacionCons.setAdapter(habitacionAdapter);

        actvHabitacionCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "" + datosHabitacion.get(i).getIdHabitaccion(), Toast.LENGTH_SHORT).show();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year_ = calendar.get(Calendar.YEAR);
        final int mes_ = calendar.get(Calendar.MONTH) + 1;
        final int dia_ = calendar.get(Calendar.DAY_OF_MONTH);

        edtFechDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SeleccionarReporteConsumo.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(SeleccionarReporteConsumo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = year + "-" + month + "-" + day;
                        edtFechHasta.setText(date);
                    }
                },year_,mes_,dia_);
                datePickerDialog.show();
            }
        });

        btnConfRepCsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRegresarSelecCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        setResult(5);
                        finish();
                    }
                }
            });

    @Override
    public void ObtenerNombreHabitacion(String nombreHabitacion) {
        lsHabitacion.add(nombreHabitacion);
    }
}