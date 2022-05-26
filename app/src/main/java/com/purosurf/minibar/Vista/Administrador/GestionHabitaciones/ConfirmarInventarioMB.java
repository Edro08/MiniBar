package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class ConfirmarInventarioMB extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresar3MB, btnConfirmarMB;
    TextView tvHabitacion2MB;
    TableLayout tblInventarioMB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_inventario_mb);

        //asignar elementos
        btnRegresar3MB = findViewById(R.id.btnRegresar3MB);
        btnConfirmarMB = findViewById(R.id.btnConfirmarMB);
        tvHabitacion2MB = findViewById(R.id.tvHabitacion2MB);
        tblInventarioMB = findViewById(R.id.tblInventarioMB);


        llenarFilas();//llenado de filas

        //evento botones
            //regresar pantalla anterior
        btnRegresar3MB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

            //confirmar inventario mini-bar
        btnConfirmarMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(5);
                finish();
            }
        });
    }

    //llenar filas TableLayout
    public void llenarFilas(){
        //divider
        TableRow row0 = new TableRow(this);
        row0.setBackgroundColor(getColor(R.color.black));
        row0.setMinimumHeight(2);
        tblInventarioMB.addView(row0);


        //llenar de datos
        for(int i= 0; i < 10; i++){
            TableRow row1 = new TableRow(this);
            TextView tvProducto = new TextView(this);
            //columna producto
            tvProducto.setText("Producto"+i);
            tvProducto.setTextColor(getColor(R.color.black));
            tvProducto.setTextSize(16); //16sp
            row1.addView(tvProducto);

            //columna cantidad
            TextView tvCantidad = new TextView(this);
            tvCantidad.setText("Cantidad"+i);
            tvCantidad.setGravity(Gravity.CENTER);
            tvCantidad.setTextColor(getColor(R.color.black));
            tvCantidad.setTextSize(16); //16sp
            row1.addView(tvCantidad);

            //columna subtotal
            TextView tvSubTotal = new TextView(this);
            tvSubTotal.setText("Minima"+i);
            tvSubTotal.setTextColor(getColor(R.color.black));
            tvSubTotal.setTextSize(16); //16sp
            row1.addView(tvSubTotal);


            //agregar a la fila
            tblInventarioMB.addView(row1);
        }
    }

}