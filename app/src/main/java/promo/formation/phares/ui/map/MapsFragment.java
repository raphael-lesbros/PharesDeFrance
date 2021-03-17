package promo.formation.phares.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import promo.formation.phares.MainActivity;
import promo.formation.phares.R;
import promo.formation.phares.lighthouse.LighthouseContent;

public class MapsFragment extends Fragment {

    private List<LighthouseContent.LighthouseItem> lighthouses;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            lighthouses = LighthouseContent.ITEMS;
            for (LighthouseContent.LighthouseItem lighthouse : lighthouses) {
                LatLng location = new LatLng(lighthouse.lat, lighthouse.lon);
                googleMap.addMarker(new MarkerOptions().position(location).title(lighthouse.nom));
                CircleOptions circleOptions = new CircleOptions().center(location).fillColor(Color.parseColor("#80FFFFE0"))
                        .strokeWidth(0).radius(lighthouse.portee*1609);
                Circle circle = googleMap.addCircle(circleOptions);
            }
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    LighthouseContent.LighthouseItem item = null;
                    for (LighthouseContent.LighthouseItem lighthouse : lighthouses) {
                        if (lighthouse.nom.equals(marker.getTitle())) {
                            item = lighthouse;
                            break;
                        }
                    }
                    if (item != null) {
                        Context context = MainActivity.getContext();
                        Resources resources = context.getResources();
                        CharSequence txt =
                                (item.nom == null ? "" : resources.getString(R.string.lighthouse_name) + " : " + item.nom) +
                                        (item.region == null ? "" : "\n" + resources.getString(R.string.lighthouse_region) + " : " + item.region) +
                                        (item.construction == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_construction) + " : " + item.construction) +
                                        (item.hauteur == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_height ) + " : " + item.hauteur) +
                                        (item.caractere == null ? "" : "\n" + resources.getString(R.string.lighthouse_type) + " : " + item.caractere) +
                                        (item.eclat == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_sparkle) + " : " + item.eclat) +
                                        (item.periode == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_period) + " : " + item.periode) +
                                        (item.portee == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_range) + " : " + item.portee) +
                                        (item.automatisation == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_automation) + " : " + item.automatisation) +
                                        (item.lon == 0 || item.lat == 0 ? "" : "\n" + resources.getString(R.string.lighthouse_location) + " : " + item.lon + " " + item.lat);
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, txt, duration);
                        toast.show();
                    }
                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}