package com.example.turizmoprograma.tourism_locations.dto;

import com.example.turizmoprograma.tourism_locations.dto.TourismLocationsData;

import java.util.List;

public class TourismGroupLocationsData {
    private final String locationCategory;
    private final List<TourismLocationsData> listLocations;

    public TourismGroupLocationsData(String locationCategory, List<TourismLocationsData> listLocations) {
        this.locationCategory = locationCategory;
        this.listLocations = listLocations;
    }

    public String getLocationCategory() {
        return locationCategory;
    }

    public List<TourismLocationsData> getListLocations() {
        return listLocations;
    }

}
