package com.example.pr5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ConfigurarGastosActivity extends AppCompatActivity {

    private EditText etMin, etMax, editTextAgregarTipoGasto;
    private Spinner spinnerTipoGasto;
    private Button buttonAgregarTipoGasto;
    private ArrayAdapter<String> adapter;
    private SharedPreferencesManager sharedPreferencesManager;
    private Spinner spinnerTipoGastoEliminar;
    private Button btnEliminarTipoGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_gastos);

        etMin = findViewById(R.id.editTextNumberDecimal);
        etMax = findViewById(R.id.editTextNumberDecimal2);
        spinnerTipoGasto = findViewById(R.id.tipoFiltroSpinner4);
        editTextAgregarTipoGasto = findViewById(R.id.editTextAgregarTipoGasto);
        Button btnGuardar = findViewById(R.id.button3);
        buttonAgregarTipoGasto = findViewById(R.id.buttonAgregarTipoGasto);
        spinnerTipoGastoEliminar = findViewById(R.id.tipoFiltroSpinner5);
        btnEliminarTipoGasto = findViewById(R.id.button4);


        sharedPreferencesManager = SharedPreferencesManager.getInstance(this);

        // Cargar tipos de gasto guardados
        List<String> tiposDeGasto = sharedPreferencesManager.getTiposDeGasto();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposDeGasto);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoGasto.setAdapter(adapter);

        spinnerTipoGastoEliminar.setAdapter(adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoGasto = spinnerTipoGasto.getSelectedItem().toString();
                String min = etMin.getText().toString();
                String max = etMax.getText().toString();

                sharedPreferencesManager.setLimiteGastoMin(tipoGasto, min);
                sharedPreferencesManager.setLimiteGastoMax(tipoGasto, max);

                Toast.makeText(ConfigurarGastosActivity.this, "Límites de gasto guardados", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAgregarTipoGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuevoTipoGasto = editTextAgregarTipoGasto.getText().toString();
                if (!nuevoTipoGasto.isEmpty()) {
                    adapter.add(nuevoTipoGasto);
                    adapter.notifyDataSetChanged();
                    sharedPreferencesManager.addTipoDeGasto(nuevoTipoGasto);
                    editTextAgregarTipoGasto.getText().clear();
                    Toast.makeText(ConfigurarGastosActivity.this, "Tipo de gasto agregado", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnEliminarTipoGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoGastoEliminar = spinnerTipoGastoEliminar.getSelectedItem().toString();
                if (!tipoGastoEliminar.isEmpty()) {
                    sharedPreferencesManager.removeTipoDeGasto(tipoGastoEliminar);
                    adapter.remove(tipoGastoEliminar);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ConfigurarGastosActivity.this, "Tipo de gasto eliminado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
