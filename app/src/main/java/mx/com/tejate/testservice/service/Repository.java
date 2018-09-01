package mx.com.tejate.testservice.service;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.com.tejate.testservice.Config.Config;
import mx.com.tejate.testservice.models.Place;
import mx.com.tejate.testservice.models.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Repository respository = new Repository();
    private ArrayList<Place> placesList;

    // service
    private Retrofit mRestAdapter;
    private Request request;

    public Repository() {
        placesList = new ArrayList<>();

        mRestAdapter = new Retrofit.Builder()
                .baseUrl(Config.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = mRestAdapter.create(Request.class);

    }

    public void initRequest() {

        Call<ResponseModel> getPlaces = request.getPlaces();
        getPlaces.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if( response.errorBody() != null ) {
                    if( response.code() == 400 ) {
                        ResponseModel respuesta = ResponseModel.fromResponseBody(response.errorBody());

                    }
                } else {
                    ResponseModel respuesta = response.body();

                    try {

                        if(respuesta != null) {
                            placesList.clear();
                            placesList = respuesta.getData();
                        }

                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("Request", "onFailure: " );
            }
        });


    }

    public static Repository instance() {
        return respository;
    }

    public List<Place> getPlacesList() {
        return this.placesList;
    }


}
