package com.example.looting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class TemuanAnda extends AppCompatActivity {

    GridView gridTemuan;
    ArrayList<Barang> list;
    BarangItemAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temuan_anda);

        gridTemuan = (GridView) findViewById(R.id.gridTemuan);
        list = new ArrayList<>();
        adapter = new BarangItemAdapter(this, R.layout.barang_items, list);
        gridTemuan.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = TambahBarang.dataBaseHelper.getData("SELECT * FROM BARANG");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String namabarang = cursor.getString(1);
            String deskbarang = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Barang(namabarang, deskbarang, image, id));
        }
        adapter.notifyDataSetChanged();

        gridTemuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(TemuanAnda.this);

                dialog.setTitle("Pilih setelan");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = TambahBarang.dataBaseHelper.getData("SELECT id FROM BARANG");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(TemuanAnda.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = TambahBarang.dataBaseHelper.getData("SELECT id FROM BARANG");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    ImageView gambarbarangBarang;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_barang);
        dialog.setTitle("Update");

        gambarbarangBarang = (ImageView) dialog.findViewById(R.id.gambarbarangBarang);
        final EditText edtNamabarang = (EditText) dialog.findViewById(R.id.edtNamabarang);
        final EditText edtDeskbarang = (EditText) dialog.findViewById(R.id.edtDeskbarang);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        //set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        gambarbarangBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request photo library
                ActivityCompat.requestPermissions(
                        TemuanAnda.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TambahBarang.dataBaseHelper.updateData(
                            edtNamabarang.getText().toString().trim(),
                            edtDeskbarang.getText().toString().trim(),
                            TambahBarang.gambarBarangToByte(gambarbarangBarang),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Berhasil diupdate",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Gagal Update", error.getMessage());
                }
                updateTemuanAnda();
            }
        });
    }

    private void showDialogDelete(final int idBarang){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(TemuanAnda.this);

        dialogDelete.setTitle("Perhatian!");
        dialogDelete.setMessage("Apa kamu yakin ingin menghapus barang ini?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    TambahBarang.dataBaseHelper.deleteData(idBarang);
                    Toast.makeText(getApplicationContext(), "Berhasil dihapus",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateTemuanAnda();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateTemuanAnda(){
        // get all data from sqlite
        Cursor cursor = TambahBarang.dataBaseHelper.getData("SELECT * FROM BARANG");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String namabarang = cursor.getString(1);
            String deskbarang = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Barang(namabarang, deskbarang, image, id));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
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

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                gambarbarangBarang.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}