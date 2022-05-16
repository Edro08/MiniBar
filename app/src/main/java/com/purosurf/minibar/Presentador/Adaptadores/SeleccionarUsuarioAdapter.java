package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.R;

import java.util.List;

public class SeleccionarUsuarioAdapter extends RecyclerView.Adapter<SeleccionarUsuarioAdapter.ViewHolder> implements View.OnClickListener {

    //declarar elemento de click
    private View.OnClickListener listener;

    private List<Usuario> mData; //lista
    private LayoutInflater minFlater; //conteenedor
    private Context context; //contexto

    //constructor
    public SeleccionarUsuarioAdapter(List<Usuario> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size(); }

    //seleccionar contenedor CardView XML
    @Override
    public SeleccionarUsuarioAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_lista_recyclerview, null);

        //declarar onclick
        view.setOnClickListener(this);

        return new SeleccionarUsuarioAdapter.ViewHolder(view);
    }

    //posicion del elemento
    @Override
    public void onBindViewHolder (final SeleccionarUsuarioAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems (List<Usuario> items) { mData = items; }

    //asignar listene
    public void setOnClickListener(View.OnClickListener listener) { this.listener = listener; }

    //creamos el click
    @Override
    public void onClick(View view){
        if (listener !=  null){
            listener.onClick(view);
        }
    }

    //asignar los datos dentro del contenedor cardview
    public class ViewHolder extends RecyclerView.ViewHolder {
        //ELEMENTOS CARDVIEW
        ImageView ivIconoUsuario;
        TextView tvUsuario;

        ViewHolder(View itemView){
            super(itemView);
            //relacionamos elementos
            ivIconoUsuario = itemView.findViewById(R.id.ivIconoRecyclerView);
            tvUsuario = itemView.findViewById(R.id.tvNombreRecyclerView);
        }
        void bindData(final Usuario item){
            //asignamos valoers
            ivIconoUsuario.setImageResource(R.drawable.ic_icono_usuario);
            tvUsuario.setText(item.getUsuario());
        }
    }

}
