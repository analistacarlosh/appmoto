package br.com.chfmr.appmotos.motorcycleCompany.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.chfmr.appmotos.R;

public class motocycleCompanyLocationMaps extends AppCompatActivity {

    GoogleMap mGoogleMap;
    LatLng mOrigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motocycle_company_location_maps);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapsMotocyclerCompany);
        mGoogleMap = fragment.getMap();
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mOrigin = new LatLng(-23.561706, -46.655981);
        updateMaps();
    }

    private void updateMaps(){
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mOrigin, 17.0f));
        mGoogleMap.addMarker(new MarkerOptions()
        .position(mOrigin)
                .title("Av. Paulista")
                .snippet("São Paulo")
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_motocycle_company_location_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
