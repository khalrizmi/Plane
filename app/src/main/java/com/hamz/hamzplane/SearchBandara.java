package com.hamz.hamzplane;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.hamz.hamzplane.adapter.CustomSearch;

public class SearchBandara extends AppCompatActivity {

    private String[][] bandara;
    private ListView listView;
    private Toolbar toolbar;
    private CustomSearch adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bandara);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bandara = new String[][]{
                {"BTH", "Batam", "Internasional Hang Nadim"},
                {"BTJ", "Banda Aceh", "Sultan Iskandar Muda"},
                {"KNO", "Deli Serdang", "Kuala Namu"},
                {"BTJ", "Banda Aceh", "Sultan Iskandar Muda"},

                {"BDO", "Bandung", "Husein Sastranegara"},
                {"CGK", "Tanggerang", "Soekarno-Hatta"},
                {"JOG", "Yogyakarta", "Adi Sucipto"}
        };


        listView = (ListView)findViewById(R.id.listview);
        adapter = new CustomSearch(this, bandara);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(getIntent().getIntExtra("type",0)==1){
                        MainActivity.ma.Lokasi1 = String.valueOf(bandara[position][1]);
                        MainActivity.ma.Kd_bandara1 = String.valueOf(bandara[position][0]);
                        MainActivity.ma.Toast(1);
                    }else if(getIntent().getIntExtra("type",0)==2){
                        MainActivity.ma.Lokasi2 = String.valueOf(bandara[position][1]);
                        MainActivity.ma.Kd_bandara2 = String.valueOf(bandara[position][0]);
                        MainActivity.ma.Toast(2);
                    }
                    finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
