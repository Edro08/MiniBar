package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.purosurf.minibar.R;


public class GestionarInventarios extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarInventarios;
    CardView cvEntradaInventarios, cvEditarInventarios, cvReportesInventarios, cvExistenciasInventarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_inventarios);

        //asignar elementos
        btnRegresarInventarios = findViewById(R.id.btnRegresarInventarios);
        cvEntradaInventarios = findViewById(R.id.cvEntradaInventarios); //registrar entrada
        cvEditarInventarios = findViewById(R.id.cvEditarInventarios); //editar existencias
        cvReportesInventarios = findViewById(R.id.cvReportesInventarios); //generar reportes
        cvExistenciasInventarios = findViewById(R.id.cvExistenciasInventarios); //ver existencias
        cvEditarInventarios.setVisibility(View.GONE); //Ocultar editar existencias

        //evento boton
            //regresar pantalla anterior
        btnRegresarInventarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //eventos cardview
            //registrar entrada
        cvEntradaInventarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent entrada = new Intent(getApplicationContext(), RegistrarEditar.class);
                entrada.putExtra("accion","entrada");
                lanzarActividad.launch(entrada);

            }
        });
            //editar existencias
        cvEditarInventarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salida = new Intent(getApplicationContext(), RegistrarEditar.class);
                salida.putExtra("accion","salida");
                //lanzarActividad.launch(salida);
            }
        });
            ///generar reportes
        cvReportesInventarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarModal(); //mostrar menu modal de opciones de reportes
            }
        });
            //ver existencias
        cvExistenciasInventarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent existencias = new Intent(getApplicationContext(), VerExistencias.class);
                startActivity(existencias);
            }
        });
    }

    //mostrar menu modal de seleccion de tipo de reporte
    private void mostrarModal(){
        final BottomSheetDialog ventanaModal = new BottomSheetDialog(this);
        ventanaModal.setContentView(R.layout.modal_menu_reportes);

        //ELEMENTOS
        CardView cvReporteConsumos = ventanaModal.findViewById(R.id.cvReporteConsumos),
                cvReporteCompras = ventanaModal.findViewById(R.id.cvReporteCompras),
                cvReporteEdicion = ventanaModal.findViewById(R.id.cvReporteEdicion);

        //evento seleccionar boton
            //reportes de consumos
        cvReporteConsumos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consumos = new Intent(getApplicationContext(), SeleccionarReporteCons.class);
                consumos.putExtra("accion","consumos");
                ventanaModal.cancel();
                lanzarActividad.launch(consumos);
                Toast.makeText(getApplicationContext(), "Consumo", Toast.LENGTH_SHORT).show();
            }
        });
            //reportes de compras
        cvReporteCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compras = new Intent(getApplicationContext(), SeleccionarReporteES.class);
                compras.putExtra("accion","compras");
                ventanaModal.cancel();
                lanzarActividad.launch(compras);
                Toast.makeText(getApplicationContext(), "Compra", Toast.LENGTH_SHORT).show();
            }
        });
            //reportes de edicion de existencias
        cvReporteEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edicion = new Intent(getApplicationContext(), SeleccionarReporteES.class);
                edicion.putExtra("accion","edicion");
                ventanaModal.cancel();
                lanzarActividad.launch(edicion);
                Toast.makeText(getApplicationContext(), "Edicion", Toast.LENGTH_SHORT).show();
            }
        });

        ventanaModal.show();
    }

    //lanzador de actividades
    /*  code 1 = registra entrada
     *   code 2 = editar existencia
     *   code 3 = generar reporte
     *   code 4 = ver existencia
     *          //modal-por si se necesita estos son los results asignados
     *   code 5 = reporte de consumo
     *   code 6 = reporte de compras
     *   code 7 = reporte de edicion de existencias
     * */
    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        String mensaje = "";
                        if(result.getResultCode() == 1){
                            mensaje = "Registro de Entrada (compras) exitoso";
                        }else if(result.getResultCode() == 2){
                            mensaje = "Edicion de existencias exitosa";
                        }else if(result.getResultCode() == 3){
                            mensaje = "";
                        }else if(result.getResultCode() == 4){
                            mensaje = "";
                        }else if(result.getResultCode() == 5){
                            mensaje = "Reporte de consumo guardado";
                        }else if(result.getResultCode() == 6){
                            mensaje = "Reporte de Compras guardado";
                        } else if(result.getResultCode() == 7){
                            mensaje = "Reporte de edición de existencias guardado";
                        }
                        Snackbar.make(findViewById(R.id.constraintInventarios), mensaje, Snackbar.LENGTH_LONG)
                                .setTextColor(getColor(R.color.azulOscuro))
                                .setBackgroundTint(getColor(R.color.azulPalido))
                                .show();
                    }

                }
            }
    );
}