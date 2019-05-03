package com.hamz.hamzplane.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hamz.hamzplane.R;

/**
 * Created by Hamz on 14/12/2017.
 */

public class CustomSearch extends ArrayAdapter {

    private final Activity context;
    private final String[][] bandara;

    public CustomSearch(Activity context, String[][] bandara) {
        super(context, R.layout.bandara, bandara);
        this.context = context;
        this.bandara = bandara;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.bandara, null, true);

        TextView tvKd_bandara = (TextView)rowView.findViewById(R.id.tvKd_bandara);
        tvKd_bandara.setText(bandara[position][0]);

        TextView tvtLokasi = (TextView)rowView.findViewById(R.id.tvLokasi);
        tvtLokasi.setText(bandara[position][1]);

        TextView tvNama = (TextView)rowView.findViewById(R.id.tvNama);
        tvNama.setText(" - "+bandara[position][2]);

        return rowView;
    }
}
