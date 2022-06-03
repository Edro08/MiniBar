package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.purosurf.minibar.Presentador.AdministradorEmpleado.DetalleReporteConsPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.IDetalleReporteCons_View;
import com.purosurf.minibar.Vista.InicioSesion.IniciarSesion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleReporteCons extends AppCompatActivity implements IDetalleReporteCons_View {

    //ELEMENTOS
    Button btnRegresarReporteCons, btnGenerarReporteCons;
    TextView    tvNumeroReporteCons,
                tvHabitacionReporteCons,
                tvFechaReporteCons,
                tvUsuarioReporteCons,
                tv_nombreDetalleCons,
                tv_TrCampo1,
                tv_TrCampo2,
                tv_TrCampo3;
    TableLayout tblReporteCons;
    Cursor datosReportes;

    //BUNDLE
    Bundle datos;
    DetalleReporteConsPresentador detalleReporteConsPresentador;

    //VARIABLES
    String accion, fecha = "", usuario = "", numReport = "", habitacion = "";
    double Total = 0;


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
        tv_nombreDetalleCons = findViewById(R.id.tv_nombreDetalleCons);
        tv_TrCampo1 = findViewById(R.id.tv_TrCampo1);
        tv_TrCampo2 = findViewById(R.id.tv_TrCampo2);
        tv_TrCampo3 = findViewById(R.id.tv_TrCampo3);
        detalleReporteConsPresentador = new DetalleReporteConsPresentador(this);

        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        usuario = String.valueOf(IniciarSesion.iduser);

        if(accion.equals("Consumo"))
        {
            tvHabitacionReporteCons.setVisibility(View.VISIBLE);
            numReport = "RP-CONS-" + datos.getInt("idHabitacion") + "-" + datos.getInt("idConsumo");
            fecha = "ALGO";
            habitacion = "" + datos.getInt("idHabitacion");
            datosReportes = detalleReporteConsPresentador.DatosConsumoHabitacion(
                    getApplicationContext(), datos.getInt("idConsumo"));
        }
        else
        {
            tvHabitacionReporteCons.setVisibility(View.GONE);
            if(accion.equals("Compra"))
            {
                numReport = "RP-COM-" + usuario;
                fecha = "Desde " + datos.getString("fechaDesde") + " Hasta " + datos.getString("fechaHasta");
                tv_TrCampo3.setText("Fecha");
                datosReportes = detalleReporteConsPresentador.DatosCompra(getApplicationContext(),
                        datos.getString("fechaDesde"),datos.getString("fechaHasta"));
            }
            else if(accion.equals("Inventario"))
            {
                fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                numReport = "RP-INV-" + usuario + "-" + fecha;
                tv_TrCampo2.setText("Existencias");
                tv_TrCampo3.setText("Precio");
                datosReportes = detalleReporteConsPresentador.DatosInventario(getApplicationContext());
            }
        }

        //mostrar texto
        tv_nombreDetalleCons.setText("Detalle de " + accion);
        tvNumeroReporteCons.setText("N° Reporte: " + numReport);
        tvHabitacionReporteCons.setText("Habitación: " + habitacion);
        tvFechaReporteCons.setText("Fecha: " + fecha);
        tvUsuarioReporteCons.setText("Usuario: " + usuario);
        TableLayoutllenarFilas();

        //evento botones
        //generar reportes
        btnGenerarReporteCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("Compra")){
                    setResult(6); //code reporte entrada/compras
                    finish();
                }else if (accion.equals("Inventario")){
                    setResult(7); //code reporte inventario
                    finish();
                }else if(accion.equals("Consumo"))
                {
                    setResult(5);//code reporte consumo
                    finish();
                }

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
    @Override
    public void TableLayoutllenarFilas(){
        //divider
        TableRow row0 = new TableRow(this);
        row0.setBackgroundColor(getColor(R.color.black));
        row0.setMinimumHeight(2);
        tblReporteCons.addView(row0);

        //llenar de datos
        while (datosReportes.moveToNext()){
            //columna producto
            TableRow row1 = new TableRow(this);
            TextView tvProducto = new TextView(this);
            if (accion.equals("Compra")){
                tvProducto.setText(datosReportes.getString(3));;
            }else if (accion.equals("Inventario")){
                tvProducto.setText(datosReportes.getString(1));
            }else if(accion.equals("Consumo"))
            {
                tvProducto.setText(datosReportes.getString(1));
            }
            tvProducto.setTextColor(getColor(R.color.black));
            tvProducto.setTextSize(16); //16sp
            row1.addView(tvProducto);

            //columna cantidad o existencias
            TextView tvCantidad = new TextView(this);
            if (accion.equals("Compra")){
                tvCantidad.setText(datosReportes.getString(5));
            }else if (accion.equals("Inventario")){
                tvCantidad.setText(datosReportes.getString(3));
            }else if(accion.equals("Consumo"))
            {
                tvCantidad.setText(datosReportes.getString(2));
            }
            tvCantidad.setGravity(Gravity.CENTER);
            tvCantidad.setTextColor(getColor(R.color.black));
            tvCantidad.setTextSize(16); //16sp
            row1.addView(tvCantidad);

            //columna subtotal o Fecha
            TextView tvSubTotal = new TextView(this);
            if (accion.equals("Compra")){
                tvSubTotal.setText(datosReportes.getString(4));
            }else if (accion.equals("Inventario")){
                tvSubTotal.setText("$" + datosReportes.getDouble(4));
            }else if(accion.equals("Consumo"))
            {
                tvSubTotal.setText("$" + datosReportes.getDouble(3));
                Total = Total + datosReportes.getDouble(3);
            }
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

        if(accion.equals("Consumo"))
        {
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
            tvSuma.setText("$" + Total);
            tvSuma.setTextColor(getColor(R.color.black));
            tvSuma.setTextSize(20);
            tvSuma.setGravity(Gravity.END);
            row3.addView(tvSuma);

            tblReporteCons.addView(row3);
        }
    }
}