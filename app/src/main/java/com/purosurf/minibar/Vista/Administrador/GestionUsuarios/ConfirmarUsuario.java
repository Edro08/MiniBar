package com.purosurf.minibar.Vista.Administrador.GestionUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.ConfirmarUsuarioPresentador;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IConfirmarUsuario_View;

public class ConfirmarUsuario extends AppCompatActivity implements IConfirmarUsuario_View {

    //ELEMENTOS
    TextView tvAppBar2ADDU, tvEnunciado2ADDU; //encabezados adicionarr/actualizar
    TextInputLayout tilUsuarioADDU, tilPasswordADDU, tilConfirmarPassADDU;
    TextInputEditText edtUsuarioADDU, edtPasswordADDU, edtConfirmarPassADDU;
    Button btnRegresar2ADDU, btnConfirmarADDU;
    String nombres, apellidos, email, pregunta, respuesta;
    String usuario, clave, claveVerificar, usuarioVerificar;
    int idPersona, idUsuario;

    //BUNDLE
    Bundle datos;

    //Variables
    String accion;

    ConfirmarUsuarioPresentador confirmarUsuarioPresentador;
    Cursor DatosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_usuario);

        //ASIGNAR ELEMENTOS
            //TextView
        tvAppBar2ADDU = findViewById(R.id.tvAppBar2ADDU); //encabezado appbar
        tvEnunciado2ADDU = findViewById(R.id.tvEnunciado2ADDU); //enunciado formulario
            //Contenedores EDT
        tilUsuarioADDU = findViewById(R.id.tilUsuarioADDU);
        tilPasswordADDU = findViewById(R.id.tilPasswordADDU);
        tilConfirmarPassADDU = findViewById(R.id.tilConfirmarPassADDU);
            //edt
        edtUsuarioADDU = findViewById(R.id.edtUsuarioADDU);
        edtPasswordADDU = findViewById(R.id.edtPasswordADDU);
        edtConfirmarPassADDU = findViewById(R.id.edtConfirmarPassADDU);
            //botones
        btnRegresar2ADDU = findViewById(R.id.btnRegresar2ADDU);
        btnConfirmarADDU = findViewById(R.id.btnConfirmarADDU);

        //obtener intent
        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        nombres = datos.getString("nombres");
        apellidos = datos.getString("apellidos");
        email = datos.getString("email");
        pregunta = datos.getString("pregunta");
        respuesta = datos.getString("respuesta");

        confirmarUsuarioPresentador = new ConfirmarUsuarioPresentador(this);

        if (accion.equals("adicionar")){
            tvAppBar2ADDU.setText("Confirmar nuevo usuario");
            tvEnunciado2ADDU.setText("Registro de Nuevo Usuario");
            btnConfirmarADDU.setText("Confirmar Registro");
            usuarioVerificar = "";
        } else if (accion.equals("actualizar")){
            tvAppBar2ADDU.setText("Confirmar actualización usuario");
            tvEnunciado2ADDU.setText("Información Usuario");
            btnConfirmarADDU.setText("Actualizar Datos");
            idUsuario = datos.getInt("IdUsuario");
            DatosUsuario = confirmarUsuarioPresentador.DatosUsuario(getApplicationContext(),idUsuario);
            DatosUsuario.moveToFirst();
            usuarioVerificar = DatosUsuario.getString(1);
            edtUsuarioADDU.setText(DatosUsuario.getString(1));
            edtPasswordADDU.setText(DatosUsuario.getString(2));
            edtConfirmarPassADDU.setText("");
            idPersona = DatosUsuario.getInt(4);
        }


        //evento botones
        btnRegresar2ADDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfirmarADDU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("adicionar")){
                    if(CapturaDatos())
                    {
                        idPersona = confirmarUsuarioPresentador.AdicionarPersona(getApplicationContext(),
                                nombres,apellidos,email,pregunta,respuesta);
                        Toast.makeText(getApplicationContext(), ""+ idPersona,Toast.LENGTH_LONG);
                        confirmarUsuarioPresentador.AdicionarUsuario(getApplicationContext(),
                                idPersona, usuario, clave);
                        setResult(1); //code adicionar
                        finish();
                    }
                } else if (accion.equals("actualizar")){
                    if(CapturaDatos())
                    {
                        confirmarUsuarioPresentador.ActualizarPersona(getApplicationContext(),
                                idPersona, nombres,apellidos,email,pregunta,respuesta);
                        confirmarUsuarioPresentador.ActualizarUsuario(getApplicationContext(),
                                idUsuario,usuario,clave);
                        setResult(3); //code actualizar
                        finish();
                    }
                }
            }
        });
    }

    public boolean CapturaDatos()
    {
        boolean estado = false;
        usuario = edtUsuarioADDU.getText().toString();
        clave = edtPasswordADDU.getText().toString();
        claveVerificar = edtConfirmarPassADDU.getText().toString();

        if (TextUtils.isEmpty(usuario)){
            edtUsuarioADDU.setError("Debe ingresar el usuario");
            edtUsuarioADDU.requestFocus();
        } else if (TextUtils.isEmpty(clave)){
            edtPasswordADDU.setError("Debe ingresar la contraseña");
            edtPasswordADDU.requestFocus();
        } else if (TextUtils.isEmpty(claveVerificar)){
            edtConfirmarPassADDU.setError("Debe ingresar la contraseña nuevamente");
            edtConfirmarPassADDU.requestFocus();
        } else if (!clave.equals(claveVerificar)) {
            edtConfirmarPassADDU.setError("Las contraseñas no coinciden!");
            edtConfirmarPassADDU.requestFocus();
        }
        else
        {
            if(!usuario.equals(usuarioVerificar))
            {
                if(confirmarUsuarioPresentador.VerificarUsuario(getApplicationContext(),usuario)){
                    edtUsuarioADDU.setError("Usuario se encuentra en uso!");
                    edtUsuarioADDU.requestFocus();
                }
                else
                {
                    estado = true;
                }
            }
            else
            {
                estado = true;
            }

        }
        return estado;
    }

    @Override
    public void OnCambiarUsuarioAdmin(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}