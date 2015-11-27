package it.poliba.studentlife;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class OSMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osm);
        MyLocationNewOverlay mLocationOverlay;


        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        mapController.setZoom(18);
        GeoPoint startPoint = new GeoPoint(41.10867,16.87886);
        mapController.setCenter(startPoint);

        mLocationOverlay = new MyLocationNewOverlay(getApplicationContext(), new GpsMyLocationProvider(getApplicationContext()),map);
        map.getOverlays().add(mLocationOverlay);
    }

}
