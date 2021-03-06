package com.vecks.timo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vecks.timo.R;
import com.vecks.timo.models.Categoria;
import com.vecks.timo.models.Materia;

import java.util.ArrayList;

/**
 * Created by carlos on 11/12/17.
 */

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.CategoriaViewHolder> {
    private Context context;
    ArrayList<Categoria> categoriaList;

    public AdapterCategorias(Context context, ArrayList<Categoria> categoriaList) {
        this.context = context;
        this.categoriaList = categoriaList;
    }

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_categoria, null, false);
        CategoriaViewHolder viewHolder = new CategoriaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoriaViewHolder holder, int position) {
        holder.txtCategoria.setText(categoriaList.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCategoria;
        public CategoriaViewHolder(View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
        }
    }
}
