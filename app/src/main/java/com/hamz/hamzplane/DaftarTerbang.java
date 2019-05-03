package com.hamz.hamzplane;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.hamz.hamzplane.adapter.AdapterTerbang;
import com.hamz.hamzplane.api.ApiClient;
import com.hamz.hamzplane.api.ApiInterface;
import com.hamz.hamzplane.model.GetPenerbangan;
import com.hamz.hamzplane.model.Penerbangan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarTerbang extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    ProgressDialog dialog;
    ApiInterface mApiInterface;
    Context context;
    public static DaftarTerbang dt;
    public static String kdAsal, kdTujuan, lokasiAsal, lokasiTujuan, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarterbang);

        dt = this;
        context = this;
        mApiInterface = new ApiClient().getClient().create(ApiInterface.class);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(lokasiAsal + " - " + lokasiTujuan);

        Refresh();


        recyclerView = (RecyclerView)findViewById(R.id.rvMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void Refresh() {
        dialog = ProgressDialog.show(context, null, "Loading...", true, false);
        mApiInterface.getPenerbangan(kdAsal, kdTujuan, tanggal).enqueue(new Callback<GetPenerbangan>() {
            @Override
            public void onResponse(Call<GetPenerbangan> call, Response<GetPenerbangan> response) {
                if (response.isSuccessful()){
                    dialog.dismiss();
                    ArrayList<Penerbangan> list = response.body().getListPenerbangan();
                    recyclerView.setAdapter(new AdapterTerbang(context, list));
                }else{
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPenerbangan> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
