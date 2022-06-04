package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.purosurf.minibar.BuildConfig;
import com.purosurf.minibar.Presentador.AdministradorEmpleado.DetalleReporteConsPresentador;
import com.purosurf.minibar.Printer.PrintPDF;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.Interfaces.IDetalleReporteCons_View;
import com.purosurf.minibar.Vista.InicioSesion.IniciarSesion;

import java.io.File;
import java.io.OutputStreamWriter;
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
    String accion, fecha = "", idusuario = "", numReport = "", habitacion = "", NombreArchivo = "";
    double Total = 0;
    Bitmap bmp, scaledbmp;
    private static final int PERMISSION_REQUEST_CODE = 200;
    int pageHeight = 1120;
    int pagewidth = 792;

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

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.purosurf);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 130, 130, false);

        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        idusuario = String.valueOf(IniciarSesion.iduser);

        if(accion.equals("Consumo"))
        {
            tvHabitacionReporteCons.setVisibility(View.VISIBLE);
            numReport = "RP-CONS-" + datos.getInt("idHabitacion") + "-" + datos.getInt("idConsumo");
            fecha = "" + datos.getString("fecha");
            habitacion = "" + datos.getString("nombreHabitacion");
            datosReportes = detalleReporteConsPresentador.DatosConsumoHabitacion(
                    getApplicationContext(), datos.getInt("idConsumo"));
        }
        else
        {
            tvHabitacionReporteCons.setVisibility(View.GONE);
            if(accion.equals("Compra"))
            {
                numReport = "RP-COM-" + idusuario;
                fecha = "desde " + datos.getString("fechaDesde") + " hasta " + datos.getString("fechaHasta");
                tv_TrCampo3.setText("Fecha");
                datosReportes = detalleReporteConsPresentador.DatosCompra(getApplicationContext(),
                        datos.getString("fechaDesde"),datos.getString("fechaHasta"));
            }
            else if(accion.equals("Inventario"))
            {
                fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                numReport = "RP-INV-" + idusuario + "-" + fecha;
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
        tvUsuarioReporteCons.setText("Usuario: " + IniciarSesion.usuario);
        TableLayoutllenarFilas();

        //evento botones
        //generar reportes
        btnGenerarReporteCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GenerarReporte(numReport))
                {
                    CompartirReporte(NombreArchivo);
                }

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
            tvProducto.setMaxWidth(400);
            tvProducto.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
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

            //divider
            TableRow rowsep = new TableRow(this);
            rowsep.setBackgroundColor(getColor(R.color.grisFondo));
            rowsep.setMinimumHeight(2);//
            tblReporteCons.addView(rowsep);
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

    @Override
    public boolean GenerarReporte(String nombreReporte) {
        boolean estado = false;
        NombreArchivo = nombreReporte + ".pdf";
        //String Informacion = "Información de mini bar";
        try
        {
            String[] datos = {"Detalle de Inventario", numReport, fecha, IniciarSesion.usuario, NombreArchivo};
            PrintPDF print = new PrintPDF(bmp, scaledbmp, datos, detalleReporteConsPresentador.DatosInventario(getApplicationContext()));
            print.generatePDF(getApplicationContext(), pagewidth, pageHeight);
            estado = true;
        }
        catch (Exception e)
        {
            Log.e("Error",e.toString());
            Toast.makeText(getApplicationContext(), "No se puede Generar el reporte", Toast.LENGTH_SHORT).show();
        }
        return estado;
    }

    @Override
    public void CompartirReporte(String nombreReporte) {
        try {
            File file = new File(getFilesDir(), nombreReporte);
            Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", file);

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("application/pdf");
            share.setClipData(ClipData.newRawUri("", contentUri));
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            share.putExtra(Intent.EXTRA_SUBJECT, "Mini Bar");
            share.putExtra(Intent.EXTRA_STREAM, contentUri);
            String Cadena = "Información de App Mini Bar";
            share.putExtra(Intent.EXTRA_TEXT, Cadena);
            startActivity(Intent.createChooser(share, "Compartir"));

        }
        catch (Exception e)
        {
            Log.e("Error",e.toString());
        }
    }
}