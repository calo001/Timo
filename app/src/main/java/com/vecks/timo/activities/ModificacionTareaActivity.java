package com.vecks.timo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vecks.timo.R;
import com.vecks.timo.dao.DatabaseHandler;
import com.vecks.timo.models.Tarea;
import com.vecks.timo.utils.DateConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModificacionTareaActivity extends AppCompatActivity {

    private int idTarea;
    private Tarea tareaActual;
    private DatabaseHandler db;

    private EditText inputNombreTareaMod, descripcionMod;
    private CalendarView calendarViewMod;
    private RadioGroup radioGroupMod;
    private String dateCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_tarea);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputNombreTareaMod = findViewById(R.id.inputNombreTarea);
        descripcionMod = findViewById(R.id.inputDescripcion);
        calendarViewMod = findViewById(R.id.calendarView);
        radioGroupMod = findViewById(R.id.radioGroup);

        calendarViewMod.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                dateCalendarView = year + "-" + month + "-" + day;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean okCmapos = validaCampos();
                if (okCmapos){
                    actualizaBDEscolar();
                    finish();
                } else {
                    Snackbar.make(view, "Campos incorrectos.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        db = new DatabaseHandler(this);
        initConfiguration();
        obtenerDatosActuales();
    }


    private void initConfiguration() {
        Intent intent = getIntent();
        idTarea = intent.getIntExtra(TareaActivity.ID_TAREA, 0);
    }

    private void obtenerDatosActuales() {
        tareaActual = db.getTAreaPorId(idTarea);
        setDatosActuales();
    }


    private void setDatosActuales() {
        inputNombreTareaMod.setText(tareaActual.getNombreTarea());
        descripcionMod.setText(tareaActual.getDetalle());
        checkRadioGroup();

        dateCalendarView = DateConverter.dateToString(tareaActual.getFechaEntrega());

        try {
            calendarViewMod.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateCalendarView).getTime(), true, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void actualizaBDEscolar() {
        String nombreTarea = inputNombreTareaMod.getText().toString().trim();
        Date date = DateConverter.stringToDate(dateCalendarView);
        int materia = tareaActual.getIdMateria();
        int categoria = 0;
        int estado = tareaActual.getEstado(); // 0 es no hecho aun, apenas se registra
        int importancia = getImportancia();
        String detalle = descripcionMod.getText().toString().trim();

        Tarea tarea = new Tarea(idTarea, nombreTarea, date, materia, categoria, estado, importancia, detalle);
        db.actualizaTarea(tarea);
    }

    private int getImportancia() {
        if (radioGroupMod.getCheckedRadioButtonId() == R.id.radioBtnPocoImportante){
            return 1;
        } else if (radioGroupMod.getCheckedRadioButtonId() == R.id.radioBtnMuyImportante){
            return 2;
        } else if (radioGroupMod.getCheckedRadioButtonId() == R.id.radioBtnUrgente){
            return 3;
        } else {
            return 0;
        }

    }

    private boolean validaCampos() {
        if (inputNombreTareaMod.getText().toString().trim().equals("")) {
            inputNombreTareaMod.setError("Obligatorio");
            return false;
        } else {
            inputNombreTareaMod.setError(null);
            return true;
        }
    }

    private void checkRadioGroup() {
        Toast.makeText(this, String.valueOf(tareaActual.getImportancia()), Toast.LENGTH_SHORT).show();
        if (tareaActual.getImportancia() == 1){
            radioGroupMod.check(R.id.radioBtnPocoImportante);
        } else if (tareaActual.getImportancia() == 2){
            radioGroupMod.check(R.id.radioBtnMuyImportante);
        } else if (tareaActual.getImportancia() == 3){
            radioGroupMod.check(R.id.radioBtnUrgente);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modificar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_eliminar) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("¿Esta seguro de borrar la tarea?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Si",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            db.deleteTarea(idTarea);
                            dialog.cancel();
                            finish();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
