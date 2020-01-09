package com.technohub.melifeapp.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonModel {
    String errorBody;
    List<ErrorModel> errorList;

    public JsonModel(String errorBody) {
        this.errorBody = errorBody;
        errorList = new ArrayList<>();
    }

    public List<ErrorModel> getErrorList() {

        try {
            JSONObject jsonObject = new JSONObject(errorBody);
            JSONArray jsonArray = jsonObject.getJSONArray("errors");
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject error = jsonArray.getJSONObject(i);
                ErrorModel errorModel = new ErrorModel();
                errorModel.setMessage(error.get("msg").toString());
                errorModel.setParam(error.get("param").toString());
                errorModel.setLocation(error.get("location").toString());
                errorList.add(errorModel);
            }
        } catch (Exception e) {}

        return errorList;
    }
}
