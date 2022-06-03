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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.AgregarProductoMBAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class AgregarProductoMB extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresar2MB, btnAgregarProductosMB;
    TextView tvHabitacionAMB, //nombre habitacion
            tvExistenciasAMB,
            tvNombreProductoAMB; //nombre producto
    TextInputEditText edtCantidadAMB,edtMinimaAMB; //cantidad y minima
    Button btnQuitarCantidadAMB, btnAgregarCantidadAMB, btnQuitarMinimaAMB, btnAgregarMinimaAMB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto_mb);

        //asignar elementos
        btnRegresar2MB = findViewById(R.id.btnRegresar2MB);
        btnAgregarProductosMB = findViewById(R.id.btnAgregarProductosMB);
        tvHabitacionAMB = findViewById(R.id.tvHabitacionAMB);
        tvExistenciasAMB = findViewById(R.id.tvExistenciasAMB);
        tvNombreProductoAMB = findViewById(R.id.tvNombreProductoAMB);
        edtCantidadAMB = findViewById(R.id.edtCantidadAMB);
        edtMinimaAMB = findViewById(R.id.edtMinimaAMB);
        btnQuitarCantidadAMB = findViewById(R.id.btnQuitarCantidadAMB);
        btnAgregarCantidadAMB = findViewById(R.id.btnAgregarCantidadAMB);
        btnQuitarMinimaAMB = findViewById(R.id.btnQuitarMinimaAMB);
        btnAgregarMinimaAMB = findViewById(R.id.btnAgregarMinimaAMB);

        tvHabitacionAMB.setText("Habitaión: ");
        tvNombreProductoAMB.setText("Producto: ");
        tvExistenciasAMB.setText("Existencias Actuales: ");

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
                //setResult(RESULT_OK);
                finish();
            }
        });


    }

}