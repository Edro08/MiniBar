package com.purosurf.minibar.Vista.Empleado;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.purosurf.minibar.Modelo.DetalleConsumo;
import com.purosurf.minibar.Modelo.InventarioHabitacion;
import com.purosurf.minibar.Modelo.InventarioHabitacionProducto;
import com.purosurf.minibar.Presentador.Adaptadores.RegistrarConsumoAdapter;
import com.purosurf.minibar.Presentador.Empleado.RegistrarConsumosPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Empleado.Interfaces.IRegistrarConsumos_View;

import java.util.ArrayList;
import java.util.List;

public class RegistrarConsumos extends AppCompatActivity implements IRegistrarConsumos_View {

    //ELEMENTOS DECLARAR
    TextView tvHabitacionRegCons;
    RecyclerView rvInventarioRegCons;
    TextView tvTotalRegCons;
    Button btnProcesarRegCons, btnRegresarRegCons;

    //Lista de productos de la habitacion
    List<InventarioHabitacionProducto> listadoInventario, ListaDetalleConfirmar;
    String[][] datosDetalleConfirmar;
    RegistrarConsumosPresentador registrarConsumosPresentador;
    int IdHabitacion, CantidadDetalleConfirmar, IdUsuario;
    double total;
    Bundle data;

    //Adaptador para RecyclerView
    RegistrarConsumoAdapter lsInventarioRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_consumos);

        //asignar elementos
        tvHabitacionRegCons = findViewById(R.id.tvHabitacionRegCons);
        rvInventarioRegCons = findViewById(R.id.rvInventarioRegCons);
        tvTotalRegCons = findViewById(R.id.tvTotalRegCons);
        btnProcesarRegCons = findViewById(R.id.btnProcesarRegCons);
        btnRegresarRegCons = findViewById(R.id.btnRegresarRegCons);
        registrarConsumosPresentador = new RegistrarConsumosPresentador(this);

        data = getIntent().getExtras();
        IdHabitacion = data.getInt("IdHabitaccion");
        tvHabitacionRegCons.setText("Habitacion: " + data.getString("nombreHabitacion"));

        //llenar listado
        ListaDetalleConfirmar = new ArrayList<>();
        listadoInventario = new ArrayList<>();
        listadoInventario.addAll(registrarConsumosPresentador.listaInventarioHabitacion(
                getApplicationContext(), IdHabitacion));

        //asinar adaptador al RecyclerView
        lsInventarioRV = new RegistrarConsumoAdapter(listadoInventario, this,this); //asignamos el adaptador
        rvInventarioRegCons.setHasFixedSize(false);
        rvInventarioRegCons.setLayoutManager(new LinearLayoutManager(this));
        rvInventarioRegCons.setAdapter(lsInventarioRV); //asignamos adaptador al recyclerview


        //botones
        btnRegresarRegCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnProcesarRegCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datosDetalleConfirmar = new String[CantidadDetalleConfirmar][4];
                datosDetalleConfirmar = registrarConsumosPresentador.obtenerDatosDetalleProductos(
                        ListaDetalleConfirmar, CantidadDetalleConfirmar).clone();

                if(datosDetalleConfirmar.length > 0)
                {

                    Intent detalleConfirmar = new Intent(getApplicationContext(), DetalleConfirmarConsumo.class);
                    detalleConfirmar.putExtra("Consumo",datosDetalleConfirmar);
                    detalleConfirmar.putExtra("Total",total);
                    detalleConfirmar.putExtra("NombreHabitacion",listadoInventario.get(0).getNombreHabitacion());
                    detalleConfirmar.putExtra("IdHabitacion",IdHabitacion);
                    lanzarDetalle.launch(detalleConfirmar);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No se han registrado productos consumidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //seleccionar elemento de la lista
       /* lsInventarioRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), listadoInventario.get(rvInventarioRegCons.getChildAdapterPosition(view)).getIdProducto(), Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void MostrarTotal(List<InventarioHabitacionProducto> lista) {
        total = registrarConsumosPresentador.CalcularTotal(lista);
        tvTotalRegCons.setText("$" + total);
    }

    @Override
    public void ExistenciaMaxima(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CalcularCantidadDetalleProductos(List<InventarioHabitacionProducto> lista) {
        CantidadDetalleConfirmar = registrarConsumosPresentador.getCantidadRegistros(lista) ;
        ListaDetalleConfirmar.clear();
        ListaDetalleConfirmar.addAll(lista);
    }


    ActivityResultLauncher<Intent> lanzarDetalle = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
    );
}