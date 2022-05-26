package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.R;

public class RegistrarEditar extends AppCompatActivity {
    //===============Actividad Registrar entrada y Editar Existencias (Salidas)===============

    //ELEMENTOS
        //botonees de desplazamiento de pantalla
    Button  btnRegresarRE, //regresar pantalla anterior
            btnRegistrarRE; // pasar a la pantalla de confirmacion
    FloatingActionButton fabSeleccionarRE; //llamar actividad para seleccionar producto a modificar inventario
        // mostrar texto en pantalla
    TextView   tvAppBarRE, //encabezado de appbar
               tvEnunciadoRE, //encabezado formulario
                tvNombreProductoRE; //nombre del producto seleccionado
        //Edit Text
    TextInputLayout tilPrecioUnitarioRE; //contenedor del edt precio
    TextInputEditText   edtPrecioUnitarioRE,//edt precio unitario producto
                        edtCantidadProductoRE, //edt cantidad de producto
                        edtMinimaProductoRE, //edt cantidad minima producto
                        edtMaximaProductoRE, //edt cantidad maxima producto
                        edtDescripcionRE; //edt descripcion del registro

        //Botones para controlar cantidades
    Button  btnQuitarCantidadRE, // quitar cantidad de producto
            btnAgregarCantidadRE, //agregar cantidad de producto
            btnQuitarMinimaRE, //quitar cantidad minima
            btnAgregarMinimaRE, //agregar cantidad minima
            btnQuitarMaximaRE, //quitar cantidad maxima
            btnAgregarMaximaRE; //agregar cantidad maxima
        //contenedor
    FrameLayout flContenedorRE; //contenedor que se muestra cuando el usuario agrega al producto
    TextView tvComentarioRE;


    //VARIABLES
    int cantidadProducto, // almacenar cantidad edt
        cantidadMinima, // almacenar cantidad minima edt
        cantidadMaxima; // almacenar cantidad maxima edt

    double precioU; //precio unitario
    String descripcion; // descripcion de la entrada/salida

    String accion; //capturar bundle (si el formulario es de entrada o salida)

    //BUNDLE
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_editar);

       //ASIGNAR ELEMENTOS
        btnRegresarRE = findViewById(R.id.btnRegresarRE);
        btnRegistrarRE = findViewById(R.id.btnRegistrarRE);
        fabSeleccionarRE = findViewById(R.id.fabSeleccionarRE);
        tvAppBarRE = findViewById(R.id.tvAppBarRE);
        tvEnunciadoRE = findViewById(R.id.tvEnunciadoRE);
        tvNombreProductoRE = findViewById(R.id.tvNombreProductoRE);
        tilPrecioUnitarioRE = findViewById(R.id.tilPrecioUnitarioRE);
        edtCantidadProductoRE = findViewById(R.id.edtCantidadProductoRE);
        edtPrecioUnitarioRE = findViewById(R.id.edtPrecioUnitarioRE);
        edtMinimaProductoRE = findViewById(R.id.edtMinimaProductoRE);
        edtMaximaProductoRE = findViewById(R.id.edtMaximaProductoRE);
        edtDescripcionRE = findViewById(R.id.edtDescripcionRE);
        btnQuitarCantidadRE = findViewById(R.id.btnQuitarCantidadRE);
        btnAgregarCantidadRE = findViewById(R.id.btnAgregarCantidadRE);
        btnQuitarMinimaRE = findViewById(R.id.btnQuitarMinimaRE);
        btnAgregarMinimaRE = findViewById(R.id.btnAgregarMinimaRE);
        btnQuitarMaximaRE = findViewById(R.id.btnQuitarMaximaRE);
        btnAgregarMaximaRE = findViewById(R.id.btnAgregarMaximaRE);
        flContenedorRE = findViewById(R.id.flContenedorRE);
        tvComentarioRE = findViewById(R.id.tvComentarioRE);

        //bundle
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        if (accion.equals("entrada")){
            tvAppBarRE.setText("Registrar Entrada");
            tvEnunciadoRE.setText("Registro de Entrada");
            btnRegistrarRE.setText("Registrar Entrada");
        } else if (accion.equals("salida")){
            tvAppBarRE.setText("Editar Existencias");
            tvEnunciadoRE.setText("Edición de Existencias");
            btnRegistrarRE.setText("Registrar Edicion de InventarioMB");
        }


        ////INICIAR VARIABLES
        cantidadProducto = 0;
        cantidadMinima = 0;
        cantidadMaxima = 0;

        //Botones desplazamiento de pantallas
        btnRegresarRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
            //confirmar registro-edicion
        btnRegistrarRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registo = new Intent(getApplicationContext(), ConfirmarRE.class);
                registo.putExtra("accion", accion);
                lanzarActividad.launch(registo);
            }
        });
            //seleccionar productos
        fabSeleccionarRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seleccionarProducto = new Intent(getApplicationContext(), AgregarProductoRE.class);
                seleccionarProducto.putExtra("accion", accion);
                lanzarActividad.launch(seleccionarProducto);
            }
        });



        //AGREGAR - QUITAR CANTIDAD PRODUCTO
        btnQuitarCantidadRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidadProducto > 0) {
                    cantidadProducto--;
                    edtCantidadProductoRE.setText(""+cantidadProducto);
                }
            }
        });

        btnAgregarCantidadRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidadProducto++;
                edtCantidadProductoRE.setText(""+cantidadProducto);
            }
        });

        //AGREGAR - QUITAR CANTIDAD MINIMA
        btnQuitarMinimaRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidadMinima > 0){
                    cantidadMinima--;
                    edtMinimaProductoRE.setText(""+cantidadMinima);
                }
            }
        });

        btnAgregarMinimaRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidadMinima++;
                edtMinimaProductoRE.setText(""+cantidadMinima);
            }
        });

        //AGREGAR - QUITAR CANTIDAD MAXIMA
        btnQuitarMaximaRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cantidadMaxima > 0){
                    cantidadMaxima--;
                    edtMaximaProductoRE.setText(""+cantidadMaxima);
                }
            }
        });

        btnAgregarMaximaRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidadMaxima++;
                edtMaximaProductoRE.setText(""+cantidadMaxima);
            }
        });
    }

    //lanzador de actividades
    /*  code 1 = registra entrada
     *   code 2 = editar existencia
     *   code 3 = generar reporte
     *   code 4 = ver existencia
     * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 1){
                        setResult(1);
                        finish();
                    }else if(result.getResultCode() == 2){
                        setResult(2);
                        finish();
                    } else if (result.getResultCode() == RESULT_OK){
                        flContenedorRE.setVisibility(View.VISIBLE); //mostrar contenedor
                        tvComentarioRE.setVisibility(View.GONE);
                    }
                }
            }
    );

}