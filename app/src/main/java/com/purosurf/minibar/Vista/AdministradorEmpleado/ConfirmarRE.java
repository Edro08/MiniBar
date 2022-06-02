package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.purosurf.minibar.R;

public class ConfirmarRE extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarCRE, btnConfirmarCRE;
    TextView    tvAppBarCRE, //enunciado app bar
                tvEnunciadoCRE, // enunciado vista
                tvProductoCRE, //nombre del producto
                tvCantidadCRE, //cantidad producto
                tvPrecioUnitarioCRE;
    TextInputEditText edtDescripcionCRE; //descripcion de la entrada-salida

    //BUNDLE
    Bundle datos;
    String accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_re);

        //ASIGNAR ELEMENTOS
        btnRegresarCRE = findViewById(R.id.btnRegresarCRE);
        btnConfirmarCRE = findViewById(R.id.btnConfirmarCRE);
        tvAppBarCRE = findViewById(R.id.tvAppBarCRE);
        tvEnunciadoCRE = findViewById(R.id.tvEnunciadoCRE);
        tvProductoCRE = findViewById(R.id.tvProductoCRE);
        tvCantidadCRE = findViewById(R.id.tvCantidadCRE);
        tvPrecioUnitarioCRE = findViewById(R.id.tvPrecioUnitarioCRE);
        edtDescripcionCRE = findViewById(R.id.edtDescripcionCRE);


        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        //mostrar en pantalla
        if (accion.equals("entrada")){
            tvAppBarCRE.setText("Confirmar Registro de Compra");
            tvEnunciadoCRE.setText("Detalle de compras");
            btnConfirmarCRE.setText("Confirmar Compra");
        } else if (accion.equals("salida")) {
            tvAppBarCRE.setText("Confirmar Edición de existencias");
            tvEnunciadoCRE.setText("Detalle de existencias");
            btnConfirmarCRE.setText("Confirmar edición");
        }

        tvProductoCRE.setText(datos.getString("ProdName"));
        tvCantidadCRE.setText("Cantidad: "+datos.getString("Cantidad"));
        tvPrecioUnitarioCRE.setText("Precio Unitario: "+datos.getString("Precio"));
        edtDescripcionCRE.setText(datos.getString("Descrip"));

        //evento botones
        btnConfirmarCRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("entrada")){
                    setResult(1);
                    finish();
                } else if (accion.equals("salida")){
                    setResult(2);
                    finish();
                }
            }
        });

        btnRegresarCRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}