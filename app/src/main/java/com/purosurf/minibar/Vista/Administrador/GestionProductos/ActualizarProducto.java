package com.purosurf.minibar.Vista.Administrador.GestionProductos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

import java.util.ArrayList;

public class ActualizarProducto extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarACP,btnCargarNuevaACP ,btnSiguienteACP;
    TextInputLayout tilProductoACP, tilCategoriaACP, tilPrecioACP;
    EditText edtProductoACP, edtPrecioACP;
    AutoCompleteTextView actvCategoriaACP;
    Switch swActivoACP;
    ImageView ivImagenProductoACP;

    ArrayList listaCategoria;

    //variables obtener datos edt
    String nombre, categoria;
    float precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_productos);

        //ASIGNAR ELEMENTOS
        btnRegresarACP = findViewById(R.id.btnRegresarACP);
        btnCargarNuevaACP = findViewById(R.id.btnCargarNuevaACP);
        btnSiguienteACP = findViewById(R.id.btnSiguienteACP);
        tilProductoACP = findViewById(R.id.tilProductoACP);
        tilCategoriaACP = findViewById(R.id.tilCategoriaACP);
        tilPrecioACP = findViewById(R.id.tilPrecioACP);
        edtProductoACP = findViewById(R.id.edtProductoACP);
        edtPrecioACP = findViewById(R.id.edtPrecioACP);
        actvCategoriaACP = findViewById(R.id.actvCategoriaACP);
        swActivoACP = findViewById(R.id.swActivoACP);
        ivImagenProductoACP = findViewById(R.id.ivImagenProductoACP);


        //=======Llenar dropdown menu
        listaCategoria = new ArrayList<String>();
        for (int i = 0; i <=6 ; i++){
            listaCategoria.add("Categoría "+i);
        }
        //adaptador para llenar el spinner
        ArrayAdapter categoriaAdapter = new ArrayAdapter
                (this, //contexto
                 R.layout.dropdown_texto, //diseño del texto
                 listaCategoria //listado de datos
                );
        actvCategoriaACP.setAdapter(categoriaAdapter); //asignar adaptador al contenedor AutoCompleteTextView


        //===========eventos botones
            //regresar pantalla anterior
        btnRegresarACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
            //cargar nueva imagen
        btnCargarNuevaACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
            //pasar a pantalla confirmar datos
        btnSiguienteACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
                Intent confirmar = new Intent(getApplicationContext(), ConfirmarDetalleProducto.class);
                confirmar.putExtra("accion","actualizar");
                lanzarActualizar.launch(confirmar);
            }
        });

    }
    //validar campos vacios
    public void validarCampos(){
        nombre = edtProductoACP.getText().toString().trim();
        String validarPrecio = edtPrecioACP.getText().toString().trim();
        categoria = actvCategoriaACP.getText().toString();

        //validacion
        if(TextUtils.isEmpty(nombre)) {
            tilProductoACP.setError("Debe ingresar nombre");
            tilProductoACP.requestFocus();
        }else if(TextUtils.isEmpty(categoria)){
            tilCategoriaACP.setError("Debe Seleccionar Categoria");
            tilCategoriaACP.requestFocus();
        }else if(TextUtils.isEmpty(validarPrecio)){
            tilPrecioACP.setError("Debe ingresar el precio");
            tilPrecioACP.requestFocus();
        }

    }

    //=====================lanzador de actividades
                /*resultCode = 1 -> adicionar
                  resultCode = 2 -> deshabilitar
                * resultCode = 3 -> actualizar
                * resultCode = 4 -> listar
                * */
    ActivityResultLauncher<Intent> lanzarActualizar = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if (result.getResultCode() == 3){
                        setResult(3);
                        finish();
                    }
                }
            }
    );
}