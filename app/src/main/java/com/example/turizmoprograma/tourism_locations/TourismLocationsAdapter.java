package com.example.turizmoprograma.tourism_locations;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;
import java.util.Objects;

public class TourismLocationsAdapter extends RecyclerView.Adapter<TourismLocationsAdapter.MyViewHolder> {
    private static final String TAG = "LocationsAdapter";
    List<TourismLocationsData> tourismDataList;
    Context context;
    TourismLocationsDB tourismLocationsDB;
    Fragment fragment;

    public TourismLocationsAdapter(Context context, List<TourismLocationsData> list, Fragment fragment) {
        this.fragment = fragment;
        this.context = context;
        this.tourismDataList = list;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "TourismLocationsAdapter onCreateViewHolder");
        tourismLocationsDB = new TourismLocationsDB(context);
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_fragment_locations, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "TourismLocationsAdapter onBindViewHolder");

        TourismLocationsData locations = tourismDataList.get(position);
        holder.locationName.setText(locations.getName());
        holder.locationImg.setImageResource(locations.getImg());

        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Location", locations);
            Fragment newFragment = new Fragment();
            newFragment.setArguments(bundle);
            Navigation.findNavController(view).navigate(R.id.action_TourismLocationsFragment_to_TourismLocationsDetailFragment, bundle);
            Objects.requireNonNull(((AppCompatActivity) fragment.requireActivity()).getSupportActionBar()).setTitle(locations.getName());
        });
    }

    public int getItemCount() {
        return tourismDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        ImageView locationImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.locationName = (TextView) itemView.findViewById(R.id.locationName);
            this.locationImg = (ImageView) itemView.findViewById(R.id.locationImg);
        }
    }
}
