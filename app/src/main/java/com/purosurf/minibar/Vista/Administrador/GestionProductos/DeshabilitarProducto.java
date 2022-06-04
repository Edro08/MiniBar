package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.DeshabilitarHabitacionPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.DeshabilitarProductoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.IDeshabilitarProducto_View;

public class DeshabilitarProducto extends AppCompatActivity implements IDeshabilitarProducto_View {

    //ELEMENTOS
    TextView tvProductoDP; //nombre del producto
    Switch swActivoDP; //estado
    Button btnRegresarDP, btnConfirmarDP;
    Bundle datos;
    DeshabilitarProductoPresentador deshabilitarProductoPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshabilitar_producto);

        //ASIGNAR ELEMENTOS
        tvProductoDP = findViewById(R.id.tvProductoDP);
        swActivoDP = findViewById(R.id.swActivoDP);
        btnRegresarDP = findViewById(R.id.btnRegresarDP);
        btnConfirmarDP = findViewById(R.id.btnConfirmarDP);

        deshabilitarProductoPresentador = new DeshabilitarProductoPresentador(this);

        datos = getIntent().getExtras();

        tvProductoDP.setText("Producto: "+datos.getString("producto"));
        if (datos.getInt("estado") == 1){
            swActivoDP.setText("Activo");
            swActivoDP.setChecked(true);
        } else {
            swActivoDP.setText("Inactivo");
            swActivoDP.setChecked(false);
        }

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
                if (swActivoDP.isChecked()){
                    setResult(3); //code deshabilitar
                    deshabilitarProductoPresentador.ModificarEstadoProducto(getApplicationContext(), datos.getInt("idproducto"),1 );
                    finish();
                } else {
                    setResult(2); //code habilitar
                    deshabilitarProductoPresentador.ModificarEstadoProducto(getApplicationContext(), datos.getInt("idproducto"), 2);
                    finish();
                }
            }
        });
    }
}