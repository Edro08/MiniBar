package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.purosurf.minibar.R;

public class DetalleReporteES extends AppCompatActivity {

    //ELEMENTOS
    TextView    tvNumeroReporteES, //numero-id de reporte
                tvTipoReporteES, //reporte salida-entrada
                tvUsuarioReporteES, //usuario que registro el reporte
                tvProductoReporteES, //nombre del producto
                tvCantidadReporteES, //cantidad del producto
                tvFechaReporteES, //fecha de creacion del reporte
                tvPrecioReporteES, //precio unitario del producto
                tvTotalReporteES; // total precio unitario * cantidad
    TextInputEditText edtDescripcionReporteES; //descripcion-detalles del registro hecho
    Button btnRegresarReporteES, //regresar pantalla anterior
            btnGenerarReporteES; // generar reporte

    //BUNDLE
    Bundle datos;

    //VARIABLES
    String accion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reporte_es);

        //ASIGNAR ELEMENTOS
        tvNumeroReporteES = findViewById(R.id.tvNumeroReporteES);
        tvTipoReporteES = findViewById(R.id.tvTipoReporteES);
        tvUsuarioReporteES = findViewById(R.id.tvUsuarioReporteES);
        tvProductoReporteES = findViewById(R.id.tvProductoReporteES);
        tvCantidadReporteES = findViewById(R.id.tvCantidadReporteES);
        tvFechaReporteES = findViewById(R.id.tvFechaReporteES);
        tvPrecioReporteES = findViewById(R.id.tvPrecioReporteES);
        tvTotalReporteES = findViewById(R.id.tvTotalReporteES);
        edtDescripcionReporteES = findViewById(R.id.edtDescripcionReporteES);
        btnRegresarReporteES = findViewById(R.id.btnRegresarReporteES);
        btnGenerarReporteES = findViewById(R.id.btnGenerarReporteES);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        //mostrar texto en pantalla
        if (accion.equals("compras")){
            tvNumeroReporteES.setText("N° Reporte: ");
            tvTipoReporteES.setText("Tipo: Reporte de Compras");
            tvUsuarioReporteES.setText("Usuario: ");
            tvProductoReporteES.setText("Producto: ");
            tvCantidadReporteES.setText("Cantidad: ");
            tvFechaReporteES.setText("Fecha: ");
            tvPrecioReporteES.setText("Precio Unitario: $");
            tvTotalReporteES.setText("Total: $");
        } else if(accion.equals("edicion")){
            tvNumeroReporteES.setText("N° Reporte: ");
            tvTipoReporteES.setText("Tipo: Reporte de Edición de Existencias");
            tvUsuarioReporteES.setText("Usuario: ");
            tvProductoReporteES.setText("Producto: ");
            tvCantidadReporteES.setText("Cantidad: ");
            tvFechaReporteES.setText("Fecha: ");
            tvPrecioReporteES.setText("Precio Unitario: $");
            tvTotalReporteES.setText("Total: $");
        }


        // evento botones
        btnRegresarReporteES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGenerarReporteES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("compras")){
                    setResult(6); //code reporte entrada/compras
                    finish();
                }else if (accion.equals("edicion")){
                    setResult(7); //code reporte edicion/salida
                    finish();
                }
            }
        });
    }
}