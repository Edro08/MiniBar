package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class DetalleReporteCons extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarReporteCons, btnGenerarReporteCons;
    TextView    tvNumeroReporteCons,
                tvHabitacionReporteCons,
                tvFechaReporteCons,
                tvUsuarioReporteCons;
    TableLayout tblReporteCons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reporte_cons);

        //ASIGNAR ELEMENTOS
        btnRegresarReporteCons = findViewById(R.id.btnRegresarReporteCons);
        btnGenerarReporteCons = findViewById(R.id.btnGenerarReporteCons);
        tvNumeroReporteCons = findViewById(R.id.tvNumeroReporteCons);
        tvHabitacionReporteCons = findViewById(R.id.tvHabitacionReporteCons);
        tvFechaReporteCons = findViewById(R.id.tvFechaReporteCons);
        tvUsuarioReporteCons = findViewById(R.id.tvUsuarioReporteCons);
        tblReporteCons = findViewById(R.id.tblReporteCons);

        //mostrar texto
        tvNumeroReporteCons.setText("N° Reporte: ");
        tvHabitacionReporteCons.setText("Habitación: ");
        tvFechaReporteCons.setText("Fecha: ");
        tvUsuarioReporteCons.setText("Usuario: ");

        llenarFilas();


        //evento botones
            //generar reportes
        btnGenerarReporteCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(5);
                finish();
            }
        });

        // boton regresar
        btnRegresarReporteCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        tblReporteCons.addView(row0);


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
            tvCantidad.setText("Cantidado"+i);
            tvCantidad.setGravity(Gravity.CENTER);
            tvCantidad.setTextColor(getColor(R.color.black));
            tvCantidad.setTextSize(16); //16sp
            row1.addView(tvCantidad);

            //columna subtotal
            TextView tvSubTotal = new TextView(this);
            tvSubTotal.setText("SubTotal"+i);
            tvSubTotal.setTextColor(getColor(R.color.black));
            tvSubTotal.setTextSize(16); //16sp
            row1.addView(tvSubTotal);


            //agregar a la fila
            tblReporteCons.addView(row1);
        }

        //divider
        TableRow row2 = new TableRow(this);
        row2.setBackgroundColor(getColor(R.color.black));
        row2.setMinimumHeight(2);//
        tblReporteCons.addView(row2);


        //TOTAL
        TableRow row3 = new TableRow(this);

        TextView tvTotal = new TextView(this);
        tvTotal.setText("TOTAL");
        tvTotal.setTextColor(getColor(R.color.black));
        tvTotal.setTextSize(20);
        tvTotal.setGravity(Gravity.START);
        row3.addView(tvTotal);

        TextView tvEspacio = new TextView(this);
        tvEspacio.setText("");
        tvEspacio.setTextColor(getColor(R.color.black));
        tvEspacio.setTextSize(20);
        tvEspacio.setGravity(Gravity.START);
        row3.addView(tvEspacio);

        TextView tvSuma = new TextView(this);
        tvSuma.setText("$0.00");
        tvSuma.setTextColor(getColor(R.color.black));
        tvSuma.setTextSize(20);
        tvSuma.setGravity(Gravity.END);
        row3.addView(tvSuma);

        tblReporteCons.addView(row3);

    }

}