package com.example.android.events;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class evenTS extends AppCompatActivity {

    static public ArrayList<String> titoli = new ArrayList<String>();
    static public ArrayList<String> descrizioniCorte = new ArrayList<String>();
    static public ArrayList<String> indirizzi = new ArrayList<String>();
    static public ArrayList<String> descrizioniLunghe = new ArrayList<String>();
    static public ArrayList<String> date = new ArrayList<String>();
    static public ArrayList<String> ore = new ArrayList<String>();
    static public ArrayList<String> sconti = new ArrayList<String>();
    static public ArrayList<String> urls = new ArrayList<String>();

    static public int p = 0, s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_ts);
        final ViewPager viewPager = findViewById(R.id.viewpager);
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        DowloadinTaskSurce dowloadinTaskSurce = new DowloadinTaskSurce();
        String result = null;
        try {

            result = dowloadinTaskSurce.execute("http://www.stracciabit.it/eventi.html").get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();
        }

        RicercaDati(result);


        for (int i = 0; i < titoli.size(); i++) {
            if (titoli.get(i).equals("|")) {
                if (p == 0)
                    p = i;
                else
                    s = i;
            }
        }
    }

    public void RicercaDati(String result) {

        Pattern pa = Pattern.compile("<titolo>(.*?)</titolo>");
        Matcher ma = pa.matcher(result);
        while (ma.find()) {
            titoli.add(ma.group(1));
        }

        pa = Pattern.compile("<descrizionecorta>(.*?)</descrizionecorta>");
        ma = pa.matcher(result);
        while (ma.find()) {
            descrizioniCorte.add(ma.group(1));
        }

        pa = Pattern.compile("<indirizzo>(.*?)</indirizzo>");
        ma = pa.matcher(result);
        while (ma.find()) {
            indirizzi.add(ma.group(1) + " ");
        }


        pa = Pattern.compile("<descrizionelunga>(.*?)</descrizionelunga>");
        ma = pa.matcher(result);
        while (ma.find()) {
            descrizioniLunghe.add(ma.group(1));
        }


        pa = Pattern.compile("<data>(.*?)</data>");
        ma = pa.matcher(result);
        while (ma.find()) {
            date.add(ma.group(1));
        }


        pa = Pattern.compile("<ora>(.*?)</ora>");
        ma = pa.matcher(result);
        while (ma.find()) {
            ore.add(ma.group(1));
        }


        pa = Pattern.compile("<sconto>(.*?)</sconto>");
        ma = pa.matcher(result);
        while (ma.find()) {
            sconti.add(ma.group(1));
        }

        pa = Pattern.compile("<immagine>(.*?)</immagine>");
        ma = pa.matcher(result);
        while (ma.find()) {
            urls.add(ma.group(1));
        }
    }

    public class DowloadinTaskSurce extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(strings[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "DOWLOADING FAIL";
            }


        }
    }
}