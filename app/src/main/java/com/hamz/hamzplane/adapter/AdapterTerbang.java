package com.hamz.hamzplane.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hamz.hamzplane.DaftarTerbang;
import com.hamz.hamzplane.R;
import com.hamz.hamzplane.RingkasanPemesanan;
import com.hamz.hamzplane.api.ApiClient;
import com.hamz.hamzplane.api.ApiInterface;
import com.hamz.hamzplane.model.GetRingkas;
import com.hamz.hamzplane.model.Penerbangan;
import com.hamz.hamzplane.model.Ringkas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hamz on 15/12/2017.
 */

public class AdapterTerbang extends RecyclerView.Adapter<AdapterTerbang.ViewHolder> {

    private Context context;
    private ArrayList<Penerbangan>penerbangan;
    ApiInterface mApiInterface;
    private ProgressDialog dialog;

    public AdapterTerbang(Context context, ArrayList<Penerbangan>penerbangan){
        this.context = context;
        this.penerbangan = penerbangan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.terbang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvKapal.setText(penerbangan.get(position).getKapal());
        holder.tvJam.setText(penerbangan.get(position).getJam()+"-"+penerbangan.get(position).getKeluar());
        holder.tvPesawat.setText(penerbangan.get(position).getPesawat());
        holder.tvLangsung.setText(penerbangan.get(position).getLangsung());
        holder.tvHarga.setText("Rp "+penerbangan.get(position).getHarga()+"/org");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiInterface = new ApiClient().getClient().create(ApiInterface.class);
                dialog = ProgressDialog.show(context, null, "Loading...", true, false);

                mApiInterface.getRowTerbang(penerbangan.get(position).getIdPenerbangan()).enqueue(new Callback<GetRingkas>() {
                    @Override
                    public void onResponse(Call<GetRingkas> call, Response<GetRingkas> response) {
                        dialog.dismiss();
                        List<Ringkas>ringkasList = response.body().getRingkases();
                        Toast.makeText(context, ringkasList.get(0).getMaskapai().toString(), Toast.LENGTH_SHORT).show();
                        Intent ringkas = new Intent(DaftarTerbang.dt.getApplicationContext(), RingkasanPemesanan.class);
//                        ringkas.putExtra("kotaAsal", "BETA");
//                        ringkas.putExtra("kotaTujuan", "BETA");
                        ringkas.putExtra("id_penerbangan", ringkasList.get(0).getId_penerbangan());
                        ringkas.putExtra("tanggal", ringkasList.get(0).getTanggal());
                        ringkas.putExtra("maskapai", ringkasList.get(0).getMaskapai());
                        ringkas.putExtra("kodeAsal", ringkasList.get(0).getKodeAsal());
                        ringkas.putExtra("kodeTujuan", ringkasList.get(0).getKodeTujuan());
                        ringkas.putExtra("pesawat", ringkasList.get(0).getPesawat());
                        ringkas.putExtra("hargaAnak", ringkasList.get(0).getHargaAnak());
                        ringkas.putExtra("hargaDewasa", ringkasList.get(0).getHargaDewasa());
                        DaftarTerbang.dt.startActivity(ringkas);
                    }

                    @Override
                    public void onFailure(Call<GetRingkas> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return penerbangan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKapal, tvJam, tvPesawat, tvLangsung, tvHarga;
        private RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            tvKapal = (TextView) itemView.findViewById(R.id.tvKapal);
            tvJam = (TextView)itemView.findViewById(R.id.tvJam);
            tvPesawat = (TextView)itemView.findViewById(R.id.tvPesawat);
            tvLangsung = (TextView)itemView.findViewById(R.id.tvLangsung);
            tvHarga = (TextView)itemView.findViewById(R.id.tvHarga);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}
