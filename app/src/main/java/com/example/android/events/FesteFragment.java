package com.example.android.events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FesteFragment extends Fragment {

    public FesteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.events_list, container, false);

        final ArrayList<Evento> feste = new ArrayList<>();

        for (int i = evenTS.p + 1; i < evenTS.s; i++) {
            feste.add(new Evento(evenTS.titoli.get(i), evenTS.descrizioniLunghe.get(i),
                    evenTS.descrizioniCorte.get(i), evenTS.date.get(i), evenTS.ore.get(i),
                    evenTS.indirizzi.get(i), evenTS.sconti.get(i), evenTS.urls.get(i)));
        }

        EventoAdapter adapter = new EventoAdapter(getActivity(), feste, R.drawable.festa);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Evento attr = feste.get(position);
                Intent npIntent = new Intent(getContext(), EventView.class);
                npIntent.putExtra("evento", attr);
                startActivity(npIntent);
            }
        });

        return rootView;
    }

}
