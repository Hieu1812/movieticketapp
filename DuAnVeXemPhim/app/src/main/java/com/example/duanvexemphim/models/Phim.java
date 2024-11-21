package com.example.duanvexemphim.models;

import android.net.Uri;

public class Phim {
    private String resourceId;
    private String title;


    public Phim(String resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
