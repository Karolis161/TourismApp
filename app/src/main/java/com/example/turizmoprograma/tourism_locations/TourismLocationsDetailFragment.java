package com.example.turizmoprograma.tourism_locations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.turizmoprograma.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TourismLocationsDetailFragment extends Fragment {
    TextView locationName;
    TextView locationDescription;
    ImageView locationImg;
    MapView mapView;
    Button btnMap;

    String name;
    double latDest, lngDest;

    private static final String TAG = "AboutFragment";
    private View view;

    public TourismLocationsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tourism_locations_detail, container, false);
        locationName = view.findViewById(R.id.locationName);
        locationDescription = view.findViewById(R.id.locationDescription);
        locationImg = view.findViewById(R.id.locationImg);
        btnMap = view.findViewById(R.id.btnNav);

        Bundle bundle = getArguments();
        assert bundle != null;
        TourismLocationsData data = (TourismLocationsData) bundle.getSerializable("Location");
        name = data.getName();
        locationName.setText(data.getName());
        locationDescription.setText(data.getDesc());
        latDest = data.getLat();
        lngDest = data.getLng();
        locationImg.setImageResource(data.getImg());

        mapView = view.findViewById(R.id.locationMap);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(requireActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(googleMap -> {
            MarkerOptions marker = new MarkerOptions().position(
                    new LatLng(latDest, lngDest)).title(name);
            LatLng position = new LatLng(latDest, lngDest);
            googleMap.addMarker(marker);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(15.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.moveCamera(cameraUpdate);
        });

        btnMap.setOnClickListener(view -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + name);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            startActivity(mapIntent);
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_locations_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "Item on bar selected " + id);
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_HomeFragment);
                return true;
            case R.id.option_route_planner:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_RoutePlannerFragment);
                return true;
            case R.id.option_tourist_locations:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_TourismLocationsFragment);
                return true;
            case R.id.option_interactive_map:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_InteractiveMapFragment);
                return true;
            case R.id.option_useful_links:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_UsefulLinksFragment);
                return true;
            case R.id.option_help:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_HelpFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_locationsDetail_to_AboutFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}