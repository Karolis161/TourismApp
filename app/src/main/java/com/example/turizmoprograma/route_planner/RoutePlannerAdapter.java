package com.example.turizmoprograma.route_planner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turizmoprograma.R;
import com.example.turizmoprograma.tourism_locations.TourismLocationsDB;

import java.util.List;
import java.util.Objects;

public class RoutePlannerAdapter extends RecyclerView.Adapter<RoutePlannerAdapter.MyViewHolder> {
    List<RoutePlannerData> routeDataList;
    Context context;
    TourismLocationsDB tourismLocationsDB;
    Fragment fragment;

    public RoutePlannerAdapter(Context context, List<RoutePlannerData> list, Fragment fragment) {
        this.fragment = fragment;
        this.context = context;
        this.routeDataList = list;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        tourismLocationsDB = new TourismLocationsDB(context);
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_fragment_route, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RoutePlannerData route = routeDataList.get(position);
        holder.routeName.setText(route.getRouteName());
        holder.routeLength.setText((route.getRouteLength()) + " km");
        holder.routeTime.setText(route.getRouteTime() + " min");
        holder.routeImg.setImageResource(route.getRouteImg());

        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Route", route);
            Fragment newFragment = new Fragment();
            newFragment.setArguments(bundle);
            Navigation.findNavController(view).navigate(R.id.action_RoutePlannerFragment_to_RoutePlannerDetailFragment, bundle);
            Objects.requireNonNull(((AppCompatActivity) fragment.requireActivity()).getSupportActionBar()).setTitle(route.getRouteName());
        });
    }

    public int getItemCount() {
        return routeDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView routeName;
        TextView routeLength;
        TextView routeTime;
        ImageView routeImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.routeName = itemView.findViewById(R.id.routeName);
            this.routeLength = itemView.findViewById(R.id.routeLength);
            this.routeTime = itemView.findViewById(R.id.routeTime);
            this.routeImg = itemView.findViewById(R.id.routeImg);
        }
    }
}