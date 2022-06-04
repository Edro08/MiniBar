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
import com.purosurf.minibar.Presentador.Adaptadores.SeleccionarProductoAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.ActualizarProductoPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionProductos.SeleccionarProductoPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.IActualizarProducto_View;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActualizarProducto extends AppCompatActivity implements IActualizarProducto_View, ISeleccionarProducto_View {

    //ELEMENTOS
    Button btnRegresarACP,btnSiguienteACP, btnQuitarMinimaACP, btnAgregarMinimaACP, btnQuitarMaximaACP, btnAgregarMaximaACP;
    TextInputLayout tilProductoACP, tilCategoriaACP, tilPrecioACP, tilImagenACP;
    EditText edtProductoACP, edtPrecioACP, edtImagenACP, edtMinimaACP, edtMaximaACP;
    AutoCompleteTextView actvCategoriaACP;
    Switch swActivoACP;
    ImageView ivImagenProductoACP;

    ArrayList listaCategoria;
    SeleccionarProductoPresentador seleccionarProductoPresentador;
    ActualizarProductoPresentador actualizarProductoPresentador;

    //variables obtener datos edt
    String nombre, categoria, imagen;
    float precio;
    int minimo, maximo;

    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_productos);

        //ASIGNAR ELEMENTOS
        btnRegresarACP = findViewById(R.id.btnRegresarACP);
        btnSiguienteACP = findViewById(R.id.btnSiguienteACP);
        btnQuitarMinimaACP = findViewById(R.id.btnQuitarMinimaACP);
        btnAgregarMinimaACP = findViewById(R.id.btnAgregarMinimaACP);
        btnQuitarMaximaACP = findViewById(R.id.btnQuitarMaximaACP);
        btnAgregarMaximaACP = findViewById(R.id.btnAgregarMaximaACP);
        tilProductoACP = findViewById(R.id.tilProductoACP);
        tilCategoriaACP = findViewById(R.id.tilCategoriaACP);
        tilPrecioACP = findViewById(R.id.tilPrecioACP);
        tilImagenACP = findViewById(R.id.tilImagenACP);
        edtProductoACP = findViewById(R.id.edtProductoACP);
        edtPrecioACP = findViewById(R.id.edtPrecioACP);
        edtImagenACP = findViewById(R.id.edtImagenACP);
        edtMinimaACP = findViewById(R.id.edtMinimaACP);
        edtMaximaACP = findViewById(R.id.edtMaximaACP);
        actvCategoriaACP = findViewById(R.id.actvCategoriaACP);
        swActivoACP = findViewById(R.id.swActivoACP);
        ivImagenProductoACP = findViewById(R.id.ivImagenProductoACP);

        datos = getIntent().getExtras();

        seleccionarProductoPresentador = new SeleccionarProductoPresentador(this);
        actualizarProductoPresentador = new ActualizarProductoPresentador(this);

        actualizarProductoPresentador.obtenerProducto(getApplicationContext(), datos.getInt("idproducto"));
            //mostrar en pantalla
        edtProductoACP.setText(actualizarProductoPresentador.getProducto().getProductoNombre());
        actvCategoriaACP.setText(actualizarProductoPresentador.getProducto().getNombreCate(), false);
        edtPrecioACP.setText(""+actualizarProductoPresentador.getProducto().getPrecioUnitario());
        edtImagenACP.setText(actualizarProductoPresentador.getProducto().getImagenURL());
        minimo = Integer.parseInt(edtMinimaACP.getText().toString());
        maximo = Integer.parseInt(edtMaximaACP.getText().toString());
        if (actualizarProductoPresentador.getProducto().getIdEstado() == 1){
            swActivoACP.setText("Activo");
            swActivoACP.setChecked(true);
        } else {
            swActivoACP.setText("Inactivo");
            swActivoACP.setChecked(false);
        }
        edtMinimaACP.setText(""+actualizarProductoPresentador.Cantidades(getApplicationContext()).get(0));//
        edtMaximaACP.setText(""+actualizarProductoPresentador.Cantidades(getApplicationContext()).get(1));
        Picasso.with(getApplicationContext()).load(actualizarProductoPresentador.getProducto().getImagenURL()).into(ivImagenProductoACP);

        minimo = Integer.parseInt(edtMinimaACP.getText().toString());
        maximo = Integer.parseInt(edtMaximaACP.getText().toString());

        //=======Llenar dropdown menu
        listaCategoria = new ArrayList<String>(seleccionarProductoPresentador.listaCategorias(getApplicationContext()));
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

            //pasar a pantalla confirmar datos
        btnSiguienteACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
                Intent confirmar = new Intent(getApplicationContext(), ConfirmarDetalleProducto.class);
                confirmar.putExtra("accion","actualizar");
                confirmar.putExtra("nombre", edtProductoACP.getText().toString());
                confirmar.putExtra("categoria", actvCategoriaACP.getText().toString().trim());
                confirmar.putExtra("precio", edtPrecioACP.getText().toString().trim());
                confirmar.putExtra("estado", swActivoACP.isChecked() ? "Activo": "Inactivo");
                confirmar.putExtra("maximo", edtMaximaACP.getText().toString().trim());
                confirmar.putExtra("minimo", edtMinimaACP.getText().toString().trim());
                confirmar.putExtra("imagen", edtImagenACP.getText().toString().trim());
                lanzarActualizar.launch(confirmar);
            }
        });

        //evento agregar quitar cantidad
        btnQuitarMinimaACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (minimo > 0){
                    minimo--;
                    edtMinimaACP.setText(""+minimo);
                }
            }
        });

        btnAgregarMinimaACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minimo++;
                edtMinimaACP.setText(""+minimo);
            }
        });

        btnQuitarMaximaACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maximo > 0){
                    maximo--;
                    edtMaximaACP.setText(""+maximo);
                }
            }
        });

        btnAgregarMaximaACP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maximo++;
                edtMaximaACP.setText(""+maximo);
            }
        });
    }
    //validar campos vacios
    public boolean validarCampos(){
        nombre = edtProductoACP.getText().toString().trim();
        String validarPrecio = edtPrecioACP.getText().toString().trim();
        categoria = actvCategoriaACP.getText().toString();
        imagen = edtImagenACP.getText().toString();
        precio = Float.parseFloat(edtPrecioACP.getText().toString());

        //validacion
        if(TextUtils.isEmpty(nombre)) {
            tilProductoACP.setError("Debe ingresar nombre");
            tilProductoACP.requestFocus();
            return false;
        }else if(actualizarProductoPresentador.verificarProducto(getApplicationContext(), nombre)){
           // tilProductoACP.setError("Nombre de producto no disponible!");
           // tilProductoACP.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(categoria)){
            tilCategoriaACP.setError("Debe Seleccionar Categoria");
            tilCategoriaACP.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(validarPrecio)){
            tilPrecioACP.setError("Debe ingresar el precio");
            tilPrecioACP.requestFocus();
            return false;
        } else if(TextUtils.isEmpty(edtMinimaACP.getText().toString())){
            tilImagenACP.setError("Debe ingresar el link de la imagen");
            tilImagenACP.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(edtMaximaACP.getText().toString())){
            tilImagenACP.setError("Debe ingresar el link de la imagen");
            tilImagenACP.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    //=====================lanzador de actividades
                /*resultCode = 1 - 2 -> adicionar
                  resultCode = 3 -> deshabilitar
                * resultCode = 4 -> actualizar
                * resultCode = 5 -> listar
                * */
    ActivityResultLauncher<Intent> lanzarActualizar = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if (result.getResultCode() == 4){
                        setResult(4);
                        actualizarProductoPresentador.actualizarPoducto(getApplicationContext(),
                                                                        actualizarProductoPresentador.getProducto().getIdProducto(),
                                                                        nombre,
                                                                        categoria,
                                                                        precio,
                                                                        swActivoACP.isChecked()? true: false,
                                                                        minimo,
                                                                        maximo,
                                                                        imagen);
                        finish();
                    }
                }
            }
    );
}