package mx.com.tejate.testservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.com.tejate.testservice.Config.Config;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(Config.EXTRA_TITLE);
        double lat = bundle.getDouble(Config.EXTRA_LAT);
        double lon = bundle.getDouble(Config.EXTRA_LON);

        Bundle args = new Bundle();
        args.putString(Config.EXTRA_TITLE, title);
        args.putDouble(Config.EXTRA_LON, lon);
        args.putDouble(Config.EXTRA_LAT, lat);

        MapaFragment mapaFragment = (MapaFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa_container);

        if (mapaFragment == null) {
            mapaFragment = MapaFragment.instace();
            mapaFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mapa_container, mapaFragment)
                    .commit();
        }


    }

}
