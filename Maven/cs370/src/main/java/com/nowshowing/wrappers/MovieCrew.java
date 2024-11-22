package com.nowshowing.wrappers;

import java.util.List;

public class MovieCrew {
    List<CrewMember> cast;
    List<CrewMember> crew;

    public List<CrewMember> getCast() {
        return cast;
    }
    public void setCast(List<CrewMember> cast) {
        this.cast = cast;
    }
    public List<CrewMember> getCrew() {
        return crew;
    }
    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }
}
