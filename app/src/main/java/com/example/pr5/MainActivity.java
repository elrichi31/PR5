package com.example.pr5;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtFecha;
    private ImageButton btnFecha;
    private EditText valorEditText;
    private Spinner tipoSpinner;
    private Button agregarGastoButton;
    private Button eliminarGastoButton;
    private ListView listaGastos;
    private SharedPreferencesManager sharedPreferencesManager;

    private MiObjetoAdapter gastosAdapter;
    SingletonData singletonData = SingletonData.getInstance();
    ArrayList<Gasto> data = singletonData.getData();
    private ArrayAdapter<String> adapterTipos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFecha = findViewById(R.id.txtFecha);
        btnFecha = findViewById(R.id.btnFecha);
        valorEditText = findViewById(R.id.valorEditText);
        tipoSpinner = findViewById(R.id.tipoSpinner);
        agregarGastoButton = findViewById(R.id.agregarGastoButton);
        eliminarGastoButton = findViewById(R.id.eliminarGastoButton);
        listaGastos = findViewById(R.id.listaGastos);
        sharedPreferencesManager = SharedPreferencesManager.getInstance(this);

        // Configurar el Spinner con los tipos de gastos
        List<String> tiposDeGasto = sharedPreferencesManager.getTiposDeGasto();
        adapterTipos= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposDeGasto);
        adapterTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(adapterTipos);

        // Configurar el adaptador y la lista de gastos
        gastosAdapter = new MiObjetoAdapter(this, data);
        listaGastos.setAdapter(gastosAdapter);

        agregarGastoButton.setOnClickListener(v -> agregarGasto());
        eliminarGastoButton.setOnClickListener(v -> eliminarGasto());
        btnFecha.setOnClickListener(v -> muestraCalendario());

        Button consultarGastosButton = findViewById(R.id.consultarGastosButton);
        Button configurarGastosButton = findViewById(R.id.configurarGastos);
        consultarGastosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaGastosActivity.class);
                startActivity(intent);
            }
        });
        configurarGastosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigurarGastosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void muestraCalendario() {
        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> txtFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                anio, mes, dia);
        datePickerDialog.show();
    }

    private void agregarGasto() {
        String fecha = txtFecha.getText().toString();

        double valor;
        try {
            valor = Double.parseDouble(valorEditText.getText().toString());
        } catch (NumberFormatException e) {
            // Valor no válido, mostrar mensaje de error y detener la ejecución
            valorEditText.setError("Valor inválido");
            return;
        }

        String tipo = tipoSpinner.getSelectedItem().toString();
        if (verificarGasto(tipo, valor)) {
            Gasto gasto = new Gasto(fecha, valor, tipo);
            singletonData.addData(gasto);
            valorEditText.setText("");
            txtFecha.setText("");
            gastosAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Gasto agregado", Toast.LENGTH_SHORT).show();
        } else {
            valorEditText.setError("Gasto excede el límite permitido");
        }
    }

    private void eliminarGasto() {
        if (!data.isEmpty()) {
            singletonData.removeData(data.get(data.size() - 1));
            gastosAdapter.notifyDataSetChanged();
        }
    }

    private boolean verificarGasto(String tipoGasto, double montoGasto) {
        double min = Double.parseDouble(sharedPreferencesManager.getLimiteGastoMin(tipoGasto));
        double max = Double.parseDouble(sharedPreferencesManager.getLimiteGastoMax(tipoGasto));

        return montoGasto >= min && montoGasto <= max;
    }
}

