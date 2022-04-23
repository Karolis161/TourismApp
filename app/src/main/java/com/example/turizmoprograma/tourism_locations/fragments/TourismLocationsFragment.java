package com.example.turizmoprograma.tourism_locations.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.turizmoprograma.R;
import com.example.turizmoprograma.recommendations.RecommendationsFragment;
import com.example.turizmoprograma.tourism_locations.TourismLocationsDB;
import com.example.turizmoprograma.tourism_locations.adapters.TourismGroupLocationsAdapter;
import com.example.turizmoprograma.tourism_locations.dto.TourismGroupLocationsData;
import com.example.turizmoprograma.tourism_locations.dto.TourismLocationsData;

import java.util.ArrayList;
import java.util.List;

public class TourismLocationsFragment extends Fragment {

    public static final String TABLE_LOCATIONS = "locations";
    private View view;

    public TourismLocationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tourism_locations, container, false);
        RecyclerView groupRecyclerViewLocations = view.findViewById(R.id.groupRecyclerViewSpots);

        List<TourismGroupLocationsData> groupSpotsDataList = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsSen = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsIst = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsPark = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsRelig = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsMuz = new ArrayList<>();
        List<TourismLocationsData> tourismLocationsMem = new ArrayList<>();

        try {
            TourismLocationsDB dbHelper = new TourismLocationsDB(getContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();
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
                    }
                    if (whichCategory.contains("istorija")) {
                        tourismLocationsIst.add(locationsData);
                    }
                    if (whichCategory.contains("parkas")) {
                        tourismLocationsPark.add(locationsData);
                    }
                    if (whichCategory.contains("religija")) {
                        tourismLocationsRelig.add(locationsData);
                    }
                    if (whichCategory.contains("muziejus")) {
                        tourismLocationsMuz.add(locationsData);
                    }
                    if (whichCategory.contains("memorialas")) {
                        tourismLocationsMem.add(locationsData);
                    }
                    cursor.moveToNext();
                }
            }
            String tag = RecommendationsFragment.savedTag;

            if (tag == null) {
                groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                groupSpotsDataList.add(new TourismGroupLocationsData("Istorija", tourismLocationsIst));
                groupSpotsDataList.add(new TourismGroupLocationsData("Muziejai", tourismLocationsMuz));
                groupSpotsDataList.add(new TourismGroupLocationsData("Religija", tourismLocationsRelig));
                groupSpotsDataList.add(new TourismGroupLocationsData("Parkai", tourismLocationsPark));
                groupSpotsDataList.add(new TourismGroupLocationsData("Memorialai", tourismLocationsMem));
            } else {
                if (tag.equals("Visi")) {
                    groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Istorija", tourismLocationsIst));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Muziejai", tourismLocationsMuz));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Religija", tourismLocationsRelig));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Parkai", tourismLocationsPark));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Memorialai", tourismLocationsMem));
                }
                if (tag.equals("Istorija")) {
                    groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Istorija", tourismLocationsIst));
                }
                if (tag.equals("Muziejai")) {
                    groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Muziejai", tourismLocationsMuz));
                }
                if (tag.equals("Religija")) {
                    groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Religija", tourismLocationsRelig));
                }
                if (tag.equals("Parkai")) {
                    groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Parkai", tourismLocationsPark));
                }
                if (tag.equals("Memorialai")) {
                    groupSpotsDataList.add(new TourismGroupLocationsData("Populiarios turistinės vietos", tourismLocationsSen));
                    groupSpotsDataList.add(new TourismGroupLocationsData("Memorialai", tourismLocationsMem));
                }
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (groupSpotsDataList.size() == 0) {
            Toast.makeText(getActivity(), "Sorry, error occurred on preparing locations data.", Toast.LENGTH_SHORT).show();
        }

        TourismGroupLocationsAdapter tourismLocationsAdapter = new TourismGroupLocationsAdapter
                (requireActivity().getApplicationContext(), groupSpotsDataList, this);
        groupRecyclerViewLocations.setAdapter(tourismLocationsAdapter);
        groupRecyclerViewLocations.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
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
            case R.id.option_tourism_helper:
                Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_TourismHelperFragment);
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