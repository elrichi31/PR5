package com.example.pr5;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SingletonData {
    private static SingletonData instance;
    private ArrayList<Gasto> data;

    // Constructor privado para evitar la creación de instancias directamente
    private SingletonData() {
        data = new ArrayList<>();
        loadInitialData();
    }

    // Método para obtener la instancia única de la clase
    public static SingletonData getInstance() {
        if (instance == null) {
            instance = new SingletonData();
        }
        return instance;
    }

    // Método para precargar datos iniciales
    private void loadInitialData() {
        data.add(new Gasto("28/2/2023", 25.99, "Vivienda"));
        data.add(new Gasto("12/2/2023", 21.99, "Salud"));
        data.add(new Gasto("3/2/2023", 123.99, "Ropa"));
        data.add(new Gasto("3/1/2023", 45.20, "Vivienda"));
        data.add(new Gasto("15/1/2023", 12.35, "Alimentación"));
        data.add(new Gasto("25/1/2023", 65.40, "Salud"));
        data.add(new Gasto("2/2/2023", 100.99, "Educación"));
        data.add(new Gasto("8/2/2023", 23.50, "Ropa"));
        data.add(new Gasto("16/2/2023", 35.10, "Vivienda"));
        data.add(new Gasto("22/2/2023", 27.99, "Alimentación"));
        data.add(new Gasto("2/3/2023", 150.00, "Educación"));
        data.add(new Gasto("9/3/2023", 40.25, "Salud"));
        data.add(new Gasto("18/3/2023", 89.00, "Ropa"));
        data.add(new Gasto("27/3/2023", 55.30, "Vivienda"));
        // Agrega más elementos según sea necesario
    }

    // Métodos para manipular los datos almacenados
    public ArrayList<Gasto> getData() {
        return data;
    }

    public void addData(Gasto objeto) {
        data.add(objeto);
    }

    public void removeData(String fecha, Double valor, String tipo) {
        for(int i=0; i<data.size(); i++){
            if(data.get(i).getFecha().equals(fecha) && data.get(i).getValor() == valor && data.get(i).getTipo().equals(tipo)){
                data.remove(data.get(i));
                Log.i("i", fecha + " " + tipo + " " +valor);

            }
        }
    }

    // Otros métodos según sea necesario
}
