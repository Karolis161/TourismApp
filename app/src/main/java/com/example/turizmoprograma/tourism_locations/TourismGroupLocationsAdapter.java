package com.example.turizmoprograma.tourism_locations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turizmoprograma.R;

import java.util.List;

public class TourismGroupLocationsAdapter extends RecyclerView.Adapter<TourismGroupLocationsAdapter.MyViewHolder> {

    private final Fragment fragment;
    List<TourismGroupLocationsData> groupLocationsDataList;
    Context context;

    public TourismGroupLocationsAdapter(Context context, List<TourismGroupLocationsData> groupLocationsDataList, Fragment fragment) {
        this.context = context;
        this.groupLocationsDataList = groupLocationsDataList;
        this.fragment = fragment;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.group_row_fragment_locations, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TourismGroupLocationsData locationsGroup = groupLocationsDataList.get(position);
        holder.placeCategory.setText(locationsGroup.getLocationCategory());

        setSpotsRecycler(holder.recyclerViewSpots, locationsGroup.getListLocations());
    }

    public int getItemCount() {
        return (groupLocationsDataList != null ? groupLocationsDataList.size() : 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView placeCategory;
        RecyclerView recyclerViewSpots;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.placeCategory = itemView.findViewById(R.id.placeCategory);
            this.recyclerViewSpots = itemView.findViewById(R.id.recyclerViewSpots);
        }
    }

    private void setSpotsRecycler(RecyclerView recyclerView, List<TourismLocationsData> locationsList) {
        TourismLocationsAdapter locationsAdapter = new TourismLocationsAdapter(context, locationsList, fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(locationsAdapter);
    }
}
