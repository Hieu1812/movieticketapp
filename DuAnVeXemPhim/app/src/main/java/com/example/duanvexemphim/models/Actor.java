package com.example.duanvexemphim.models;


public class Actor {
    private String actorName;
    private String actorImage;

    public Actor() {}

    public Actor(String actorName, String actorImage) {
        this.actorName = actorName;
        this.actorImage = actorImage;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorImage() {
        return actorImage;
    }

    public void setActorImage(String actorImage) {
        this.actorImage = actorImage;
    }
}