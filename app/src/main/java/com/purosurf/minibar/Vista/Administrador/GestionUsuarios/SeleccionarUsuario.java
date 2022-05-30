package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

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

import com.purosurf.minibar.Presentador.Adaptadores.SeleccionarUsuarioAdapter;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces.ISeleccionarUsuarioPresentador;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.SeleccionarUsuarioPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.ISeleccionarUsuario_View;

public class SeleccionarUsuario extends AppCompatActivity implements ISeleccionarUsuario_View {

    //ELEMENTOS
    Button btnRegresarSU;
    TextView tvEncabezadoSU;
    RecyclerView rvSeleccionarUsuarioSU;

    //adaptador del recyclerview
    SeleccionarUsuarioAdapter rvSeleccionarAdapter;

    //bundle
    Bundle datos;

    //variables
    String accion;

    ISeleccionarUsuarioPresentador seleccionarUsuarioPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_usuario);

        //asignar elementos
        btnRegresarSU = findViewById(R.id.btnRegresarSU);
        tvEncabezadoSU = findViewById(R.id.tvEncabezadoSU);
        rvSeleccionarUsuarioSU = findViewById(R.id.rvSeleccionarUsuarioSU);

        seleccionarUsuarioPresentador = new SeleccionarUsuarioPresentador(this);

        ///obtener que actividad la invoca
        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        if (accion.equals("deshabilitar")){
            tvEncabezadoSU.setText("Deshabilitar Usuario");
        } else if (accion.equals("actualizar")){
            tvEncabezadoSU.setText("Actualizar Usuario");
        } else if (accion.equals("listar")){
            tvEncabezadoSU.setText("Listado de Usuarios");
        }

        //Configurar RecyclerView
        rvSeleccionarAdapter = new SeleccionarUsuarioAdapter(seleccionarUsuarioPresentador.listaUsuarios(this), this); //asignamos adaptador
        rvSeleccionarUsuarioSU.setHasFixedSize(false);
        rvSeleccionarUsuarioSU.setLayoutManager(new LinearLayoutManager(this));
        rvSeleccionarUsuarioSU.setAdapter(rvSeleccionarAdapter);

        //evento seleccionar elemento de la lista
        rvSeleccionarAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int IdUsuario = seleccionarUsuarioPresentador.listaUsuarios(getApplicationContext()).get(rvSeleccionarUsuarioSU.getChildAdapterPosition(view)).getIdUsuario();
                if (accion.equals("deshabilitar")){
                    Intent deshabilitar = new Intent(getApplicationContext(), DeshabilitarUsuario.class);
                    deshabilitar.putExtra("IdUsuario",IdUsuario);
                    lanzarActividad.launch(deshabilitar);
                } else if (accion.equals("actualizar")){
                    Intent actualizar = new Intent(getApplicationContext(), AdicionarActualizarUsuario.class);
                    actualizar.putExtra("accion","actualizar");
                    actualizar.putExtra("IdUsuario",IdUsuario);
                    lanzarActividad.launch(actualizar);
                } else if (accion.equals("listar")){
                    Intent listar = new Intent(getApplicationContext(), DetalleUsuario.class);
                    listar.putExtra("IdUsuario",IdUsuario);
                    lanzarActividad.launch(listar);
                }
            }
        });

        //boton regresar pantalla anterior
        btnRegresarSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //lanzador de actividades
    /*  resultCode = 1 -> adicionar
     *   resultCode = 2 -> deshabilitar
     *   resultCode = 3 -> actualizar
     *   resultCode = 4 -> listar
     * */


    ActivityResultLauncher<Intent> lanzarActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() > 0){
                        }if (result.getResultCode() == 2 || result.getResultCode() == 5){
                            int code = result.getResultCode();
                            setResult(code);
                            finish();
                        }else if (result.getResultCode() == 3){
                            setResult(3);
                            finish();
                        }else if (result.getResultCode() == 4){
                            finish();
                        }
                    }
    });
}