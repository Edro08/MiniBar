package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Inventario;
import com.purosurf.minibar.R;

import java.util.List;

public class VerExistenciasAdapter extends RecyclerView.Adapter<VerExistenciasAdapter.ViewHolder> implements View.OnClickListener {


    //declarar click
    private  View.OnClickListener listener;

    private List<Inventario> mData;
    private LayoutInflater minFlater;
    private Context context;

    public VerExistenciasAdapter(List<Inventario> itemList, Context context) {
        this.minFlater = LayoutInflater.from(context);
        this.mData = itemList;
        this.context = context;
    }

    //cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size();}

    //seleccionar contenedor cardview xml
    @Override
    public VerExistenciasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minFlater.inflate(R.layout.cardview_ver_existencias, parent,false);

        //declarar onclick
        view.setOnClickListener(this);

        return new VerExistenciasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerExistenciasAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    //reasignar elementos
    public void setItems(List<Inventario> items) { mData = items; }

    //asignar listener
    public void setOnClickListener(View.OnClickListener listener) { this.listener = listener; }

    //creamos el click
    @Override
    public void onClick(View view) {
        if (listener!= null){
            listener.onClick(view);
        }
    }

    //
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvProductoExistencias, tvCantidadExistencias, tvCantidadMinExistencias, tvCantidadMaxExistencias;
        LinearLayout expandirExistencias;
        CardView cvVerExistencias;
        ViewHolder(View itemView){
            super(itemView);
            tvProductoExistencias = itemView.findViewById(R.id.tvProductoExistencias);
            tvCantidadExistencias = itemView.findViewById(R.id.tvCantidadExistencias);
            tvCantidadMinExistencias = itemView.findViewById(R.id.tvCantidadMinExistencias);
            tvCantidadMaxExistencias = itemView.findViewById(R.id.tvCantidadMaxExistencias);
            expandirExistencias = itemView.findViewById(R.id.expandirExistencias);
            cvVerExistencias = itemView.findViewById(R.id.cvVerExistencias);
        }
        void bindData(final Inventario item){
            //tvProductoExistencias.setText("Producto "+item.getIdProducto());
            tvProductoExistencias.setText("Producto: "+item.getNombreProd());
            tvCantidadExistencias.setText(Integer.toString(item.getExistencias()));
            tvCantidadMinExistencias.setText("Cantidad Mínima: "+item.getCantidadMinima());
            tvCantidadMaxExistencias.setText("Cantidad Máxima: "+item.getCantidadMaxima());

            //expandir--colapsar cardview
            cvVerExistencias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(expandirExistencias.getVisibility() == View.GONE){
                        expandirExistencias.setVisibility(View.VISIBLE);
                        TransitionManager.beginDelayedTransition(cvVerExistencias, new AutoTransition());
                    } else {
                        expandirExistencias.setVisibility(View.GONE);
                        TransitionManager.beginDelayedTransition(cvVerExistencias, new AutoTransition());
                    }
                }
            });
        }
    }


}
