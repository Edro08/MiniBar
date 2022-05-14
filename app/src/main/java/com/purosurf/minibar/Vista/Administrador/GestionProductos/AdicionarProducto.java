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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

import java.util.ArrayList;

public class AdicionarProducto extends AppCompatActivity {

    //ELEMENTOS
    TextInputLayout tilNombreAP, tilCategoriaAP, tilPrecioAP; //contenedores de EditText
    TextInputEditText edtNombreAP, edtPrecioAP; //edit text
    Switch swActivoAP; //estado activo
    AutoCompleteTextView actvCategoriaAP; //dropdown menu
    Button btnCargarImagenAP, btnSiguienteAP, btnRegresarAP;

    //ArrayList para llenar el spinner
    ArrayList<String> listaCategoria;

    //variables
    String nombre, categoria;
    float precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_productos);

        //ASIGNAR ELEMENTOS
        tilNombreAP = findViewById(R.id.tilNombreAP);
        tilPrecioAP = findViewById(R.id.tilPrecioAP);
        edtNombreAP = findViewById(R.id.edtNombreAP);
        tilCategoriaAP = findViewById(R.id.tilCategoriaAP);
        edtPrecioAP = findViewById(R.id.edtPrecioAP);
        actvCategoriaAP = findViewById(R.id.actvCategoriaAP);
        swActivoAP = findViewById(R.id.swActivoAP);
        btnCargarImagenAP = findViewById(R.id.btnCargarImagenAP);
        btnSiguienteAP = findViewById(R.id.btnSiguienteAP);
        btnRegresarAP = findViewById(R.id.btnRegresarAP);


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
        actvCategoriaAP.setAdapter(categoriaAdapter); //asignar adaptador al contenedor AutoCompleteTextView

        //=====evento botones
        btnRegresarAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //regresar pantalla anterior
            }
        });

        btnCargarImagenAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSiguienteAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
                Intent confirmar = new Intent(getApplicationContext(), ConfirmarDetalleProducto.class);
                confirmar.putExtra("accion","adicionar");
                lanzarActividad.launch(confirmar);
            }
        });

        // evento seleccionar dropdown menu categoria
        actvCategoriaAP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                categoriaAdapter.getItemId(i);//obtener indice del elemento
                Toast.makeText(getApplicationContext(), ""+categoriaAdapter.getItemId(i), Toast.LENGTH_SHORT).show(); //
            }
        });
    }

    //validar campos vacios
    public void validarCampos(){
        nombre = edtNombreAP.getText().toString().trim();
        String validarPrecio = edtPrecioAP.getText().toString().trim();
        categoria = actvCategoriaAP.getText().toString();

        //validacion
        if(TextUtils.isEmpty(nombre)) {
            tilNombreAP.setError("Debe ingresar nombre");
            tilNombreAP.requestFocus();
        }else if(TextUtils.isEmpty(categoria)){
            tilCategoriaAP.setError("Debe Seleccionar Categoria");
            tilCategoriaAP.requestFocus();

        }else if(TextUtils.isEmpty(validarPrecio)){
            tilPrecioAP.setError("Debe ingresar el precio");
            tilPrecioAP.requestFocus();
        }

    }

    //=====================lanzador de actividades
    /*resultCode = 1 -> adicionar
      resultCode = 2 -> deshabilitar
    * resultCode = 3 -> actualizar
    * resultCode = 4 -> listar
    * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if (result.getResultCode() == 1){
                        setResult(1);
                        finish();
                    }
                }
            }
    );
}