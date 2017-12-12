package com.vecks.timo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vecks.timo.R;
import com.vecks.timo.dao.DatabaseHandler;
import com.vecks.timo.models.Materia;
import com.vecks.timo.models.Tarea;
import com.vecks.timo.utils.DateConverter;

import java.util.Date;

public class RegistroTareaActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private String tipoTarea;
    private int idMateria = 0;
    private int idCategoria = 0;

    private EditText inputNombreTarea, descripcion;
    private CalendarView calendarView;
    private RadioGroup radioGroup;
    private String dateCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tarea);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputNombreTarea = findViewById(R.id.inputNombreTarea);
        descripcion = findViewById(R.id.inputDescripcion);
        calendarView = findViewById(R.id.calendarView);
        radioGroup = findViewById(R.id.radioGroup);

        Date actualDate = DateConverter.calendarToDate(calendarView);
        dateCalendarView = DateConverter.dateToString(actualDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                dateCalendarView = year + "-" + month + "-" + day;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (idMateria != 0){
                    boolean okCmapos = validaCampos();
                    if (okCmapos){
                        guardaenBDEscolar();
                        finish();
                    } else {
                        Snackbar.make(view, "Campos incorrectos.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }

            }
        });

        setupDB();
        setUpConfiguration();

        //Toast.makeText(this, String.valueOf(idMateria), Toast.LENGTH_SHORT).show();
    }

    private void guardaenBDEscolar () {

        String nombreTarea = inputNombreTarea.getText().toString().trim();
        Date date = DateConverter.stringToDate(dateCalendarView);
        int materia = idMateria;
        int categoria = 0;
        int estado = 0; // 0 es no hecho aun, apenas se registra
        int importancia = getImportancia();
        String detalle = descripcion.getText().toString().trim();

        Tarea tarea = new Tarea(nombreTarea, date, materia, categoria, estado, importancia, detalle);
        db.addTarea(tarea);
    }

    private int getImportancia() {
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioBtnPocoImportante){
            return 1;
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioBtnMuyImportante){
            return 2;
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioBtnUrgente){
            return 3;
        } else {
            return 0;
        }

    }


    private boolean validaCampos() {
        if (inputNombreTarea.getText().toString().trim().equals("")) {
            inputNombreTarea.setError("Obligatorio");
            return false;
        } else {
            inputNombreTarea.setError(null);
            return true;
        }
    }

    private void setupDB() {
        db = new DatabaseHandler(this);
    }

    private void setUpConfiguration() {
        Intent intent = getIntent();
        tipoTarea = intent.getStringExtra(EscolarActivity.TIPO_TAREA);
        if (tipoTarea.equals(EscolarActivity.TAREA_ESCOLAR)){
            idMateria = intent.getIntExtra(EscolarActivity.ID_MATERIA, 0);
        }
    }

}
