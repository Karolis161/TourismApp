package com.example.turizmoprograma.route_planner;

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
import com.example.turizmoprograma.tourism_locations.TourismLocationsDB;

import java.util.ArrayList;
import java.util.List;

public class RoutePlannerFragment extends Fragment {

    public static final String TABLE_LOCATIONS = "locations";
    private View view;
    RecyclerView groupRecyclerViewRoutes;

    public RoutePlannerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_route_planner, container, false);
        groupRecyclerViewRoutes = view.findViewById(R.id.groupRecyclerViewRoutes);

        List<RoutePlannerGroupData> groupRouteDataList = new ArrayList<>();
        List<RoutePlannerData> route1 = new ArrayList<>();
        List<RoutePlannerData> route2 = new ArrayList<>();
        List<RoutePlannerData> route3 = new ArrayList<>();
        List<RoutePlannerData> route4 = new ArrayList<>();
        List<RoutePlannerData> route5 = new ArrayList<>();

        try {
            TourismLocationsDB dbHelper = new TourismLocationsDB(getContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + TABLE_LOCATIONS, null);
            String whichCategory;

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {

                    whichCategory = cursor.getString(1);

//                    RoutePlannerData routeData = new RoutePlannerData();
//
//                    List<String> routeNames = new ArrayList<>();
//                    routeNames.add("Maršrutas po istorinius Vilniaus objektus");
//                    routeNames.add("Maršrutas po Vilniaus parkus");
//                    routeNames.add("Maršrutas po religinius Vilniaus objektus");
//                    routeNames.add("Maršrutas po Vilniaus muziejus");
//                    routeNames.add("Maršrutas po Vilniaus memorialus");
//
//                    List<Double> routeLengths = new ArrayList();
//                    routeLengths.add(2.3);
//                    routeLengths.add(13.26);
//                    routeLengths.add(1.24);
//                    routeLengths.add(3.24);
//                    routeLengths.add(2.15);
//
//                    List<Integer> routeTimes = new ArrayList<>();
//                    routeTimes.add(30);
//                    routeTimes.add(60);
//                    routeTimes.add(20);
//                    routeTimes.add(45);
//                    routeTimes.add(25);
//
//                    List<Integer> routeImgs = new ArrayList<>();
//                    routeImgs.add(R.drawable.gediminoprospektasroute);
//                    routeImgs.add(R.drawable.parkairoute);
//                    routeImgs.add(R.drawable.baznyciaroute);
//                    routeImgs.add(R.drawable.muziejairoute);
//                    routeImgs.add(R.drawable.memorialairoute);
//
//                    List<String> routeDescriptions = new ArrayList<>();
//                    routeDescriptions.add(getResources().getString(R.string.routehistorydesc));
//                    routeDescriptions.add(getResources().getString(R.string.routeparkdesc));
//                    routeDescriptions.add(getResources().getString(R.string.routereligiondesc));
//                    routeDescriptions.add(getResources().getString(R.string.routemuseumsdesc));
//                    routeDescriptions.add(getResources().getString(R.string.routememorialsdesc));
//
//                    for (int i = 0; i <= routeNames.size(); i++) {
//                        routeData.setRouteName(routeNames.get(i));
//                        routeData.setRouteLength(routeLengths.get(i));
//                        routeData.setRouteTime(routeTimes.get(i));
//                        routeData.setRouteImg(routeImgs.get(i));
//                        routeData.setRouteDescription(routeDescriptions.get(i));
//                        if (whichCategory.contains("istorija")) {
//                            route1.add(routeData);
//
//                        }
//                        if (whichCategory.contains("parkas")) {
//                            route2.add(routeData);
//                        }
//                        if (whichCategory.contains("religija")) {
//                            route3.add(routeData);
//                        }
//                        if (whichCategory.contains("muziejus")) {
//                            route4.add(routeData);
//                        }
//                        if (whichCategory.contains("memorialas")) {
//                            route5.add(routeData);
//                        }
//                        Log.i("Pirmas", String.valueOf(route1.get(1)));
//                    }


                    RoutePlannerData routeData = new RoutePlannerData();
                    RoutePlannerData routeData1 = new RoutePlannerData();
                    RoutePlannerData routeData2 = new RoutePlannerData();
                    RoutePlannerData routeData3 = new RoutePlannerData();
                    RoutePlannerData routeData4 = new RoutePlannerData();

                    routeData.setRouteName("Maršrutas po istorinius Vilniaus objektus");
                    routeData.setRouteLength(2.3);
                    routeData.setRouteTime(30);
                    routeData.setRouteImg(R.drawable.gediminoprospektasroute);
                    routeData.setRouteDescription(getResources().getString(R.string.routehistorydesc));
                    routeData.setLat(cursor.getDouble(5));
                    routeData.setLng(cursor.getDouble(6));
                    if (whichCategory.contains("istorija") && whichCategory.contains("route")) {
                        route1.add(routeData);
                    }

                    routeData1.setRouteName("Maršrutas po Vilniaus parkus");
                    routeData1.setRouteLength(13.26);
                    routeData1.setRouteTime(60);
                    routeData1.setRouteImg(R.drawable.parkairoute);
                    routeData1.setRouteDescription(getResources().getString(R.string.routeparkdesc));
                    routeData1.setLat(cursor.getDouble(5));
                    routeData1.setLng(cursor.getDouble(6));
                    if (whichCategory.contains("parkas") && whichCategory.contains("route")) {
                        route2.add(routeData1);
                    }

                    routeData2.setRouteName("Maršrutas po religinius Vilniaus objektus");
                    routeData2.setRouteLength(1.24);
                    routeData2.setRouteTime(20);
                    routeData2.setRouteImg(R.drawable.baznyciaroute);
                    routeData2.setRouteDescription(getResources().getString(R.string.routereligiondesc));
                    routeData2.setLat(cursor.getDouble(5));
                    routeData2.setLng(cursor.getDouble(6));
                    if (whichCategory.contains("religija") && whichCategory.contains("route")) {
                        route3.add(routeData2);
                    }

                    routeData3.setRouteName("Maršrutas po Vilniaus muziejus");
                    routeData3.setRouteLength(3.24);
                    routeData3.setRouteTime(45);
                    routeData3.setRouteImg(R.drawable.muziejairoute);
                    routeData3.setRouteDescription(getResources().getString(R.string.routemuseumsdesc));
                    routeData3.setLat(cursor.getDouble(5));
                    routeData3.setLng(cursor.getDouble(6));
                    if (whichCategory.contains("muziejus") && whichCategory.contains("route")) {
                        route4.add(routeData3);
                    }

                    routeData4.setRouteName("Maršrutas po Vilniaus memorialus");
                    routeData4.setRouteLength(2.15);
                    routeData4.setRouteTime(25);
                    routeData4.setRouteImg(R.drawable.memorialairoute);
                    routeData4.setRouteDescription(getResources().getString(R.string.routememorialsdesc));
                    routeData4.setLat(cursor.getDouble(5));
                    routeData4.setLng(cursor.getDouble(6));
                    if (whichCategory.contains("memorialas") && whichCategory.contains("route")) {
                        route5.add(routeData4);
                    }

                    cursor.moveToNext();
                }
            }
            groupRouteDataList.add(new RoutePlannerGroupData("Istorija", route1));
            groupRouteDataList.add(new RoutePlannerGroupData("Parkai", route2));
            groupRouteDataList.add(new RoutePlannerGroupData("Religija", route3));
            groupRouteDataList.add(new RoutePlannerGroupData("Muziejai", route4));
            groupRouteDataList.add(new RoutePlannerGroupData("Memorialai", route5));

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (groupRouteDataList.size() == 0) {
            Toast.makeText(getActivity(), "Sorry, error occurred on preparing route data.", Toast.LENGTH_SHORT).show();
        }

        RoutePlannerGroupAdapter routePlannerAdapter = new RoutePlannerGroupAdapter
                (requireActivity().getApplicationContext(), groupRouteDataList, this);
        groupRecyclerViewRoutes.setAdapter(routePlannerAdapter);
        groupRecyclerViewRoutes.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_route, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_HomeFragment);
                return true;
            case R.id.option_tourist_locations:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_TourismLocationsFragment);
                return true;
            case R.id.option_interactive_map:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_InteractiveMapFragment);
                return true;
            case R.id.option_tourism_helper:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_TourismHelperFragment);
                return true;
            case R.id.option_help:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_HelpFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_AboutFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}