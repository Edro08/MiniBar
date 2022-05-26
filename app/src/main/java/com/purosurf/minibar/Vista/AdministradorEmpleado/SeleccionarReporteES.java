package com.purosurf.minibar.Vista.AdministradorEmpleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.purosurf.minibar.Modelo.Entrada;
import com.purosurf.minibar.Modelo.Salida;
import com.purosurf.minibar.Presentador.Adaptadores.EntradaAdapter;
import com.purosurf.minibar.Presentador.Adaptadores.SalidaAdapter;
import com.purosurf.minibar.R;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarReporteES extends AppCompatActivity {

    //ELEMENTOS
    Button btnRegresarSR;
    RecyclerView rvSeleccionarReporteSR;
    TextView tvEnunciadoSR;

    //BUNDLE
    Bundle datos;

    //LISTADOS
    List<Entrada> lsEntrada;
    List<Salida> lsSalida;

    //ADAPTADORES
    EntradaAdapter entradaAdapter;
    SalidaAdapter salidaAdapter;

    //VARIABLES
    String accion; //capturar intento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_reporte_es);

        //ASIGNAR ELEMENTOS
        btnRegresarSR = findViewById(R.id.btnRegresarSR);
        rvSeleccionarReporteSR = findViewById(R.id.rvSeleccionarReporteSR);
        tvEnunciadoSR = findViewById(R.id.tvEnunciadoSR);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");

        if (accion.equals("compras")){
            tvEnunciadoSR.setText("Reportes de compras");
            //asignar datos en recyclerview
            lsEntrada = new ArrayList<Entrada>();
            for (int i = 1; i <= 10; i++){ lsEntrada.add(new Entrada(i, 1, i, "Descripcion producto", i+"/05/2022", 30, 2, 60 )); }
            entradaAdapter = new EntradaAdapter(lsEntrada, this);
            rvSeleccionarReporteSR.setHasFixedSize(false);
            rvSeleccionarReporteSR.setLayoutManager(new LinearLayoutManager(this));
            rvSeleccionarReporteSR.setAdapter(entradaAdapter); //asignar adaptador

            //evento seleccionar reporte de compras
            entradaAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent entradaDetalle = new Intent(getApplicationContext(), DetalleReporteES.class);
                    entradaDetalle.putExtra("accion", accion);
                    lanzarActividad.launch(entradaDetalle);
                    //obtener elemento
                    Toast.makeText(getApplicationContext(), "Entrada: "+lsEntrada.get(rvSeleccionarReporteSR.getChildAdapterPosition(view)).getFecha(), Toast.LENGTH_SHORT).show();
                }
            });

        } else if (accion.equals("edicion")){
            tvEnunciadoSR.setText("Reportes de Edición de Existencias");
            lsSalida = new ArrayList<Salida>();
            for (int i = 10; i <=20 ; i++){ lsSalida.add(new Salida(i, 1, i, "Descripcion producto", i+"/05/2022", 30, 2, 60 )); }
            salidaAdapter = new SalidaAdapter(lsSalida, this);
            rvSeleccionarReporteSR.setHasFixedSize(false);
            rvSeleccionarReporteSR.setLayoutManager(new LinearLayoutManager(this));
            rvSeleccionarReporteSR.setAdapter(salidaAdapter); //asignar adaptador

            //evento seleccionar reporte de edicion
            //evento seleccionar reporte de compras
            salidaAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent salidaDetalle = new Intent(getApplicationContext(), DetalleReporteES.class);
                    salidaDetalle.putExtra("accion", accion);
                    lanzarActividad.launch(salidaDetalle);
                    //obtener elemento
                    Toast.makeText(getApplicationContext(),"Salida: "+ lsSalida.get(rvSeleccionarReporteSR.getChildAdapterPosition(view)).getFecha(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        //evento boton
        btnRegresarSR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                        if(result.getResultCode() == 6){
                            setResult(6);
                            finish();
                        }else if(result.getResultCode() == 7){
                            setResult(7);
                            finish();
                        }
                    }
                }
            }
    );
}