package com.hamz.hamzplane.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hamz on 17/12/2017.
 */

public class GetRingkas {

    @SerializedName("result")
    List<Ringkas> ringkases;

    public List<Ringkas> getRingkases() {
        return ringkases;
    }

    public void setRingkases(List<Ringkas> ringkases) {
        this.ringkases = ringkases;
    }
}
