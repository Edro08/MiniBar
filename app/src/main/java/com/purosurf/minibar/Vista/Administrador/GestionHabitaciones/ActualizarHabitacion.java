package com.purosurf.minibar.Vista.Administrador.GestionHabitaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.ActualizarHabitacionPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionHabitaciones.AdicionarHabitacionPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionHabitaciones.Interfaces.IActualizarHabitacion_View;

import java.util.ArrayList;
import java.util.List;

public class ActualizarHabitacion extends AppCompatActivity implements IActualizarHabitacion_View {

    //ELEMENTOS
    Button btnRegresarACH, btnActualizarACH;
    TextView tvNombreHabitacionACH;
    TextInputLayout tilNuevoNombreACH;
    TextInputEditText edtNuevoNombreACH;

    //variables
    String nuevoNombre, actualNombre;
    int idHabitacion;
    List<Habitacion> datosHabitacion;

    ActualizarHabitacionPresentador actualizarHabitacionPresentador;
    Bundle data;

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

        //obtener datos Bundle
        data = getIntent().getExtras();
        idHabitacion = data.getInt("idhabitacion");
        actualizarHabitacionPresentador = new ActualizarHabitacionPresentador(this);

        //Iniciarlizar lista para datos de habitacion y agregar datos
        datosHabitacion = new ArrayList<>(actualizarHabitacionPresentador.DatosHabitacion(
                getApplicationContext(),idHabitacion));

        actualNombre = datosHabitacion.get(0).getNombreHabitacion();
        tvNombreHabitacionACH.setText("Habitación: " + actualNombre);
        edtNuevoNombreACH.setText(actualNombre);

        btnActualizarACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDatos())
                {
                    actualizarHabitacionPresentador.ActualizarHabitacion(getApplicationContext(),
                            idHabitacion,nuevoNombre);
                    setResult(3); // code actualizar
                    finish();
                }
            }
        });

        btnRegresarACH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean validarDatos(){
        boolean estado = false;
        nuevoNombre = edtNuevoNombreACH.getText().toString();
        if(TextUtils.isEmpty(nuevoNombre)){
            tilNuevoNombreACH.setError("Debe ingresar nombre de la habitación");
            tilNuevoNombreACH.requestFocus();
        }else if(actualizarHabitacionPresentador.verificarHabitacion(
                getApplicationContext(),nuevoNombre)
                && !nuevoNombre.toLowerCase().equals(actualNombre.toLowerCase()))
        {
            tilNuevoNombreACH.setError("Nombre de habitación no disponible!");
            tilNuevoNombreACH.requestFocus();
        }else
        {
            estado = true;
        }
        return estado;
    }
}