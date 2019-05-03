package com.hamz.hamzplane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hamz.hamzplane.api.ApiClient;
import com.hamz.hamzplane.api.ApiInterface;
import com.hamz.hamzplane.model.Ringkas;

public class RingkasanPemesanan extends AppCompatActivity {

    private Toolbar toolbar;
    private Integer id_penerbangan;
    ApiInterface mApiInterface;
    private Button btnPilih;
    public String  tanggal="27", maskapai="Batik Cina", kodeAsal="BDO", kodeTujuan="TJO", pesawat="sukhoi";
    private TextView tvKotaAsal, tvKotaTujuan, tvTanggal, tvMaskapai, tvKodeAsal, tvKodeTujuan, tvPesawat;
    public static RingkasanPemesanan rp;
    public static String kotaAsal, kotaTujuan;

    //Harga
    private Integer hargaAnak, hargaDewasa;
    public static Integer jmlAnak, jmlDewasa;
    private TextView tvHargaA, tvHargaD, tvDewasa, tvAnak, tvTotalHarga, tvPax;

    private Integer total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringkasan_pemesanan);

        rp = this;

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.ringkasanPemesanan);

        id_penerbangan = getIntent().getIntExtra("id_penerbangan", 0);

        mApiInterface = new ApiClient().getClient().create(ApiInterface.class);

        initComponent();
        initHarga();
    }

    private void initHarga() {
        hargaAnak = getIntent().getIntExtra("hargaAnak", 0) * jmlAnak;
        hargaDewasa = getIntent().getIntExtra("hargaDewasa", 0) * jmlDewasa;

        tvAnak = (TextView)findViewById(R.id.tvAnak);
        tvDewasa = (TextView)findViewById(R.id.tvDewasa);
        tvHargaA = (TextView)findViewById(R.id.tvHargaA);
        tvHargaD = (TextView)findViewById(R.id.tvHargaD);
        tvTotalHarga = (TextView)findViewById(R.id.tvTotalharga);
        tvPax = (TextView)findViewById(R.id.tvPax);

        tvAnak.setText("Anak("+jmlAnak+")");
        tvDewasa.setText("Dewasa("+jmlDewasa+")");

        tvHargaA.setText(String.valueOf(hargaAnak));
        tvHargaD.setText(String.valueOf(hargaDewasa));

        total = hargaAnak + hargaDewasa;
        tvTotalHarga.setText("Rp "+total);
        tvPax.setText("Total harga untuk "+(jmlAnak+jmlDewasa)+" pax");
    }

    private void initComponent() {
        tvKotaAsal = (TextView)findViewById(R.id.tvKotaAsal);
        tvKotaTujuan = (TextView)findViewById(R.id.tvKotaTujuan);
        tvTanggal = (TextView)findViewById(R.id.tvTanggal);
        tvMaskapai = (TextView)findViewById(R.id.tvMaskapai);
        tvKodeAsal = (TextView)findViewById(R.id.tvKodeAsal);
        tvKodeTujuan = (TextView)findViewById(R.id.tvKodeTujuan);
        tvPesawat = (TextView)findViewById(R.id.tvPesawat);

//        kotaAsal = getIntent().getStringExtra("kotaAsal");
//        kotaTujuan = getIntent().getStringExtra("kotaTujuan");
        tanggal = getIntent().getStringExtra("tanggal");
        maskapai = getIntent().getStringExtra("maskapai");
        kodeAsal = getIntent().getStringExtra("kodeAsal");
        kodeTujuan = getIntent().getStringExtra("kodeTujuan");
        pesawat = getIntent().getStringExtra("pesawat");

        Ringkas ringkas = new Ringkas();
        tvKotaAsal.setText(kotaAsal);
        tvKotaTujuan.setText(kotaTujuan);
        tvTanggal.setText(tanggal);
        tvMaskapai.setText(maskapai);
        tvKodeAsal.setText(kodeAsal);
        tvKodeTujuan.setText(kodeTujuan);
        tvPesawat.setText(pesawat);

        btnPilih = (Button)findViewById(R.id.btnPilih);
        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pilih = new Intent(RingkasanPemesanan.this, DataPesan.class);
                pilih.putExtra("tiketAnak", jmlAnak);
                pilih.putExtra("tiketDewasa", jmlDewasa);
                pilih.putExtra("total", total);
                pilih.putExtra("id_penerbangan", getIntent().getIntExtra("id_penerbangan", 0));
                startActivity(pilih);
            }
        });
    }

}
