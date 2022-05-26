package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Salida;
import com.purosurf.minibar.R;

import java.util.List;

public class SalidaAdapter extends RecyclerView.Adapter<SalidaAdapter.ViewHolder> implements View.OnClickListener {

    //declarar click
    private View.OnClickListener listener;

    //
    private List<Salida> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public SalidaAdapter(List<Salida> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size(); }

    //seleccionar contenedor CardView XML
    @Override
    public SalidaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_lista_recyclerview, parent, false);

        //declararr onclick
        view.setOnClickListener(this);

        return new SalidaAdapter.ViewHolder(view);
    }

    //posicion elemento
    @Override
    public void onBindViewHolder (final SalidaAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems(List<Salida> items){ mData = items; }

    //asignar listene
    public void setOnClickListener(View.OnClickListener listener){ this.listener = listener; }

    //creamos click
    public void onClick(View view){
        if (listener!=null){
            listener.onClick(view);
        }
    }

    //asignamos datos dentro deel contenedor CardView
    public class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIconoRecyclerView;
        TextView tvNombreRecyclerView;
        ViewHolder(View itemView){
            super(itemView);
            ivIconoRecyclerView = itemView.findViewById(R.id.ivIconoRecyclerView);
            tvNombreRecyclerView = itemView.findViewById(R.id.tvNombreRecyclerView);
        }
        void bindData(final Salida item){
            ivIconoRecyclerView.setImageResource(R.drawable.ic_icono_reporte_existencias);
            tvNombreRecyclerView.setText(item.getFecha());
        }
    }

}
