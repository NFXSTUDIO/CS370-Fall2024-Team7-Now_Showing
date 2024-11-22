package com.nowshowing.wrappers;

import java.util.List;

public class Media {
    int[] genre_ids;
    int id;
    String overview;
    String poster_path;
    List<String> cast;

    public int[] getGenre_ids() {
        return genre_ids;
    }
    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public List<String> getCast() {
        return cast;
    }
    public void setCast(List<String> cast) {
        this.cast = cast;
    }

}
