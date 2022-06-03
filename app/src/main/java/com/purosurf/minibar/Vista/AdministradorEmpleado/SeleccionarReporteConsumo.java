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

import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Adaptadores.ConsumoAdapter;
import com.purosurf.minibar.Presentador.AdministradorEmpleado.SeleccionarReporteConsumoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.ISeleccionarReporteConsumo_View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SeleccionarReporteConsumo extends AppCompatActivity implements ISeleccionarReporteConsumo_View {

    //ELEMENTOS
    Button btnRegresarSelecCons, btnConfRepCsm;
    AutoCompleteTextView actvHabitacionCons;
    RecyclerView rvSeleccionarReporteCons;

    //LISTAS
    ArrayList<String> lsHabitacion;
    List<Consumo> lsConsumo;
    List<Habitacion> datosHabitacion;
    SeleccionarReporteConsumoPresentador seleccionarReporteConsumoPresentador;
    String accion, fechaDesde, fechaHasta;
    int IdHabitacion, IdConsumo;
    Bundle datos;

    //ADAPTADOR
    ConsumoAdapter consumoAdapter;
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
        rvSeleccionarReporteCons = findViewById(R.id.rvSeleccionarReporteCons);
        seleccionarReporteConsumoPresentador = new SeleccionarReporteConsumoPresentador(this);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        //llenar dropdown menu
        lsHabitacion = new ArrayList<String>();
        datosHabitacion = new ArrayList<>(seleccionarReporteConsumoPresentador.DatosHabitacion(getApplicationContext()));

        habitacionAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_texto, lsHabitacion);
        actvHabitacionCons.setAdapter(habitacionAdapter);

        actvHabitacionCons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IdHabitacion = datosHabitacion.get(i).getIdHabitaccion();
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date Fecha = null;
                        try {
                            Fecha = simpleDateFormat.parse(year +"-" + month +"-" + day);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(Fecha);
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date Fecha = null;
                        try {
                            Fecha = simpleDateFormat.parse(year +"-" + month +"-" + day);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(Fecha);
                        edtFechHasta.setText(date);
                    }
                },year_,mes_,dia_);
                datePickerDialog.show();
            }
        });

        //recyclerview
        lsConsumo = new ArrayList<>();
        consumoAdapter = new ConsumoAdapter(lsConsumo, this);
        rvSeleccionarReporteCons.setHasFixedSize(false);
        rvSeleccionarReporteCons.setLayoutManager(new LinearLayoutManager(this));

        btnConfRepCsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lsConsumo.clear();
                fechaDesde = edtFechDesde.getText().toString().trim();
                fechaHasta = edtFechHasta.getText().toString().trim();
                lsConsumo.addAll(seleccionarReporteConsumoPresentador.DatosConsumoHabitacion(
                        getApplicationContext(), IdHabitacion, fechaDesde , fechaHasta));
                rvSeleccionarReporteCons.setAdapter(consumoAdapter);
            }
        });

        consumoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IdConsumo = lsConsumo.get(rvSeleccionarReporteCons.getChildAdapterPosition(view)).getIdConsumo();
                Intent compras = new Intent(getApplicationContext(), DetalleReporteCons.class);
                compras.putExtra("fechaDesde",fechaDesde);
                compras.putExtra("fechaHasta",fechaHasta);
                compras.putExtra("idHabitacion",IdHabitacion);
                compras.putExtra("idConsumo",IdConsumo);
                compras.putExtra("accion",accion);
                lanzarActividad.launch(compras);
            }
        });

        btnRegresarSelecCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void ObtenerNombreHabitacion(String nombreHabitacion) {
        lsHabitacion.add(nombreHabitacion);
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
}