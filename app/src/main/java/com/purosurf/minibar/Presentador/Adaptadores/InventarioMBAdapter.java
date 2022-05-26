package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.purosurf.minibar.Modelo.Entrada;
import com.purosurf.minibar.Modelo.InventarioHabitacion;
import com.purosurf.minibar.R;

import java.util.List;

public class InventarioMBAdapter extends RecyclerView.Adapter<InventarioMBAdapter.ViewHolder> implements View.OnClickListener {

    //declarar elemento click
    private View.OnClickListener listener;

    //
    private List<InventarioHabitacion> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public InventarioMBAdapter(List<InventarioHabitacion> itemList, Context context) {
        this.mData = itemList;
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
    }

    //cantidad elementos
    @Override
    public int getItemCount() {
        return mData.size();
    }

        //seleccionar contenedor CardView XML
    @Override
    public InventarioMBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minFlater.inflate(R.layout.cardview_inventario_mb, parent, false);

        //
        view.setOnClickListener(this);

        return new InventarioMBAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InventarioMBAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems (List<InventarioHabitacion> items) { mData = items; }

    //Asignar listener
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    //creamos el click
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    //Asinar los datos dentro del contenedor CardView
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreMB; //nombre del producto
        Button btnQuitarCantidadMB, //quitar cantidad de producto
                btnAgregarCantidadMB, //agregar cantidad de producto
                btnQuitarMinimaMB, //quitaar cantidad minima producto
                btnAgregarMinimaMB; //argegar cantidad minima producto
        TextInputEditText edtCantidadMB,
                            edtMinimaMB;

        //variable para obtener y modificar la cantidad en base a los botones
        int cantidad = 0, cantidadMin=0;

        ViewHolder(View view){
            super(view);
            tvNombreMB = view.findViewById(R.id.tvNombreMB);
            btnQuitarCantidadMB = view.findViewById(R.id.btnQuitarCantidadMB);
            btnAgregarCantidadMB = view.findViewById(R.id.btnAgregarCantidadMB);
            btnQuitarMinimaMB = view.findViewById(R.id.btnQuitarMinimaMB);
            btnAgregarMinimaMB = view.findViewById(R.id.btnAgregarMinimaMB);
            edtCantidadMB = view.findViewById(R.id.edtCantidadMB);
            edtMinimaMB = view.findViewById(R.id.edtMinimaMB);
        }
        void bindData(final InventarioHabitacion item){

            tvNombreMB.setText("Nombre Producto"); //dato quemado

            //=======evento botones cantidad producto
                //quitar cantidad a producto
            btnQuitarCantidadMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cantidad >0 ){
                        cantidad--;
                        edtCantidadMB.setText(Integer.toString(cantidad));
                    }
                }
            });
                //agregar cantidad a producto
            btnAgregarCantidadMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cantidad++;
                    edtCantidadMB.setText(Integer.toString(cantidad));
                }
            });


            //=======evento botones cantidad minima producto
                //quitar cantidad minima
            btnQuitarMinimaMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cantidadMin >0 ){
                        cantidadMin--;
                        edtMinimaMB.setText(Integer.toString(cantidadMin));
                    }
                }
            });
                //agregar cantidad minima
            btnAgregarMinimaMB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cantidadMin++;
                    edtMinimaMB.setText(Integer.toString(cantidadMin));
                }
            });
        }
    }

}
