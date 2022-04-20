package com.example.turizmoprograma.route_planner;

import java.util.List;

public class RoutePlannerGroupData {
    private final String routeCategory;
    private final List<RoutePlannerData> routesList;

    public RoutePlannerGroupData(String routesCategory, List<RoutePlannerData> listRoutes) {
        this.routeCategory = routesCategory;
        this.routesList = listRoutes;
    }

    public String getRouteCategory() {
        return routeCategory;
    }

    public List<RoutePlannerData> getRoutesList() {
        return routesList;
    }
}