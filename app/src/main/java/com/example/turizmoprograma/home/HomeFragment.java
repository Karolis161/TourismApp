package com.example.turizmoprograma.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.turizmoprograma.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnRoute = view.findViewById(R.id.routePlannerBtn);
        Button btnLocations = view.findViewById(R.id.locationsBtn);
        Button btnMap = view.findViewById(R.id.mapBtn);
        Button btnLinks = view.findViewById(R.id.linksBtn);
        Button btnHelp = view.findViewById(R.id.helpBtn);
        Button btnAbout = view.findViewById(R.id.aboutBtn);

        btnRoute.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_HomeFragment_to_RoutePlannerFragment));
        btnLocations.setOnClickListener(view2 -> Navigation.findNavController(view2).navigate(R.id.action_HomeFragment_to_TourismLocationsFragment));
        btnMap.setOnClickListener(view3 -> Navigation.findNavController(view3).navigate(R.id.action_HomeFragment_to_InteractiveMapFragment));
        btnLinks.setOnClickListener(view4 -> Navigation.findNavController(view4).navigate(R.id.action_HomeFragment_to_UsefulLinksFragment));
        btnHelp.setOnClickListener(view5 -> Navigation.findNavController(view5).navigate(R.id.action_HomeFragment_to_HelpFragment));
        btnAbout.setOnClickListener(view6 -> Navigation.findNavController(view6).navigate(R.id.action_HomeFragment_to_AboutFragment));
        return view;
    }
}