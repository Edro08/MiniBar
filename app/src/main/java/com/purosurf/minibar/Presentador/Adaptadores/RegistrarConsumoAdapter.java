package com.purosurf.minibar.Presentador.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purosurf.minibar.Modelo.DetalleConsumo;
import com.purosurf.minibar.R;

import java.text.DecimalFormat;
import java.util.List;

public class RegistrarConsumoAdapter extends RecyclerView.Adapter<RegistrarConsumoAdapter.ViewHolder> implements View.OnClickListener {



    //Declarar elemento del evento click
    private View.OnClickListener listener;

    //
    private List<DetalleConsumo> mData; //lista
    private LayoutInflater minFlater; //contenedor
    private Context context;

    //constructor
    public RegistrarConsumoAdapter (List<DetalleConsumo> itemList, Context context){
        this.minFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    //retornar cantidad de elementos
    @Override
    public int getItemCount(){ return mData.size();}

    //Seleccionar el contenedor CardView XML
    @Override
    public RegistrarConsumoAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = minFlater.inflate(R.layout.cardview_registrar_consumos, null );

        //declarar onclick
        view.setOnClickListener(this);

        return new RegistrarConsumoAdapter.ViewHolder(view);
    }

    //posicion del elemento
    @Override
    public void onBindViewHolder (final RegistrarConsumoAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    //reasignar lista
    public void setItems(List<DetalleConsumo> items){
        mData = items;
    }

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

    //Asinar los datos dentro del contenedor CardView
    public class ViewHolder extends RecyclerView.ViewHolder{
        //elementos del cardview
        ImageView ivIconoRegCons;
        TextView tvNombreRegCons, tvSubtotalRegCons;
        Button btnAgregarRegCons, btnQuitarRegCons;
        EditText edtCantidadRegCons;

        //contador de producto
        int cantidad;

        ViewHolder(View itemView){
            super(itemView);

            //relacionamos los elementos
            ivIconoRegCons = itemView.findViewById(R.id.ivIconoRegCons);
            tvNombreRegCons = itemView.findViewById(R.id.tvNombreRegCons);
            tvSubtotalRegCons = itemView.findViewById(R.id.tvSubtotalRegCons);
            btnAgregarRegCons = itemView.findViewById(R.id.btnAgregarRegCons);
            btnQuitarRegCons = itemView.findViewById(R.id.btnQuitarRegCons);
            edtCantidadRegCons = itemView.findViewById(R.id.edtCantidadRegCons);

        }
        void bindData(final DetalleConsumo item){
            //asignar datos a los elementos
            ivIconoRegCons.setImageResource(R.drawable.ic_icono_comida);
            tvNombreRegCons.setText("Producto: "+item.getIdProducto());
            //formato para dinero
            String formato = new DecimalFormat("#,##0.00").format(item.getSubTotal());
            tvSubtotalRegCons.setText("$ "+formato);
            //contador cantidad
            cantidad = 0;

            btnAgregarRegCons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //añadir cantidad
                    cantidad++;
                    edtCantidadRegCons.setText(""+cantidad);
                    item.setCantidad(cantidad);
                }
            });

            btnQuitarRegCons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cantidad>0){
                        //quitar cantidad
                        cantidad--;
                        edtCantidadRegCons.setText(""+ cantidad);
                        item.setCantidad(cantidad);
                    }
                }
            });

        }
    }
}
