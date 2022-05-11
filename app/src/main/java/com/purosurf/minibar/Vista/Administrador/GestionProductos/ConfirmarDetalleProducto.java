package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class ConfirmarDetalleProducto extends AppCompatActivity {

    //ELEMENTOS
    TextView tvEnunciadoCDP, tvNombreCDP, tvCategoriaCDP, tvPrecioCDP, tvEstadoCDP;
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
        }else if(accion.equals("actualizar")){
            tvEnunciadoCDP.setText("Confirmar Actualizacion de Producto");
        }else if(accion.equals("listar")){
            tvEnunciadoCDP.setText("Detalle Producto");
            btnConfirmarCDP.setEnabled(false);
            btnConfirmarCDP.setVisibility(View.GONE);
        }

        //mostrar texto en pantalla
        tvNombreCDP.setText("Nombre: ");
        tvCategoriaCDP.setText("Categoria: ");
        tvPrecioCDP.setText("Precio : $");
        tvEstadoCDP.setText("Estado: ");

        //evento botones
        btnConfirmarCDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accion.equals("adicionar")){
                    setResult(1); //code adicionar
                    finish();
                }else if(accion.equals("actualizar")){
                    setResult(3); //code actualizar
                    finish();
                }
            }
        });

        btnRegresarCDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}