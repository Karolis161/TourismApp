package com.example.turizmoprograma.help;

import android.annotation.SuppressLint;
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

import com.example.turizmoprograma.R;

public class HelpFragment extends Fragment {

    private static final String TAG = "HelpFragment";
    private View view;

    public HelpFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_help, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_help, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_HelpFragment_to_HomeFragment);
                return true;
            case R.id.option_route_planner:
                Navigation.findNavController(view).navigate(R.id.action_HelpFragment_to_RoutePlannerFragment);
                return true;
            case R.id.option_tourist_locations:
                Navigation.findNavController(view).navigate(R.id.action_HelpFragment_to_TourismLocationsFragment);
                return true;
            case R.id.option_interactive_map:
                Navigation.findNavController(view).navigate(R.id.action_HelpFragment_to_InteractiveMapFragment);
                return true;
            case R.id.option_tourism_helper:
                Navigation.findNavController(view).navigate(R.id.action_HelpFragment_to_TourismHelperFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_HelpFragment_to_AboutFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}