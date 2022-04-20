package com.example.turizmoprograma.route_planner;

import java.io.Serializable;
import java.util.List;

public class RoutePlannerData implements Serializable {
    private String routeName;
    private double routeLength;
    private int routeTime;
    private int routeImg;

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

}
