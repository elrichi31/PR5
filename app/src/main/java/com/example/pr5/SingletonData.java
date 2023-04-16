package com.example.pr5;

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
        data.add(new Gasto("28/02/2023", 25.99, "Vivienda"));
        data.add(new Gasto("12/02/2023", 21.99, "Salud"));
        data.add(new Gasto("03/02/2023", 123.99, "Ropa"));
        data.add(new Gasto("03/01/2023", 45.20, "Vivienda"));
        data.add(new Gasto("15/01/2023", 12.35, "Alimentación"));
        data.add(new Gasto("25/01/2023", 65.40, "Salud"));
        data.add(new Gasto("02/02/2023", 100.99, "Educación"));
        data.add(new Gasto("08/02/2023", 23.50, "Ropa"));
        data.add(new Gasto("16/02/2023", 35.10, "Vivienda"));
        data.add(new Gasto("22/02/2023", 27.99, "Alimentación"));
        data.add(new Gasto("02/03/2023", 150.00, "Educación"));
        data.add(new Gasto("09/03/2023", 40.25, "Salud"));
        data.add(new Gasto("18/03/2023", 89.00, "Ropa"));
        data.add(new Gasto("27/03/2023", 55.30, "Vivienda"));
        // Agrega más elementos según sea necesario
    }

    // Métodos para manipular los datos almacenados
    public ArrayList<Gasto> getData() {
        return data;
    }

    public void addData(Gasto objeto) {
        data.add(objeto);
    }

    public void removeData(Gasto objeto) {
        data.remove(objeto);
    }

    // Otros métodos según sea necesario
}
