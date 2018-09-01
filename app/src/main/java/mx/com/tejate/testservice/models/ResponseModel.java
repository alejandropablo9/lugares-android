package mx.com.tejate.testservice.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;

public class ResponseModel {

    private int status;
    private String message;
    private ArrayList<Place> data;

    public ResponseModel(int status, String message, ArrayList<Place> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Place> getData() {
        return data;
    }

    public void setData(ArrayList<Place> data) {
        this.data = data;
    }

    public static ResponseModel fromResponseBody(ResponseBody responseBody) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(responseBody.string(), ResponseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
