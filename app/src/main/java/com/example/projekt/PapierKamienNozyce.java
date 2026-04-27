package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class PapierKamienNozyce extends AppCompatActivity {

    TextView tvWynikGracz, tvWynikKomputer, tvRezultat, tvPunkty;
    int punktyGracz = 0;
    int punktyKomputer = 0;

    // Dostepne opcje dla komputera
    String[] opcje = {"Kamien", "Papier", "Nozyce"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papier_kamien_nozyce);

        tvWynikGracz    = findViewById(R.id.tvWynikGracz);
        tvWynikKomputer = findViewById(R.id.tvWynikKomputer);
        tvRezultat      = findViewById(R.id.tvRezultat);
        tvPunkty        = findViewById(R.id.tvPunkty);

        Button btnKamien = findViewById(R.id.btnKamien);
        Button btnPapier = findViewById(R.id.btnPapier);
        Button btnNozyce = findViewById(R.id.btnNozyce);
        Button btnWroc   = findViewById(R.id.btnWrocPKN);

        // Stary sposob - osobny klikacz dla kazdego przycisku
        btnKamien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zagraj("Kamien");
            }
        });

        btnPapier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zagraj("Papier");
            }
        });

        btnNozyce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zagraj("Nozyce");
            }
        });

        btnWroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // wraca do menu
            }
        });
    }

    // Glowna metoda gry
    void zagraj(String wyborGracza) {
        // Losujemy wybor komputera
        Random random = new Random();
        String wyborKomputera = opcje[random.nextInt(3)];

        // Pokazujemy wybory na ekranie
        tvWynikGracz.setText("Ty: " + wyborGracza);
        tvWynikKomputer.setText("Komputer: " + wyborKomputera);

        // Sprawdzamy wynik i pokazujemy komunikat
        String wynik = sprawdzWyniku(wyborGracza, wyborKomputera);
        tvRezultat.setText(wynik);

        // Aktualizujemy wynik punktowy
        tvPunkty.setText("Wynik: " + punktyGracz + " - " + punktyKomputer);
    }

    // Porownuje wybory i zwraca wynik jako tekst
    String sprawdzWyniku(String gracz, String komputer) {
        // Jesli obaj wybrali to samo - remis
        if (gracz.equals(komputer)) {
            return "Remis!";
        }

        // Sprawdzamy czy gracz wygral
        // Kamien bije Nozyce
        // Nozyce bija Papier
        // Papier bije Kamien
        boolean graczWygral =
                (gracz.equals("Kamien") && komputer.equals("Nozyce")) ||
                        (gracz.equals("Nozyce") && komputer.equals("Papier")) ||
                        (gracz.equals("Papier") && komputer.equals("Kamien"));

        if (graczWygral) {
            punktyGracz++;
            return "Wygrales!";
        } else {
            punktyKomputer++;
            return "Przegrales...";
        }
    }
}