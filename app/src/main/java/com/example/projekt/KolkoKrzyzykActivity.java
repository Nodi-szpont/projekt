package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KolkoKrzyzykActivity extends AppCompatActivity {

    Button[] pola = new Button[9];
    String[] planszaDane = new String[9];
    boolean turaPierwszegoGracza = true;
    boolean koniecGry = false;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolko_krzyzyk);

        tvStatus = findViewById(R.id.tvStatus);

        // Pobieramy wszystkie 9 przyciskow
        pola[0] = findViewById(R.id.b0);
        pola[1] = findViewById(R.id.b1);
        pola[2] = findViewById(R.id.b2);
        pola[3] = findViewById(R.id.b3);
        pola[4] = findViewById(R.id.b4);
        pola[5] = findViewById(R.id.b5);
        pola[6] = findViewById(R.id.b6);
        pola[7] = findViewById(R.id.b7);
        pola[8] = findViewById(R.id.b8);

        // Ustawiamy puste dane i klikacz dla kazdego pola
        for (int i = 0; i < 9; i++) {
            planszaDane[i] = "";
        }

        // Ustawiamy klikacze osobno dla kazdego pola - stary sposob
        pola[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(0); }
        });
        pola[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(1); }
        });
        pola[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(2); }
        });
        pola[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(3); }
        });
        pola[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(4); }
        });
        pola[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(5); }
        });
        pola[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(6); }
        });
        pola[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(7); }
        });
        pola[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { kliknietoPole(8); }
        });

        // Przycisk restart
        Button btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowaGra();
            }
        });

        // Przycisk wróc do menu
        Button btnWroc = findViewById(R.id.btnWrocKK);
        btnWroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // zamyka aktywnosc, wraca do menu
            }
        });
    }

    // Wywoływana po kliknieciu w pole planszy
    void kliknietoPole(int index) {
        // Jesli gra skonczona lub pole zajete - nic nie rob
        if (koniecGry || !planszaDane[index].equals("")) {
            return;
        }

        // Wpisz symbol aktualnego gracza
        if (turaPierwszegoGracza) {
            planszaDane[index] = "X";
            pola[index].setText("X");
        } else {
            planszaDane[index] = "O";
            pola[index].setText("O");
        }

        // Sprawdz czy ktos wygral
        if (sprawdzWygrana()) {
            String zwyciezca = turaPierwszegoGracza ? "X" : "O";
            tvStatus.setText("Wygral gracz: " + zwyciezca);
            koniecGry = true;
            return;
        }

        // Sprawdz czy remis
        if (sprawdzRemis()) {
            tvStatus.setText("Remis!");
            koniecGry = true;
            return;
        }

        // Zmien ture na drugiego gracza
        turaPierwszegoGracza = !turaPierwszegoGracza;
        String nastepny = turaPierwszegoGracza ? "X" : "O";
        tvStatus.setText("Tura gracza: " + nastepny);
    }

    // Sprawdza wszystkie mozliwe linie wygranej
    boolean sprawdzWygrana() {
        int[][] linie = {
                {0,1,2}, {3,4,5}, {6,7,8}, // poziome
                {0,3,6}, {1,4,7}, {2,5,8}, // pionowe
                {0,4,8}, {2,4,6}            // ukosne
        };

        for (int i = 0; i < linie.length; i++) {
            String a = planszaDane[linie[i][0]];
            String b = planszaDane[linie[i][1]];
            String c = planszaDane[linie[i][2]];

            // Trzy takie same symbole i nie puste = wygrana
            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                return true;
            }
        }
        return false;
    }

    // Sprawdza czy wszystkie pola sa zajete
    boolean sprawdzRemis() {
        for (int i = 0; i < 9; i++) {
            if (planszaDane[i].equals("")) {
                return false; // jest puste pole wiec nie remis
            }
        }
        return true;
    }

    // Resetuje gre od nowa
    void nowaGra() {
        for (int i = 0; i < 9; i++) {
            planszaDane[i] = "";
            pola[i].setText("");
        }
        turaPierwszegoGracza = true;
        koniecGry = false;
        tvStatus.setText("Tura gracza: X");
    }
}