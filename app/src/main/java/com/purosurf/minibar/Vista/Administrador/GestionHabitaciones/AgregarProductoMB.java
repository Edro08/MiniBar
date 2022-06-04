package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.Presentador.Adaptadores.AgregarProductoMBAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.AgregarProductoMBPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IAgregarProductoMB_View;

import java.util.ArrayList;
import java.util.List;

public class AgregarProductoMB extends AppCompatActivity implements IAgregarProductoMB_View{

    //ELEMENTOS
    Button btnRegresar2MB, btnAgregarProductosMB;
    TextView tvHabitacionAMB, //nombre habitacion
            tvExistenciasAMB,
            tvNombreProductoAMB,
            tvCantidadMinAMB,
            tvInventaroGeAMB; //nombre producto
    TextInputEditText edtCantidadAMB,edtMinimaAMB; //cantidad y minima
    Button btnQuitarCantidadAMB, btnAgregarCantidadAMB, btnQuitarMinimaAMB, btnAgregarMinimaAMB;

    //Variables
    String nombreHabitacion = "",
            nombreProducto = "";

    int existenciasActuales = 0,
            cantidadMinActual = 0,
            idProducto,
            idHabitacion = 0,
            cantidadExistencia = 0,
            cantidadMin = 0,
            inventarioGeneral = 0;

    //Bundle y otros
    Bundle data;
    AgregarProductoMBPresentador agregarProductoMBPresentador;

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
        tvCantidadMinAMB = findViewById(R.id.tvCantidadMinAMB);
        tvInventaroGeAMB = findViewById(R.id.tvInventaroGeAMB);
        edtCantidadAMB = findViewById(R.id.edtCantidadAMB);
        edtMinimaAMB = findViewById(R.id.edtMinimaAMB);
        btnQuitarCantidadAMB = findViewById(R.id.btnQuitarCantidadAMB);
        btnAgregarCantidadAMB = findViewById(R.id.btnAgregarCantidadAMB);
        btnQuitarMinimaAMB = findViewById(R.id.btnQuitarMinimaAMB);
        btnAgregarMinimaAMB = findViewById(R.id.btnAgregarMinimaAMB);
        agregarProductoMBPresentador = new AgregarProductoMBPresentador(this);

        //Extraer datos de bundle
        data = getIntent().getExtras();
        nombreHabitacion = data.getString("nombreHabitacion");
        nombreProducto = data.getString("nombreProducto");
        idProducto = data.getInt("idProducto");
        idHabitacion = data.getInt("idHabitacion");

        if (agregarProductoMBPresentador.VerificarInventarioHabitacion(
                getApplicationContext(), idProducto, idHabitacion))
        {
            cantidadExistencia = existenciasActuales;
            cantidadMin = cantidadMinActual;
            edtCantidadAMB.setText("" + cantidadExistencia);
            edtMinimaAMB.setText("" + cantidadMin);
        }

        tvHabitacionAMB.setText("Habitaión: " + nombreHabitacion);
        tvNombreProductoAMB.setText("Producto: " + nombreProducto);
        tvInventaroGeAMB.setText("Cantidad en Inventario General: " + inventarioGeneral);
        tvExistenciasAMB.setText("Existencias Actuales en MiniBar: " + existenciasActuales);
        tvCantidadMinAMB.setText("Cantidad Minima Actual: " + cantidadMinActual);

        //evento botones
        btnAgregarCantidadAMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidadExistencia++;
                edtCantidadAMB.setText("" + cantidadExistencia);
            }
        });

        btnQuitarCantidadAMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidadExistencia > 0)
                {
                    cantidadExistencia--;
                    edtCantidadAMB.setText("" + cantidadExistencia);
                }
            }
        });

        btnAgregarMinimaAMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidadMin++;
                edtMinimaAMB.setText("" + cantidadMin);
            }
        });

        btnQuitarMinimaAMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidadMin > 0)
                {
                    cantidadMin--;
                    edtMinimaAMB.setText("" + cantidadMin);
                }
            }
        });

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

    @Override
    public void ObtenerExistenciasActuales(int existenciasActuales) {
        this.existenciasActuales = existenciasActuales;
    }

    @Override
    public void ObtenerCantidadMinActual(int cantidadMinActual) {
        this.cantidadMinActual = cantidadMinActual;
    }
}