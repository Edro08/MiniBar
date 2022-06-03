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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.purosurf.minibar.Modelo.Entrada;
import com.purosurf.minibar.Modelo.Salida;
import com.purosurf.minibar.Presentador.Adaptadores.EntradaAdapter;
import com.purosurf.minibar.Presentador.Adaptadores.SalidaAdapter;
import com.purosurf.minibar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SeleccionarReporteCompras extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarSR, btnConfRepES;
    TextView tvEnunciadoSR;

    //BUNDLE
    Bundle datos;

    //VARIABLES
    String accion, fechaDesde, fechaHasta; //capturar intento

    EditText edtFechDesde, edtFechHasta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_reporte_compras);

        //ASIGNAR ELEMENTOS
        btnRegresarSR = findViewById(R.id.btnRegresarSR);
        tvEnunciadoSR = findViewById(R.id.tvEnunciadoSR);
        edtFechDesde = findViewById(R.id.edtFechDesdeES);
        edtFechHasta = findViewById(R.id.edtFechHastaES);
        btnConfRepES = findViewById(R.id.btnConfRepES);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        //evento boton
        btnRegresarSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfRepES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fechaDesde = edtFechDesde.getText().toString().trim();
                fechaHasta = edtFechHasta.getText().toString().trim();
                Intent compras = new Intent(getApplicationContext(), DetalleReporteCons.class);
                compras.putExtra("accion",accion);
                compras.putExtra("fechaDesde",fechaDesde);
                compras.putExtra("fechaHasta",fechaHasta);
                lanzarActividad.launch(compras);
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year_ = calendar.get(Calendar.YEAR);
        final int mes_ = calendar.get(Calendar.MONTH) + 1;
        final int dia_ = calendar.get(Calendar.DAY_OF_MONTH);

        edtFechDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SeleccionarReporteCompras.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(SeleccionarReporteCompras.this, new DatePickerDialog.OnDateSetListener() {
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
    }

    //lanzador de actividades
    /*  code 1 = registra entrada
     *   code 2 = editar existencia
     *   code 3 = generar reporte
     *   code 4 = ver existencia
     *          //modal-por si se necesita estos son los results asignados
     *   code 5 = reporte de consumo
     *   code 6 = reporte de compras
     *   code 7 = reporte de edicion de existencias
     * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        setResult(6);
                        finish();
                    }
                }
            }
    );
}