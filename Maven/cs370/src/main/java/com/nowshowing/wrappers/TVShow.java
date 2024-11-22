package com.nowshowing.wrappers;

public class TVShow extends Media{
    String creator;
    String first_air_date;
    String name;
    String last_air_date;
    int number_of_episodes;
    int number_of_seasons;
    String status;
    
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getFirst_air_date() {
        return first_air_date;
    }
    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLast_air_date() {
        return last_air_date;
    }
    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }
    public int getNumber_of_episodes() {
        return number_of_episodes;
    }
    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }
    public int getNumber_of_seasons() {
        return number_of_seasons;
    }
    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
