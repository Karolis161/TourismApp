package com.example.turizmoprograma.route_planner;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.turizmoprograma.R;
import com.example.turizmoprograma.tourism_locations.TourismLocationsDB;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;

public class RoutePlannerDetailFragment extends Fragment implements OnMapReadyCallback {

    private String name;
    private View view;
    public static final String TABLE_LOCATIONS = "locations";
    private ScrollView scrollView;
    private ImageView transparentImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_route_planner_detail, container, false);
        TextView routeName = view.findViewById(R.id.routeName);
        TextView routeDescription = view.findViewById(R.id.routeDescription);
        ImageView routeImg = view.findViewById(R.id.routeImg);
        scrollView = view.findViewById(R.id.scrollView);
        transparentImage = view.findViewById(R.id.transparent_image);

        Bundle bundle = getArguments();
        assert bundle != null;
        RoutePlannerData data = (RoutePlannerData) bundle.getSerializable("Route");

        name = data.getRouteName();
        routeName.setText(data.getRouteName());
        routeDescription.setText(data.getRouteDescription());
        routeImg.setImageResource(data.getRouteImg());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.routeMap);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        try {
            TourismLocationsDB dbHelper = new TourismLocationsDB(getContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + TABLE_LOCATIONS, null);

            int a = 1;

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {

                    GeoApiContext context = new GeoApiContext.Builder()
                            .apiKey(getString(R.string.map_api_key))
                            .build();

                    List<LatLng> path = new ArrayList();

                    if (name.equals("Maršrutas po istorinius Vilniaus objektus")) {

                        LatLng defaultView = new LatLng(54.67708355, 25.292666587299994);

                        LatLng latLng1 = new LatLng(54.67708355, 25.292666587299994);
                        LatLng latLng2 = new LatLng(54.682949, 25.2892256);
                        LatLng latLng3 = new LatLng(54.6830121, 25.2864244);
                        LatLng latLng4 = new LatLng(54.6894452, 25.2659319);

                        googleMap.addMarker(new MarkerOptions().position(latLng1).title("Vilniaus gynybinės sienos bastėja"));
                        googleMap.addMarker(new MarkerOptions().position(latLng2).title("Pilies gatvė"));
                        googleMap.addMarker(new MarkerOptions().position(latLng3).title("Prezidentūra"));
                        googleMap.addMarker(new MarkerOptions().position(latLng4).title("Gedimino prospektas"));

                        if (a == 1) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.67708355, 25.292666587299994", "54.682949, 25.2892256");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 2) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.682949, 25.2892256", "54.6830121, 25.286424");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 3) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6830121, 25.286424", "54.6894452, 25.2659319");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultView, 15));

                        cursor.close();
                        db.close();
                    }

                    if (name.equals("Maršrutas po Vilniaus parkus")) {

                        LatLng defaultView = new LatLng(54.6828199, 25.2357264);

                        LatLng latLng1 = new LatLng(54.6828199, 25.2357264);
                        LatLng latLng2 = new LatLng(54.6849983, 25.2915597);
                        LatLng latLng3 = new LatLng(54.6797076, 25.3186679);
                        LatLng latLng4 = new LatLng(54.7356049, 25.4013226);

                        googleMap.addMarker(new MarkerOptions().position(latLng1).title("Vingio parkas"));
                        googleMap.addMarker(new MarkerOptions().position(latLng2).title("Bernardinų sodas"));
                        googleMap.addMarker(new MarkerOptions().position(latLng3).title("Pavilnių regioninis parkas"));
                        googleMap.addMarker(new MarkerOptions().position(latLng4).title("Vilniaus universiteto botanikos sodas"));

                        if (a == 1) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6828199, 25.2357264", "54.6849983, 25.2915597");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 2) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6849983, 25.2915597", "54.6797076, 25.3186679");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 3) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6797076, 25.3186679", "54.7356049, 25.4013226");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultView, 15));

                        cursor.close();
                        db.close();
                    }

                    if (name.equals("Maršrutas po religinius Vilniaus objektus")) {

                        LatLng defaultView = new LatLng(54.6830429, 25.2932156);

                        LatLng latLng1 = new LatLng(54.6830429, 25.2932156);
                        LatLng latLng2 = new LatLng(54.682698450000004, 25.291944589971443);
                        LatLng latLng3 = new LatLng(54.6826992, 25.2885845);
                        LatLng latLng4 = new LatLng(54.6776799, 25.2885776);
                        LatLng latLng5 = new LatLng(54.6749179, 25.2895846);

                        googleMap.addMarker(new MarkerOptions().position(latLng1).title("Šv. Onos bažnyčia"));
                        googleMap.addMarker(new MarkerOptions().position(latLng2).title("Bažnytinio paveldo muziejus"));
                        googleMap.addMarker(new MarkerOptions().position(latLng3).title("Šv. Jonų bažnyčia"));
                        googleMap.addMarker(new MarkerOptions().position(latLng4).title("Šv. Kazimiero bažnyčia"));
                        googleMap.addMarker(new MarkerOptions().position(latLng5).title("Aušros Vartai"));

                        if (a == 1) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6830429, 25.2932156", "54.682698450000004, 25.291944589971443");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 2) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.682698450000004, 25.291944589971443", "54.6826992, 25.2885845");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 3) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6826992, 25.2885845", "54.6776799, 25.2885776");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 4) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6776799, 25.2885776", "54.6749179, 25.2895846");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultView, 15));

                        cursor.close();
                        db.close();
                    }

                    if (name.equals("Maršrutas po Vilniaus muziejus")) {

                        LatLng defaultView = new LatLng(54.690189, 25.2782818);

                        LatLng latLng1 = new LatLng(54.690189, 25.2782818);
                        LatLng latLng2 = new LatLng(54.6894671, 25.2957649);
                        LatLng latLng3 = new LatLng(54.6817345, 25.2991171);
                        LatLng latLng4 = new LatLng(54.6784222, 25.2849577);

                        googleMap.addMarker(new MarkerOptions().position(latLng1).title("Vytauto Kasiulo dailės muziejus"));
                        googleMap.addMarker(new MarkerOptions().position(latLng2).title("Parodų erdvė Istorijų namai"));
                        googleMap.addMarker(new MarkerOptions().position(latLng3).title("Lietuvos meno pažinimo centras „Tartle“"));
                        googleMap.addMarker(new MarkerOptions().position(latLng4).title("Vilniaus miesto muziejus"));

                        if (a == 1) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.690189, 25.2782818", "54.6894671, 25.2957649");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 2) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6894671, 25.2957649", "54.6817345, 25.2991171");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 3) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6817345, 25.2991171", "54.6784222, 25.2849577");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultView, 15));

                        cursor.close();
                        db.close();
                    }

                    if (name.equals("Maršrutas po Vilniaus memorialus")) {

                        LatLng defaultView = new LatLng(54.6881581, 25.2712152);

                        LatLng latLng1 = new LatLng(54.6881581, 25.2712152);
                        LatLng latLng2 = new LatLng(54.687366, 25.289186523648198);
                        LatLng latLng3 = new LatLng(54.6846752, 25.2886371);
                        LatLng latLng4 = new LatLng(54.67603855, 25.281411114296752);

                        googleMap.addMarker(new MarkerOptions().position(latLng1).title("Inskripcijos sovietinių represijų aukoms"));
                        googleMap.addMarker(new MarkerOptions().position(latLng2).title("Karaliaus Mindaugo paminklas"));
                        googleMap.addMarker(new MarkerOptions().position(latLng3).title("Paminklas LDK didžiajam kunigaikščiui Gediminui"));
                        googleMap.addMarker(new MarkerOptions().position(latLng4).title("Miesto vartų sargybinio skulptūra"));

                        if (a == 1) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6881581, 25.2712152", "54.687366, 25.289186523648198");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 2) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.687366, 25.289186523648198", "54.6846752, 25.2886371");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        if (a == 3) {
                            DirectionsApiRequest req = DirectionsApi.getDirections(context, "54.6846752, 25.2886371", "54.67603855, 25.281411114296752");
                            DirectionsResult res = req.await();
                            if (res.routes != null && res.routes.length > 0) {
                                DirectionsRoute route = res.routes[0];
                                if (route.legs != null) {
                                    for (int i = 0; i < route.legs.length; i++) {
                                        DirectionsLeg leg = route.legs[i];
                                        if (leg.steps != null) {
                                            for (int j = 0; j < leg.steps.length; j++) {
                                                DirectionsStep step = leg.steps[j];
                                                if (step.steps != null && step.steps.length > 0) {
                                                    for (int k = 0; k < step.steps.length; k++) {
                                                        DirectionsStep step1 = step.steps[k];
                                                        EncodedPolyline points1 = step1.polyline;
                                                        if (points1 != null) {
                                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    EncodedPolyline points = step.polyline;
                                                    if (points != null) {
                                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                        for (com.google.maps.model.LatLng coord : coords) {
                                                            path.add(new LatLng(coord.lat, coord.lng));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            a++;
                            if (path.size() > 0) {
                                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                                googleMap.addPolyline(opts);
                            }
                        }

                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultView, 15));

                        cursor.close();
                        db.close();
                    }
                    cursor.moveToNext();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        transparentImage.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:

                case MotionEvent.ACTION_MOVE:
                    scrollView.requestDisallowInterceptTouchEvent(true);
                    return false;

                case MotionEvent.ACTION_UP:
                    scrollView.requestDisallowInterceptTouchEvent(false);
                    return true;

                default:
                    return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_fragment_route_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.option_home:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_HomeFragment);
                return true;
            case R.id.option_route_planner:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_RoutePlannerFragment);
                return true;
            case R.id.option_tourist_locations:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_TourismLocationFragment);
                return true;
            case R.id.option_interactive_map:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_InteractiveMapFragment);
                return true;
            case R.id.option_tourism_helper:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_TourismHelperFragment);
                return true;
            case R.id.option_help:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_HelpFragment);
                return true;
            case R.id.option_about:
                Navigation.findNavController(view).navigate(R.id.action_RoutePlannerDetailFragment_to_AboutFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}