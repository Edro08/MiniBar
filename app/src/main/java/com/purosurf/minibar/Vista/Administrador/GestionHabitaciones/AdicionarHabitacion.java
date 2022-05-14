package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class AdicionarHabitacion extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarAH, btnConfirmarAH;
    TextInputLayout tilNombreAH; //contenedor del edt nombre habitacion
    TextInputEditText edtNombreAH; //edt nombre habitacion
    Switch swActivoAH; //switch estado habitación

    //Variables
    String nombreHabitación;

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
                validarCampos();
                setResult(1); //code adicionar
                finish();
            }
        });
    }

    public void validarCampos(){
        nombreHabitación = edtNombreAH.getText().toString();

        if(TextUtils.isEmpty(nombreHabitación)){
            tilNombreAH.setError("Debe ingresar el nombre de la habitación");
            tilNombreAH.requestFocus();
        }

    }
}