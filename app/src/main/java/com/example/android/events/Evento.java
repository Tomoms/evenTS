package com.example.android.events;

import java.io.Serializable;

public class Evento implements Serializable {

    private String titolo, descrizione, descr_breve, data, ora, luogo, sconto, url;

    Evento(String t, String dl, String db, String date, String time, String l, String sc, String img) {
        titolo = t;
        descrizione = dl;
        descr_breve = db;
        data = date;
        ora = time;
        luogo = l;
        sconto = sc;
        url = img;
    }

    public String getData() {
        return data;
    }

    public String getDescr_breve() {
        return descr_breve;
    }

    public String getOra() {
        return ora;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getSconto() {
        return sconto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getUrl() {
        return url;
    }
}
