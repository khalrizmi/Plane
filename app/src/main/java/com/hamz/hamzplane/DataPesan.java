package com.hamz.hamzplane;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hamz.hamzplane.api.ApiClient;
import com.hamz.hamzplane.api.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPesan extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnSimpan;
    private EditText etNama, etEmail, etTelepon;
    ApiInterface mApiInterface;
    ProgressDialog dialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pesan);

        context = this;

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Data Pesan");

        mApiInterface = new ApiClient().getClient().create(ApiInterface.class);

        initComponent();
    }

    private void initComponent() {
        etNama = (EditText)findViewById(R.id.etNama);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etTelepon = (EditText)findViewById(R.id.etTelepon);
        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(context, null, "Loading...", true, false);
                mApiInterface.simpanPesan(getIntent().getIntExtra("id_penerbangan", 0),
                        etNama.getText().toString(),
                        etEmail.getText().toString(),
                        etTelepon.getText().toString(),
                        getIntent().getIntExtra("tiketAnak", 0),
                        getIntent().getIntExtra("tiketDewasa", 0),
                        getIntent().getIntExtra("total", 0)).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        try {
                            JSONObject jsonRESULT = new JSONObject(response.body().string());
                            if (jsonRESULT.getString("error").equals("false")){
                                Toast.makeText(context, "Silakan cek email \nBeberapa saat Lagi", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(DataPesan.this, MainActivity.class));
                            }else{
                                Toast.makeText(context, "Gagal Menyimpan", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
