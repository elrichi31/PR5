package com.example.pr5;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";
    private static final String TIPOS_DE_GASTO_KEY = "tiposDeGasto";

    private SharedPreferences sharedPreferences;

    private static SharedPreferencesManager instance;

    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public String getLimiteGastoMin(String tipoGasto) {
        return sharedPreferences.getString(tipoGasto + "_min", null);
    }

    public void setLimiteGastoMin(String tipoGasto, String limiteGastoMin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tipoGasto + "_min", limiteGastoMin);
        editor.apply();
    }

    public String getLimiteGastoMax(String tipoGasto) {
        return sharedPreferences.getString(tipoGasto + "_max", null);
    }

    public void setLimiteGastoMax(String tipoGasto, String limiteGastoMax) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tipoGasto + "_max", limiteGastoMax);
        editor.apply();
    }

    public void removeLimiteGasto(String tipoGasto) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(tipoGasto + "_min");
        editor.remove(tipoGasto + "_max");
        editor.apply();
    }

    public List<String> getTiposDeGasto() {
        Set<String> tiposDeGastoSet = sharedPreferences.getStringSet(TIPOS_DE_GASTO_KEY, null);
        if (tiposDeGastoSet == null) {
            // Inicializar con valores predeterminados si no se encuentra ningún tipo de gasto guardado
            List<String> defaultTiposDeGasto = Arrays.asList("Vivienda", "Alimentación", "Salud", "Educación", "Ropa");
            addTiposDeGasto(defaultTiposDeGasto);
            return defaultTiposDeGasto;
        } else {
            return new ArrayList<>(tiposDeGastoSet);
        }
    }

    public void addTipoDeGasto(String tipoDeGasto) {
        Set<String> tiposDeGastoSet = sharedPreferences.getStringSet(TIPOS_DE_GASTO_KEY, new HashSet<>());
        Set<String> newTiposDeGastoSet = new HashSet<>(tiposDeGastoSet); // Crear una copia mutable
        newTiposDeGastoSet.add(tipoDeGasto);
        sharedPreferences.edit().putStringSet(TIPOS_DE_GASTO_KEY, newTiposDeGastoSet).apply();
    }

    public void addTiposDeGasto(List<String> tiposDeGasto) {
        Set<String> tiposDeGastoSet = new HashSet<>(tiposDeGasto);
        sharedPreferences.edit().putStringSet(TIPOS_DE_GASTO_KEY, tiposDeGastoSet).apply();
    }
    public void removeTipoDeGasto(String tipoDeGasto) {
        Set<String> tiposDeGastoSet = sharedPreferences.getStringSet(TIPOS_DE_GASTO_KEY, new HashSet<>());
        Set<String> newTiposDeGastoSet = new HashSet<>(tiposDeGastoSet); // Crear una copia mutable
        newTiposDeGastoSet.remove(tipoDeGasto);
        sharedPreferences.edit().putStringSet(TIPOS_DE_GASTO_KEY, newTiposDeGastoSet).apply();
    }

}
