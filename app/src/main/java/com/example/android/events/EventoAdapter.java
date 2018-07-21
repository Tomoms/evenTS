package com.example.android.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter {

    int immg;

    public EventoAdapter(Context context, ArrayList<Evento> words, int image) {
        super(context, 0, words);
        immg = image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Evento currentEv = (Evento) getItem(position);
        TextView titolo = listItemView.findViewById(R.id.titolo);
        TextView indirizzo = listItemView.findViewById(R.id.indirizzo);
        TextView descr_breve = listItemView.findViewById(R.id.descr_breve);
        TextView data = listItemView.findViewById(R.id.data);
        TextView ora = listItemView.findViewById(R.id.ora);
        TextView sconto = listItemView.findViewById(R.id.sconto);
        ImageView img = listItemView.findViewById(R.id.image);

        titolo.setText(currentEv.getTitolo());
        indirizzo.setText(currentEv.getLuogo());
        descr_breve.setText(currentEv.getDescr_breve());
        data.setText(currentEv.getData());
        ora.setText(currentEv.getOra());
        int s = Integer.parseInt(currentEv.getSconto());
        String scontoStr = currentEv.getSconto() + "%";
        sconto.setText(scontoStr);
        if (s == 0)
            sconto.setVisibility(View.INVISIBLE);

        img.setImageResource(immg);

        img.setVisibility(View.VISIBLE);

        return listItemView;
    }

}
