package com.example.looting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Pencarian extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pencarian);

        //Halaman Semua Barang
        Button cari = (Button) findViewById(R.id.btn_cari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent semuabarangIntent = new Intent(Pencarian.this, SemuaBarang.class);
                startActivity(semuabarangIntent);
            }
        });

        //Initialize And Assign Variable
        bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Beranda Selected
        bottomNavigationView.setSelectedItemId(R.id.menu_cari);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext()
                                ,Beranda.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_cari:
                        return true;

                    case R.id.menu_user:
                        startActivity(new Intent(getApplicationContext()
                                ,Profil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        mySearchView = (SearchView)findViewById(R.id.cari_barang);
        myList = (ListView)findViewById(R.id.baranglist);

        list=new ArrayList<String>();

        list.add("Dompet");
        list.add("Tas");
        list.add("Kartu");
        list.add("Handphone");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

        });
    }
}






