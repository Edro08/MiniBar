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

import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.Presentador.Adaptadores.ConsumoAdapter;
import com.purosurf.minibar.Printer.PrintPDF;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SeleccionarReporteCons extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarSelecCons, btnGenerarRPT;
    AutoCompleteTextView actvHabitacionCons;

    //LISTAS
    List<Consumo> lsConsumo;
    ArrayList<String> lsHabitacion;

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
        setContentView(R.layout.activity_seleccionar_reporte_cons);

        //ASIGNAR ELEMENTOS
        btnRegresarSelecCons = findViewById(R.id.btnRegresarSelecCons);
        actvHabitacionCons = findViewById(R.id.actvHabitacionCons);
        edtFechDesde = findViewById(R.id.edtFechDesdeCnsm);
        edtFechHasta = findViewById(R.id.edtFechHastaCnsm);
        btnGenerarRPT = findViewById(R.id.btnConfRepCsm);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.purosurf);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 130, 130, false);

        btnRegresarSelecCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
}