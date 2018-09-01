package mx.com.tejate.testservice.service;

import mx.com.tejate.testservice.models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Request {

    @GET("/wa/test/servicio.php")
    Call<ResponseModel> getPlaces();
}
