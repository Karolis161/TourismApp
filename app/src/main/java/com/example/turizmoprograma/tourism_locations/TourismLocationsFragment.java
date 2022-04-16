package com.example.turizmoprograma.tourism_locations;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.turizmoprograma.R;

import java.util.ArrayList;
import java.util.List;

public class TourismLocationsFragment extends Fragment {

    private static final String TAG = "LocationsFragment";
    public static final String TABLE_LOCATIONS = "locations";

    private View view;

    public TourismLocationsFragment() {
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "OnCreateView in TourismLocationsFragment");
        view = inflater.inflate(R.layout.fragment_tourism_locations, container, false);

        List<TourismGroupLocationsData> groupSpotsDataList = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsSen = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsIst = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsPark = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsRelig = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsMuz = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsNoTag = new ArrayList<>();

        try {
            TourismLocationsDB dbHelper = new TourismLocationsDB(getContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + TABLE_LOCATIONS, null);

            String whichCategory;
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    TourismLocationsData locationsData = new TourismLocationsData();

                    locationsData.setId(cursor.getInt(0));
                    locationsData.setTags(cursor.getString(1));
                    locationsData.setName(cursor.getString(2));
                    locationsData.setAddress(cursor.getString(3));
                    locationsData.setDesc(cursor.getString(4));
                    locationsData.setLat(cursor.getDouble(5));
                    locationsData.setLng(cursor.getDouble(6));
                    locationsData.setImg(cursor.getInt(7));

                    whichCategory = cursor.getString(1);

                    if (whichCategory.contains("senamiestis")) {
                        tourismLocationsSen.add(locationsData);
                        Log.d(TAG, "a location is added to senamiestis ");
                    }
                    if (whichCategory.contains("istorija")) {
                        tourismLocationsIst.add(locationsData);
                        Log.d(TAG, "a location is added istorija ");
                    }
                    if (whichCategory.contains("parkas")) {
                        tourismLocationsPark.add(locationsData);
                        Log.d(TAG, "a location is added parkas ");
                    }
                    if (whichCategory.contains("religija")) {
                        tourismLocationsRelig.add(locationsData);
                        Log.d(TAG, "a location is added religija ");
                    }
                    if (whichCategory.contains("muziejus")) {
                        tourismLocationsMuz.add(locationsData);
                        Log.d(TAG, "a location is added muziejus ");
                    }
                    if (!whichCategory.contains("senamiestis")
                            && !whichCategory.contains("istorija")
                            && !whichCategory.contains("religija")
                            && !whichCategory.contains("muziejus")
                            && !whichCategory.contains("parkas")) {
                        tourismLocationsNoTag.add(locationsData);
                        Log.d(TAG, "a location is added notag ");
                    }
                    cursor.moveToNext();
                }
            }
            groupSpotsDataList.add(new TourismGroupLocationsData("Popular spots", tourismLocationsSen));
            groupSpotsDataList.add(new TourismGroupLocationsData("History", tourismLocationsIst));
            groupSpotsDataList.add(new TourismGroupLocationsData("Museums", tourismLocationsMuz));
            groupSpotsDataList.add(new TourismGroupLocationsData("Religion", tourismLocationsRelig));
            groupSpotsDataList.add(new TourismGroupLocationsData("Parks", tourismLocationsPark));

            if (tourismLocationsNoTag.size() > 0) {
                groupSpotsDataList.add(new TourismGroupLocationsData("その他", tourismLocationsNoTag));
            }
            cursor.close();
            db.close();
            Log.d(TAG, "Group spots datalist created ");
        } catch (Exception e) {
            Log.d(TAG, "Error " + e);
        }

        if (groupSpotsDataList.size() == 0) {
            Toast.makeText(getActivity(), "Sorry, error occurred on preparing locations data.", Toast.LENGTH_SHORT).show();
        }

        RecyclerView groupRecyclerViewLocations = (RecyclerView) view.findViewById(R.id.groupRecyclerViewSpots);
        groupRecyclerViewLocations.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireActivity().getApplicationContext());
        groupRecyclerViewLocations.setLayoutManager(layoutManager);
        TourismGroupLocationsAdapter tourismLocationsAdapter = new TourismGroupLocationsAdapter(requireActivity().getApplicationContext(), groupSpotsDataList, this);
        groupRecyclerViewLocations.setAdapter(tourismLocationsAdapter);
        tourismLocationsAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_locations, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "Item on bar selected " + id);
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_HomeFragment);
                return true;
            case R.id.option_route_planner:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_RoutePlannerFragment);
                return true;
            case R.id.option_interactive_map:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_InteractiveMapFragment);
                return true;
            case R.id.option_useful_links:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_UsefulLinksFragment);
                return true;
            case R.id.option_help:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_HelpFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_AboutFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}