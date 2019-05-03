package com.hamz.hamzplane;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

//import android.icu.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout kotaAsal, kotaTujuan, tanggal;
    private TextView tvLokasi1, tvLokasi2, tvTanggal;
    public String Lokasi1, Kd_bandara1, Nama1;
    public String Lokasi2, Kd_bandara2, Nama2;
    public static MainActivity ma;

    private ImageView btnDewasaPlus, btnDewasaMin, btnAnakPlus, btnAnakMin;
    private TextView tvJmlDewasa, tvJmlAnak;
    private int year_x;
    private int month_x;
    private int day_x;
    private String fixture;
    static final int DIALOG_ID = 0;
    Calendar cal = Calendar.getInstance();
    String[] bulan = new String[]{"","Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

    private Button btnCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma = this;
        Kd_bandara1 = "BDO";
        Lokasi1 = "Bandung";

        Kd_bandara2 = "JOG";
        Lokasi2 = "Yogyakarta";

        tvLokasi1 = (TextView)findViewById(R.id.tvLokasi1);
        tvLokasi2 = (TextView)findViewById(R.id.tvLokasi2);
        tvTanggal = (TextView)findViewById(R.id.tvTanggal);
        tanggal = (RelativeLayout)findViewById(R.id.tanggal);
        kotaAsal = (RelativeLayout)findViewById(R.id.kotaAsal);
        kotaTujuan = (RelativeLayout)findViewById(R.id.kotaTujuan);
        tvJmlDewasa = (TextView)findViewById(R.id.jmlDewasa);
        tvJmlAnak = (TextView)findViewById(R.id.jmlAnak);
        btnCari = (Button)findViewById(R.id.btnCari);

        tvLokasi1.setText(Lokasi1 + " ("+Kd_bandara1+")");
        tvLokasi2.setText(Lokasi2 + " ("+Kd_bandara2+")");

        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH) + 1;
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        tvTanggal.setText(day_x + " " + bulan[month_x] + " " + year_x);

        kotaAsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searcha = new Intent(getApplicationContext(), SearchBandara.class);
                searcha.putExtra("type", 1);
                startActivity(searcha);
            }
        });

        kotaTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searcha = new Intent(getApplicationContext(), SearchBandara.class);
                searcha.putExtra("type", 2);
                startActivity(searcha);
            }
        });

        btnDewasaMin = (ImageView) findViewById(R.id.btnDewasaMin);
        btnDewasaPlus = (ImageView) findViewById(R.id.btnDewasaPlus);
        btnAnakMin = (ImageView) findViewById(R.id.btnAnakMin);
        btnAnakPlus = (ImageView) findViewById(R.id.btnAnakPlus);

        btnDewasaMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer jmlD = Integer.parseInt(tvJmlDewasa.getText().toString());
                if(jmlD == 1){
                    Toast.makeText(getApplicationContext(), "Minimal "+jmlD, Toast.LENGTH_SHORT).show();
                }else{
                    jmlD--;
                    tvJmlDewasa.setText(""+jmlD);
                }
            }
        });
        btnDewasaPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer jmlD = Integer.parseInt(tvJmlDewasa.getText().toString());
                jmlD++;
                tvJmlDewasa.setText(""+jmlD);
            }
        });

        btnAnakMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer jmlA = Integer.parseInt(tvJmlAnak.getText().toString());
                if(jmlA == 0){
                    Toast.makeText(getApplicationContext(), "Sudah "+jmlA, Toast.LENGTH_SHORT).show();
                }else{
                    jmlA--;
                    tvJmlAnak.setText(""+jmlA);
                }
            }
        });
        btnAnakPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer jmlA = Integer.parseInt(tvJmlAnak.getText().toString());
                jmlA++;
                tvJmlAnak.setText(""+jmlA);
            }
        });


        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RingkasanPemesanan.kotaAsal = Lokasi1;
                RingkasanPemesanan.kotaTujuan = Lokasi2;
                RingkasanPemesanan.jmlAnak = Integer.parseInt((String) tvJmlAnak.getText());
                DaftarTerbang.kdAsal = Kd_bandara1;
                DaftarTerbang.kdTujuan = Kd_bandara2;
                DaftarTerbang.lokasiAsal = Lokasi1;
                DaftarTerbang.lokasiTujuan = Lokasi2;
                DaftarTerbang.tanggal = fixture;
                RingkasanPemesanan.jmlDewasa = Integer.parseInt((String) tvJmlDewasa.getText());
                startActivity(new Intent(getApplicationContext(), DaftarTerbang.class));
            }
        });

    }

    protected Dialog onCreateDialog(int id){
        if (id==DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
            fixture = year_x + "-" + month_x + "-" + day_x;
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            tvTanggal.setText(day_x + " " + bulan[month_x] + " " + year_x);
            Toast.makeText(getApplicationContext(), "bulan :"+year+"-"+month_x+"-"+day_x, Toast.LENGTH_SHORT).show();
            fixture=year+"-"+month_x+"-"+day_x;
        }
    };


    public void Toast(Integer i){
        if(i == 1){
            tvLokasi1 = (TextView)findViewById(R.id.tvLokasi1);
            tvLokasi1.setText(Lokasi1 + " ("+Kd_bandara1+")");
            Toast.makeText(getApplicationContext(), Lokasi1+" ("+Kd_bandara1+")", Toast.LENGTH_SHORT).show();
        }else if(i == 2){
            tvLokasi2 = (TextView)findViewById(R.id.tvLokasi2);
            tvLokasi2.setText(Lokasi2 + " ("+Kd_bandara2+")");
            Toast.makeText(getApplicationContext(), Lokasi2+" ("+Kd_bandara2+")", Toast.LENGTH_SHORT).show();
        }
    }
}
