package com.example.turizmoprograma.interactive_map;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.turizmoprograma.R;
import com.example.turizmoprograma.tourism_locations.TourismLocationsDB;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class InteractiveMapFragment extends Fragment {

    public static final String TABLE_LOCATIONS = "locations";
    private View view;

    public InteractiveMapFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_interactive_map, container, false);

        TourismLocationsDB dbHelper = new TourismLocationsDB(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_LOCATIONS, null);

        MapView mapView = view.findViewById(R.id.locationMap);
        mapView.onCreate(savedInstance);
        mapView.onResume();

        SearchView searchView = view.findViewById(R.id.idSearchView);

        try {
            MapsInitializer.initialize(requireActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(googleMap -> {
            while (cursor.moveToNext()) {
                MarkerOptions marker = new MarkerOptions()
                        .position(new LatLng(cursor.getDouble(5), cursor.getDouble(6)))
                        .title(cursor.getString(2) + " - " + cursor.getString(3));
                googleMap.addMarker(marker);
            }

            LatLng position = new LatLng(54.687185, 25.279586);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(15.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.moveCamera(cameraUpdate);

            googleMap.setOnMapClickListener(point -> {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(point);
                googleMap.addMarker(markerOptions);
            });

            googleMap.setOnMapLongClickListener(point -> googleMap.clear());

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String location = searchView.getQuery().toString();
                    List<Address> addressList = null;
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    } else {
                        Toast.makeText(getContext(), "Wrong address", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            cursor.close();
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_map, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_InteractiveMapFragment_to_HomeFragment);
                return true;
            case R.id.option_route_planner:
                Navigation.findNavController(view).navigate(R.id.action_InteractiveMapFragment_to_RoutePlannerFragment);
                return true;
            case R.id.option_tourist_locations:
                Navigation.findNavController(view).navigate(R.id.action_InteractiveMapFragment_to_TourismLocationsFragment);
                return true;
            case R.id.option_tourism_helper:
                Navigation.findNavController(view).navigate(R.id.action_InteractiveMapFragment_to_TourismHelperFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_InteractiveMapFragment_to_AboutFragment);
                return true;
            case R.id.option_help:
                Navigation.findNavController(view).navigate(R.id.action_InteractiveMapFragment_to_HelpFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}