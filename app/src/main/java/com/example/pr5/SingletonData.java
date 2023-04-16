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
        data.add(new Gasto("03/02/2002", 25.99, "Vivienda"));
        data.add(new Gasto("12/07/2012", 21.99, "Salud"));
        data.add(new Gasto("03/02/2023", 123.99, "Ropa"));
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
