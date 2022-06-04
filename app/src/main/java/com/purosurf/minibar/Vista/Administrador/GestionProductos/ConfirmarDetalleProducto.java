package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purosurf.minibar.R;
import com.squareup.picasso.Picasso;

public class ConfirmarDetalleProducto extends AppCompatActivity {

    //ELEMENTOS
    TextView tvEnunciadoCDP, tvNombreCDP, tvCategoriaCDP, tvPrecioCDP, tvEstadoCDP, tvCantidadMinCDP, tvCantidadMaxCDP;
    ImageView ivImagenProductoCDP;
    Button btnConfirmarCDP, btnRegresarCDP;

    //bundle
    Bundle datos;

    //variables
    String accion; //variable que captura la accion (adicionar, actualizar,detalle)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_detalle_producto);

        //ASIGNAR ELEMENTOS
        tvEnunciadoCDP = findViewById(R.id.tvEnunciadoCDP);
        tvNombreCDP = findViewById(R.id.tvNombreCDP);
        tvCategoriaCDP = findViewById(R.id.tvCategoriaCDP);
        tvPrecioCDP = findViewById(R.id.tvPrecioCDP);
        tvCantidadMinCDP = findViewById(R.id.tvCantidadMinCDP);
        tvCantidadMaxCDP = findViewById(R.id.tvCantidadMaxCDP);
        tvEstadoCDP = findViewById(R.id.tvEstadoCDP);
        ivImagenProductoCDP = findViewById(R.id.ivImagenProductoCDP);
        btnConfirmarCDP = findViewById(R.id.btnConfirmarCDP);
        btnRegresarCDP = findViewById(R.id.btnRegresarCDP);

        //obtener bundle
        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        //asignar enunciado
        if(accion.equals("adicionar")){
            tvEnunciadoCDP.setText("Confirmar Nuevo Producto");
            tvNombreCDP.setText("Nombre: "+datos.getString("nombre"));
            tvCategoriaCDP.setText("Categoria: "+datos.getString("categoria"));
            tvCantidadMinCDP.setText("Cantidad Mínima: "+datos.getString("minimo"));
            tvCantidadMaxCDP.setText("Cantidad Máxima: "+datos.getString("maximo"));
            tvEstadoCDP.setText("Estado: "+datos.getString("estado"));
            tvPrecioCDP.setText("Precio : $" + datos.getString("precio"));
        }else if(accion.equals("actualizar")){
            tvEnunciadoCDP.setText("Confirmar Actualizacion de Producto");
            tvNombreCDP.setText("Nombre: "+datos.getString("nombre"));
            tvCategoriaCDP.setText("Categoria: "+datos.getString("categoria"));
            tvCantidadMinCDP.setText("Cantidad Mínima: "+datos.getString("minimo"));
            tvCantidadMaxCDP.setText("Cantidad Máxima: "+datos.getString("maximo"));
            tvEstadoCDP.setText("Estado: "+datos.getString("estado"));
            tvPrecioCDP.setText("Precio : $" + datos.getString("precio"));
        }else if(accion.equals("listar")){
            tvEnunciadoCDP.setText("Detalle Producto");
            btnConfirmarCDP.setEnabled(false);
            tvNombreCDP.setText("Nombre: "+datos.getString("nombre"));
            tvCategoriaCDP.setText("Categoria: "+datos.getString("categoria"));
            tvPrecioCDP.setText("Precio: $"+datos.getFloat("precio"));
            tvCantidadMinCDP.setText("Cantidad Mínima: "+datos.getInt("minimo"));
            tvCantidadMaxCDP.setText("Cantidad Máxima: "+datos.getInt("maximo"));
            btnConfirmarCDP.setVisibility(View.GONE);
            if (datos.getInt("estado") == 1){
                tvEstadoCDP.setText("Estado: Activo");
            } else {
                tvEstadoCDP.setText("Estado: Inactivo");
            }
        }


        Picasso.with(getApplicationContext())
                .load(datos.getString("imagen"))
                .into(ivImagenProductoCDP);


        //evento botones
        btnConfirmarCDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accion.equals("adicionar")){
                    setResult(1); //code adicionar
                    finish();
                }else if(accion.equals("actualizar")){
                    setResult(4); //code actualizar
                    finish();
                }
            }
        });

        btnRegresarCDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("listar")){
                    setResult(5); //code listar
                }
                finish();
            }
        });
    }

}