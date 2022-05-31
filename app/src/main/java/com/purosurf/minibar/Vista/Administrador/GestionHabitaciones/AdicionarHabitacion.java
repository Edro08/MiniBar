package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.AdicionarHabitacionPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IAdicionarHabitacion_View;

public class AdicionarHabitacion extends AppCompatActivity implements IAdicionarHabitacion_View {

    //ELEMENTOS
    Button btnRegresarAH, btnConfirmarAH;
    TextInputLayout tilNombreAH; //contenedor del edt nombre habitacion
    TextInputEditText edtNombreAH; //edt nombre habitacion
    Switch swActivoAH; //switch estado habitación
    boolean estado = true;
    String nombrehabitacion;
    AdicionarHabitacionPresentador adicionarHabitacionPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_habitacion);

        //ASIGNAR ELEMENTOS
        btnRegresarAH = findViewById(R.id.btnRegresarAH);
        btnConfirmarAH = findViewById(R.id.btnConfirmarAH);
        tilNombreAH = findViewById(R.id.tilNombreAH);
        edtNombreAH = findViewById(R.id.edtNombreAH);
        swActivoAH = findViewById(R.id.swActivoAH);

        adicionarHabitacionPresentador = new AdicionarHabitacionPresentador(this);

        swActivoAH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swActivoAH.isChecked())
                {
                    swActivoAH.setText("Activo");
                    estado = true;
                }
                else
                {
                    swActivoAH.setText("Inactivo");
                    estado = false;
                }
            }
        });

        //evento botones
        btnRegresarAH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
            }
        });

        btnConfirmarAH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos())
                {
                    adicionarHabitacionPresentador.AdicionarHabitacion(getApplicationContext(),
                            nombrehabitacion, estado);
                    setResult(1); //code adicionar
                    finish();
                }
            }
        });
    }

    public boolean validarCampos(){
        boolean estado = false;
        nombrehabitacion = edtNombreAH.getText().toString();

        if(TextUtils.isEmpty(nombrehabitacion)){
            tilNombreAH.setError("Debe ingresar el nombre de la habitación");
            tilNombreAH.requestFocus();
        }else if(adicionarHabitacionPresentador.verificarHabitacion(getApplicationContext(),nombrehabitacion))
        {
            tilNombreAH.setError("Nombre de habitacion no disponible!");
            tilNombreAH.requestFocus();
        }else
        {
            estado = true;
        }
        return estado;
    }
}