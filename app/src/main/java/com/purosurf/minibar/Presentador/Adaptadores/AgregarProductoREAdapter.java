package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.Producto;
import com.purosurf.minibar.R;
import com.purosurf.minibar.Vista.AdministradorEmpleado.AgregarProductoRE;

import java.util.List;

public class AgregarProductoREAdapter extends RecyclerView.Adapter<AgregarProductoREAdapter.ViewHolder> {

    //declarar click
    private int rdbseleccion = -1;

    //
    private List<Producto> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public AgregarProductoREAdapter(List<Producto> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size();}

    //seleccionar contenedor CardView XML
    @Override
    public AgregarProductoREAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_lista_productos, parent,false );

        return new AgregarProductoREAdapter.ViewHolder(view);
    }


    //posicion elemento
    @Override
    public void onBindViewHolder (final AgregarProductoREAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
        holder.rdbProducto.setChecked(rdbseleccion == position);//esta condicion evita que se quiten las selecciones anteriore
    }

    //reasignar lista
    public void setItems(List<Producto> items){
        mData = items;
    }


    //asignar datos dentro del contenedor CardView
    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton rdbProducto;
        ViewHolder(View itemView){
            super(itemView);
            rdbProducto = itemView.findViewById(R.id.rdbProducto);
        }
        void bindData (final Producto item){
            rdbProducto.setText(item.getProductoNombre()); //dato quemado
            //seleccionar solo un rdb de la lista
            rdbProducto.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rdbseleccion = getAdapterPosition();
                            notifyDataSetChanged();
                            AgregarProductoRE.PrdSelct.clear();
                            AgregarProductoRE.PrdSelct.add(item);
                        }
                    });
        }
    }
}
