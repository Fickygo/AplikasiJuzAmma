package com.example.AplikasiJuzAmma;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pengenalantanamanobat.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailNama;
    TextView tvDetailDeskripsi;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailNama = findViewById(R.id.tvDetailNama);
        tvDetailDeskripsi =findViewById(R.id.tvDetailDeskripsi);
        toolbar = findViewById(R.id.toolbar);

        tvDetailNama.setText(getIntent().getStringExtra("nama"));
        tvDetailDeskripsi.setText(getIntent().getStringExtra("deskripsi"));

        toolbar.setOnClickListener(view -> finish());
    }
}
