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
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.AdicionarProductoPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.Interfaces.ISeleccionarProductoPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.SeleccionarProductoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.IAdicionarProducto_View;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;

import java.util.ArrayList;

public class AdicionarProducto extends AppCompatActivity implements IAdicionarProducto_View, ISeleccionarProducto_View {

    //ELEMENTOS
    TextInputLayout tilNombreAP, tilCategoriaAP, tilPrecioAP, tilImagenAP; //contenedores de EditText
    TextInputEditText edtNombreAP, edtPrecioAP, edtMaximo, edtMinimo, edtImagenAP; //edit text
    Switch swActivoAP; //estado activo
    AutoCompleteTextView actvCategoriaAP; //dropdown menu
    Button btnSiguienteAP, btnRegresarAP, btnQuitarMinimaAP, btnAgregarMinimaAP, btnQuitarMaximaAP, btnAgregarMaximaAP;


    //ArrayList para llenar el spinner
    ArrayList<String> listaCategoria;

    //variables
    String nombre, categoria, imagen;
    int minimo, maximo;
    float precio;


    AdicionarProductoPresentador adicionarProductoPresentador;
    SeleccionarProductoPresentador seleccionarProductoPresentador;

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
        tilImagenAP = findViewById(R.id.tilImagenAP);
        edtImagenAP = findViewById(R.id.edtImagenAP);
        btnSiguienteAP = findViewById(R.id.btnSiguienteAP);
        btnRegresarAP = findViewById(R.id.btnRegresarAP);
        edtMaximo = findViewById(R.id.edtMaximaAP);
        edtMinimo = findViewById(R.id.edtMinimaAP);
        btnQuitarMinimaAP = findViewById(R.id.btnQuitarMinimaAP);
        btnAgregarMinimaAP = findViewById(R.id.btnAgregarMinimaAP);
        btnQuitarMaximaAP = findViewById(R.id.btnQuitarMaximaAP);
        btnAgregarMaximaAP = findViewById(R.id.btnAgregarMaximaAP);

        //minimo = Integer.parseInt(edtMinimo.getText().toString());
        minimo = 0;
        maximo = 0;
        //maximo = Integer.parseInt(edtMaximo.getText().toString());

        adicionarProductoPresentador = new AdicionarProductoPresentador(this);
        seleccionarProductoPresentador = new SeleccionarProductoPresentador(this);

        //=======Llenar dropdown menu
        listaCategoria = (ArrayList<String>) seleccionarProductoPresentador.listaCategorias(getApplicationContext());
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

        btnSiguienteAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    Intent confirmar = new Intent(getApplicationContext(), ConfirmarDetalleProducto.class);
                    confirmar.putExtra("accion","adicionar");
                    confirmar.putExtra("nombre", edtNombreAP.getText().toString());
                    confirmar.putExtra("categoria", actvCategoriaAP.getText().toString().trim());
                    confirmar.putExtra("precio", edtPrecioAP.getText().toString().trim());
                    confirmar.putExtra("estado", swActivoAP.isChecked() ? "Activo": "Inactivo");
                    confirmar.putExtra("maximo", edtMaximo.getText().toString().trim());
                    confirmar.putExtra("minimo", edtMinimo.getText().toString().trim());
                    confirmar.putExtra("imagen", edtImagenAP.getText().toString().trim());
                    lanzarActividad.launch(confirmar);
                }
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

        //evento agregar quitar cantidad
        btnQuitarMinimaAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     if (minimo > 0){
                         minimo--;
                         edtMinimo.setText(""+minimo);
                     }
            }
        });

        btnAgregarMinimaAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minimo++;
                edtMinimo.setText(""+minimo);
            }
        });

        btnQuitarMaximaAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     if (maximo > 0){
                         maximo--;
                         edtMaximo.setText(""+maximo);
                     }
            }
        });

        btnAgregarMaximaAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maximo++;
                edtMaximo.setText(""+maximo);
            }
        });
    }

    //validar campos vacios
    public boolean validarCampos(){
        nombre = edtNombreAP.getText().toString().trim();
        String validarPrecio = edtPrecioAP.getText().toString().trim();
        categoria = actvCategoriaAP.getText().toString();
        imagen = edtImagenAP.getText().toString();

        //validacion
        if(TextUtils.isEmpty(nombre)) {
            tilNombreAP.setError("Debe ingresar nombre");
            tilNombreAP.requestFocus();
            return false;
        }else if(adicionarProductoPresentador.verificarProducto(getApplicationContext(), nombre)){
            tilNombreAP.setError("Nombre de producto no disponible!");
            tilNombreAP.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(categoria)){
            tilCategoriaAP.setError("Debe Seleccionar Categoria");
            tilCategoriaAP.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(validarPrecio)){
            tilPrecioAP.setError("Debe ingresar el precio");
            tilPrecioAP.requestFocus();
            return false;
        } else if(TextUtils.isEmpty(edtMinimo.getText().toString())){
            tilImagenAP.setError("Debe ingresar el link de la imagen");
            tilImagenAP.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(edtMaximo.getText().toString())){
            tilImagenAP.setError("Debe ingresar el link de la imagen");
            tilImagenAP.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(imagen)){
            tilImagenAP.setError("Debe ingresar el link de la imagen");
            tilImagenAP.requestFocus();
            return false;
        } else {
            return true;
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
                        precio = Float.parseFloat(edtPrecioAP.getText().toString());
                        adicionarProductoPresentador.AdicionarProducto(getApplicationContext(), nombre, categoria, precio, swActivoAP.isChecked(), minimo, maximo,  imagen);
                        finish();
                    }
                }
            }
    );
}