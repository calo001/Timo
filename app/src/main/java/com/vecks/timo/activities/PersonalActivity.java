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
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.vecks.timo.R;
import com.vecks.timo.adapters.AdapterCategorias;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        builder.setTitle("Agrega una nueva materia");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(view, "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void setupUI() {

        recyclerView = (RecyclerView) findViewById(R.id.rvCategorias);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);

        adapterCategorias = new AdapterCategorias(this, getListaMaterias());
        recyclerView.setAdapter(adapterCategorias);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private ArrayList<Materia> getListaMaterias() {

        ArrayList<Materia> lista = new ArrayList<>();
        lista.add(new Materia(0, "Matematicas"));
        lista.add(new Materia(1, "Ingles"));
        lista.add(new Materia(2, "Español"));
        lista.add(new Materia(3, "Historia"));
        lista.add(new Materia(4, "Geografia"));

        return lista;
    }

}
