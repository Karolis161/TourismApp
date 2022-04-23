package com.example.turizmoprograma.help;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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

import com.example.turizmoprograma.R;

public class HelpFragment extends Fragment {

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

        Button btnCallCentre = view.findViewById(R.id.btnCentreCall);
        Button btnEmailCentre = view.findViewById(R.id.btnCentreEmail);
        Button btnFindCentre = view.findViewById(R.id.btnCentreMap);
        Button btnEmailCreator = view.findViewById(R.id.btnCreatorEmail);
        Button btnEmailCreatorUni = view.findViewById(R.id.btnUniEmail);
        Button btnFindCreatorUni = view.findViewById(R.id.btnUniMap);

        btnCallCentre.setOnClickListener(view -> {
            String number = "852629660";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            startActivity(intent);
        });

        btnEmailCentre.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "tic@vilnius.lt", null));
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });

        btnFindCentre.setOnClickListener(view -> {
            Uri gmmIntentUri = Uri.parse("geo:54.6845904,25.2899959" + "Vilnius Tourism Centre");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        btnEmailCreator.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "karolis.urbaitis@stud.vilniustech.lt", null));
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });

        btnEmailCreatorUni.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "itsc@vilniustech.lt", null));
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });

        btnFindCreatorUni.setOnClickListener(view -> {
            Uri gmmIntentUri = Uri.parse("geo:54.7219118,25.336616247800787" + "Vilnius Tourism Centre");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
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