package com.example.turizmoprograma.tourism_helper;

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
import android.widget.ImageButton;

import com.example.turizmoprograma.R;

public class TourismHelperFragment extends Fragment {

    private static final String TAG = "HelpFragment";
    private View view;

    ImageButton imgButtonUber;
    ImageButton imgButtonBooking;
    ImageButton imgButtonCitybee;
    ImageButton imgButtonBolt;
    ImageButton imgButtonAirbnb;
    ImageButton imgButtonTrafi;

    Button linkButtonVLNWeb;
    Button linkButtonCovid;
    Button linkButtonLTGov;
    Button linkButtonLTTour;
    Button linkButtonLTReng;
    Button linkButtonLTAbout;

    public TourismHelperFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_tourism_helper, container, false);

        imgButtonUber = view.findViewById(R.id.btnUber);
        imgButtonBooking = view.findViewById(R.id.btnBooking);
        imgButtonCitybee = view.findViewById(R.id.btnCitybee);
        imgButtonBolt = view.findViewById(R.id.btnBolt);
        imgButtonAirbnb = view.findViewById(R.id.btnAirbnb);
        imgButtonTrafi = view.findViewById(R.id.btnTrafi);

        linkButtonVLNWeb = view.findViewById(R.id.btnVLNWeb);
        linkButtonCovid = view.findViewById(R.id.btnCovid);
        linkButtonLTGov = view.findViewById(R.id.btnLTGov);
        linkButtonLTTour = view.findViewById(R.id.btnLTTour);
        linkButtonLTReng = view.findViewById(R.id.btnLTReng);
        linkButtonLTAbout = view.findViewById(R.id.btnLTAbout);

        imgButtonUber.setImageResource(R.drawable.uber1);
        imgButtonBooking.setImageResource(R.drawable.booking1);
        imgButtonCitybee.setImageResource(R.drawable.citybee1);
        imgButtonBolt.setImageResource(R.drawable.bolt1);
        imgButtonAirbnb.setImageResource(R.drawable.airbnb1);
        imgButtonTrafi.setImageResource(R.drawable.trafi1);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent intent1 = new Intent(Intent.ACTION_VIEW);

        imgButtonUber.setOnClickListener(view -> {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.ubercab"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        imgButtonBooking.setOnClickListener(view -> {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.booking"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        imgButtonCitybee.setOnClickListener(view -> {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.primeleasing.citybee"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        imgButtonBolt.setOnClickListener(view -> {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=ee.mtakso.client"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        imgButtonAirbnb.setOnClickListener(view -> {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.airbnb.android"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        imgButtonTrafi.setOnClickListener(view -> {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.trafi.android.tr"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });

        linkButtonVLNWeb.setOnClickListener(view -> {
            intent1.setData(Uri.parse("https://www.govilnius.lt/visit-vilnius"));
            startActivity(intent1);
        });

        linkButtonCovid.setOnClickListener(view -> {
            intent1.setData(Uri.parse("https://koronastop.lrv.lt/en/"));
            startActivity(intent1);
        });

        linkButtonLTGov.setOnClickListener(view -> {
            intent1.setData(Uri.parse("https://lrv.lt/en/"));
            startActivity(intent1);
        });

        linkButtonLTTour.setOnClickListener(view -> {
            intent1.setData(Uri.parse("https://www.lithuania.travel/lt/"));
            startActivity(intent1);
        });

        linkButtonLTReng.setOnClickListener(view -> {
            intent1.setData(Uri.parse("https://renginiai.kasvyksta.lt/lietuva/siandien"));
            startActivity(intent1);
        });

        linkButtonLTAbout.setOnClickListener(view -> {
            intent1.setData(Uri.parse("https://www.lithuania.travel/lt/kategorija/kas-yra-lietuva"));
            startActivity(intent1);
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_helper, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_TourismHelperFragment_to_HomeFragment);
                return true;
            case R.id.option_route_planner:
                Navigation.findNavController(view).navigate(R.id.action_TourismHelperFragment_to_RoutePlannerFragment);
                return true;
            case R.id.option_tourist_locations:
                Navigation.findNavController(view).navigate(R.id.action_TourismHelperFragment_to_TourismLocationFragment);
                return true;
            case R.id.option_interactive_map:
                Navigation.findNavController(view).navigate(R.id.action_TourismHelperFragment_to_InteractiveMapFragment);
                return true;
            case R.id.option_help:
                Navigation.findNavController(view).navigate(R.id.action_TourismHelperFragment_to_HelpFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_TourismHelperFragment_to_AboutFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}