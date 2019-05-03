package com.hamz.hamzplane.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hamz on 15/12/2017.
 */

public class GetPenerbangan {

    @SerializedName("result")
    ArrayList<Penerbangan> listPenerbangan;

    public ArrayList<Penerbangan> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(ArrayList<Penerbangan> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }
}
