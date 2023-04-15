package com.example.pr5;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultaGastosActivity extends AppCompatActivity {

    private Spinner tipoFiltroSpinner;
    private DatePicker fechaInicioPicker;
    private DatePicker fechaFinPicker;
    private EditText valorMinEditText;
    private EditText valorMaxEditText;
    private Button aplicarFiltrosButton;
    private TextView totalGastosTextView;

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
    }

    private void aplicarFiltros() {
        // Aquí deberás recuperar y filtrar los datos de gastos según los filtros seleccionados.
        // Por ejemplo, puedes filtrar por tipo, fecha y rango de valores.
        // Luego, actualiza el total de gastos y muestra los resultados en totalGastosTextView.
    }
}
