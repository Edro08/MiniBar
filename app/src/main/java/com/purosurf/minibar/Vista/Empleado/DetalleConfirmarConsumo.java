package com.purosurf.minibar.Vista.Empleado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Empleado.Interfaces.IDetalleConfirmarConsumo_View;

import java.util.ArrayList;

public class DetalleConfirmarConsumo extends AppCompatActivity implements IDetalleConfirmarConsumo_View {

    //ELEMENTOS
    TableLayout tblDetalleDCC;
    Button btnConfirmarDCC, btnRegresarDCC;
    TextView tvNombreHabitacion;
    String[][] datosDetalleConfirmar;
    int Cantidad;
    double total;

    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_confirmar_consumo);
        tblDetalleDCC = findViewById(R.id.tblDetalleDCC);
        btnConfirmarDCC = findViewById(R.id.btnConfirmarDCC);
        btnRegresarDCC = findViewById(R.id.btnRegresarDCC);
        tvNombreHabitacion = findViewById(R.id.tv_nombreHabitacionDCC);

        datos = getIntent().getExtras();
        datosDetalleConfirmar = (String[][]) datos.getSerializable("Consumo");
        tvNombreHabitacion.setText(datos.getString("NombreHabitacion"));
        total = datos.getDouble("Total");
        MostrarDatosdeConsumo(datosDetalleConfirmar);


        //evento botones
        btnConfirmarDCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        btnRegresarDCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void MostrarDatosdeConsumo(String[][] datos){
        //divider
        TableRow row0 = new TableRow(this);
        row0.setBackgroundColor(getColor(R.color.black));
        row0.setMinimumHeight(2);
        tblDetalleDCC.addView(row0);


        //llenar de datos
        for(int i= 0; i < datos.length ; i++){
            TableRow row1 = new TableRow(this);
            TextView tvProducto = new TextView(this);
            //columna producto
            tvProducto.setText(datos[i][1]);
            tvProducto.setTextColor(getColor(R.color.black));
            tvProducto.setTextSize(16); //16sp
            row1.addView(tvProducto);

            //columna cantidad
            TextView tvCantidad = new TextView(this);
            tvCantidad.setText(datos[i][2]);
            tvCantidad.setGravity(Gravity.CENTER);
            tvCantidad.setTextColor(getColor(R.color.black));
            tvCantidad.setTextSize(16); //16sp
            row1.addView(tvCantidad);

            //columna subtotal
            TextView tvSubTotal = new TextView(this);
            tvSubTotal.setText("$" + datos[i][3]);
            tvSubTotal.setTextColor(getColor(R.color.black));
            tvSubTotal.setTextSize(16); //16sp
            row1.addView(tvSubTotal);

            //agregar a la fila
            tblDetalleDCC.addView(row1);
        }

        //divider
        TableRow row2 = new TableRow(this);
        row2.setBackgroundColor(getColor(R.color.black));
        row2.setMinimumHeight(2);//
        tblDetalleDCC.addView(row2);

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
        tvSuma.setText("$" + total);
        tvSuma.setTextColor(getColor(R.color.black));
        tvSuma.setTextSize(20);
        tvSuma.setGravity(Gravity.END);
        row3.addView(tvSuma);

        tblDetalleDCC.addView(row3);


    }
}