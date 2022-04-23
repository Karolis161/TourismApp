package com.example.turizmoprograma.route_planner;

import java.io.Serializable;

public class RoutePlannerData implements Serializable {
    private String routeName;
    private double routeLength;
    private int routeTime;
    private int routeImg;
    private String routeDescription;
    private double lat, lng;

    public RoutePlannerData() {
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public int getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(int routeTime) {
        this.routeTime = routeTime;
    }

    public int getRouteImg() {
        return routeImg;
    }

    public void setRouteImg(int routeImg) {
        this.routeImg = routeImg;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
