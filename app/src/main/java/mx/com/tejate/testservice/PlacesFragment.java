package mx.com.tejate.testservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.tejate.testservice.Config.Config;
import mx.com.tejate.testservice.Config.Resources;
import mx.com.tejate.testservice.Config.Util;
import mx.com.tejate.testservice.models.Place;
import mx.com.tejate.testservice.models.ResponseModel;
import mx.com.tejate.testservice.service.Repository;
import mx.com.tejate.testservice.service.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesFragment extends Fragment {

    private ListView mPlacesList;
    private PlacesAdapter mPlacesAdapter;
    private ArrayList<Place> placesList;
    private SwipeRefreshLayout swipeRefreshLayout;
    // service
    private Retrofit mRestAdapter;
    private Request request;

    private ProgressBar progressBar;

    public PlacesFragment() {
    }

    public static PlacesFragment instace() {
        return  new PlacesFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        placesList = new ArrayList<>();
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(Config.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = mRestAdapter.create(Request.class);


        mPlacesList =  view.findViewById(R.id.places_list);
        progressBar = view.findViewById(R.id.pb_loading);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRequest();
            }
        });


        mPlacesAdapter = new PlacesAdapter(getActivity(), placesList);

        mPlacesList.setAdapter(mPlacesAdapter);

        mPlacesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Place currentPlace = mPlacesAdapter.getItem(position);

                Intent intent = new Intent(getActivity(), DetalleActivity.class);

                intent.putExtra(Config.EXTRA_LON, currentPlace.getAnchor().getGeolocation().getLon());
                intent.putExtra(Config.EXTRA_LAT, currentPlace.getAnchor().getGeolocation().getLat());
                intent.putExtra(Config.EXTRA_TITLE, currentPlace.getTitle());
                startActivity(intent);
            }
        });

        initRequest();

        return view;
    }


    private void initRequest() {
        swipeRefreshLayout.setRefreshing(true);
        Call<ResponseModel> getPlaces = request.getPlaces();
        getPlaces.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if( response.errorBody() != null ) {
                    if( response.code() == 400 ) {
                        ResponseModel respuesta = ResponseModel.fromResponseBody(response.errorBody());
                        Util.showMessage(response.message(), getContext());
                        loading(false);
                    }
                } else {
                    ResponseModel respuesta = response.body();

                    try {

                        if(respuesta != null) {
                            mPlacesAdapter.clear();
                            mPlacesAdapter.addAll(respuesta.getData());
                            mPlacesAdapter.notifyDataSetChanged();
                            Util.showMessage(response.message(), getContext());
                            progressBar.setVisibility(View.VISIBLE);

                        }
                        loading(false);
                    } catch ( Exception e ) {
                        Util.showMessage(Resources.getString(getContext(), R.string.error_interno), getContext());
                        loading(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Util.showMessage(getString(R.string.error_peticion_servidor), getContext());
                loading(false);
            }
        });


    }

    private void loading(boolean loading) {
        progressBar.setVisibility( loading? View.VISIBLE : View.GONE);
        mPlacesList.setVisibility( loading? View.GONE : View.VISIBLE);
        swipeRefreshLayout.setRefreshing(loading);
    }

}
