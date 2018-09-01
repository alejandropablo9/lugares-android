package mx.com.tejate.testservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlacesFragment placesFragment = (PlacesFragment)
                getSupportFragmentManager().findFragmentById(R.id.main_container);

        if (placesFragment == null) {
            placesFragment = PlacesFragment.instace();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, placesFragment)
                    .commit();
        }

    }
}
