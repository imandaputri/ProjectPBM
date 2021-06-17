package com.example.looting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TambahBarang extends AppCompatActivity {

    EditText edtNamabarang, edtDeskbarang;
    Button btnTambah, btnAddgambar;
    ImageView gambarBarang;

    final int REQUEST_CODE_GALLERY = 999;

    public static DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_barang);

        init();

        dataBaseHelper = new DataBaseHelper(this, "BarangDB.sqlite", null, 1);

        dataBaseHelper.queryData("CREATE TABLE IF NOT EXISTS BARANG(Id INTEGER PRIMARY KEY AUTOINCREMENT, namabarang VARCHAR, deskbarang VARCHAR, image BLOB)");

        btnAddgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        TambahBarang.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    dataBaseHelper.insertData(
                            edtNamabarang.getText().toString().trim(),
                            edtDeskbarang.getText().toString().trim(),
                            gambarBarangToByte(gambarBarang)
                    );
                    Toast.makeText(getApplicationContext(), "Berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                    edtNamabarang.setText("");
                    edtDeskbarang.setText("");
                    gambarBarang.setImageResource(R.drawable.kamera);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(TambahBarang.this, SemuaBarang.class);
                startActivity(intent);
            }
        });
    }


    public static byte[] gambarBarangToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "Kamu tidak memiliki izin mengakses file!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                gambarBarang.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtNamabarang = (EditText)findViewById(R.id.edtNamabarang);
        edtDeskbarang = (EditText)findViewById(R.id.edtDeskbarang);
        btnTambah = (Button)findViewById(R.id.btn_tambah);
        btnAddgambar = (Button)findViewById(R.id.btn_addgambar);
        gambarBarang = (ImageView) findViewById(R.id.gambarBarang);
    }
}