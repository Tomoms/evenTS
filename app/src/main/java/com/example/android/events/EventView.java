package com.example.android.events;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class EventView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titolo_grande = findViewById(R.id.titolo_grande);
        TextView indirizzo_grande = findViewById(R.id.indirizzo_grande);
        TextView data_grande = findViewById(R.id.data_grande);
        TextView ora_grande = findViewById(R.id.ora_grande);
        TextView sconto_grande = findViewById(R.id.sconto_grande);
        TextView descrizione = findViewById(R.id.descrizione);
        ImageView img = findViewById(R.id.big_image);
        Button button = findViewById(R.id.prenota);
        final Evento mEvento = (Evento) getIntent().getSerializableExtra("evento");

        titolo_grande.setText(mEvento.getTitolo());
        indirizzo_grande.setText(mEvento.getLuogo());
        data_grande.setText(mEvento.getData());
        ora_grande.setText(mEvento.getOra());
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(mEvento.getUrl()).into(img);
        img.setVisibility(View.VISIBLE);
        sconto_grande.setText(mEvento.getSconto() + "%");
        int s = Integer.parseInt(mEvento.getSconto());
        if (s == 0) {
            sconto_grande.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
        }
        descrizione.setText(mEvento.getDescrizione());

        indirizzo_grande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "geo:0,0?q=" + mEvento.getLuogo();
                uri.replaceAll(" ", "+");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getBaseContext().startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Prenotato.class);
                getBaseContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;    }
}
