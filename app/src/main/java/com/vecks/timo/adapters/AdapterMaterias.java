package com.vecks.timo.adapters;

        import android.content.Context;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.vecks.timo.R;
        import com.vecks.timo.interfaces.OnClickMateria;
        import com.vecks.timo.models.Materia;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by carlos on 11/12/17.
 */

public class AdapterMaterias extends RecyclerView.Adapter<AdapterMaterias.MateriaViewHolder> {
    private Context context;
    List<Materia> materiaList;
    OnClickMateria onClickMateria;

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public AdapterMaterias(Context context, List<Materia> materiaList, OnClickMateria onClickMateria) {
        this.context = context;
        this.materiaList = materiaList;
        this.onClickMateria = onClickMateria;
    }

    @Override
    public MateriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_materia, null, false);
        MateriaViewHolder viewHolder = new MateriaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MateriaViewHolder holder, final int position) {
        holder.txtMateria.setText(materiaList.get(position).getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMateria.intentTareaDetalleActivity(materiaList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return materiaList.size();
    }

    public static class MateriaViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView txtMateria;
        public MateriaViewHolder(View itemView) {
            super(itemView);
            txtMateria = itemView.findViewById(R.id.txtMateria);
            cardView = itemView.findViewById(R.id.cardviewMateria);
        }
    }
}
