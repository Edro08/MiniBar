package com.purosurf.minibar.Vista.AdministradorEmpleado;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Adaptadores.ConsumoAdapter;
import com.purosurf.minibar.Printer.PrintPDF;
import com.purosurf.minibar.Presentador.AdministradorEmpleado.SeleccionarReporteConsumoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.ISeleccionarReporteConsumo_View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SeleccionarReporteConsumo extends AppCompatActivity implements ISeleccionarReporteConsumo_View {

    //ELEMENTOS
    Button btnRegresarSelecCons, btnGenerarRPT;
    Button btnRegresarSelecCons, btnConfRepCsm;
    AutoCompleteTextView actvHabitacionCons;
    RecyclerView rvSeleccionarReporteCons;

    //LISTAS
    ArrayList<String> lsHabitacion;
    List<Consumo> lsConsumo;
    List<Habitacion> datosHabitacion;
    SeleccionarReporteConsumoPresentador seleccionarReporteConsumoPresentador;
    String accion;
    int IdHabitacion;
    Bundle datos;

    //ADAPTADOR
    ConsumoAdapter consumoAdapter;
    ArrayAdapter<String> habitacionAdapter;

    EditText edtFechDesde, edtFechHasta;
    Bitmap bmp, scaledbmp;
    int pageHeight = 1120, pagewidth = 792;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_reporte_consumo);

        //ASIGNAR ELEMENTOS
        btnRegresarSelecCons = findViewById(R.id.btnRegresarSelecCons);
        actvHabitacionCons = findViewById(R.id.actvHabitacionCons);
        edtFechDesde = findViewById(R.id.edtFechDesdeCnsm);
        edtFechHasta = findViewById(R.id.edtFechHastaCnsm);
        btnGenerarRPT = findViewById(R.id.btnConfRepCsm);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.purosurf);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 130, 130, false);
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

        btnGenerarRPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintPDF print = new PrintPDF(bmp,scaledbmp);
                print.generatePDF(getApplicationContext(), pagewidth, pageHeight);
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

        consumoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compras = new Intent(getApplicationContext(), DetalleReporteCons.class);
                compras.putExtra("accion",accion);
                lanzarActividad.launch(compras);
            }
        });

        btnConfRepCsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lsConsumo.clear();
                lsConsumo.addAll(seleccionarReporteConsumoPresentador.DatosConsumoHabitacion(
                        getApplicationContext(), IdHabitacion, edtFechDesde.getText().toString().trim(),
                        edtFechHasta.getText().toString().trim()));
                rvSeleccionarReporteCons.setAdapter(consumoAdapter);
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

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

}