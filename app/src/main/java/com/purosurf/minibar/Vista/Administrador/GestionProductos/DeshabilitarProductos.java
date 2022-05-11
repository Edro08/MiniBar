package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.purosurf.minibar.R;

public class DeshabilitarProductos extends AppCompatActivity {

    //ELEMENTOS
    TextView tvProductoDP; //nombre del producto
    Switch swActivoDP; //estado
    Button btnRegresarDP, btnConfirmarDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshabilitar_producto);

        //ASIGNAR ELEMENTOS
        tvProductoDP = findViewById(R.id.tvProductoDP);
        swActivoDP = findViewById(R.id.swActivoDP);
        btnRegresarDP = findViewById(R.id.btnRegresarDP);
        btnConfirmarDP = findViewById(R.id.btnConfirmarDP);

        tvProductoDP.setText("Producto: ");

        //botones
        btnRegresarDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfirmarDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2); //code deshabilitar
                finish();
            }
        });
    }
}