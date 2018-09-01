package mx.com.tejate.testservice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.com.tejate.testservice.Config.Config;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private static MapaFragment _instance;
    private GoogleMap gMap;
    private MapView mapView;

    private View view;
    private String title;
    private double lat;
    private double lon;

    public static MapaFragment instace() {
        if (_instance == null) {
            _instance = new MapaFragment();
        }
        return _instance;
    }

    public MapaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mapa, container, false);

        title = getArguments().getString(Config.EXTRA_TITLE);
        lat = getArguments().getDouble(Config.EXTRA_LAT);
        lon = getArguments().getDouble(Config.EXTRA_LON);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.map);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng cali = new LatLng(lat, lon);
        gMap.addMarker(new MarkerOptions()
                .position(cali)
                .title(title));

        gMap.setMinZoomPreference(8);
        gMap.setMaxZoomPreference(21);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(cali)
                .zoom(18)
                .bearing(0)
                .tilt(30)
                .build();

        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

}
