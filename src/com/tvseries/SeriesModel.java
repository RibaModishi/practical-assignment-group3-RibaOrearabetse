package com.tvseries;

public class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
    
    public SeriesModel(String id, String name, String age, String episodes) {
        this.SeriesId = id;
        this.SeriesName = name;
        this.SeriesAge = age;
        this.SeriesNumberOfEpisodes = episodes;
    }
    
    // Getters for better encapsulation
    public String getSeriesId() { return SeriesId; }
    public String getSeriesName() { return SeriesName; }
    public String getSeriesAge() { return SeriesAge; }
    public String getSeriesNumberOfEpisodes() { return SeriesNumberOfEpisodes; }
    
    // Setters for updates
    public void setSeriesName(String name) { this.SeriesName = name; }
    public void setSeriesAge(String age) { this.SeriesAge = age; }
    public void setSeriesNumberOfEpisodes(String episodes) { this.SeriesNumberOfEpisodes = episodes; }
}