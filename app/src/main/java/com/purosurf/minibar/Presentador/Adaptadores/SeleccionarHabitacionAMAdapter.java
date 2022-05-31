package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Habitacion;
import com.purosurf.minibar.R;

import java.util.List;

public class SeleccionarHabitacionAMAdapter extends RecyclerView.Adapter<SeleccionarHabitacionAMAdapter.ViewHolder> implements View.OnClickListener {

    //Declarar elemnto click
    private View.OnClickListener listener;

    private List<Habitacion> mData; //lista
    private LayoutInflater minFlater; //conteenedor
    private Context context;

    //constructor
    public SeleccionarHabitacionAMAdapter(List<Habitacion> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size(); }

    //Seleccionar el conteneedor CardView
    @Override
    public SeleccionarHabitacionAMAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_seleccionar_habitacion_admi, null);

        //declarar onclick
        view.setOnClickListener(this);

        return new SeleccionarHabitacionAMAdapter.ViewHolder(view);
    }

    //posicion del elemento
    @Override
    public  void onBindViewHolder(final SeleccionarHabitacionAMAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems(List<Habitacion> items){ mData = items; }

    //asignar listener
    public void setOnClickListener(View.OnClickListener listener) {this.listener = listener;}

    //Creamos el click
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    //asignar los elementos dentro del contenedor cardview
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIconoHabitacionGH;
        TextView tvNombreHabitacionGH, tvEstadoHabitacionGH;

        ViewHolder(View itemView){
            super(itemView);
            //relacionamos elementos
            ivIconoHabitacionGH = itemView.findViewById(R.id.ivIconoHabitacionGH);
            tvNombreHabitacionGH = itemView.findViewById(R.id.tvNombreHabitacionGH);
            tvEstadoHabitacionGH = itemView.findViewById(R.id.tvEstadoHabitacionGH);
        }
        void bindData(final Habitacion item){
            ivIconoHabitacionGH.setImageResource(R.drawable.ic_icono_habitacion);
            tvNombreHabitacionGH.setText(item.getNombreHabitacion());
            //tvEstadoHabitacionGH.setText(item.getNombreHabitacion());
            if (item.getIdEstado() == 1)
                tvEstadoHabitacionGH.setText("Activo");
            else
                tvEstadoHabitacionGH.setText("Inactivo");
        }
    }
}

