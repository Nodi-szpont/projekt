package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnKolkoKrzyzyk, btnPapierKamien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKolkoKrzyzyk = findViewById(R.id.btnKolkoKrzyzyk);
        btnPapierKamien = findViewById(R.id.btnPapierKamien);

        // Stary sposob - bez lambd
        btnKolkoKrzyzyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KolkoKrzyzykActivity.class);
                startActivity(intent);
            }
        });

        btnPapierKamien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PapierKamienNozyce.class);
                startActivity(intent);
            }
        });
    }
}