package com.example.loginsqlliteass;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class CreateActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper database;
    Button btn_simpan;
    EditText nama,kampus;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Inisialisasi toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Tampilkan tombol panah kembali
        getSupportActionBar().setTitle("Tambah Data");

        // Inisialisasi database dan view
        database = new DatabaseHelper(this);
        nama = findViewById(R.id.nama);
        kampus = findViewById(R.id.kampus);
        btn_simpan = findViewById(R.id.btn_simpan);

        // Set listener untuk tombol simpan
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidInput()) {
                    SQLiteDatabase db = database.getWritableDatabase();
                    db.execSQL("INSERT INTO mahasiswa(nama,kampus) values('" +
                            nama.getText().toString() + "','" +
                            kampus.getText().toString() + "')");
                    Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    MainActivity.ma.RefreshList();
                    finish();
                } else {
                    Toast.makeText(CreateActivity.this, "Data Harus Lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method untuk validasi input
    private boolean isValidInput() {
        return !TextUtils.isEmpty(nama.getText().toString()) && !TextUtils.isEmpty(kampus.getText().toString());
    }

    // Method yang dipanggil ketika tombol panah kembali ditekan
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // Periksa apakah yang ditekan adalah tombol panah kembali
            onBackPressed(); // Panggil metode onBackPressed() untuk kembali ke activity sebelumnya
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
