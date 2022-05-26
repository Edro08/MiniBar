package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Consumo;
import com.purosurf.minibar.R;

import java.util.List;

public class ConsumoAdapter extends RecyclerView.Adapter<ConsumoAdapter.ViewHolder> implements View.OnClickListener {

    //Declarar elemento de click
    private View.OnClickListener listener;

    private List<Consumo> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public ConsumoAdapter(List<Consumo> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size(); }

    // Seleccionar el contenedor CardView XML
    @Override
    public ConsumoAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_lista_recyclerview, null );

        //declarar onclick
        view.setOnClickListener(this);

        return new ConsumoAdapter.ViewHolder(view);
    }

    //posicion del elemento
    @Override
    public void onBindViewHolder (final ConsumoAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems (List<Consumo> items) { mData = items; }

    //Asignar listener
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    //Creamos el click
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    //Asignar los datos dentro del contenedor CardView
    public class ViewHolder extends RecyclerView.ViewHolder{
        //ELEMENTOS del cardView
        ImageView ivIconoRecyclerView;
        TextView tvNombreRecyclerView;

        ViewHolder(View itemView){
            super(itemView);

            //Relacionamos elementos
            ivIconoRecyclerView = itemView.findViewById(R.id.ivIconoRecyclerView);
            tvNombreRecyclerView = itemView.findViewById(R.id.tvNombreRecyclerView);
        }
        void bindData(final Consumo item){
            //asignamos valores al cardview
            ivIconoRecyclerView.setImageResource(R.drawable.ic_icono_reporte_consumo);
            tvNombreRecyclerView.setText(item.getFecha());
        }
    }

}
