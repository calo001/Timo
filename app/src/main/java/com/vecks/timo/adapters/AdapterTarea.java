package com.vecks.timo.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vecks.timo.R;
import com.vecks.timo.interfaces.OnClickTarea;
import com.vecks.timo.models.Tarea;
import com.vecks.timo.utils.DateConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 12/12/17.
 */

public class AdapterTarea extends RecyclerView.Adapter<AdapterTarea.TareaViewHolder>{

    private Context context;
    List<Tarea> tareasList;
    OnClickTarea onClickTarea;

    public void setTareasList(List<Tarea> tareasList) {
        this.tareasList = tareasList;
    }

    public AdapterTarea(Context context, List<Tarea> tareasList, OnClickTarea onClickTarea) {
        this.context = context;
        this.tareasList = tareasList;
        this.onClickTarea = onClickTarea;
    }

    @Override
    public TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarea, null, false);
        AdapterTarea.TareaViewHolder viewHolder = new TareaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TareaViewHolder holder, final int position) {
        String fecha = DateConverter.dateToString(tareasList.get(position).getFechaEntrega());
        boolean checked = tareasList.get(position).getEstado() == 1 ? true : false;

        holder.txtNombreTarea.setText( tareasList.get(position).getNombreTarea() );
        holder.txtDescripcion.setText( tareasList.get(position).getDetalle() );
        holder.txtFecha.setText( fecha );
        holder.checkBoxHecho.setChecked(checked);

        holder.checkBoxHecho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    onClickTarea.actualizaStatus(tareasList.get(position), 1);
                } else {
                    onClickTarea.actualizaStatus(tareasList.get(position), 0);
                }
                //notifyDataSetChanged();
            }
        });

        holder.cardViewTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickTarea.intentRegistroTareaActivity(tareasList.get(position).getId());
            }
        });

        //Toast.makeText(context, String.valueOf(tareasList.get(position).getIdMateria()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return tareasList.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombreTarea;
        private TextView txtDescripcion;
        private TextView txtFecha;
        private CheckBox checkBoxHecho;
        private CardView cardViewTarea;
        public TareaViewHolder(View itemView) {
            super(itemView);
            txtNombreTarea = itemView.findViewById(R.id.txtNombreTarea);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            checkBoxHecho = itemView.findViewById(R.id.checkBoxHecho);
            cardViewTarea = itemView.findViewById(R.id.cardviewTarea);
        }
    }
}
