package com.example.pr5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DatePicker fechaPicker;
    private EditText valorEditText;
    private Spinner tipoSpinner;
    private Button agregarGastoButton;
    private Button eliminarGastoButton;
    private ListView listaGastos;

    private ArrayList<Gasto> gastos = new ArrayList<>();
    private MiObjetoAdapter gastosAdapter;
    SingletonData singletonData = SingletonData.getInstance();
    ArrayList<Gasto> data = singletonData.getData();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fechaPicker = findViewById(R.id.fechaPicker);
        valorEditText = findViewById(R.id.valorEditText);
        tipoSpinner = findViewById(R.id.tipoSpinner);
        agregarGastoButton = findViewById(R.id.agregarGastoButton);
        eliminarGastoButton = findViewById(R.id.eliminarGastoButton);
        listaGastos = findViewById(R.id.listaGastos);

        // Configurar el Spinner con los tipos de gastos
        ArrayAdapter<CharSequence> tiposAdapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_gastos, android.R.layout.simple_spinner_item);
        tiposAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSpinner.setAdapter(tiposAdapter);

        // Configurar el adaptador y la lista de gastos
        gastosAdapter = new MiObjetoAdapter(this, data);
        listaGastos.setAdapter(gastosAdapter);

        agregarGastoButton.setOnClickListener(v -> agregarGasto());

        eliminarGastoButton.setOnClickListener(v -> eliminarGasto());

        Button consultarGastosButton = findViewById(R.id.consultarGastosButton);
        consultarGastosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaGastosActivity.class);
                startActivity(intent);
            }
        });

    }


    private void agregarGasto() {
        int dia = fechaPicker.getDayOfMonth();
        int mes = fechaPicker.getMonth() + 1; // Los meses comienzan desde 0 en DatePicker
        int anio = fechaPicker.getYear();
        String fecha = dia + "/" + mes + "/" + anio;

        double valor;
        try {
            valor = Double.parseDouble(valorEditText.getText().toString());
        } catch (NumberFormatException e) {
            // Valor no válido, mostrar mensaje de error y detener la ejecución
            valorEditText.setError("Valor inválido");
            return;
        }

        String tipo = tipoSpinner.getSelectedItem().toString();

        Gasto gasto = new Gasto(fecha, valor, tipo);
        singletonData.addData(gasto);
        gastosAdapter.notifyDataSetChanged();

    }

    private void eliminarGasto() {
        if (!data.isEmpty()) {
            //gastos.remove(gastos.size() - 1);
            singletonData.removeData(data.get(data.size() - 1));
            gastosAdapter.notifyDataSetChanged();
        }
    }
}
