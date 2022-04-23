package com.example.turizmoprograma.route_planner.adapters;

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
import com.example.turizmoprograma.route_planner.dto.RoutePlannerData;
import com.example.turizmoprograma.route_planner.dto.RoutePlannerGroupData;

import java.util.List;

public class RoutePlannerGroupAdapter extends RecyclerView.Adapter<RoutePlannerGroupAdapter.MyViewHolder> {

    private final Fragment fragment;
    List<RoutePlannerGroupData> groupRoutesDataList;
    Context context;

    public RoutePlannerGroupAdapter(Context context, List<RoutePlannerGroupData> groupRoutesDataList, Fragment fragment) {
        this.context = context;
        this.groupRoutesDataList = groupRoutesDataList;
        this.fragment = fragment;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.group_row_fragment_routes, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        RoutePlannerGroupData routesGroup = groupRoutesDataList.get(position);
        holder.routeCategory.setText(routesGroup.getRouteCategory());

        setSpotsRecycler(holder.recyclerViewRoutes, routesGroup.getRoutesList());
    }

    public int getItemCount() {
        return (groupRoutesDataList != null ? groupRoutesDataList.size() : 0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView routeCategory;
        RecyclerView recyclerViewRoutes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.routeCategory = itemView.findViewById(R.id.routeCategory);
            this.recyclerViewRoutes = itemView.findViewById(R.id.recyclerViewRoutes);
        }
    }

    private void setSpotsRecycler(RecyclerView recyclerView, List<RoutePlannerData> routesList) {
        RoutePlannerAdapter routesAdapter = new RoutePlannerAdapter(context, routesList, fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(routesAdapter);
    }
}