package com.example.pr5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MiObjetoAdapter extends ArrayAdapter<Gasto> {

    public MiObjetoAdapter(Context context, ArrayList<Gasto> objetos) {
        super(context, 0, objetos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gasto objeto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(objeto.getTipo() + "\n" + "Fecha: " + objeto.getFecha() + "\nValor: $" +  objeto.getValor());

        return convertView;
    }
}
