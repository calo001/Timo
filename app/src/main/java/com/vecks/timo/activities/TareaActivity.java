package com.vecks.timo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.vecks.timo.R;
import com.vecks.timo.adapters.AdapterTarea;
import com.vecks.timo.dao.DatabaseHandler;
import com.vecks.timo.interfaces.OnClickTarea;
import com.vecks.timo.models.Materia;
import com.vecks.timo.models.Tarea;
import com.vecks.timo.utils.DateConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TareaActivity extends AppCompatActivity implements OnClickTarea {

    public static final String ID_TAREA = "idTarea";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AdapterTarea adapterTarea;
    private DatabaseHandler db;
    private String tipoTarea;
    private int idMateria = 0;
    private int idCategoria = 0;
    private List<Tarea> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistroTareaActivity.class);

                if (idMateria != 0){
                    intent.putExtra(EscolarActivity.ID_MATERIA, idMateria);
                    intent.putExtra(EscolarActivity.TIPO_TAREA, tipoTarea);
                }

                startActivity(intent);
            }
        });

        setupDB();
        setUpConfiguration();
    }

    private void setupDB() {
        db = new DatabaseHandler(this);
    }

    private void setUpConfiguration() {
        Intent intent = getIntent();
        tipoTarea = intent.getStringExtra(EscolarActivity.TIPO_TAREA);
        if (tipoTarea.equals(EscolarActivity.TAREA_ESCOLAR)){
            idMateria = intent.getIntExtra(EscolarActivity.ID_MATERIA, 0);
            setupUIMateria();
        }
    }

    private void setupUIMateria() {
        recyclerView = findViewById(R.id.rv_lista_tareas);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);

        adapterTarea = new AdapterTarea(this, getListaTareasMateria(), this);
        recyclerView.setAdapter(adapterTarea);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<Tarea> getListaTareasMateria() {
        lista = db.getTareasPorMateria(idMateria);
        return lista;
    }

    @Override
    public void intentRegistroTareaActivity(int idTarea) {
        // Modificacion
        Intent intent = new Intent(this, ModificacionTareaActivity.class);
        intent.putExtra(ID_TAREA, idTarea);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lista = db.getTareasPorMateria(idMateria);
        adapterTarea.setTareasList(lista);
        adapterTarea.notifyDataSetChanged();
    }
}
