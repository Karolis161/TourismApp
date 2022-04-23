package com.example.turizmoprograma.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.turizmoprograma.R;
import com.example.turizmoprograma.recommendations.RecommendationsFragment;

public class HomeFragment extends Fragment {

    private View view;

    public HomeFragment() {
    }

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnRoute = view.findViewById(R.id.routePlannerBtn);
        Button btnLocations = view.findViewById(R.id.locationsBtn);
        Button btnMap = view.findViewById(R.id.mapBtn);
        Button btnHelper = view.findViewById(R.id.helperBtn);
        Button btnHelp = view.findViewById(R.id.helpBtn);
        Button btnAbout = view.findViewById(R.id.aboutBtn);
        TextView textName = view.findViewById(R.id.textName);
        TextView textChoice = view.findViewById(R.id.textChoice);

        btnRoute.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_HomeFragment_to_RoutePlannerFragment));
        btnLocations.setOnClickListener(view2 -> Navigation.findNavController(view2).navigate(R.id.action_HomeFragment_to_TourismLocationsFragment));
        btnMap.setOnClickListener(view3 -> Navigation.findNavController(view3).navigate(R.id.action_HomeFragment_to_InteractiveMapFragment));
        btnHelper.setOnClickListener(view4 -> Navigation.findNavController(view4).navigate(R.id.action_HomeFragment_to_TourismHelperFragment));
        btnHelp.setOnClickListener(view5 -> Navigation.findNavController(view5).navigate(R.id.action_HomeFragment_to_HelpFragment));
        btnAbout.setOnClickListener(view6 -> Navigation.findNavController(view6).navigate(R.id.action_HomeFragment_to_AboutFragment));

        String name = RecommendationsFragment.savedName;
        String lastName = RecommendationsFragment.savedLastName;
        String tag = RecommendationsFragment.savedTag;

        if (name != null && lastName != null && !name.equals("") && !lastName.equals("")) {
            textName.setText("Sveiki " + name + " " + lastName);
            textChoice.setText("Jūs pasirinkote: " + tag);
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.options_form) {
            Navigation.findNavController(view).navigate(R.id.action_HomeFragment_to_RecommendationsFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}