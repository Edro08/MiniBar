package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

public class SeleccionarProductoAdapter extends RecyclerView.Adapter<SeleccionarProductoAdapter.ViewHolder> implements View.OnClickListener {

    //Declarar elemeento de click
    private View.OnClickListener listener;

    private List<Producto> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public SeleccionarProductoAdapter(List<Producto> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public  int getItemCount(){ return mData.size(); }

    // Selccionar el contenedor CardView XML
    @Override
    public SeleccionarProductoAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_seleccionar_producto,parent, false);

        //declarar onclick
        view.setOnClickListener(this);

        //
        return new SeleccionarProductoAdapter.ViewHolder(view);
    }

    //posicion del elemento
    @Override
    public void onBindViewHolder (final SeleccionarProductoAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems (List<Producto> items ) { mData = items; }

    //asignar listener
    public void setOnClickListener(View.OnClickListener listener) { this.listener = listener; }

    //Creamos el click
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    //asignar datos dentro del contenedor cardview
    public class ViewHolder extends RecyclerView.ViewHolder{
        //ELEMENTOS del cardView
        ImageView ivIconoProductoSEP; // imagen producto
        TextView tvNombreProductoSEP; // nombre producto
        TextView tvEstadoProductoSEP; // estado producto (activo o desactivado)
        TextView tvPrecioProductoSEP;

        ViewHolder(View itemView){
            super(itemView);

            //Relacionamos elementos
            ivIconoProductoSEP = itemView.findViewById(R.id.ivIconoProductoSEP);
            tvNombreProductoSEP = itemView.findViewById(R.id.tvNombreProductoSEP);
            tvEstadoProductoSEP = itemView.findViewById(R.id.tvEstadoProductoSEP);
            tvPrecioProductoSEP = itemView.findViewById(R.id.tvPrecioProductoSEP);
        }
        void bindData(final Producto item){
            //asignamos valores al cardview //trae los valores del modelo
            Picasso.with(context).load(item.getImagenURL()).into(ivIconoProductoSEP);
            tvNombreProductoSEP.setText(item.getProductoNombre());
            if (item.getIdEstado() == 1){
                tvEstadoProductoSEP.setText("Activo"); //dato quemado
            } else {
                tvEstadoProductoSEP.setText("Inactivo"); //dato quemado
            }
            //precio
            String precio = new DecimalFormat("#,##0.00").format(item.getPrecioUnitario());
            tvPrecioProductoSEP.setText("$ "+precio);
        }
    }
}
