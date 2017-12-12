package com.vecks.timo.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.vecks.timo.R;
import com.vecks.timo.adapters.AdapterCategorias;
import com.vecks.timo.models.Categoria;
import com.vecks.timo.models.Materia;

import java.util.ArrayList;

public class PersonalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    AdapterCategorias adapterCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPersonal);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNuevaMateria(view);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupUI();
    }

    private void showDialogNuevaMateria(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        // I'm using fragment here so I'm using getView() to provide ViewGroup
        // but you can provide here any other instance of ViewGroup from your Fragment / Activity
        View viewInflated = LayoutInflater.from(this)
                            .inflate(R.layout.add_item_dialog,
                                    (ViewGroup) getWindow().getDecorView().getRootView(),
                                    false);
        // Set up the input
        final EditText input = viewInflated.findViewById(R.id.input);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(viewInflated);

        // Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void setupUI() {

        recyclerView = findViewById(R.id.rvCategorias);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);

        adapterCategorias = new AdapterCategorias(this, getListaCategoria());
        recyclerView.setAdapter(adapterCategorias);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private ArrayList<Categoria> getListaCategoria() {

        ArrayList<Categoria> lista = new ArrayList<>();
        lista.add(new Categoria(0, "Ejercicio"));
        lista.add(new Categoria(1, "Compras"));
        lista.add(new Categoria(2, "Hogar"));
        lista.add(new Categoria(3, "Familia"));
        lista.add(new Categoria(4, "Amigos"));

        return lista;
    }

}
