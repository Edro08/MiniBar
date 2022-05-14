package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class ActualizarHabitacion extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarACH, btnActualizarACH;
    TextView tvNombreHabitacionACH;
    TextInputLayout tilNuevoNombreACH;
    TextInputEditText edtNuevoNombreACH;

    //variables
    String nuevoNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_habitacion);

        //asignar elementos
        btnRegresarACH = findViewById(R.id.btnRegresarACH);
        btnActualizarACH = findViewById(R.id.btnActualizarACH);
        tvNombreHabitacionACH = findViewById(R.id.tvNombreHabitacionACH);
        tilNuevoNombreACH = findViewById(R.id.tilNuevoNombreACH);
        edtNuevoNombreACH = findViewById(R.id.edtNuevoNombreACH);

        tilNuevoNombreACH.setHelperTextEnabled(true);
        tilNuevoNombreACH.setHelperText("Dato requerido");
        tvNombreHabitacionACH.setText("Habitación: Nombre Habitación");

        btnActualizarACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatos();
                setResult(3); // code actualizar
                finish();
            }
        });

        btnRegresarACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void validarDatos(){
        nuevoNombre = edtNuevoNombreACH.getText().toString();
        if(TextUtils.isEmpty(nuevoNombre)){
            tilNuevoNombreACH.setError("Debe ingresar nombre de la habitación");
            tilNuevoNombreACH.requestFocus();
        }
    }
}