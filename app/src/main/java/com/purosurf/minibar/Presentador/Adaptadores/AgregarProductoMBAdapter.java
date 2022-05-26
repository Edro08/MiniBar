package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.R;

import java.util.List;

public class AgregarProductoMBAdapter extends RecyclerView.Adapter<AgregarProductoMBAdapter.ViewHolder> implements View.OnClickListener{
    //declarar click
    private View.OnClickListener listener;

    //
    private List<Producto> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public AgregarProductoMBAdapter(List<Producto> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size();}

    //seleccionar contenedor CardView XML
    @Override
    public AgregarProductoMBAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_agregar_productos_mb, parent, false );

        //declarar onclick
        view.setOnClickListener(this);

        return new AgregarProductoMBAdapter.ViewHolder(view);
    }


    //posicion elemento
    @Override
    public void onBindViewHolder (final AgregarProductoMBAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems(List<Producto> items){
        mData = items;
    }

    //asignar listener
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    //creamos click
    public void onClick (View view){
        if (listener!= null){
            listener.onClick(view);
        }
    }

    //asignar datos dentro del contenedor CardView
    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbProductoMB;
        ViewHolder(View itemView){
            super(itemView);
            cbProductoMB = itemView.findViewById(R.id.cbProductoMB);
        }
        void bindData (final Producto item){
            cbProductoMB.setText("Producto: "+item.getIdProducto()+ " Categoría: "+item.getIdCategoria()); //dato quemado
        }
    }
}
