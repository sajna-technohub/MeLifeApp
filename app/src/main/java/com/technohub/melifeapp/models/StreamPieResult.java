package com.technohub.melifeapp.models;

import java.util.ArrayList;

public class StreamPieResult {
    public ArrayList<StreamPieDescription> DESCRIPTION_OF_YOUR_STREAMS;
    public ArrayList<StreamPieResult2> result2;

    public ArrayList<StreamPieDescription> getDESCRIPTION_OF_YOUR_STREAMS() {
        return DESCRIPTION_OF_YOUR_STREAMS;
    }

    public void setDESCRIPTION_OF_YOUR_STREAMS(ArrayList<StreamPieDescription> DESCRIPTION_OF_YOUR_STREAMS) {
        this.DESCRIPTION_OF_YOUR_STREAMS = DESCRIPTION_OF_YOUR_STREAMS;
    }

    public ArrayList<StreamPieResult2> getResult2() {
        return result2;
    }

    public void setResult2(ArrayList<StreamPieResult2> result2) {
        this.result2 = result2;
    }

    public ArrayList<StreamPieResult3> getResult3() {
        return result3;
    }

    public void setResult3(ArrayList<StreamPieResult3> result3) {
        this.result3 = result3;
    }

    public ArrayList<StreamPieResult3> result3;

}
