package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Modelo.Entrada;
import com.purosurf.minibar.Modelo.Salida;
import com.purosurf.minibar.Presentador.Administrador.GestionarInventarios.GestionarInventario;
import com.purosurf.minibar.Presentador.Administrador.GestionarInventarios.Interfaces.IntrfcGestionInventarios;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionProductos.Interfaces.ISeleccionarProducto_View;
import com.purosurf.minibar.Vista.InicioSesion.IniciarSesion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarEditar extends AppCompatActivity implements ISeleccionarProducto_View {
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
                        edtMinimaProductoRE, //edt cantidad minima producto
                        edtDescripcionRE; //edt descripcion del registro

        //Botones para controlar cantidades
    Button
            btnQuitarMinimaRE, //quitar cantidad minima
            btnAgregarMinimaRE; //agregar cantidad minima

        //contenedor
    FrameLayout flContenedorRE; //contenedor que se muestra cuando el usuario agrega al producto
    TextView tvComentarioRE;


    //VARIABLES
    int cantidadProducto, // almacenar cantidad edt
        idProducto,
        cantidadMinima, // almacenar cantidad minima edt
        cantidadMaxima; // almacenar cantidad maxima edt

    double precioU, total; //precio unitario
    String descripcion; // descripcion de la entrada/salida

    String accion; //capturar bundle (si el formulario es de entrada o salida)

    //BUNDLE
    Bundle datos;

    IntrfcGestionInventarios intrfcGestionInventarios;

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
        edtPrecioUnitarioRE = findViewById(R.id.edtPrecioUnitarioRE);
        edtMinimaProductoRE = findViewById(R.id.edtMinimaProductoRE);
        edtDescripcionRE = findViewById(R.id.edtDescripcionRE);
        btnQuitarMinimaRE = findViewById(R.id.btnQuitarMinimaRE);
        btnAgregarMinimaRE = findViewById(R.id.btnAgregarMinimaRE);
        flContenedorRE = findViewById(R.id.flContenedorRE);
        tvComentarioRE = findViewById(R.id.tvComentarioRE);

        intrfcGestionInventarios = new GestionarInventario(this);

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
                    registo.putExtra("ProdName", tvNombreProductoRE.getText().toString().trim());
                    registo.putExtra("Cantidad", edtMinimaProductoRE.getText().toString().trim());
                    registo.putExtra("Precio", edtPrecioUnitarioRE.getText().toString().trim());
                    registo.putExtra("Descrip", edtDescripcionRE.getText().toString().trim());
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
                    String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    if(result.getResultCode() == 1){
                        setResult(1);
                        precioU = Float.parseFloat(edtPrecioUnitarioRE.getText().toString().trim());
                        cantidadProducto = Integer.parseInt(edtMinimaProductoRE.getText().toString().trim());
                        total = precioU * cantidadProducto;
                        Entrada entrada = new Entrada(0,IniciarSesion.iduser, idProducto,edtDescripcionRE.getText().toString().trim(),
                                fecha, cantidadProducto, (float) precioU, (float) total);
                        intrfcGestionInventarios.InsertEntrada(entrada, getApplicationContext());
                        finish();
                    }else if(result.getResultCode() == 2){
                        setResult(2);
                        precioU = Float.parseFloat(edtPrecioUnitarioRE.getText().toString().trim());
                        cantidadProducto = Integer.parseInt(edtMinimaProductoRE.getText().toString().trim());
                        total = precioU * cantidadProducto;
                        Salida salida = new Salida(0, IniciarSesion.iduser, idProducto, edtDescripcionRE.getText().toString().trim(),
                                fecha, cantidadProducto, (float) precioU, (float) total);
                        intrfcGestionInventarios.InsertSalida(salida, getApplicationContext());
                        finish();
                    } else if (result.getResultCode() == RESULT_OK){
                        flContenedorRE.setVisibility(View.VISIBLE); //mostrar contenedor
                        tvNombreProductoRE.setText("Producto: " + result.getData().getStringExtra("name"));
                        idProducto = result.getData().getIntExtra("id",0);
                        tvComentarioRE.setVisibility(View.GONE);
                    }
                }
            }
    );

}