package com.vecks.timo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.vecks.timo.R;
import com.vecks.timo.adapters.AdapterCategorias;
import com.vecks.timo.adapters.AdapterMaterias;
import com.vecks.timo.dao.DatabaseHandler;
import com.vecks.timo.interfaces.OnClickMateria;
import com.vecks.timo.models.Materia;
import com.vecks.timo.models.Tarea;

import java.util.ArrayList;
import java.util.List;

public class EscolarActivity extends AppCompatActivity implements OnClickMateria {

    public static final String ID_MATERIA = "idMateria";
    public static final String TIPO_TAREA = "tipo";
    public static final String TAREA_ESCOLAR = "tareaEscolar";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AdapterMaterias adapterMaterias;
    private DatabaseHandler db;
    private List<Materia> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolar);
        Toolbar toolbar = findViewById(R.id.toolbarEscolar);
        toolbar.setTitle("Mis materias");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogNuevaMateria(view);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDB();
        setupUI();

    }

    private void setupDB() {
        db = new DatabaseHandler(this);
    }

    private void showDialogNuevaMateria(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregue una nueva Materia");
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
        builder.setCancelable(false);

        // Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String campoMateria = input.getText().toString().trim();
                if ( campoMateria.isEmpty() ){
                    Snackbar.make(view, "El campo no puede estar vacio", Snackbar.LENGTH_SHORT).show();
                } else {
                    db.addMateria(new Materia(campoMateria));
                    adapterMaterias.setMateriaList(getListaMaterias());
                    adapterMaterias.notifyDataSetChanged();
                    dialog.dismiss();
                }
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
        recyclerView = findViewById(R.id.rv_lista_escolar);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);

        adapterMaterias = new AdapterMaterias(this, getListaMaterias(), this);
        recyclerView.setAdapter(adapterMaterias);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<Materia> getListaMaterias() {

        lista = db.getAllMaterias();
        return lista;
    }


    @Override
    public void intentTareaDetalleActivity(int paramIdMateria) {
        Intent intent = new Intent(this, TareaActivity.class);
        intent.putExtra(ID_MATERIA, paramIdMateria);
        intent.putExtra(TIPO_TAREA, TAREA_ESCOLAR);
        startActivity(intent);
    }
}
