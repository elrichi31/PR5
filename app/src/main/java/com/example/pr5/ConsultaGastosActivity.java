package com.example.pr5;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConsultaGastosActivity extends AppCompatActivity {

    private Spinner tipoFiltroSpinner;
    private EditText fechaInicioPicker;
    private EditText fechaFinPicker;
    private EditText valorMinEditText;
    private EditText valorMaxEditText;
    private Button aplicarFiltrosButton;
    private TextView totalGastosTextView;
    private ImageButton btnFechaInicio, btnFechaFin;
    private ListView filtroGastos;

    private MiObjetoAdapter filtroGastosAdapter;
    SingletonData singletonData = SingletonData.getInstance();
    ArrayList<Gasto> data = singletonData.getData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_gastos);

        tipoFiltroSpinner = findViewById(R.id.tipoFiltroSpinner);
        fechaInicioPicker = findViewById(R.id.fechaInicioPicker);
        fechaFinPicker = findViewById(R.id.fechaFinPicker);
        valorMinEditText = findViewById(R.id.valorMinEditText);
        valorMaxEditText = findViewById(R.id.valorMaxEditText);
        aplicarFiltrosButton = findViewById(R.id.aplicarFiltrosButton);
        totalGastosTextView = findViewById(R.id.totalGastosTextView);
        btnFechaInicio = findViewById(R.id.btnFecha2);
        btnFechaFin = findViewById(R.id.btnFecha);
        filtroGastos = findViewById(R.id.filtroGastos);

        filtroGastosAdapter = new MiObjetoAdapter(this, data);
        filtroGastos.setAdapter(filtroGastosAdapter);



        // Configura el spinner con las opciones de tipo de gasto.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos_gastos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoFiltroSpinner.setAdapter(adapter);

        // Establece un OnClickListener para el botón de aplicar filtros.
        aplicarFiltrosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aplicarFiltros();
            }
        });

        btnFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muestraCalendario(fechaInicioPicker);
            }
        });

        btnFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muestraCalendario(fechaFinPicker);
            }
        });

    }

    private void muestraCalendario(final EditText fechaEditText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                    fechaEditText.setText(fechaSeleccionada);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void aplicarFiltros() {
        SingletonData singletonData = SingletonData.getInstance();
        ArrayList<Gasto> data = singletonData.getData();

        String tipoFiltro = tipoFiltroSpinner.getSelectedItem().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date fechaInicio;
        try {
            fechaInicio = sdf.parse(fechaInicioPicker.getText().toString());
        } catch (ParseException e) {
            fechaInicioPicker.setError("Fecha inválida");
            return;
        }

        Date fechaFin;
        try {
            fechaFin = sdf.parse(fechaFinPicker.getText().toString());
        } catch (ParseException e) {
            fechaFinPicker.setError("Fecha inválida");
            return;
        }

        double valorMin;
        try {
            valorMin = Double.parseDouble(valorMinEditText.getText().toString());
        } catch (NumberFormatException e) {
            valorMinEditText.setError("Valor inválido");
            return;
        }

        double valorMax;
        try {
            valorMax = Double.parseDouble(valorMaxEditText.getText().toString());
        } catch (NumberFormatException e) {
            valorMaxEditText.setError("Valor inválido");
            return;
        }

        double totalGastos = 0;
        ArrayList<Gasto> filteredData = new ArrayList<>();
        for (Gasto gasto : data) {
            try {
                Date fechaGasto = sdf.parse(gasto.getFecha());

                if (gasto.getTipo().equals(tipoFiltro) &&
                        (fechaGasto.compareTo(fechaInicio) >= 0 && fechaGasto.compareTo(fechaFin) <= 0) &&
                        (gasto.getValor() >= valorMin && gasto.getValor() <= valorMax)) {
                    totalGastos += gasto.getValor();
                    filteredData.add(gasto);

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        filtroGastosAdapter = new MiObjetoAdapter(this, filteredData);
        filtroGastos.setAdapter(filtroGastosAdapter);

        filtroGastosAdapter.notifyDataSetChanged();
        totalGastosTextView.setText(String.format(Locale.getDefault(), "Total Gastos: %.2f", totalGastos));
    }


}
